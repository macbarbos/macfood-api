package com.macbarbos.macfood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.macbarbos.macfood.MacfoodApiApplication;
import com.macbarbos.macfood.domain.model.Restaurante;
import com.macbarbos.macfood.domain.repository.RestauranteRepository;

public class BuscarRestauranteMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(MacfoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		
		Restaurante restaurante = restauranteRepository.buscar(1L);
		
		System.out.println(restaurante.getNome());
	}

}
