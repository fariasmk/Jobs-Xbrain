package com.jobsxbrain.pedido.controllers;

import com.jobsxbrain.pedido.model.entities.Cliente;
import com.jobsxbrain.pedido.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(path = {"/cliente"})
public class ClienteController {

    @Autowired
    private ClienteRepository clientes;

    @GetMapping
    public Cliente getCliente(int id) {
        Cliente cliente = new Cliente();
        try {
            cliente = clientes.findById(id);
        } catch (Exception e) {
            return null;
        }
        return cliente;
    }

    @PostMapping
    public Cliente cadastrarCliente(Cliente cliente) {
        clientes.save(cliente);
        return cliente;
    }

    @PutMapping("/alterar")
    public String alterarClienteById(int id, String nome, String cpf, Date dataNascimento) {
        Cliente cliente = new Cliente();
        try {
            cliente = clientes.findById(id);
        } catch (Exception e) {
            return "Cliente não encontrado";
        }

        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setDataNascimento(dataNascimento);
        clientes.save(cliente);
        return "Cliente " + cliente.getNome() + " atualizado!";
    }
}
