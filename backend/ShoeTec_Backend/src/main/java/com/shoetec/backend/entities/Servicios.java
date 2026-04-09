package com.shoetec.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "servicios")
public class Servicios {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_service;
    private String descripcion;
    private Double precio;
    
    @Column(name = "id_material_consumible")
    private Integer idMaterialConsumible;
    
    @Column(name = "cantidad_consumible")
    private Integer cantidadConsumible;
    
    // Getters y Setters
    public Integer getId_service() { return id_service;}
    public void setId_service(Integer id_service) { this.id_service = id_service; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
    
    public Integer getIdMaterialConsumible() { return idMaterialConsumible; }
    public void setIdMaterialConsumible(Integer idMaterialConsumible) { this.idMaterialConsumible = idMaterialConsumible; }

    public Integer getCantidadConsumible() { return cantidadConsumible; }
    public void setCantidadConsumible(Integer cantidadConsumible) { this.cantidadConsumible = cantidadConsumible; }
}
