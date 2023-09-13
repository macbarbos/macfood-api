package com.macbarbos.macfood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.macbarbos.macfood.api.MacFoodLinks;
import com.macbarbos.macfood.api.converters.UsuarioModelConverter;
import com.macbarbos.macfood.api.model.UsuarioModel;
import com.macbarbos.macfood.api.openapi.controller.RestauranteUsuarioResponsavelControllerOpenApi;
import com.macbarbos.macfood.domain.model.Restaurante;
import com.macbarbos.macfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(path = "/restaurantes/{restauranteId}/responsaveis", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestauranteUsuarioResponsavelController implements RestauranteUsuarioResponsavelControllerOpenApi {

	@Autowired
	private CadastroRestauranteService cadastroRestaurante;

	@Autowired
	private UsuarioModelConverter usuarioModelConverter;
	
	@Autowired
    private MacFoodLinks macFoodLinks;

	@Override
	@GetMapping
	public CollectionModel<UsuarioModel> listar(@PathVariable Long restauranteId) {
	    Restaurante restaurante = cadastroRestaurante.buscarOuFalhar(restauranteId);
	    
	    return usuarioModelConverter.toCollectionModel(restaurante.getResponsaveis())
	            .removeLinks()
	            .add(macFoodLinks.linkToResponsaveisRestaurante(restauranteId));
	}

	@Override
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestaurante.desassociarResponsavel(restauranteId, usuarioId);
	}

	@Override
	@PutMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestaurante.associarResponsavel(restauranteId, usuarioId);
	}
}
