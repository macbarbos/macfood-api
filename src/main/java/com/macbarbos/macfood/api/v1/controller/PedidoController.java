package com.macbarbos.macfood.api.v1.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;
import com.macbarbos.macfood.api.v1.converters.PedidoConverter;
import com.macbarbos.macfood.api.v1.converters.PedidoModelConverter;
import com.macbarbos.macfood.api.v1.converters.PedidoResumoModelConverter;
import com.macbarbos.macfood.api.v1.model.PedidoModel;
import com.macbarbos.macfood.api.v1.model.PedidoResumoModel;
import com.macbarbos.macfood.api.v1.model.input.PedidoInput;
import com.macbarbos.macfood.api.v1.openapi.controller.PedidoControllerOpenApi;
import com.macbarbos.macfood.core.data.PageWrapper;
import com.macbarbos.macfood.core.data.PageableTranslator;
import com.macbarbos.macfood.domain.exception.EntidadeNaoEncontradaException;
import com.macbarbos.macfood.domain.exception.NegocioException;
import com.macbarbos.macfood.domain.model.Pedido;
import com.macbarbos.macfood.domain.model.Usuario;
import com.macbarbos.macfood.domain.repository.PedidoRepository;
import com.macbarbos.macfood.domain.repository.filter.PedidoFilter;
import com.macbarbos.macfood.domain.service.EmissaoPedidoService;
import com.macbarbos.macfood.infrastructure.repository.spec.PedidoSpecs;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping(path = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenApi {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private EmissaoPedidoService emissaoPedido;
	
	@Autowired
	private PedidoModelConverter pedidoModelConverter;
	
	@Autowired
	private PedidoResumoModelConverter pedidoResumoModelConverter;
	
	@Autowired
	private PedidoConverter pedidoConverter;
	
	@Autowired
	private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;
	
	@Override
	@GetMapping
	public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, 
			@PageableDefault(size = 10) Pageable pageable) {
		Pageable pageableTraduzido = traduzirPageable(pageable);
		
		Page<Pedido> pedidosPage = pedidoRepository.findAll(
				PedidoSpecs.usandoFiltro(filtro), pageableTraduzido);
		
		pedidosPage = new PageWrapper<>(pedidosPage, pageable);
		
		return pagedResourcesAssembler.toModel(pedidosPage, pedidoResumoModelConverter);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PedidoModel adicionar(@Valid @RequestBody PedidoInput pedidoInput) {
		try {
			Pedido novoPedido = pedidoConverter.toDomainObject(pedidoInput);

			// TODO pegar usuário autenticado
			novoPedido.setCliente(new Usuario());
			novoPedido.getCliente().setId(1L);

			novoPedido = emissaoPedido.emitir(novoPedido);

			return pedidoModelConverter.toModel(novoPedido);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@ApiImplicitParams({
		@ApiImplicitParam(value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
				name = "campos", paramType = "query", type = "string")
	})
	@GetMapping("/{codigoPedido}")
	public PedidoModel buscar(@PathVariable String codigoPedido) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(codigoPedido);
		
		return pedidoModelConverter.toModel(pedido);
	}
	
	private Pageable traduzirPageable(Pageable apiPageable) {
		var mapeamento = ImmutableMap.of(
				"codigo", "codigo",
				"restaurante.nome", "restaurante.nome",
				"nomeCliente", "cliente.nome",
				"valorTotal", "valorTotal"
			);
		
		return PageableTranslator.translate(apiPageable, mapeamento);
	}
	
}