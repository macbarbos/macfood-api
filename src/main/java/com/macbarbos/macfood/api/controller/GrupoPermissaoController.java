package com.macbarbos.macfood.api.controller;

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

import com.macbarbos.macfood.api.MacFoodLinks;
import com.macbarbos.macfood.api.converters.PermissaoModelConverter;
import com.macbarbos.macfood.api.model.PermissaoModel;
import com.macbarbos.macfood.api.openapi.controller.GrupoPermissaoControllerOpenApi;
import com.macbarbos.macfood.domain.model.Grupo;
import com.macbarbos.macfood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping(path = "/grupos/{grupoId}/permissoes", produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {

	@Autowired
	private CadastroGrupoService cadastroGrupo;
	
	@Autowired
	private PermissaoModelConverter permissaoModelConverter;
	
	@Autowired
	private MacFoodLinks macFoodLinks;
	
	@GetMapping
	public CollectionModel<PermissaoModel> listar(@PathVariable Long grupoId) {
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
		
		CollectionModel<PermissaoModel> permissoesModel 
        = permissaoModelConverter.toCollectionModel(grupo.getPermissoes())
            .removeLinks()
            .add(macFoodLinks.linkToGrupoPermissoes(grupoId))
            .add(macFoodLinks.linkToGrupoPermissaoAssociacao(grupoId, "associar"));
    
    permissoesModel.getContent().forEach(permissaoModel -> {
        permissaoModel.add(macFoodLinks.linkToGrupoPermissaoDesassociacao(
                grupoId, permissaoModel.getId(), "desassociar"));
    });
    
    return permissoesModel;
	}
	
	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupo.desassociarPermissao(grupoId, permissaoId);
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		cadastroGrupo.associarPermissao(grupoId, permissaoId);
		
		return ResponseEntity.noContent().build();
	}

}
