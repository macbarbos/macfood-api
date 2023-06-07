package com.macbarbos.macfood.api.model.mixin;

import java.util.List;

import com.macbarbos.macfood.domain.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class CozinhaMixin {

	@JsonIgnore
	private List<Restaurante> restaurantes;
	
}
