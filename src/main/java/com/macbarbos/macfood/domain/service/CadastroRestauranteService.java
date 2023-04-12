package com.macbarbos.macfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.macbarbos.macfood.domain.exception.EntidadeEmUsoException;
import com.macbarbos.macfood.domain.model.Cozinha;
import com.macbarbos.macfood.domain.model.Restaurante;
import com.macbarbos.macfood.domain.repository.CozinhasRepository;
import com.macbarbos.macfood.domain.repository.RestauranteRepository;
import com.macbarbos.macfood.domain.service.exception.EntidadeNaoEncontradaException;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhasRepository cozinhaRepository;

	public Restaurante salvar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		/*
		 * Optional<Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);
		 * 
		 * if (cozinha.isEmpty()) { throw new EntidadeNaoEncontradaException(
		 * String.format("Não existe cadastro de cozinha com código %d", cozinhaId)); }
		 * 
		 * restaurante.setCozinha(cozinha.get());
		 * 
		 * return restauranteRepository.salvar(restaurante);
		 */
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException(
						String.format("Não existe cadastro de cozinha com código %d", cozinhaId)));
			
			restaurante.setCozinha(cozinha);
			
			return restauranteRepository.save(restaurante);
	}

	public void excluir(Long restauranteId) {
		try {
			restauranteRepository.deleteById(restauranteId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de restaurante com código %d", restauranteId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Restaurante de código %d não pode ser removido, pois está em uso", restauranteId));
		}
	}

}
