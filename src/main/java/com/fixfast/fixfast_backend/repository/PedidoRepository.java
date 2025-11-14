package com.fixfast.fixfast_backend.repository;

import com.fixfast.fixfast_backend.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
