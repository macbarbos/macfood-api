package com.macbarbos.macfood.api.converters;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.macbarbos.macfood.api.model.CozinhaModel;
import com.macbarbos.macfood.domain.model.Cozinha;

@Component
public class CozinhaModelConverter {
	
	@Autowired
    private ModelMapper modelMapper;
    
    public CozinhaModel toModel(Cozinha cozinha) {
        return modelMapper.map(cozinha, CozinhaModel.class);
    }
    
    public List<CozinhaModel> toCollectionModel(List<Cozinha> cozinhas) {
        return cozinhas.stream()
                .map(cozinha -> toModel(cozinha))
                .collect(Collectors.toList());
    }

}
