package com.macbarbos.macfood.api.v1.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.macbarbos.macfood.api.v1.MacFoodLinks;
import com.macbarbos.macfood.api.v1.controller.FormaPagamentoController;
import com.macbarbos.macfood.api.v1.model.FormaPagamentoModel;
import com.macbarbos.macfood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoModelConverter extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoModel>{
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MacFoodLinks macFoodLinks;
	
	public FormaPagamentoModelConverter() {
        super(FormaPagamentoController.class, FormaPagamentoModel.class);
    }
	
	public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
		FormaPagamentoModel formaPagamentoModel = 
                createModelWithId(formaPagamento.getId(), formaPagamento);
        
        modelMapper.map(formaPagamento, formaPagamentoModel);
        
        formaPagamentoModel.add(macFoodLinks.linkToFormasPagamento("formasPagamento"));
        
        return formaPagamentoModel;
	}
	
	@Override
    public CollectionModel<FormaPagamentoModel> toCollectionModel(Iterable<? extends FormaPagamento> entities) {
        return super.toCollectionModel(entities)
            .add(macFoodLinks.linkToFormasPagamento());
    } 

}
