package com.macbarbos.macfood.domain.repository;

import java.util.List;

import com.macbarbos.macfood.domain.model.Cozinha;

public interface CozinhasRepository {
	
	List<Cozinha> listar();
	Cozinha buscar(Long id);
	Cozinha salvar(Cozinha cozinha);
	void remover(Cozinha cozinha);

}
