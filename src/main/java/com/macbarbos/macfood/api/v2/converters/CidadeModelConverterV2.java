package com.macbarbos.macfood.api.v2.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.macbarbos.macfood.api.v2.MacFoodLinksV2;
import com.macbarbos.macfood.api.v2.controller.CidadeControllerV2;
import com.macbarbos.macfood.api.v2.model.CidadeModelV2;
import com.macbarbos.macfood.domain.model.Cidade;

@Component
public class CidadeModelConverterV2 
		extends RepresentationModelAssemblerSupport<Cidade, CidadeModelV2> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MacFoodLinksV2 macFoodLinks;
	
	public CidadeModelConverterV2() {
		super(CidadeControllerV2.class, CidadeModelV2.class);
	}
	
	@Override
	public CidadeModelV2 toModel(Cidade cidade) {
		CidadeModelV2 cidadeModel = createModelWithId(cidade.getId(), cidade);
		
		modelMapper.map(cidade, cidadeModel);
		
		cidadeModel.add(macFoodLinks.linkToCidades("cidades"));
		
		return cidadeModel;
	}
	
	@Override
	public CollectionModel<CidadeModelV2> toCollectionModel(Iterable<? extends Cidade> entities) {
		return super.toCollectionModel(entities)
				.add(macFoodLinks.linkToCidades());
	}
	
}
