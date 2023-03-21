package com.macbarbos.macfood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.macbarbos.macfood.di.modelo.Cliente;
import com.macbarbos.macfood.di.notificacao.NivelUrgencia;
import com.macbarbos.macfood.di.notificacao.Notificador;
import com.macbarbos.macfood.di.notificacao.TipoDoNotificador;

@Component
public class AtivacaoClienteService {

	//@Autowired//(required = false)
	//Desambiguação por @Primary
	//private List<Notificador> notificadores;
	
	//@Qualifier("email") //Desambiguação por @Qualifier
	//** Desambiguação Customizada **/
	@TipoDoNotificador(NivelUrgencia.URGENTE)
	@Autowired
	private Notificador notificador;
	
//	@Autowired
//	public AtivacaoClienteService(Notificador notificador) {
//		this.notificador = notificador;
//	}
//	
//	public AtivacaoClienteService(String qualquer) {
//		
//	}

	/*
	 * public void ativar(Cliente cliente) { cliente.ativar();
	 * 
	 * if (notificador != null) { notificador.notificar(cliente,
	 * "Seu cadastro no sistema está ativo!"); } else {
	 * System.out.println("Não existe notificador, mas cliente foi ativado"); } }
	 */
	
	//Desambiguação por List
	/*public void ativar(Cliente cliente) {
		cliente.ativar();
		
		for(Notificador notificador : notificadores)
			notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
		}
	}*/
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
	}
}
//	@Autowired
//	public void setNotificador(Notificador notificador) {
//		this.notificador = notificador;
//	}
	
