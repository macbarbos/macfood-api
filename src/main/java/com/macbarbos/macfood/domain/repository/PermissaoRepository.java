package com.macbarbos.macfood.domain.repository;

import java.util.List;

import com.macbarbos.macfood.domain.model.Permissao;

public interface PermissaoRepository {
	
	List<Permissao> listar();
	Permissao buscar(Long id);
	Permissao salvar(Permissao permissao);
	void remover(Permissao permissao);

}
