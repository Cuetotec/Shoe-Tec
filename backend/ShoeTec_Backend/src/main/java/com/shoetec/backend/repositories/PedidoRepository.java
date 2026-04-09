package com.shoetec.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.shoetec.backend.entities.Pedido;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> { 
    List<Pedido> findByIdCliente(Integer idCliente);
    
}