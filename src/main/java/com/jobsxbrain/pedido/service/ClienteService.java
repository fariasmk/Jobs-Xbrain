package com.jobsxbrain.pedido.service;

import com.jobsxbrain.pedido.model.entities.Cliente;
import com.jobsxbrain.pedido.model.repositories.ClienteRepository;
import com.jobsxbrain.pedido.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public List<Cliente> findAll() {
        List<Cliente> list = repository.findAll();
        return list;
    }

    public Cliente create(Cliente cliente) {
        return repository.save(cliente);
    }

    public Cliente update(Integer id, Cliente cliente) {
        Cliente newObj = findById(id);
        newObj.setNome(cliente.getNome());
        newObj.setCpf(cliente.getCpf());
        newObj.setDataNascimento(cliente.getDataNascimento());
        newObj.setEnderecoEntrega(cliente.getEnderecoEntrega());
        return repository.save(newObj);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
