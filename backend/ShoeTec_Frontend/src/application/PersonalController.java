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
    public void initialize() {
        // Enlazar columnas con los atributos de la clase Personal
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colRol.setCellValueFactory(new PropertyValueFactory<>("rol"));
        colEspecialidad.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
                
        // Carga inicial
        cargarPersonal();
    }

    @FXML
    private void guardarEmpleado() {
        // 1. Validación de campos
        if (txtNombre.getText().trim().isEmpty() || txtRol.getText().trim().isEmpty()) {
            mostrarAlerta("Campos incompletos", "Por favor, rellena al menos el nombre y el rol.", Alert.AlertType.WARNING);
            return;
        }

        // 2. Crear objeto
        Personal nuevo = new Personal();
        nuevo.setNombre(txtNombre.getText());
        nuevo.setRol(txtRol.getText());
        nuevo.setEspecialidad(txtEspecialidad.getText());

        // 3. Petición POST
        String jsonBody = new Gson().toJson(nuevo);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/personal"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    Platform.runLater(() -> {
                        if (response.statusCode() == 200 || response.statusCode() == 201) {
                            mostrarAlerta("Éxito", "Empleado guardado correctamente.", Alert.AlertType.INFORMATION);
                            txtNombre.clear();
                            txtRol.clear();
                            txtEspecialidad.clear();
                            cargarPersonal(); // Refrescar tabla
                        } else {
                            mostrarAlerta("Error", "No se pudo guardar: Código " + response.statusCode(), Alert.AlertType.ERROR);
                        }
                    });
                })
                .exceptionally(e -> {
                    Platform.runLater(() -> mostrarAlerta("Error de Conexión", e.getMessage(), Alert.AlertType.ERROR));
                    return null;
                });
    }

    @FXML
    private void cargarPersonal() {
        fetchData("http://localhost:8080/personal", new TypeToken<List<Personal>>(){}, list -> 
            tablaPersonal.setItems(FXCollections.observableArrayList(list)));
    }

    // Método genérico para obtener datos (Evita el error de BEGIN_OBJECT si el servidor falla)
    private <T> void fetchData(String url, TypeToken<List<T>> typeToken, java.util.function.Consumer<List<T>> callback) {
        HttpClient.newHttpClient().sendAsync(
            HttpRequest.newBuilder().uri(URI.create(url)).build(),
            HttpResponse.BodyHandlers.ofString()
        ).thenAccept(res -> {
            String json = res.body().trim();
            
            // DEBUG: Imprime en la consola de Eclipse para ver qué llega exactamente
            System.out.println("Respuesta de " + url + ": " + json);

            Platform.runLater(() -> {
                try {
                    // Si el JSON empieza por '{', es un error del servidor o un objeto suelto
                    if (json.startsWith("{")) {
                        System.err.println("¡ATENCIÓN! El servidor envió un objeto, no una lista. Posible error en el Backend.");
                        // Si quieres ver el error en pantalla:
                        // mostrarAlerta("Error de datos", "El servidor no envió una lista válida.", Alert.AlertType.ERROR);
                        return;
                    }

                    List<T> list = new Gson().fromJson(json, typeToken.getType());
                    if (list != null) callback.accept(list);

                } catch (Exception e) {
                    System.err.println("Error de GSON: " + e.getMessage());
                }
            });
        }).exceptionally(ex -> {
            Platform.runLater(() -> mostrarAlerta("Error de red", "No se pudo conectar con el servidor.", Alert.AlertType.ERROR));
            return null;
        });
    }

    private void mostrarAlerta(String titulo, String msg, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}