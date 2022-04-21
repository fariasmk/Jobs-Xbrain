package com.jobsxbrain.pedido.repositories;

import com.jobsxbrain.pedido.model.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    public Cliente findById(int id);
}
