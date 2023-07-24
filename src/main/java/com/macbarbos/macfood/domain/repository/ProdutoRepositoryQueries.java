package com.macbarbos.macfood.domain.repository;

import com.macbarbos.macfood.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {

	FotoProduto save(FotoProduto foto);
	
	void delete(FotoProduto foto);
	
}
