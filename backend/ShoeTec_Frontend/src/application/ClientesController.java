package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import java.net.http.*;
import java.net.URI;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ClientesController {

    // IMPORTANTE: Estos nombres deben ser IGUALES a los fx:id de tu FXML
    @FXML private TableView<Cliente> clientes; 
    @FXML private TableColumn<Cliente, Integer> colId;
    @FXML private TableColumn<Cliente, String> colNombre;
    @FXML private TableColumn<Cliente, String> colDireccion;
    @FXML private TableColumn<Cliente, String> colLocalidad;
    @FXML private TableColumn<Cliente, String> colProvincia;
    @FXML private TableColumn<Cliente, String> colEmail;
    @FXML private TableColumn<Cliente, String> colTelefono;
    
    @FXML private TextField txtBuscador;
    
    private ObservableList<Cliente> masterData = FXCollections.observableArrayList(); 
    private FilteredList<Cliente> filteredData;
       
    @FXML
    public void initialize() {
       
        colId.setCellValueFactory(new PropertyValueFactory<>("id_cliente"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre")); 
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colLocalidad.setCellValueFactory(new PropertyValueFactory<>("localidad"));
        colProvincia.setCellValueFactory(new PropertyValueFactory<>("provincia"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        
        System.out.println("Tabla de clientes inicializada correctamente.");
        
        filteredData = new FilteredList<>(masterData, p -> true);
        
        txtBuscador.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(cliente -> {
                // Si el buscador está vacío, mostramos todos
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                String filter = newValue.toLowerCase();
                
                // Reglas de búsqueda: por nombre o por email
                if (cliente.getNombre() != null && cliente.getNombre().toLowerCase().contains(filter)) return true;
                if (cliente.getEmail() != null && cliente.getEmail().toLowerCase().contains(filter)) return true;
                
                return false; // No coincide con la búsqueda
            });
        });

        // 4. IMPORTANTE: La tabla debe usar la lista FILTRADA
        clientes.setItems(filteredData);

        // 5. Cargamos los datos del servidor
        handleCargarClientes();
    }
    
    @FXML
    private void handleCargarClientes() {
        System.out.println("Conectando con el Backend...");
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/clientes"))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(json -> {
                    // 1. Convertimos el JSON a lista (Aquí es donde nace listaRecibida)
                    Gson gson = new Gson();
                    List<Cliente> listaRecibida = gson.fromJson(json, new TypeToken<List<Cliente>>(){}.getType());
                    
                    // 2. Usamos Platform.runLater para actualizar la interfaz
                    Platform.runLater(() -> {
                        if (listaRecibida != null) {
                            // IMPORTANTE: Actualizamos masterData para que el buscador funcione
                            masterData.setAll(listaRecibida); 
                            System.out.println("¡Datos cargados y listos para filtrar!");
                        }
                    });
                })
                .exceptionally(ex -> {
                    Platform.runLater(() -> 
                        System.err.println("Error al conectar: " + ex.getMessage())
                    );
                    return null;
                });
    }
    @FXML
    private void eliminarClienteSeleccionado() {
        Cliente seleccionado = clientes.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarAlerta("Atención", "Selecciona un cliente de la tabla.", Alert.AlertType.WARNING);
            return;
        }

        // Confirmación antes de borrar
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION, "¿Borrar a " + seleccionado.getNombre() + "?", ButtonType.YES, ButtonType.NO);
        if (confirmacion.showAndWait().get() == ButtonType.YES) {
            HttpClient.newHttpClient().sendAsync(
                HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/clientes/" + seleccionado.getId_cliente()))
                    .DELETE()
                    .build(),
                HttpResponse.BodyHandlers.ofString()
            ).thenAccept(res -> {
                Platform.runLater(() -> {
                    if (res.statusCode() == 200) {
                        handleCargarClientes(); // Recargamos la tabla
                    } else {
                        mostrarAlerta("Error", "No se puede borrar (posiblemente tiene pedidos)", Alert.AlertType.ERROR);
                    }
                });
            });
        }
    }
        private void mostrarAlerta(String titulo, String msg, Alert.AlertType tipo) {
            Alert alert = new Alert(tipo);
            alert.setTitle(titulo);
            alert.setHeaderText(null);
            alert.setContentText(msg);
            alert.showAndWait();
        
    }
}