package com.macbarbos.macfood.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.macbarbos.macfood.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

	Optional<Usuario> findByEmail(String e);
	
}
