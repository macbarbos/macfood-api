package com.macbarbos.macfood.api.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.macbarbos.macfood.api.MacFoodLinks;
import com.macbarbos.macfood.api.controller.PedidoController;
import com.macbarbos.macfood.api.model.PedidoResumoModel;
import com.macbarbos.macfood.domain.model.Pedido;

@Component
public class PedidoResumoModelConverter extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel> {

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private MacFoodLinks macFoodLinks;

    public PedidoResumoModelConverter() {
        super(PedidoController.class, PedidoResumoModel.class);
    }
    
    @Override
    public PedidoResumoModel toModel(Pedido pedido) {
        PedidoResumoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoModel);
        
        pedidoModel.add(macFoodLinks.linkToPedidos());
        
        pedidoModel.getRestaurante().add(
        		macFoodLinks.linkToRestaurante(pedido.getRestaurante().getId()));

        pedidoModel.getCliente().add(macFoodLinks.linkToUsuario(pedido.getCliente().getId()));
        
        return pedidoModel;
    }
    
}
