package com.macbarbos.macfood.di.notificacao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.macbarbos.macfood.di.modelo.Cliente;

//@Primary //Desambiguação por @Primary
//@Qualifier("sms")//Desambiguação por @Qualifier
//** Desambiguação Customizada **/
@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadorSMS implements Notificador {

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Notificando %s por SMS através do telefone %s: %s\n", 
				cliente.getNome(), cliente.getTelefone(), mensagem);
		
	}

}
