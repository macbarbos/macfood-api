package com.macbarbos.macfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.macbarbos.macfood.domain.model.Cozinha;
import com.macbarbos.macfood.domain.repository.CozinhasRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhasRepository {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Cozinha> listar(){
		
		return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}
	
	@Override
	public Cozinha buscar(Long id) {
		return manager.find(Cozinha.class, id);
	}
	
	@Transactional
	@Override
	public Cozinha salvar(Cozinha cozinha) {
		return manager.merge(cozinha);
		
	}
	
	@Transactional
	@Override
	public void remover(Cozinha cozinha) {
		cozinha = buscar(cozinha.getId());
		manager.remove(cozinha);
	}

}
