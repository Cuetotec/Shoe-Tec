package com.shoetec.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.shoetec.backend.entities.Cliente;
import com.shoetec.backend.repositories.ClienteRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping("/clientes")
    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }
}
