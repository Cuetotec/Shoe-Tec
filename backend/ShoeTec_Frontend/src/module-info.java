module ShoeTec_Frontend {
	
	requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
	requires com.google.gson;
	requires javafx.graphics;
	requires javafx.base;
	requires kernel;
	requires io;
	requires layout;
	requires java.desktop;
    
	opens application to javafx.fxml, com.google.gson, javafx.base;
	exports application;
}
