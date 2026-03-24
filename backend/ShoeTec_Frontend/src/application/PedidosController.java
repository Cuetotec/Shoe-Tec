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

public class PedidosController {
	@FXML private TableView<Pedido> tablaPedidos;
    @FXML private TableColumn<Pedido, Integer> colId;
    @FXML private TableColumn<Pedido, Integer> colCliente;
    @FXML private TableColumn<Pedido, String> colEstado;
    @FXML private TableColumn<Pedido, Double> colPrecio;
    @FXML private TableColumn<Pedido, String> colFecha;

    @FXML
    public void initialize() {
        // Enlazamos las columnas con los atributos de la clase Pedido.java
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
                
        // Cargamos los datos automáticamente al abrir la pantalla
        cargarPedidos();
    }

    @FXML
    private void cargarPedidos() {
        System.out.println("DEBUG: Solicitando lista de pedidos...");
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/pedidos"))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(json -> {
                    System.out.println("DEBUG: JSON Pedidos recibido: " + json);
                    
                    List<Pedido> lista = new Gson().fromJson(json, new TypeToken<List<Pedido>>(){}.getType());
                    
                    Platform.runLater(() -> {
                        if (lista != null) {
                            tablaPedidos.setItems(FXCollections.observableArrayList(lista));
                            System.out.println("DEBUG: Tabla de pedidos actualizada.");
                        }
                    });
                })
                .exceptionally(e -> {
                    System.err.println("DEBUG: Error al conectar con pedidos: " + e.getMessage());
                    return null;
                });
    }
}
