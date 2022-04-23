package com.jobsxbrain.pedido.model.repositories;

import com.jobsxbrain.pedido.model.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    public Pedido findById(int id);
}
