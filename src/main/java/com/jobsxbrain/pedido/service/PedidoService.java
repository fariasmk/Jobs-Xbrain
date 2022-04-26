package com.jobsxbrain.pedido.service;

import com.jobsxbrain.pedido.model.entities.Cliente;
import com.jobsxbrain.pedido.model.entities.Pedido;
import com.jobsxbrain.pedido.model.repositories.PedidoRepository;
import com.jobsxbrain.pedido.model.repositories.ProdutoRepository;
import com.jobsxbrain.pedido.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Pedido findById(Integer id) {
        Optional<Pedido> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public List<Pedido> findAll() {
        List<Pedido> list = repository.findAll();
        return list;
    }

    public Pedido create(Pedido pedido) {
        pedido.calcularValorTotal();
        pedido.setDataCompra(LocalDate.now());
        return repository.save(pedido);
    }

    public Pedido updateEntrega(Integer id, Pedido pedido) {
        Pedido newPedido = findById(id);
        newPedido.setEntregaEfetuada(true);
        return repository.save(newPedido);
    }


    public void delete(Integer id) {
        repository.deleteById(id);
    }

}
