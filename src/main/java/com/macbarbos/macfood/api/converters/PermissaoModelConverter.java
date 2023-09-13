package com.macbarbos.macfood.api.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.macbarbos.macfood.api.MacFoodLinks;
import com.macbarbos.macfood.api.model.PermissaoModel;
import com.macbarbos.macfood.domain.model.Permissao;

@Component
public class PermissaoModelConverter implements RepresentationModelAssembler<Permissao, PermissaoModel> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MacFoodLinks macFoodLinks;
	
	@Override
	public PermissaoModel toModel(Permissao permissao) {
		return modelMapper.map(permissao, PermissaoModel.class);
	}
	
	@Override
	public CollectionModel<PermissaoModel> toCollectionModel(Iterable<? extends Permissao> entities) {
		return RepresentationModelAssembler.super.toCollectionModel(entities)
				.add(macFoodLinks.linkToPermissoes());
	}

}
