package com.macbarbos.macfood.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.macbarbos.macfood.di.notificacao.NivelUrgencia;
import com.macbarbos.macfood.di.notificacao.Notificador;
import com.macbarbos.macfood.di.notificacao.TipoDoNotificador;
import com.macbarbos.macfood.di.service.ClienteAtivadoEvent;


@Component
public class NotificacaoService {

	@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
	@Autowired
	private Notificador notificador;
	
	@EventListener
	public void clienteAtivadoListener(ClienteAtivadoEvent event) {
		notificador.notificar(event.getCliente(), "Seu cadastro no sistema está ativo!");
	}
	
}
