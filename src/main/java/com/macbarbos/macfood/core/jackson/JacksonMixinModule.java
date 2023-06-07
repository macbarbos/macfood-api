package com.macbarbos.macfood.core.jackson;

import org.springframework.stereotype.Component;

import com.macbarbos.macfood.api.model.mixin.CidadeMixin;
import com.macbarbos.macfood.api.model.mixin.CozinhaMixin;
import com.macbarbos.macfood.api.model.mixin.RestauranteMixin;
import com.macbarbos.macfood.domain.model.Cidade;
import com.macbarbos.macfood.domain.model.Cozinha;
import com.macbarbos.macfood.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Component
public class JacksonMixinModule extends SimpleModule {

	private static final long serialVersionUID = 1L;

	public JacksonMixinModule() {
		setMixInAnnotation(Restaurante.class, RestauranteMixin.class);
		setMixInAnnotation(Cidade.class, CidadeMixin.class);
	    setMixInAnnotation(Cozinha.class, CozinhaMixin.class);
	}
	
}
