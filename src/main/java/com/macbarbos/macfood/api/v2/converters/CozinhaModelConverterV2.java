package com.macbarbos.macfood.api.v2.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.macbarbos.macfood.api.v2.MacFoodLinksV2;
import com.macbarbos.macfood.api.v2.controller.CozinhaControllerV2;
import com.macbarbos.macfood.api.v2.model.CozinhaModelV2;
import com.macbarbos.macfood.domain.model.Cozinha;

@Component
public class CozinhaModelConverterV2 
        extends RepresentationModelAssemblerSupport<Cozinha, CozinhaModelV2> {

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private MacFoodLinksV2 macFoodLinks;
    
    public CozinhaModelConverterV2() {
        super(CozinhaControllerV2.class, CozinhaModelV2.class);
    }
    
    @Override
    public CozinhaModelV2 toModel(Cozinha cozinha) {
        CozinhaModelV2 cozinhaModel = createModelWithId(cozinha.getId(), cozinha);
        modelMapper.map(cozinha, cozinhaModel);
        
        cozinhaModel.add(macFoodLinks.linkToCozinhas("cozinhas"));
        
        return cozinhaModel;
    }   
}
