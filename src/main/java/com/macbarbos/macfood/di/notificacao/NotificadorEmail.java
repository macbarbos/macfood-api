package com.macbarbos.macfood.di.notificacao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.macbarbos.macfood.di.modelo.Cliente;

//@Primary //Desambiguação por @Primary
//@Qualifier("email")//Desambiguação por @Qualifier
//** Desambiguação Customizada **/
@TipoDoNotificador(NivelUrgencia.NORMAL)
@Component
public class NotificadorEmail implements Notificador {
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Notificando %s através do e-mail %s: %s\n", 
				cliente.getNome(), cliente.getEmail(), mensagem);
	}

}

