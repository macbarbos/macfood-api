package com.macbarbos.macfood.api.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.macbarbos.macfood.api.MacFoodLinks;
import com.macbarbos.macfood.api.controller.RestauranteProdutoController;
import com.macbarbos.macfood.api.model.ProdutoModel;
import com.macbarbos.macfood.domain.model.Produto;

@Component
public class ProdutoModelConverter extends RepresentationModelAssemblerSupport<Produto, ProdutoModel> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MacFoodLinks macFoodLinks;
	
	public ProdutoModelConverter() {
        super(RestauranteProdutoController.class, ProdutoModel.class);
    }
	
	@Override
	public ProdutoModel toModel(Produto produto) {
		ProdutoModel produtoModel = createModelWithId(produto.getId(), produto, produto.getRestaurante().getId());
		
		modelMapper.map(produto, produtoModel);
		
		produtoModel.add(macFoodLinks.linkToProdutos(produto.getRestaurante().getId(), "produtos"));
		
		return produtoModel;
	}
	
	/*
	 * public List<ProdutoModel> toCollectionModel(List<Produto> produtos) { return
	 * produtos.stream() .map(produto -> toModel(produto))
	 * .collect(Collectors.toList()); }
	 */

}
