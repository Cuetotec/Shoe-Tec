package com.shoetec.backend.entities;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "historial_pedidos")
public class HistorialPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_historial;
    
    @ManyToOne
    @JoinColumn(name = "id_pedido_fk", referencedColumnName = "id_pedido")
    @JsonBackReference
    private Pedido pedido;
    
    private String estado;
    private String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    private Boolean completo = true;

    // --- GETTERS Y SETTERS ---

    public Long getId_historial() { return id_historial; }
    public void setId_historial(Long id_historial) { this.id_historial = id_historial; }

    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public Boolean getCompleto() { return completo; }
    public void setCompleto(Boolean completo) { this.completo = completo; }
}