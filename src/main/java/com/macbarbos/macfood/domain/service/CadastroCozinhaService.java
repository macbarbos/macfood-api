package com.macbarbos.macfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.macbarbos.macfood.domain.exception.EntidadeEmUsoException;
import com.macbarbos.macfood.domain.model.Cozinha;
import com.macbarbos.macfood.domain.repository.CozinhasRepository;
import com.macbarbos.macfood.domain.service.exception.EntidadeNaoEncontradaException;

@Service
public class CadastroCozinhaService {
	
	@Autowired
	private CozinhasRepository cozinhasRepository;
	
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhasRepository.salvar(cozinha);
	}
	
	public void excluir(Long cozinhaId) {
		try {
			cozinhasRepository.remover(cozinhaId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cozinha com o código %d", cozinhaId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format("Cozinha de código %d não pode ser removida, pois está em uso", cozinhaId));
		}
	}

}
