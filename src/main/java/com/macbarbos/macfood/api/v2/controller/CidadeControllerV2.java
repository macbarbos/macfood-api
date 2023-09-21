package com.macbarbos.macfood.api.v2.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.macbarbos.macfood.api.ResourceUriHelper;
import com.macbarbos.macfood.api.v2.converters.CidadeConverterV2;
import com.macbarbos.macfood.api.v2.converters.CidadeModelConverterV2;
import com.macbarbos.macfood.api.v2.model.CidadeModelV2;
import com.macbarbos.macfood.api.v2.model.input.CidadeInputV2;
import com.macbarbos.macfood.api.v2.openapi.CidadeControllerV2OpenApi;
import com.macbarbos.macfood.domain.exception.EstadoNaoEncontradoException;
import com.macbarbos.macfood.domain.exception.NegocioException;
import com.macbarbos.macfood.domain.model.Cidade;
import com.macbarbos.macfood.domain.repository.CidadeRepository;
import com.macbarbos.macfood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping(path= "/v2/cidades", produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadeControllerV2 implements CidadeControllerV2OpenApi {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroCidadeService cadastroCidade;

	@Autowired
	private CidadeModelConverterV2 cidadeModelConverter;

	@Autowired
	private CidadeConverterV2 cidadeConverter;

	@Override
	@GetMapping /* (produces = MacFoodMediaTypes.V2_APPLICATION_JSON_VALUE) */
	public CollectionModel<CidadeModelV2> listar() {
		List<Cidade> todasCidades = cidadeRepository.findAll();
		
		return cidadeModelConverter.toCollectionModel(todasCidades);
	}

	@Override
	@GetMapping(path = "/{cidadeId}"/* , produces = MacFoodMediaTypes.V2_APPLICATION_JSON_VALUE */)
	public CidadeModelV2 buscar(@PathVariable Long cidadeId) {
		Cidade cidade = cadastroCidade.buscarOuFalhar(cidadeId);
		
		return cidadeModelConverter.toModel(cidade);
	}

	@Override
	@PostMapping /* (produces = MacFoodMediaTypes.V2_APPLICATION_JSON_VALUE) */
	@ResponseStatus(HttpStatus.CREATED)
	public CidadeModelV2 adicionar(@RequestBody CidadeInputV2 cidadeInput) {
		try {
			Cidade cidade = cidadeConverter.toDomainObject(cidadeInput);

			cidade = cadastroCidade.salvar(cidade);
			
			CidadeModelV2 CidadeModelV2 = cidadeModelConverter.toModel(cidade);
			
			ResourceUriHelper.addUriInResponseHeader(CidadeModelV2.getIdCidade());
			
			return CidadeModelV2;
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@Override
	@PutMapping(path = "/{cidadeId}"/* , produces = MacFoodMediaTypes.V2_APPLICATION_JSON_VALUE */)
	public CidadeModelV2 atualizar(@PathVariable Long cidadeId, @RequestBody @Valid CidadeInputV2 cidadeInput) {
		try {
			Cidade cidadeAtual = cadastroCidade.buscarOuFalhar(cidadeId);

			cidadeConverter.copyToDomainObject(cidadeInput, cidadeAtual);

			cidadeAtual = cadastroCidade.salvar(cidadeAtual);

			return cidadeModelConverter.toModel(cidadeAtual);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@Override
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId) {
		cadastroCidade.excluir(cidadeId);
	}

}