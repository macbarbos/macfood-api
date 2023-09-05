package com.macbarbos.macfood.api.converters;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.macbarbos.macfood.api.MacFoodLinks;
import com.macbarbos.macfood.api.controller.EstadoController;
import com.macbarbos.macfood.api.model.EstadoModel;
import com.macbarbos.macfood.domain.model.Estado;

@Component
public class EstadoModelConverter extends RepresentationModelAssemblerSupport<Estado, EstadoModel> {
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
    private MacFoodLinks macFoodLinks;
	
	public EstadoModelConverter() {
		super(EstadoController.class, EstadoModel.class);
	}
    
	@Override
	public EstadoModel toModel(Estado estado) {
	    EstadoModel estadoModel = createModelWithId(estado.getId(), estado);
	    modelMapper.map(estado, estadoModel);
	    
	    estadoModel.add(macFoodLinks.linkToEstados("estados"));
	    
	    return estadoModel;
	}
    
    @Override
    public CollectionModel<EstadoModel> toCollectionModel(Iterable<? extends Estado> entities) {
        return super.toCollectionModel(entities)
            .add(linkTo(EstadoController.class).withSelfRel());
    }

}
