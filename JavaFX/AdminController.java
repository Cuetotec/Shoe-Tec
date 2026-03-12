package JavaFX;

import java.lang.classfile.Label;

import javax.swing.text.TableView;

public class AdminController {
    @FXML
    private TableView<Pedido> tablaPedidos;
    @FXML
    private Label lblStockAlerta;

    // Método para actualizar el estado del pedido desde el panel [cite: 296, 303]
    @FXML
    public void finalizarReparacion() {
        Pedido seleccionado = tablaPedidos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            seleccionado.setEstado("Terminado"); // [cite: 135]
            // Aquí iría la llamada al backend para actualizar la DB
            System.out.println("Pedido " + seleccionado.getIdPedido() + " actualizado a Terminado.");
        }
    }
}

