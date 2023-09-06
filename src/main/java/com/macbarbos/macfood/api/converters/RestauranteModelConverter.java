package com.macbarbos.macfood.api.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.macbarbos.macfood.api.MacFoodLinks;
import com.macbarbos.macfood.api.controller.RestauranteController;
import com.macbarbos.macfood.api.model.RestauranteModel;
import com.macbarbos.macfood.domain.model.Restaurante;

@Component
public class RestauranteModelConverter 
		extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MacFoodLinks macFoodLinks;
	
	public RestauranteModelConverter() {
		super(RestauranteController.class, RestauranteModel.class);
	}
	
	@Override
	public RestauranteModel toModel(Restaurante restaurante) {
		RestauranteModel restauranteModel = createModelWithId(restaurante.getId(), restaurante);
		modelMapper.map(restaurante, restauranteModel);
		
		restauranteModel.add(macFoodLinks.linkToRestaurantes("restaurantes"));
		
		if (restaurante.ativacaoPermitida()) {
			restauranteModel.add(
					macFoodLinks.linkToRestauranteAtivacao(restaurante.getId(), "ativar"));
		}

		if (restaurante.inativacaoPermitida()) {
			restauranteModel.add(
					macFoodLinks.linkToRestauranteInativacao(restaurante.getId(), "inativar"));
		}

		if (restaurante.aberturaPermitida()) {
			restauranteModel.add(
					macFoodLinks.linkToRestauranteAbertura(restaurante.getId(), "abrir"));
		}

		if (restaurante.fechamentoPermitido()) {
			restauranteModel.add(
					macFoodLinks.linkToRestauranteFechamento(restaurante.getId(), "fechar"));
		}
		
		restauranteModel.getCozinha().add(
				macFoodLinks.linkToCozinha(restaurante.getCozinha().getId()));
		
		restauranteModel.getEndereco().getCidade().add(
				macFoodLinks.linkToCidade(restaurante.getEndereco().getCidade().getId()));
		
		restauranteModel.add(macFoodLinks.linkToRestauranteFormasPagamento(restaurante.getId(), 
				"formas-pagamento"));
		
		restauranteModel.add(macFoodLinks.linkToRestauranteResponsaveis(restaurante.getId(), 
				"responsaveis"));
		
		return restauranteModel;
	}
	
	@Override
	public CollectionModel<RestauranteModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
		return super.toCollectionModel(entities)
				.add(macFoodLinks.linkToRestaurantes());
	}
	
}