package com.jobsxbrain.pedido.service;

import com.jobsxbrain.pedido.model.entities.Cliente;
import com.jobsxbrain.pedido.model.entities.Produto;
import com.jobsxbrain.pedido.model.repositories.ClienteRepository;
import com.jobsxbrain.pedido.model.repositories.PedidoRepository;
import com.jobsxbrain.pedido.model.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    public void instanciaBaseDeDados() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Cliente c1 = new Cliente(null, "Maikon Farias", "000.000.000.00", sdf.parse("02/04/1980"), "Rua Bom Jesus da Lapa, 106");
        Cliente c2 = new Cliente(null, "Diego da Cruz", "009.000.000.00", sdf.parse("10/05/1990"), "Rua Bom Jesus da Lapa, 106");

        Produto pr1 = new Produto(null, "Notebook", "Asus i7", new BigDecimal("3000.00"), new BigDecimal("1.00"));
        Produto pr2 = new Produto(null, "Mouse", "Gamer", new BigDecimal("300.00"), new BigDecimal("1.00"));
        Produto pr3 = new Produto(null, "Teclado", "Mecânico", new BigDecimal("300.00"), new BigDecimal("1.00"));
        Produto pr4 = new Produto(null, "Notebook", "Avell i7", new BigDecimal("4000.00"), new BigDecimal("1.00"));

        clienteRepository.saveAll(Arrays.asList(c1, c2));
        produtoRepository.saveAll(Arrays.asList(pr1, pr2, pr3, pr4));


//        Pedido p1 = new Pedido(null, c1, Arrays.asList(pr1, pr2));
//        Pedido p2 = new Pedido(null, c2, Arrays.asList(pr3, pr4));
//
//        pedidoRepository.saveAll(Arrays.asList(p1, p2));

    }

}
