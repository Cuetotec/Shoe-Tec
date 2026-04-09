package com.shoetec.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.shoetec.backend.entities.Material;
import com.shoetec.backend.repositories.MaterialRepository;
import java.util.List;

@RestController
@RequestMapping("/materiales")
@CrossOrigin(origins = "*")
public class MaterialController {
	
	@Autowired
    private MaterialRepository repository;

	@GetMapping
    public List<Material> listarTodo() {
        return repository.findAll();
    }
	@PostMapping
	public Material guardar(@RequestBody Material m) {
	     return repository.save(m);
	    }
	}

