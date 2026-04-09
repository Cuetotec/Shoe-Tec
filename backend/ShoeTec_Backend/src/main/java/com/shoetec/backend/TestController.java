package com.shoetec.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class TestController {
	
	@GetMapping("/hola")
    public Map<String, String> sayHello() {
        return Map.of(
            "estado", "Funcionando",
            "mensaje", "¡Bienvenido a ShoeTec API!",
            "sistema", "Spring Boot 4.0.4 + Java 23"
        );
    }
}