package com.macbarbos.macfood.api.converters;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.macbarbos.macfood.api.controller.CozinhaController;
import com.macbarbos.macfood.api.model.CozinhaModel;
import com.macbarbos.macfood.domain.model.Cozinha;

@Component
public class CozinhaModelConverter extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModel> {
	
	@Autowired
    private ModelMapper modelMapper;
	
	public CozinhaModelConverter() {
		super(CozinhaController.class, CozinhaModel.class);
	}
    
	@Override
    public CozinhaModel toModel(Cozinha cozinha) {
		CozinhaModel cozinhaModel = createModelWithId(cozinha.getId(), cozinha);
		modelMapper.map(cozinha, cozinhaModel);
		
		cozinhaModel.add(linkTo(CozinhaController.class).withRel("cozinhas"));
		
        return cozinhaModel;
    }
    
}
