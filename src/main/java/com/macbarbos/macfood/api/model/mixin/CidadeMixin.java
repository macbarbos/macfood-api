package com.macbarbos.macfood.api.model.mixin;

import com.macbarbos.macfood.domain.model.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public abstract class CidadeMixin {

	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	private Estado estado;
	
}
