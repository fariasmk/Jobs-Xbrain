package com.jobsxbrain.pedido.controllers;

import com.jobsxbrain.pedido.model.entities.Cliente;
import com.jobsxbrain.pedido.model.entities.Pedido;
import com.jobsxbrain.pedido.model.entities.Produto;
import com.jobsxbrain.pedido.model.repositories.ClienteRepository;
import com.jobsxbrain.pedido.model.repositories.PedidoRepository;
import com.jobsxbrain.pedido.model.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping
    public Pedido getPedido(int id) {
        return pedidoRepository.findById(id);
    }

    @GetMapping("/lista")
    public Iterable<Pedido> getPedidos() {
        return pedidoRepository.findAll();
    }

    @PostMapping
    public Pedido abrirPedido() {
        Pedido pedido = new Pedido();
        pedidoRepository.save(pedido);
        return pedido;
    }

    @PutMapping("/cliente")
    public String vincularClientePedido(int cliente_id, int pedido_id) {
        Cliente cliente = new Cliente();
        Pedido pedido;
        try {
            cliente = clienteRepository.findById(cliente_id);
        } catch (Exception e1) {
            return "Cliente não encontrado!";
        }

        try {
            pedido = pedidoRepository.findById(pedido_id);
        } catch (Exception e1) {
            return "Pedido não encontrado!";
        }

        pedido.setCliente(cliente);
        pedidoRepository.save(pedido);
        return cliente.getNome() + " vinculado ao pedido de número " + pedido.getId();
    }

    @PutMapping("/cliente/desvincular")
    public String desvincularClientePedido(int id) {
        Pedido pedido;
        try {
            pedido = pedidoRepository.findById(id);
        } catch (Exception e1) {
            return "Pedido não encontrado!";
        }
        pedido.setCliente(null);
        pedidoRepository.save(pedido);
        return "Desvinculado o cliente do pedido de número " + pedido.getId();
    }

    @PutMapping("/produto")
    public String vincularProdutoPedido(int produto_id, int pedido_id) {
        Produto produto = new Produto();
        Pedido pedido;
        try {
            produto = produtoRepository.findById(produto_id);
        } catch (Exception e1) {
            return "Produto não encontrado!";
        }

        try {
            pedido = pedidoRepository.findById(pedido_id);
        } catch (Exception e1) {
            return "Pedido não encontrado!";
        }

        if(pedido.adicionaProduto(produto)) {
            pedidoRepository.save(pedido);
            produtoRepository.save(produto);
            return produto.getNome() + " adicionado ao pedido de número " + pedido.getId();
        }
        return "Produto " + produto.getNome() + " não possui quantidade suficiente";
    }

    @PutMapping("/produto/desvincular")
    public String desvincularProdutoPedido(int produto_id, int pedido_id) {
        Produto produto = new Produto();
        Pedido pedido;
        try {
            produto = produtoRepository.findById(produto_id);
        } catch (Exception e1) {
            return "Produto não encontrado!";
        }

        try {
            pedido = pedidoRepository.findById(pedido_id);
        } catch (Exception e1) {
            return "Pedido não encontrado!";
        }

        if(pedido.removeProduto(produto)) {
            pedidoRepository.save(pedido);
            produtoRepository.save(produto);
            return produto.getNome() + " removido do pedido de número " + pedido.getId();
        }
        return "Produto " + produto.getNome() + " não inserido no pedido!";
    }

    @PutMapping("/entregue/{id}")
    public String entregaEfetuada(int id) {
        Pedido pedido;
        try {
            pedido = pedidoRepository.findById(id);
        } catch (Exception e1) {
            return "Pedido não encontrado!";
        }
        pedido.setEntregaEfetuada(true);
        pedidoRepository.save(pedido);
        return "Pedido Entreguado com sucesso!";
    }



    @DeleteMapping
    public String deletarPedido(int id) {
        Pedido pedido;
        try {
            pedido = pedidoRepository.findById(id);
        } catch (Exception e1) {
            return "Pedido não encontrado!";
        }
        pedidoRepository.delete(pedido);
        return "Pedido deletado com sucesso!";
    }
}
