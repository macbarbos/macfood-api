package com.macbarbos.macfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macbarbos.macfood.api.converters.PedidoModelConverter;
import com.macbarbos.macfood.api.converters.PedidoResumoModelConverter;
import com.macbarbos.macfood.api.model.PedidoModel;
import com.macbarbos.macfood.api.model.PedidoResumoModel;
import com.macbarbos.macfood.domain.model.Pedido;
import com.macbarbos.macfood.domain.repository.PedidoRepository;
import com.macbarbos.macfood.domain.service.EmissaoPedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private EmissaoPedidoService emissaoPedido;
    
    @Autowired
    private PedidoModelConverter pedidoModelConverter;
    
    @Autowired
    private PedidoResumoModelConverter pedidoResumoModelConverter;
    
    @GetMapping
    public List<PedidoResumoModel> listar() {
        List<Pedido> todosPedidos = pedidoRepository.findAll();
        
        return pedidoResumoModelConverter.toCollectionModel(todosPedidos);
    }
    
    @GetMapping("/{pedidoId}")
    public PedidoModel buscar(@PathVariable Long pedidoId) {
        Pedido pedido = emissaoPedido.buscarOuFalhar(pedidoId);
        
        return pedidoModelConverter.toModel(pedido);
    }            
}
