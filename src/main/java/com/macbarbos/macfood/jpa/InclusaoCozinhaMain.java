package com.macbarbos.macfood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.macbarbos.macfood.MacfoodApiApplication;
import com.macbarbos.macfood.domain.model.Cozinha;

public class InclusaoCozinhaMain {

	public static void main(String[] args) {
		
		ApplicationContext applicationContext = new SpringApplicationBuilder(MacfoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);

		CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);
		
		Cozinha cozinha = new Cozinha();
		cozinha.setId(1l);
		cozinha.setNome("Brasileira");
		
		cadastroCozinha.salvar(cozinha);
		
	}

}
