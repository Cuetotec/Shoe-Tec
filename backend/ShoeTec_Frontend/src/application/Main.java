package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
	        // Cambiamos Main.fxml por Login.fxml para que sea lo primero que se vea
	        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
	        Scene scene = new Scene(root);
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("Shoe Tec - Login");
	        primaryStage.show();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	}	
	        public static void main(String[] args) {
	    		launch(args);
	    	}
	    } 