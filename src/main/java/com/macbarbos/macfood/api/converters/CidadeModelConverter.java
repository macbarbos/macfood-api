package com.macbarbos.macfood.api.converters;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.macbarbos.macfood.api.controller.CidadeController;
import com.macbarbos.macfood.api.controller.EstadoController;
import com.macbarbos.macfood.api.model.CidadeModel;
import com.macbarbos.macfood.domain.model.Cidade;

@Component
public class CidadeModelConverter extends RepresentationModelAssemblerSupport<Cidade, CidadeModel> {
	
	@Autowired
    private ModelMapper modelMapper;
	
	public CidadeModelConverter() {
		super(CidadeController.class, CidadeModel.class);
	}
    
	@Override
    public CidadeModel toModel(Cidade cidade) {
CidadeModel cidadeModel = createModelWithId(cidade.getId(), cidade);
		
		modelMapper.map(cidade, cidadeModel);
		
		cidadeModel.add(linkTo(methodOn(CidadeController.class)
				.listar()).withRel("cidades"));
		
		cidadeModel.getEstado().add(linkTo(methodOn(EstadoController.class)
				.buscar(cidadeModel.getEstado().getId())).withSelfRel());
		
		return cidadeModel;
    }
	
	@Override
	public CollectionModel<CidadeModel> toCollectionModel(Iterable<? extends Cidade> entities) {
		return super.toCollectionModel(entities)
				.add(linkTo(CidadeController.class).withSelfRel());
	}
    
}
