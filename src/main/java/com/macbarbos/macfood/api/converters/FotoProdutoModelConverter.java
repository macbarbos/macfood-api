package com.macbarbos.macfood.api.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.macbarbos.macfood.api.model.FotoProdutoModel;
import com.macbarbos.macfood.domain.model.FotoProduto;

@Component
public class FotoProdutoModelConverter {
	
	@Autowired
    private ModelMapper modelMapper;
    
	public FotoProdutoModel toModel(FotoProduto foto) {
		return modelMapper.map(foto, FotoProdutoModel.class);
	}

}
