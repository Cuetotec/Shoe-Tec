package com.shoetec.backend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PruebaController {
	
	@GetMapping("/api/estado")
    public String comprobar() {
        return "Servidor de SHOE TEC operativo y conectado a MySQL";
    }
}
