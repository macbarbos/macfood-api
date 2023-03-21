package com.macbarbos.macfood.di.notificacao;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.beans.factory.annotation.Qualifier;
//** Desambiguação Customizada **/
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface TipoDoNotificador {

	NivelUrgencia value();
	
}
