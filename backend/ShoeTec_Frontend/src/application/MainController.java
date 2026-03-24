package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;

public class MainController {
	
	@FXML
    private StackPane contentArea; 
	
	@FXML
	public void initialize() {
		irInicio();
	}
	
	@FXML
    private void irInicio() {
		cargarPantalla("Inicio.fxml"); 
	    System.out.println("DEBUG: Volviendo a la pantalla principal...");
    }

	@FXML
    private void abrirAlmacen() {
    	cargarPantalla("Almacen.fxml"); 
        System.out.println("Cargando módulo de Almacén...");
    }

    @FXML
    private void abrirPedidos() {
    	cargarPantalla("Pedidos.fxml");
        System.out.println("Cargando módulo de Pedidos...");
    }

    @FXML
    private void abrirClientes() {
        // Este carga tu tabla de clientes actual
        cargarPantalla("Sample.fxml");
        System.out.println("Cargando módulo de Clientes...");
    }

    @FXML
    private void abrirPersonal() {
    	cargarPantalla("Personal.fxml");
        System.out.println("Cargando módulo de Personal...");
    }

    @FXML
    private void cerrarSesion() {
        try {
            // 1. Cargamos el FXML del Login (suponiendo que se llama Login.fxml)
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            
            // 2. Creamos una nueva escena
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Shoe Tec - Login");
            stage.show();
            
            // 3. Cerramos la ventana actual del Dashboard
            // (Buscamos la ventana actual a través de cualquier elemento, por ejemplo contentArea)
            Stage currentStage = (Stage) contentArea.getScene().getWindow();
            currentStage.close();
            
            System.out.println("Sesión cerrada correctamente.");
            
        } catch (IOException e) {
            System.err.println("Error al volver al Login: " + e.getMessage());
        }
    }
    // El método mágico que cambia las pantallas
    private void cargarPantalla(String fxmlFile) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource(fxmlFile));
            contentArea.getChildren().clear();
            contentArea.getChildren().add(fxml);
        } catch (IOException e) {
            System.err.println("No se pudo cargar el archivo: " + fxmlFile);
        }
     }
}
