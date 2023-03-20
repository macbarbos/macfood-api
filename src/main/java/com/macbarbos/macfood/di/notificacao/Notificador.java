package com.macbarbos.macfood.di.notificacao;

import com.macbarbos.macfood.di.modelo.Cliente;

public interface Notificador {

	void notificar(Cliente cliente, String mensagem);

}
