package com.macbarbos.macfood.api.v1.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.macbarbos.macfood.api.v1.MacFoodLinks;
import com.macbarbos.macfood.api.v1.controller.RestauranteController;
import com.macbarbos.macfood.api.v1.model.RestauranteBasicoModel;
import com.macbarbos.macfood.domain.model.Restaurante;

@Component
public class RestauranteBasicoModelConverter 
		extends RepresentationModelAssemblerSupport<Restaurante, RestauranteBasicoModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MacFoodLinks macFoodLinks;
	
	public RestauranteBasicoModelConverter() {
		super(RestauranteController.class, RestauranteBasicoModel.class);
	}
	
	@Override
	public RestauranteBasicoModel toModel(Restaurante restaurante) {
		RestauranteBasicoModel restauranteModel = createModelWithId(
				restaurante.getId(), restaurante);
		
		modelMapper.map(restaurante, restauranteModel);
		
		restauranteModel.add(macFoodLinks.linkToRestaurantes("restaurantes"));
		
		restauranteModel.getCozinha().add(
				macFoodLinks.linkToCozinha(restaurante.getCozinha().getId()));
		
		return restauranteModel;
	}
	
	@Override
	public CollectionModel<RestauranteBasicoModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
		return super.toCollectionModel(entities)
				.add(macFoodLinks.linkToRestaurantes());
	}
	
}