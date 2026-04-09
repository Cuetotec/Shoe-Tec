package com.shoetec.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.shoetec.backend.entities.Personal;
import com.shoetec.backend.repositories.PersonalRepository;
import java.util.List;

@RestController
@RequestMapping("/personal")
@CrossOrigin(origins = "*")
public class PersonalController {
	
	@Autowired
    private PersonalRepository repository;

    @GetMapping
    public List<Personal> listarTodo() {
        return repository.findAll();
    }
    @PostMapping
    public Personal crearEmpleado(@RequestBody Personal p) {
        return repository.save(p);
    }
}