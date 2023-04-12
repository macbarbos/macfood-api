package com.macbarbos.macfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.macbarbos.macfood.domain.model.Cozinha;

@Repository
public interface CozinhasRepository extends JpaRepository<Cozinha, Long> {
	
	
}
