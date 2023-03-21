package com.macbarbos.macfood.di.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.macbarbos.macfood.di.modelo.Cliente;
import com.macbarbos.macfood.di.notificacao.NivelUrgencia;
import com.macbarbos.macfood.di.notificacao.Notificador;
import com.macbarbos.macfood.di.notificacao.TipoDoNotificador;

//@Component
public class AtivacaoClienteService /* implements InitializingBean, DisposableBean */{

	@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
	@Autowired
	private Notificador notificador;
	
	//@PostConstruct
	public void init() {
		System.out.println("INIT" + notificador);
	}
	//@PreDestroy
	public void destroy() {
		System.out.println("DESTROY");
	}
	
	public void ativar(Cliente cliente) {
		cliente.ativar();

		notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
	}
	
}
	
