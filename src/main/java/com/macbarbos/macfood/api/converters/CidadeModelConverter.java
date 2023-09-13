package com.macbarbos.macfood.api.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.macbarbos.macfood.api.MacFoodLinks;
import com.macbarbos.macfood.api.controller.CidadeController;
import com.macbarbos.macfood.api.model.CidadeModel;
import com.macbarbos.macfood.domain.model.Cidade;

@Component
public class CidadeModelConverter 
		extends RepresentationModelAssemblerSupport<Cidade, CidadeModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MacFoodLinks macFoodLinks;
	
	public CidadeModelConverter() {
		super(CidadeController.class, CidadeModel.class);
	}
	
	@Override
	public CidadeModel toModel(Cidade cidade) {
		CidadeModel cidadeModel = createModelWithId(cidade.getId(), cidade);
		
		modelMapper.map(cidade, cidadeModel);
		
		cidadeModel.add(macFoodLinks.linkToCidades("cidades"));
		
		cidadeModel.getEstado().add(macFoodLinks.linkToEstado(cidadeModel.getEstado().getId()));
		
		return cidadeModel;
	}
	
	@Override
	public CollectionModel<CidadeModel> toCollectionModel(Iterable<? extends Cidade> entities) {
		return super.toCollectionModel(entities)
				.add(macFoodLinks.linkToCidades());
	}
	
}
