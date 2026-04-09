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
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import java.io.File;
import java.awt.Desktop;

public class PedidosController {
    @FXML private TableView<Pedido> tablaPedidos;
    @FXML private TableColumn<Pedido, Integer> colId;
    @FXML private TableColumn<Pedido, Integer> colCliente;
    @FXML private TableColumn<Pedido, String> colEstado;
    @FXML private TableColumn<Pedido, Double> colPrecio;
    @FXML private TableColumn<Pedido, String> colFecha;
    
    @FXML private ComboBox<Cliente> comboClientes;
    @FXML private ComboBox<Servicios> comboServicios;
    @FXML private Button btnTaller;
    @FXML private Button btnEntregar;
       
    @FXML
    public void initialize() {
        // Vinculación de columnas (Asegúrate que coincidan con los nombres en Pedido.java)
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente")); 
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
                
        configurarCombos();
        cargarDatosIniciales();
        
        // Desactiva el botón si no hay nada seleccionado en la tabla
        btnEntregar.disableProperty().bind(tablaPedidos.getSelectionModel().selectedItemProperty().isNull());
        btnTaller.disableProperty().bind(tablaPedidos.getSelectionModel().selectedItemProperty().isNull());
    }
    
    private void configurarCombos() {
        comboClientes.setCellFactory(lv -> new ListCell<Cliente>() {
            @Override
            protected void updateItem(Cliente item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getNombre());
            }
        });
        comboClientes.setButtonCell(comboClientes.getCellFactory().call(null));
        
        comboServicios.setCellFactory(lv -> new ListCell<Servicios>() {
            @Override protected void updateItem(Servicios item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : item.getDescripcion() + " (" + item.getPrecio() + "€)");
            }
        });
        comboServicios.setButtonCell(comboServicios.getCellFactory().call(null));
    }
    
    private void cargarDatosIniciales() {
        cargarClientesCombo();
        cargarServiciosCombo();
        cargarPedidos();
    }
        
    @FXML
    private void finalizarPedido() {
        Cliente c = comboClientes.getSelectionModel().getSelectedItem();
        Servicios s = comboServicios.getSelectionModel().getSelectedItem();

        if (c == null || s == null) {
            mostrarAlerta("Atención", "Debes seleccionar un cliente y un servicio.", Alert.AlertType.WARNING);
            return;
        }

        Pedido p = new Pedido();
        p.setIdCliente(c.getId_cliente());
        p.setIdServicio(s.getId_service());
        p.setPrecio(s.getPrecio());
        p.setEstado("Pendiente");

        String json = new Gson().toJson(p);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/pedidos"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
        .thenAccept(res -> {
            Platform.runLater(() -> {
                if (res.statusCode() == 200 || res.statusCode() == 201) {
                    mostrarAlerta("Éxito", "Pedido guardado correctamente.", Alert.AlertType.INFORMATION);
                    cargarPedidos();
                    comboServicios.getSelectionModel().clearSelection();
                } else {
                    mostrarAlerta("Error de Inventario", "No hay suficiente material para este servicio.", Alert.AlertType.ERROR);
                }
            });
        });
    }
        
    private void cargarClientesCombo() {
        fetchData("http://localhost:8080/clientes", new TypeToken<List<Cliente>>(){}, list -> 
            comboClientes.setItems(FXCollections.observableArrayList(list)));
    }

    private void cargarServiciosCombo() {
        fetchData("http://localhost:8080/servicios", new TypeToken<List<Servicios>>(){}, list -> 
            comboServicios.setItems(FXCollections.observableArrayList(list)));
    }

    @FXML
    private void cargarPedidos() {
        fetchData("http://localhost:8080/pedidos", new TypeToken<List<Pedido>>(){}, list -> 
            tablaPedidos.setItems(FXCollections.observableArrayList(list)));
    }

    private <T> void fetchData(String url, TypeToken<List<T>> typeToken, java.util.function.Consumer<List<T>> callback) {
        HttpClient.newHttpClient().sendAsync(
            HttpRequest.newBuilder().uri(URI.create(url)).build(),
            HttpResponse.BodyHandlers.ofString()
        ).thenAccept(res -> {
            String json = res.body() != null ? res.body().trim() : "";
            
            System.out.println("DEBUG: JSON de " + url + " -> " + json);

            Platform.runLater(() -> {
                try {
                    if (json.isEmpty() || !json.startsWith("[")) {
                        System.err.println("Respuesta no válida de " + url + ": " + json);
                        return;
                    }

                    List<T> list = new Gson().fromJson(json, typeToken.getType());
                    if (list != null) callback.accept(list);

                } catch (Exception e) {
                    System.err.println("Error procesando JSON de " + url);
                    e.printStackTrace();
                }
            });
        }).exceptionally(ex -> {
            Platform.runLater(() -> System.err.println("Error de red en " + url + ": " + ex.getMessage()));
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

    @FXML
    private void entregarPedido() {
        Pedido seleccionado = tablaPedidos.getSelectionModel().getSelectedItem();
        if (seleccionado == null) return;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/pedidos/" + seleccionado.getId() + "/entregar"))
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
        .thenAccept(res -> {
            Platform.runLater(() -> {
                if (res.statusCode() == 200) {
                    // --- CAMBIO AQUÍ: Llamamos a la generación del PDF ---
                    generarTicketPDF(seleccionado); 
                    
                    mostrarAlerta("¡Listo!", "Pedido marcado como Entregado y Ticket generado.", Alert.AlertType.INFORMATION);
                    cargarPedidos();
                } else {
                    mostrarAlerta("Error", "No se pudo actualizar el pedido.", Alert.AlertType.ERROR);
                }
            });
        });
    }
        private void generarTicketPDF(Pedido p) {
            try {
                // El nombre del archivo incluirá el ID del pedido
                String destino = "Ticket_Pedido_" + p.getId() + ".pdf";
                PdfWriter writer = new PdfWriter(destino);
                PdfDocument pdf = new PdfDocument(writer);
                Document documento = new Document(pdf);

                // Encabezado
                documento.add(new Paragraph("SHOE TEC - TICKET DE ENTREGA")
                        .setTextAlignment(TextAlignment.CENTER)
                        .setBold()
                        .setFontSize(18));
                
                documento.add(new Paragraph("---------------------------------------------------------")
                        .setTextAlignment(TextAlignment.CENTER));

                // Datos del pedido
                documento.add(new Paragraph("ID Pedido: " + p.getId()));
                documento.add(new Paragraph("Estado: ENTREGADO"));
                documento.add(new Paragraph("Precio Total: " + p.getPrecio() + " €").setBold());
                
                documento.add(new Paragraph("\n¡Gracias por su compra en Shoe Tec!")
                        .setTextAlignment(TextAlignment.CENTER)
                        .setItalic());

                documento.close();
                System.out.println("PDF generado con éxito en: " + destino);

                // Abrir el PDF automáticamente
                File path = new File(destino);
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(path);
                }

            } catch (Exception e) {
                System.err.println("Error al crear el PDF: " + e.getMessage());
                e.printStackTrace();
            }
        } 
        @FXML
        private void marcarEnTaller() {
            Pedido seleccionado = tablaPedidos.getSelectionModel().getSelectedItem();
            if (seleccionado == null) return;

            HttpClient client = HttpClient.newHttpClient();
            // Creamos un endpoint nuevo en tu API para "empezar" la reparación
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/pedidos/" + seleccionado.getId() + "/taller"))
                    .PUT(HttpRequest.BodyPublishers.noBody())
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenAccept(res -> {
                Platform.runLater(() -> {
                    if (res.statusCode() == 200) {
                        mostrarAlerta("Actualizado", "El pedido ahora está EN TALLER.", Alert.AlertType.INFORMATION);
                        cargarPedidos(); // Refresca la tabla para ver el cambio
                    } else {
                        mostrarAlerta("Error", "No se pudo actualizar el estado.", Alert.AlertType.ERROR);
                    }
                });
            });
        }
    }
