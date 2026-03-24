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

public class PersonalController {
	@FXML private TableView<Personal> tablaPersonal;
    @FXML private TableColumn<Personal, Integer> colId;
    @FXML private TableColumn<Personal, String> colNombre;
    @FXML private TableColumn<Personal, String> colRol;
    @FXML private TableColumn<Personal, String> colEspecialidad;
    
    @FXML private TextField txtNombre;
    @FXML private TextField txtRol;
    @FXML private TextField txtEspecialidad;
    
    @FXML
    private void guardarEmpleado() {
    	// 1. Validar que no haya campos vacíos (opcional pero recomendado)
        if (txtNombre.getText().isEmpty() || txtRol.getText().isEmpty()) {
            System.err.println("Por favor, rellena al menos el nombre y el rol.");
            return;
        }

        // 2. Crear el objeto con los datos de los TextField
        Personal nuevo = new Personal();
        nuevo.setNombre(txtNombre.getText());
        nuevo.setRol(txtRol.getText());
        nuevo.setEspecialidad(txtEspecialidad.getText());

        // 3. Convertir a JSON
        String jsonBody = new Gson().toJson(nuevo);

        // 4. Configurar la petición POST
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/personal"))
                .header("Content-Type", "application/json") // Avisamos que enviamos JSON
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        // 5. Enviar de forma asíncrona
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    if (response.statusCode() == 200 || response.statusCode() == 201) {
                        System.out.println("DEBUG: Empleado guardado con éxito en el servidor.");
                        
                        // 6. Actualizar la interfaz (en el hilo de JavaFX)
                        Platform.runLater(() -> {
                            txtNombre.clear();
                            txtRol.clear();
                            txtEspecialidad.clear();
                            cargarPersonal(); // Refrescamos la tabla para ver el nuevo empleado
                        });
                    } else {
                        System.err.println("Error al guardar: Código " + response.statusCode());
                    }
                })
                .exceptionally(e -> {
                    System.err.println("Error de conexión al guardar: " + e.getMessage());
                    return null;
                });
    }
    
    @FXML
    public void initialize() {
        // Enlazamos las columnas con los atributos de la clase Pedido.java
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colRol.setCellValueFactory(new PropertyValueFactory<>("rol"));
        colEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
                
        // Cargamos los datos automáticamente al abrir la pantalla
        cargarPersonal();
    }

    @FXML
    private void cargarPersonal() {
        System.out.println("DEBUG: Solicitando lista de personal...");
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/personal"))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(json -> {
                    System.out.println("DEBUG: JSON Personal recibido: " + json);
                    
                    List<Personal> lista = new Gson().fromJson(json, new TypeToken<List<Personal>>(){}.getType());
                    
                    Platform.runLater(() -> {
                        if (lista != null) {
                            tablaPersonal.setItems(FXCollections.observableArrayList(lista));
                            System.out.println("DEBUG: Tabla de personal actualizada.");
                        }
                    });
                })
                .exceptionally(e -> {
                    System.err.println("DEBUG: Error al conectar con personal: " + e.getMessage());
                    return null;
                });
    }
}
