package com.macbarbos.macfood.di.service;

import com.macbarbos.macfood.di.modelo.Cliente;
import com.macbarbos.macfood.di.notificacao.Notificador;

public class AtivacaoClienteService {

	private Notificador notificador;
	
	public AtivacaoClienteService(Notificador notificador) {
		this.notificador = notificador;
		
		System.out.println("AtivacaoClienteService: " + notificador);
	}

	public void ativar(Cliente cliente) {
		cliente.ativar();

		notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
	}
	
}
