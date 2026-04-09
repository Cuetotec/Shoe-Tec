package com.shoetec.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.shoetec.backend.entities.Cliente;
import com.shoetec.backend.repositories.ClienteRepository;

import java.util.List;

@RestController
@CrossOrigin(origins = "*") // ¡IMPORTANTE para evitar bloqueos de navegador/JavaFX!
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;
    
    @GetMapping("/clientes")
    public List<Cliente> obtenerTodos() {
        try {
            List<Cliente> clientes = clienteRepository.findAll();
            System.out.println("DEBUG: Enviando " + clientes.size() + " clientes.");
            return clientes;
        } catch (Exception e) {
            // Esto imprimirá el error real en la consola de Spring Boot
            System.err.println("ERROR EN CLIENTECONTROLLER: " + e.getMessage());
            e.printStackTrace();
            throw e; 
        }
    }
        @DeleteMapping("/clientes/{id}")
        public ResponseEntity<?> eliminarCliente(@PathVariable Integer id) {
            try {
                if (clienteRepository.existsById(id)) {
                	clienteRepository.deleteById(id);
                	System.out.println("DEBUG: Cliente " + id + " eliminado con éxito.");
                    return ResponseEntity.ok().build();
                } else {
                	return ResponseEntity.status(404).body("El cliente no existe en la base de datos.");
                }
            } catch (Exception e) {
                // 2. Si da error aquí, suele ser porque el cliente tiene PEDIDOS asociados (Integridad referencial)
                System.err.println("ERROR al eliminar cliente: " + e.getMessage());
                return ResponseEntity.status(500).body("No se puede eliminar: el cliente tiene historial de pedidos.");
            }
        }
        @PostMapping("/clientes/registro")
        public ResponseEntity<Cliente> registrarCliente(@RequestBody Cliente nuevoCliente) {
            try {
                Cliente guardado = clienteRepository.save(nuevoCliente);
                System.out.println("DEBUG: Cliente " + guardado.getNombre() + " registrado con éxito.");
                return ResponseEntity.ok(guardado);
            } catch (Exception e) {
                System.err.println("ERROR al registrar cliente: " + e.getMessage());
                return ResponseEntity.status(500).build();
            }
        }
        @PostMapping("/clientes/login")
        public ResponseEntity<?> login(@RequestBody Cliente datosLogin) {
            // Buscamos por email (o el campo que uses como usuario)
            Cliente cliente = clienteRepository.findByEmail(datosLogin.getEmail());

            if (cliente != null && cliente.getPassword().equals(datosLogin.getPassword())) {
                System.out.println("DEBUG: Login correcto para " + cliente.getNombre());
                return ResponseEntity.ok(cliente); // Devolvemos el objeto completo si coincide
            } else {
                return ResponseEntity.status(401).body("Credenciales incorrectas");
            }
        }
        @PostMapping("/clientes/actualizar")
        public ResponseEntity<?> actualizarCliente(@RequestBody Cliente datosActualizados) {
            try {
                // 1. Forzamos la conversión a Integer para que el Repositorio no proteste
                // Usamos (int) o .intValue() dependiendo de si getIdCliente devuelve long o Long
                Integer idParaBuscar = datosActualizados.getId_cliente().intValue();

                // 2. Ahora findById recibirá un Integer y el error desaparecerá
                return clienteRepository.findById(idParaBuscar)
                    .map(clienteExistente -> {
                        // 3. Actualizamos los campos
                        clienteExistente.setTelefono(datosActualizados.getTelefono());
                        clienteExistente.setDireccion(datosActualizados.getDireccion());
                        
                        // 4. Guardamos en la BD
                        clienteRepository.save(clienteExistente);
                        
                        System.out.println("DEBUG: Cliente " + idParaBuscar + " actualizado con éxito.");
                        return ResponseEntity.ok().build();
                    })
                    .orElseGet(() -> {
                        System.err.println("ERROR: No se encontró el ID " + idParaBuscar);
                        return ResponseEntity.status(404).body("Cliente no encontrado");
                    });
                    
            } catch (Exception e) {
                System.err.println("ERROR CRÍTICO: " + e.getMessage());
                return ResponseEntity.status(500).body("Error interno: " + e.getMessage());
            }
        }
}
