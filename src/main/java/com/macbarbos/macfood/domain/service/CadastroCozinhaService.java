package com.macbarbos.macfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.macbarbos.macfood.domain.exception.CozinhaNaoEncontradaException;
import com.macbarbos.macfood.domain.exception.EntidadeEmUsoException;
import com.macbarbos.macfood.domain.model.Cozinha;
import com.macbarbos.macfood.domain.repository.CozinhasRepository;

@Service
public class CadastroCozinhaService {

	private static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";

	@Autowired
	private CozinhasRepository cozinhasRepository;

	public Cozinha salvar(Cozinha cozinha) {
		return cozinhasRepository.save(cozinha);
	}

	public void excluir(Long cozinhaId) {
		try {
			cozinhasRepository.deleteById(cozinhaId);
		} catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(cozinhaId);

		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_COZINHA_EM_USO, cozinhaId));
		}
	}

	public Cozinha buscarOuFalhar(Long cozinhaId) {
		return cozinhasRepository.findById(cozinhaId).orElseThrow(
				() -> new CozinhaNaoEncontradaException(cozinhaId));
	}

}
