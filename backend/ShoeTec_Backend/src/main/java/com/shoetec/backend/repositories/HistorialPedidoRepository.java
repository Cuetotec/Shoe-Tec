package com.shoetec.backend.repositories;

import com.shoetec.backend.entities.HistorialPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HistorialPedidoRepository extends JpaRepository<HistorialPedido, Long> {
    // Esto permitirá buscar los hitos de un pedido específico
	@Query("SELECT h FROM HistorialPedido h WHERE h.pedido.id_pedido = :idPedido")
	List<HistorialPedido> buscarPorIdPedido(@Param("idPedido") Long idPedido);
}
