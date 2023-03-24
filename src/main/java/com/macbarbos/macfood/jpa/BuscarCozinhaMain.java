package com.macbarbos.macfood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.macbarbos.macfood.MacfoodApiApplication;
import com.macbarbos.macfood.domain.model.Cozinha;
import com.macbarbos.macfood.domain.repository.CozinhasRepository;

public class BuscarCozinhaMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(MacfoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		CozinhasRepository cozinhasRepository = applicationContext.getBean(CozinhasRepository.class);
		
		Cozinha cozinha = cozinhasRepository.buscar(1L);
		
		System.out.println(cozinha.getNome());
	}

}
