package com.macbarbos.macfood.api.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.macbarbos.macfood.api.MacFoodLinks;
import com.macbarbos.macfood.api.controller.PedidoController;
import com.macbarbos.macfood.api.model.PedidoModel;
import com.macbarbos.macfood.domain.model.Pedido;

@Component
public class PedidoModelConverter extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private MacFoodLinks macFoodLinks;

    public PedidoModelConverter() {
        super(PedidoController.class, PedidoModel.class);
    }
    
    @Override
    public PedidoModel toModel(Pedido pedido) {
        PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
        modelMapper.map(pedido, pedidoModel);
        
        pedidoModel.add(macFoodLinks.linkToPedidos("pedidos"));
        
        if (pedido.podeSerConfirmado()) {
			pedidoModel.add(macFoodLinks.linkToConfirmacaoPedido(pedido.getCodigo(), "confirmar"));
		}
		
		if (pedido.podeSerCancelado()) {
			pedidoModel.add(macFoodLinks.linkToCancelamentoPedido(pedido.getCodigo(), "cancelar"));
		}
		
		if (pedido.podeSerEntregue()) {
			pedidoModel.add(macFoodLinks.linkToEntregaPedido(pedido.getCodigo(), "entregar"));
		}
        
        pedidoModel.getRestaurante().add(
        		macFoodLinks.linkToRestaurante(pedido.getRestaurante().getId()));
        
        pedidoModel.getCliente().add(
        		macFoodLinks.linkToUsuario(pedido.getCliente().getId()));
        
        pedidoModel.getFormaPagamento().add(
        		macFoodLinks.linkToFormaPagamento(pedido.getFormaPagamento().getId()));
        
        pedidoModel.getEnderecoEntrega().getCidade().add(
        		macFoodLinks.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));
        
        pedidoModel.getItens().forEach(item -> {
            item.add(macFoodLinks.linkToProduto(
                    pedidoModel.getRestaurante().getId(), item.getProdutoId(), "produto"));
        });
        
        return pedidoModel;
    }
}