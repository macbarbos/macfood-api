package com.macbarbos.macfood.api.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.macbarbos.macfood.api.MacFoodLinks;
import com.macbarbos.macfood.api.controller.CozinhaController;
import com.macbarbos.macfood.api.model.CozinhaModel;
import com.macbarbos.macfood.domain.model.Cozinha;

@Component
public class CozinhaModelConverter extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModel> {
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
    private MacFoodLinks macFoodLinks;
	
	public CozinhaModelConverter() {
		super(CozinhaController.class, CozinhaModel.class);
	}
    
	@Override
	public CozinhaModel toModel(Cozinha cozinha) {
	    CozinhaModel cozinhaModel = createModelWithId(cozinha.getId(), cozinha);
	    modelMapper.map(cozinha, cozinhaModel);
	    
	    cozinhaModel.add(macFoodLinks.linkToCozinhas("cozinhas"));
	    
	    return cozinhaModel;
	}
    
}
