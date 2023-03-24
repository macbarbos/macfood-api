package com.macbarbos.macfood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.macbarbos.macfood.MacfoodApiApplication;
import com.macbarbos.macfood.domain.model.Restaurante;
import com.macbarbos.macfood.domain.repository.RestauranteRepository;

public class ConsultaRestauranteMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(MacfoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);
		
		List<Restaurante> restaurantes = restauranteRepository.listar();
		
		for(Restaurante restaurante : restaurantes) {
			System.out.println(restaurante.getNome());
		}
	}

}
