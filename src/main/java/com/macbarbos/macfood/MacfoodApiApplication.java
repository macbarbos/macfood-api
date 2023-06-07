package com.macbarbos.macfood;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MacfoodApiApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(MacfoodApiApplication.class, args);
	}

}
