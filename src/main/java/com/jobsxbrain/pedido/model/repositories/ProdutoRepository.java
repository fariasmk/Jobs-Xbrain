package com.jobsxbrain.pedido.model.repositories;

import com.jobsxbrain.pedido.model.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    public Produto findById(int id);
}
