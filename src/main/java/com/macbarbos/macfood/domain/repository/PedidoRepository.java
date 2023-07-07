package com.macbarbos.macfood.domain.repository;

import org.springframework.stereotype.Repository;

import com.macbarbos.macfood.domain.model.Pedido;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Long> {

}
