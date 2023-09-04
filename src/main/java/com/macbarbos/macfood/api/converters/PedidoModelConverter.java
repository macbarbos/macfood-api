package com.macbarbos.macfood.api.converters;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariable.VariableType;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.macbarbos.macfood.api.controller.CidadeController;
import com.macbarbos.macfood.api.controller.FormaPagamentoController;
import com.macbarbos.macfood.api.controller.PedidoController;
import com.macbarbos.macfood.api.controller.RestauranteController;
import com.macbarbos.macfood.api.controller.RestauranteProdutoController;
import com.macbarbos.macfood.api.controller.UsuarioController;
import com.macbarbos.macfood.api.model.PedidoModel;
import com.macbarbos.macfood.domain.model.Pedido;

@Component
public class PedidoModelConverter extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoModelConverter() {
        super(PedidoController.class, PedidoModel.class);
    }
    
    @Override
	public PedidoModel toModel(Pedido pedido) {
		PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido);
		modelMapper.map(pedido, pedidoModel);
		
		TemplateVariables pageVariables = new TemplateVariables(
				new TemplateVariable("page", VariableType.REQUEST_PARAM),
				new TemplateVariable("size", VariableType.REQUEST_PARAM),
				new TemplateVariable("sort", VariableType.REQUEST_PARAM));
		
		TemplateVariables filtroVariables = new TemplateVariables(
				new TemplateVariable("clienteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("restauranteId", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoInicio", VariableType.REQUEST_PARAM),
				new TemplateVariable("dataCriacaoFim", VariableType.REQUEST_PARAM));
		
		String pedidosUrl = linkTo(PedidoController.class).toUri().toString();
		
		pedidoModel.add(Link.of(UriTemplate.of(pedidosUrl, pageVariables.concat(filtroVariables)), "pedidos"));
		
//		pedidoModel.add(linkTo(PedidoController.class).withRel("pedidos"));
		
		pedidoModel.getRestaurante().add(linkTo(methodOn(RestauranteController.class)
				.buscar(pedido.getRestaurante().getId())).withSelfRel());
		
		pedidoModel.getCliente().add(linkTo(methodOn(UsuarioController.class)
				.buscar(pedido.getCliente().getId())).withSelfRel());
		
		// Passamos null no segundo argumento, porque é indiferente para a
		// construção da URL do recurso de forma de pagamento
		pedidoModel.getFormaPagamento().add(linkTo(methodOn(FormaPagamentoController.class)
				.buscar(pedido.getFormaPagamento().getId(), null)).withSelfRel());
		
		pedidoModel.getEnderecoEntrega().getCidade().add(linkTo(methodOn(CidadeController.class)
				.buscar(pedido.getEnderecoEntrega().getCidade().getId())).withSelfRel());
		
		pedidoModel.getItens().forEach(item -> {
			item.add(linkTo(methodOn(RestauranteProdutoController.class)
					.buscar(pedidoModel.getRestaurante().getId(), item.getProdutoId()))
					.withRel("produto"));
		});
		
		return pedidoModel;
	}
}