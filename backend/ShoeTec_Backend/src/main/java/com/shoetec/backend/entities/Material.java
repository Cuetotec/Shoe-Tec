package com.shoetec.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "materiales")
public class Material {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_material;
	
	private String nombre;
	private String proveedor;
    private Integer stock_minimo;
    
    @Column(name = "stock_actual")
    private Integer stockActual;
	    
	public Integer getId_material() {
		return id_material;
	}
	public void setId_material(Integer id_material) {
		this.id_material = id_material;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public Integer getStock_minimo() {
		return stock_minimo;
	}
	public void setStock_minimo(Integer stock_minimo) {
		this.stock_minimo = stock_minimo;
	}
	public Integer getStockActual() { return stockActual; }
	public void setStockActual(Integer stockActual) { this.stockActual = stockActual; }
}
