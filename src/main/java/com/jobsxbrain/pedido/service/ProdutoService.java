package com.jobsxbrain.pedido.service;

import com.jobsxbrain.pedido.model.entities.Produto;
import com.jobsxbrain.pedido.model.repositories.ProdutoRepository;
import com.jobsxbrain.pedido.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto findById(Integer id) {
        Optional<Produto> produto = repository.findById(id);
        return produto.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
    }

    public List<Produto> findAll() {
        List<Produto> list = repository.findAll();
        return list;
    }

    public Produto create(Produto produto) {
        produto.setId(null);
        return repository.save(produto);
    }

    public Produto update(Integer id, Produto produto) {
        Produto newProduto = findById(id);
        newProduto.setNome(produto.getNome());
        newProduto.setDescricao(produto.getDescricao());
        newProduto.setPreco(produto.getPreco());
        newProduto.setQuantidade(produto.getQuantidade());
        return repository.save(newProduto);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}

