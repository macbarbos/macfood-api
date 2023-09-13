package com.macbarbos.macfood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macbarbos.macfood.api.MacFoodLinks;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController {

	@Autowired
	private MacFoodLinks macFoodLinks;
	
	@GetMapping
	public RootEntryPointModel root() {
		var rootEntryPointModel = new RootEntryPointModel();
		
		rootEntryPointModel.add(macFoodLinks.linkToCozinhas("cozinhas"));
		rootEntryPointModel.add(macFoodLinks.linkToPedidos("pedidos"));
		rootEntryPointModel.add(macFoodLinks.linkToRestaurantes("restaurantes"));
		rootEntryPointModel.add(macFoodLinks.linkToGrupos("grupos"));
		rootEntryPointModel.add(macFoodLinks.linkToUsuarios("usuarios"));
		rootEntryPointModel.add(macFoodLinks.linkToPermissoes("permissoes"));
		rootEntryPointModel.add(macFoodLinks.linkToFormasPagamento("formas-pagamento"));
		rootEntryPointModel.add(macFoodLinks.linkToEstados("estados"));
		rootEntryPointModel.add(macFoodLinks.linkToCidades("cidades"));
		rootEntryPointModel.add(macFoodLinks.linkToEstatisticas("estatisticas"));
		
		return rootEntryPointModel;
	}
	
	private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {
	}
	
}
