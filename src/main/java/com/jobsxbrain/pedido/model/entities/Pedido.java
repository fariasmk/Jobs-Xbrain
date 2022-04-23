package com.jobsxbrain.pedido.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Access(AccessType.PROPERTY)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="clienteId")
    private Cliente cliente;

    private String EnderecoEntrega;

    private BigDecimal totalCompra;

    private Date dataCompra;

    private boolean entregaEfetuada;

    @Access(AccessType.PROPERTY)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Produto> produtos;

    public Pedido() {
        super();
        this.totalCompra = BigDecimal.ZERO;
        this.dataCompra = new Date();
        this.entregaEfetuada = false;
    }

    public void calcularValorTotal() {
        this.totalCompra = BigDecimal.ZERO;
        for(Produto produto : produtos)
            this.totalCompra = this.totalCompra.add(produto.getPreco().multiply(produto.getQuantidade()));
    }

    public boolean adicionaProduto(Produto produto) {
        if(produto.getQuantidade().compareTo(BigDecimal.ZERO) > 0) {
            this.produtos.add(produto);
            this.calcularValorTotal();
            return true;
        }
        return false;
    }

    public boolean removeProduto(Produto produto) {
        for(Produto produtoTemp : produtos) {
            if(produtoTemp.equals(produto)) {
                produtos.remove(produtoTemp);
                return true;
            }
        }
        return false;
    }
}
