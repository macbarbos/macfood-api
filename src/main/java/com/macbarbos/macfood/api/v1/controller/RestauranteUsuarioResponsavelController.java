package com.macbarbos.macfood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.macbarbos.macfood.api.v1.MacFoodLinks;
import com.macbarbos.macfood.api.v1.converters.UsuarioModelConverter;
import com.macbarbos.macfood.api.v1.model.UsuarioModel;
import com.macbarbos.macfood.api.v1.openapi.controller.RestauranteUsuarioResponsavelControllerOpenApi;
import com.macbarbos.macfood.domain.model.Restaurante;
import com.macbarbos.macfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/responsaveis", produces = MediaType.APPLICATION_JSON_VALUE)
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
	    
	    CollectionModel<UsuarioModel> usuariosModel =  usuarioModelConverter.toCollectionModel(restaurante.getResponsaveis())
	            .removeLinks()
	            .add(macFoodLinks.linkToResponsaveisRestaurante(restauranteId))
	            .add(macFoodLinks.linkToRestauranteResponsavelAssociacao(restauranteId, "associar"));
	    
	    usuariosModel.getContent().stream().forEach(usuarioModel -> {
	    	usuarioModel.add(macFoodLinks.linkToRestauranteResponsavelDesassociacao(restauranteId, usuarioModel.getId(), "desassociar"));
	    });
	    
	    return usuariosModel;
	}

	@Override
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestaurante.desassociarResponsavel(restauranteId, usuarioId);
		
		return ResponseEntity.noContent().build();
	}

	@Override
	@PutMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		cadastroRestaurante.associarResponsavel(restauranteId, usuarioId);
		
		return ResponseEntity.noContent().build();
	}
}
