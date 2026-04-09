package com.shoetec.backend.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "clientes")
public class Cliente {

	public Cliente() {}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;
    private String nombre;
    private String direccion;
    private String localidad;
    private String provincia;
    private String email;
    private String telefono;
    
    @Column(name = "password")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    // Getters y Setters 
    public Long getId_cliente() { return id_cliente; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; }
    public String getLocalidad() { return localidad; } 
    public String getProvincia() { return provincia; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
    public String getPassword() { return password; }
    
    public void setId_cliente(Long id_cliente) { this.id_cliente = id_cliente; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setLocalidad(String localidad) { this.localidad = localidad; }
    public void setProvincia(String provincia) { this.provincia = provincia; }
	public void setEmail(String email) { this.email = email; }
	public void setTelefono(String telefono) { this.telefono = telefono; }
	public void setPassword(String password) { this.password = password; }
}

