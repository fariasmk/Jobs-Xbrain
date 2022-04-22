package com.jobsxbrain.pedido.controllers;

import com.jobsxbrain.pedido.model.entities.Produto;
import com.jobsxbrain.pedido.model.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtosRepository;

    @GetMapping
    public Produto getProduto(int id) {
        Produto produto = new Produto();
        try {
            produto = produtosRepository.findById(id);

        } catch (Exception e) {
            return null;
        }
        return produto;
    }

    @PostMapping
    public Produto cadastrarProduto(Produto produto) {
        produtosRepository.save(produto);
        return produto;
    }

    @PutMapping("/alterarProduto")
    public String alterarProdutoById(int id, String nome, String descricao, BigDecimal preco, BigDecimal quantidade) {
        Produto produto = new Produto();
        try {
            produto = produtosRepository.findById(id);
        } catch (Exception e) {
            return "Produto não encontrado";
        }

        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produto.setQuantidade(quantidade);
        produtosRepository.save(produto);
        return "Produto " + produto.getNome() + " alterado!";
    }

    @DeleteMapping("{id}")
    public String delete(int id) {
        Produto produto = new Produto();
        try {
            produto = produtosRepository.findById(id);
        } catch (Exception e) {
            return "Produto não encontrado";
        }

        produtosRepository.delete(produto);
        return null;
    }
}
