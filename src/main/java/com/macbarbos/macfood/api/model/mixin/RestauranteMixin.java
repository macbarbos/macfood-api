package com.macbarbos.macfood.api.model.mixin;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.macbarbos.macfood.domain.model.Cozinha;
import com.macbarbos.macfood.domain.model.Endereco;
import com.macbarbos.macfood.domain.model.FormaPagamento;
import com.macbarbos.macfood.domain.model.Produto;

public abstract class RestauranteMixin {

	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	private Cozinha cozinha;
	
	@JsonIgnore
	private Endereco endereco;
	
	@JsonIgnore
	private OffsetDateTime dataCadastro;
	
	@JsonIgnore
	private OffsetDateTime dataAtualizacao;
	
	@JsonIgnore
	private List<FormaPagamento> formasPagamento = new ArrayList<>();
	
	@JsonIgnore
	private List<Produto> produtos = new ArrayList<>();
	
}
