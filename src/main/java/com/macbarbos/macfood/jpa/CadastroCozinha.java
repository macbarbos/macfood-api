package com.macbarbos.macfood.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.macbarbos.macfood.domain.model.Cozinha;

@Component
public class CadastroCozinha {
	
	@PersistenceContext
	private EntityManager manager;
	
	public List<Cozinha> listar(){
		/*
		 * TypedQuery<Cozinha> query = manager.createQuery("form Cozinha", Cozinha.class);
		 * 
		 * return query.getResultList();
		 */
		return manager.createQuery("from Cozinha", Cozinha.class).getResultList();
	}

}
