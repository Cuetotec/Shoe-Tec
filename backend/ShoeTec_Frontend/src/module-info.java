module ShoeTec_Frontend {
	
	requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
	requires com.google.gson;
	requires javafx.graphics;
    
	opens application to javafx.fxml, com.google.gson;
	exports application;
}
