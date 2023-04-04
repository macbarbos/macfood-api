package com.macbarbos.macfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.macbarbos.macfood.domain.model.Cozinha;
import com.macbarbos.macfood.domain.repository.CozinhasRepository;

@RestController
@RequestMapping(value = "/cozinhas") //, produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {

	@Autowired
	private CozinhasRepository cozinhaRepository;
	
	@GetMapping
	public List<Cozinha> lista() {
		return cozinhaRepository.listar();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping("/{cozinhaId}")
	public Cozinha buscar(@PathVariable("cozinhaId") Long id) {
		System.out.println(id);
		return cozinhaRepository.buscar(id);
	}
	
}