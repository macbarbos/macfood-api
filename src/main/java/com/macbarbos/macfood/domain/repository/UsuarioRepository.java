package com.macbarbos.macfood.domain.repository;

import org.springframework.stereotype.Repository;

import com.macbarbos.macfood.domain.model.Usuario;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<Usuario, Long> {

}
