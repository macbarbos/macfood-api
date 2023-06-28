package com.macbarbos.macfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.macbarbos.macfood.api.converters.GrupoConverter;
import com.macbarbos.macfood.api.converters.GrupoModelConverter;
import com.macbarbos.macfood.api.model.GrupoModel;
import com.macbarbos.macfood.api.model.input.GrupoInput;
import com.macbarbos.macfood.domain.model.Grupo;
import com.macbarbos.macfood.domain.repository.GrupoRepository;
import com.macbarbos.macfood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private GrupoModelConverter grupoModelConverter;
	
	@Autowired
	private GrupoConverter grupoConverter;
	
	@Autowired
	private CadastroGrupoService cadastroGrupo;
	
	@GetMapping
	public List<GrupoModel> listar() {
		List<Grupo> todosGrupos = grupoRepository.findAll();
		
		return grupoModelConverter.toCollectionModel(todosGrupos);
	}
	
	@GetMapping("/{grupoId}")
	public GrupoModel buscar(@PathVariable Long grupoId) {
		Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);
		
		return grupoModelConverter.toModel(grupo);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupo = grupoConverter.toDomainObject(grupoInput);
		
		grupo = cadastroGrupo.salvar(grupo);
		
		return grupoModelConverter.toModel(grupo);
	}
	
	@PutMapping("/{grupoId}")
	public GrupoModel atualizar(@PathVariable Long grupoId,
			@RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupoAtual = cadastroGrupo.buscarOuFalhar(grupoId);
		
		grupoConverter.copyToDomainObject(grupoInput, grupoAtual);
		
		grupoAtual = cadastroGrupo.salvar(grupoAtual);
		
		return grupoModelConverter.toModel(grupoAtual);
	}
	
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long grupoId) {
		cadastroGrupo.excluir(grupoId);	
	}

}
