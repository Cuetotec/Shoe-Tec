package com.shoetec.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;
    
    private String nombre;
    private String direccion;
    private String email;
    private String telefono;
    private String password;

    // Getters y Setters (Necesarios para que Spring lea los datos)
    public Long getId_cliente() { return id_cliente; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
}

