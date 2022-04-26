package com.jobsxbrain.pedido.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Access(AccessType.PROPERTY)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "clienteId")
    private Cliente cliente;

    private BigDecimal totalCompra;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCompra;

    private boolean entregaEfetuada;

    @Access(AccessType.PROPERTY)
    @OneToMany(cascade = CascadeType.MERGE)
    @JoinColumn(name = "produtoId")
    private List<Produto> produtos;

    public Pedido(Integer id, Cliente cliente, List<Produto> produtos) {
        super();
        this.id = id;
        this.cliente = cliente;
        this.produtos = produtos;
    }


    public void calcularValorTotal() {
        this.totalCompra = BigDecimal.ZERO;
        for (Produto produto : produtos)
            this.totalCompra = this.totalCompra.add(produto.getPreco().multiply(produto.getQuantidade()));
    }

//    public void setIdPedidoNoProduto(Pedido pedido) {
//        for (Produto produto : produtos)
//            produto.setPedido(pedido.getId());
//    }

}
