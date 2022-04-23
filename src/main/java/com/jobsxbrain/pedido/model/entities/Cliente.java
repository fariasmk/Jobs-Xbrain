package com.jobsxbrain.pedido.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String cpf;
    private Date dataNascimento;
    private String EnderecoEntrega;


    public Cliente(String nome, String cpf, Date dataNascimento, String enderecoEntrega) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        EnderecoEntrega = enderecoEntrega;
    }
}
