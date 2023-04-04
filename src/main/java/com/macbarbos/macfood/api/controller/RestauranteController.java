package com.macbarbos.macfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macbarbos.macfood.domain.model.Restaurante;
import com.macbarbos.macfood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@GetMapping(produces = { MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public List<Restaurante> listar(){
		return restauranteRepository.listar();
	}

}
