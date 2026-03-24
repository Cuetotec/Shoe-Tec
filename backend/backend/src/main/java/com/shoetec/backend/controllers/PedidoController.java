package com.shoetec.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.shoetec.backend.entities.Pedido;
import com.shoetec.backend.repositories.PedidoRepository;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {
    
    @Autowired
    private PedidoRepository repository;

    @GetMapping
    public List<Pedido> listarTodos() {
        return repository.findAll();
    }
}
