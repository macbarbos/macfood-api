package com.macbarbos.macfood;

import org.springframework.context.annotation.Bean;

import com.macbarbos.macfood.di.notificacao.NotificadorEmail;
import com.macbarbos.macfood.di.service.AtivacaoClienteService;

//@Configuration
public class MacFoodConfig {

	@Bean
	public NotificadorEmail notificadorEmail() {
		NotificadorEmail notificador = new NotificadorEmail("smtp.algamail.com.br");
		notificador.setCaixaAlta(true);
		
		return notificador;
	}
	
	@Bean
	public AtivacaoClienteService ativacaoClienteService() {
		return new AtivacaoClienteService(notificadorEmail());
	}
	
}
