package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginController {
	@FXML private TextField txtUsuario;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblMensaje;

    @FXML
    private void handleLogin() {
        // Validación súper simple para tu proyecto
        if (txtUsuario.getText().equals("admin") && txtPassword.getText().equals("1234")) {
            try {
                // Si es correcto, cargamos el Main.fxml (tu pantalla principal)
                Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
                Stage stage = (Stage) txtUsuario.getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Shoe Tec - Gestión Integral");
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            lblMensaje.setText("Usuario o contraseña incorrectos");
        }
    }
}
