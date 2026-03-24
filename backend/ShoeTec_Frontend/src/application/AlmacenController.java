package application;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.application.Platform;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.net.URI;
import java.net.http.*;
import java.util.List;

public class AlmacenController {
    @FXML private TableView<Material> tablaMateriales;
    @FXML private TableColumn<Material, Integer> colId, colStock, colMinimo;
    @FXML private TableColumn<Material, String> colNombre;
    @FXML private TableColumn<Material, String> colProveedor;
    
    @FXML private TextField txtNombre, txtStock, txtProveedor;

    @FXML
    public void initialize() {
        // Mapeo de columnas (Asegúrate de que coincidan con los atributos de tu clase Material)
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colProveedor.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stockActual"));
        colMinimo.setCellValueFactory(new PropertyValueFactory<>("stockMinimo"));
        
        // Cargar datos automáticamente al abrir la ventana
        cargarMateriales();
    }

    @FXML
    private void guardarMaterial() {
        if (txtNombre.getText().isEmpty()) {
        	System.out.println("DEBUG: El nombre es obligatorio");
        	return;
        }

        Material nuevo = new Material();
        nuevo.setNombre(txtNombre.getText());
        nuevo.setProveedor(txtProveedor.getText());
               
        try {
           
        	int cantidad = Integer.parseInt(txtStock.getText());
        	nuevo.setStockActual(cantidad);
        	
        	nuevo.setStockMinimo(5);
      
        } catch (NumberFormatException e) {
            System.err.println("DEBUG: El stock debe ser un número válido");
        	nuevo.setStockActual(0);
        	nuevo.setStockMinimo(5);
        }

        String jsonBody = new Gson().toJson(nuevo);
        System.out.println("DEBUG: Enviando al servidor: " + jsonBody);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/materiales")) 
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    // El servidor suele devolver 201 (Created)
                    if (response.statusCode() == 200 || response.statusCode() == 201) {
                        Platform.runLater(() -> {
                            limpiarFormulario();
                            cargarMateriales(); // Refrescar la tabla
                        });
                    } else {
                        System.err.println("Error API. Código: " + response.statusCode());
                        System.err.println("Respuesta servidor: " + response.body());
                    }
                })
                .exceptionally(e -> {
                    System.err.println("Error de conexión: " + e.getMessage());
                    return null;
                });
    }

    private void limpiarFormulario() {
        txtNombre.clear();
        txtStock.clear();
        txtProveedor.clear();
    }

    @FXML
    private void cargarMateriales() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/materiales"))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(json -> {
                    List<Material> lista = new Gson().fromJson(json, new TypeToken<List<Material>>(){}.getType());
                    
                    Platform.runLater(() -> {
                        if (lista != null) {
                            tablaMateriales.setItems(FXCollections.observableArrayList(lista));
                        }
                    });
                })
                .exceptionally(e -> {
                    System.err.println("Error al cargar materiales: " + e.getMessage());
                    return null;
                });
    }
}