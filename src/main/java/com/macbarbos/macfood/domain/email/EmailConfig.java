package com.macbarbos.macfood.domain.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.macbarbos.macfood.domain.service.EnvioEmailService;
import com.macbarbos.macfood.infrastructure.service.email.FakeEnvioEmailService;
import com.macbarbos.macfood.infrastructure.service.email.SmtpEnvioEmailService;

@Configuration
public class EmailConfig {

	@Autowired
	private EmailProperties emailProperties;

	@Bean
	public EnvioEmailService envioEmailService() {
		// Acho melhor usar switch aqui do que if/else if
		switch (emailProperties.getImpl()) {
			case FAKE:
				return new FakeEnvioEmailService();
			case SMTP:
				return new SmtpEnvioEmailService();
			default:
				return null;
		}
	}

}
