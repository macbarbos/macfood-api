package com.macbarbos.macfood.core.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.mediatype.hal.HalConfiguration;
import org.springframework.http.MediaType;

@Configuration
public class MacFoodHalConfiguration {

	@Bean
	public HalConfiguration globalPolicy() {
		return new HalConfiguration()
				.withMediaType(MediaType.APPLICATION_JSON)
				.withMediaType(MacFoodMediaTypes.V1_APPLICATION_JSON)
				.withMediaType(MacFoodMediaTypes.V2_APPLICATION_JSON);
	}
	
}
