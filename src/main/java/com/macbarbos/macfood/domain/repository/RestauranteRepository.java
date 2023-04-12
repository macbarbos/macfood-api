package com.macbarbos.macfood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.macbarbos.macfood.domain.model.Restaurante;

public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {
	
	

}
