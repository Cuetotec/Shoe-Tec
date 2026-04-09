package com.shoetec.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.shoetec.backend.entities.Pedido;
import com.shoetec.backend.entities.HistorialPedido;
import com.shoetec.backend.entities.Servicios;
import com.shoetec.backend.entities.Material;
import com.shoetec.backend.repositories.PedidoRepository;
import com.shoetec.backend.repositories.MaterialRepository;
import com.shoetec.backend.repositories.ServiciosRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private ServiciosRepository servicioRepository;

    // Formateador de fecha para que en Android se vea bonito (ej: 08/04/2026 13:45)
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Transactional
    public Pedido guardarPedidoYRestarStock(Pedido pedido) {
        // 1. Validar Servicio y Stock (Tu lógica original)
        Servicios servicioAsociado = servicioRepository.findById(pedido.getIdServicio())
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        if (servicioAsociado.getIdMaterialConsumible() != null) {
            Material material = materialRepository.findById(servicioAsociado.getIdMaterialConsumible())
                    .orElseThrow(() -> new RuntimeException("Material asociado no encontrado"));

            if (material.getStockActual() < servicioAsociado.getCantidadConsumible()) {
                throw new RuntimeException("Stock insuficiente de: " + material.getNombre());
            }

            material.setStockActual(material.getStockActual() - servicioAsociado.getCantidadConsumible());
            materialRepository.save(material);
        }
        
        // 2. CREAR EL PRIMER ESTADO DEL HISTORIAL: "RECIBIDO"
        Pedido pedidoGuardado = pedidoRepository.saveAndFlush(pedido);
        
        HistorialPedido h = new HistorialPedido();
        h.setEstado("PEDIDO RECIBIDO");
        h.setFecha(LocalDateTime.now().format(formatter));
        h.setCompleto(true);
        h.setPedido(pedidoGuardado);

        // 3. Añadirlo a la lista (CascadeType.ALL lo guardará en la BD por nosotros)
        if (pedidoGuardado.getHistorial() == null) {
            pedidoGuardado.setHistorial(new ArrayList<>());
        }
        pedidoGuardado.getHistorial().add(h);

        return pedidoRepository.save(pedidoGuardado);
    }

    @Transactional
    public Pedido marcarComoTaller(Long id) {
        Pedido p = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        
        p.setEstado("EN TALLER");

        // CREAR NUEVO HITO: "EN TALLER"
        HistorialPedido h = new HistorialPedido();
        h.setEstado("EN TALLER");
        h.setFecha(LocalDateTime.now().format(formatter));
        h.setCompleto(true);
        h.setPedido(p);
        
        p.getHistorial().add(h);

        return pedidoRepository.save(p);
    }

    @Transactional
    public Pedido marcarComoEntregado(Long id) {
        Pedido p = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        
        p.setEstado("ENTREGADO");

        // CREAR NUEVO HITO: "ENTREGADO"
        HistorialPedido h = new HistorialPedido();
        h.setEstado("ENTREGADO");
        h.setFecha(LocalDateTime.now().format(formatter));
        h.setCompleto(true);
        h.setPedido(p);
        
        p.getHistorial().add(h);

        return pedidoRepository.save(p);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }
    public List<Pedido> listarPorCliente(Integer id) {
        return pedidoRepository.findByIdCliente(id);
    }
}