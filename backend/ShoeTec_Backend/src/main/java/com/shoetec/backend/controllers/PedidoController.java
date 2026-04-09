package com.shoetec.backend.controllers;

import com.shoetec.backend.entities.Pedido;
import com.shoetec.backend.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // CORREGIDO: POST para guardar (Coincide con @POST en Android)
    @PostMapping("/guardar")
    public ResponseEntity<?> crearPedido(@RequestBody Pedido pedido) {
    	
       	try {
            Pedido guardado = pedidoService.guardarPedidoYRestarStock(pedido);
            return ResponseEntity.ok(guardado);
        } catch (RuntimeException e) {
        	
        	System.out.println("ERROR LOGICO: " + e.getMessage());
        	
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            
        } catch (Exception e) {
            System.out.println("ERROR GENERAL: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            
        }
    }

    // CORREGIDO: GET para listar por cliente (Coincide con @GET en Android)
    @GetMapping("/cliente/{id}")
    public List<Pedido> listarPorCliente(@PathVariable Integer id) {
        return pedidoService.listarPorCliente(id); 
    }

    // Listar todos (Opcional, útil para administración)
    @GetMapping
    public List<Pedido> listarTodos() {
        return pedidoService.listarTodos();
    }

    // CORREGIDO: PUT para taller (Acción de actualizar estado)
    @PutMapping("/{id}/taller")
    public ResponseEntity<?> marcarComoTaller(@PathVariable Long id) {
        try {
            Pedido actualizado = pedidoService.marcarComoTaller(id);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // CORREGIDO: PUT para entregar (Acción de actualizar estado)
    @PutMapping("/{id}/entregar")
    public ResponseEntity<?> entregar(@PathVariable Long id) {
        try {
            Pedido actualizado = pedidoService.marcarComoEntregado(id);
            return ResponseEntity.ok(actualizado);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}