package com.shoetec.backend.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pedido;

    @JsonProperty("direccion_entrega") 
    private String direccionEntrega;

    @JsonProperty("tipo_calzado")
    private String tipoCalzado;

    private String servicio;

    @JsonProperty("fecha_recogida")
    private String fechaRecogida;

    @JsonProperty("franja_horaria")
    private String franjaHoraria;

    private String observaciones;
    private String estado = "PENDIENTE";
    private String fecha_creacion = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    private Double precio_total;

    @JsonProperty("id_cliente")
    @Column(name = "id_cliente")
    private Integer idCliente;

    @JsonProperty("id_servicio")
    @Column(name = "id_servicio") 
    private Integer idServicio;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<HistorialPedido> historial = new ArrayList<>();

    // --- GETTERS Y SETTERS ESTÁNDAR (Generados para evitar fallos de mapeo) ---

    public Long getId_pedido() { return id_pedido; }
    public void setId_pedido(Long id_pedido) { this.id_pedido = id_pedido; }

    public String getDireccionEntrega() { return direccionEntrega; }
    public void setDireccionEntrega(String direccionEntrega) { this.direccionEntrega = direccionEntrega; }

    public String getTipoCalzado() { return tipoCalzado; }
    public void setTipoCalzado(String tipoCalzado) { this.tipoCalzado = tipoCalzado; }

    public String getServicio() { return servicio; }
    public void setServicio(String servicio) { this.servicio = servicio; }

    public String getFechaRecogida() { return fechaRecogida; }
    public void setFechaRecogida(String fechaRecogida) { this.fechaRecogida = fechaRecogida; }

    public String getFranjaHoraria() { return franjaHoraria; }
    public void setFranjaHoraria(String franjaHoraria) { this.franjaHoraria = franjaHoraria; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getFecha_creacion() { return fecha_creacion; }
    public void setFecha_creacion(String fecha_creacion) { this.fecha_creacion = fecha_creacion; }

    public Double getPrecio_total() { return precio_total; }
    public void setPrecio_total(Double precio_total) { this.precio_total = precio_total; }

    public Integer getIdCliente() { return idCliente; }
    public void setIdCliente(Integer idCliente) { this.idCliente = idCliente; }
    @JsonProperty("id_servicio")
    public Integer getIdServicio() { return idServicio; }
    @JsonProperty("id_servicio")
    public void setIdServicio(Integer idServicio) { this.idServicio = idServicio; }

    public List<HistorialPedido> getHistorial() { return historial; }
    public void setHistorial(List<HistorialPedido> historial) { this.historial = historial; }
}