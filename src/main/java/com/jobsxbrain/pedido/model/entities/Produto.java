package com.jobsxbrain.pedido.model.entities;

import com.jobsxbrain.pedido.model.repositories.PedidoRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

//    @Autowired
//    private PedidoRepository pedidoRepository;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String descricao;
    private BigDecimal preco;
    private BigDecimal quantidade;

    @Access(AccessType.PROPERTY)
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "pedidoId")
    private Pedido pedido;


    public Produto(Integer id, String nome, String descricao, BigDecimal preco, BigDecimal quantidade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

}
