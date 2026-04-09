package com.shoetec.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.shoetec.backend.entities.Servicios;
import com.shoetec.backend.repositories.ServiciosRepository;
import java.util.List;

@RestController
@RequestMapping("/servicios")
@CrossOrigin(origins = "*")
public class ServiciosController {
	@Autowired
    private ServiciosRepository repository;

    @GetMapping
    public List<Servicios> listarTodo() {
        return repository.findAll();
    }
}
