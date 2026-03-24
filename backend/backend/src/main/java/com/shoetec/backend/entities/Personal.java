package com.shoetec.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "personal")
public class Personal {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_personal;
    private String nombre;
    private String rol;
    private String especialidad;

    // Getters y Setters
    public Integer getId_personal() { return id_personal; }
    public void setId_personal(Integer id_personal) { this.id_personal = id_personal; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
}
