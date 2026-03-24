package com.shoetec.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "pedidos")
public class Pedido {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pedido;
    
	private String fecha_creacion;
    private String estado;
    private Double precio_total;
    private Integer id_cliente;
    
    public Integer getId_pedido() {
		return id_pedido;
	}
	public void setId_pedido(Integer id_pedido) {
		this.id_pedido = id_pedido;
	}
	public String getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Double getPrecio_total() {
		return precio_total;
	}
	public void setPrecio_total(Double precio_total) {
		this.precio_total = precio_total;
	}
	public Integer getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	
}
