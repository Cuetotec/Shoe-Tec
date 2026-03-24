package application;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
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
    @FXML private TableColumn<Cliente, String> colEmail;
    @FXML private TableColumn<Cliente, String> colDireccion;

    @FXML
    public void initialize() {
        // Vinculamos las columnas con los atributos de la clase Cliente.java
        // El texto entre comillas debe ser el nombre del atributo en Cliente.java
        colId.setCellValueFactory(new PropertyValueFactory<>("id_cliente"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre")); 
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        
        System.out.println("Tabla de clientes inicializada correctamente.");
    }

    @FXML
    private void handleCargarClientes() {
        System.out.println("Conectando con el Backend...");
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/api/clientes"))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(json -> {
                    // 1. Usamos GSON para convertir el texto JSON en una Lista de objetos Cliente
                    Gson gson = new Gson();
                    List<Cliente> listaRecibida = gson.fromJson(json, new TypeToken<List<Cliente>>(){}.getType());
                    
                    // 2. Pasamos la lista a la tabla (usando Platform.runLater para evitar errores de hilos)
                    javafx.application.Platform.runLater(() -> {
                        clientes.setItems(FXCollections.observableArrayList(listaRecibida));
                        System.out.println("¡Datos cargados en la tabla!");
                    });
                })
                .exceptionally(ex -> {
                    System.err.println("Error al conectar: " + ex.getMessage());
                    return null;
                });
    }
}