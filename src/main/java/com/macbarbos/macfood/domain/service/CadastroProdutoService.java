package com.macbarbos.macfood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macbarbos.macfood.domain.exception.ProdutoNaoEncontradoException;
import com.macbarbos.macfood.domain.model.Produto;
import com.macbarbos.macfood.domain.repository.ProdutoRepository;

@Service
public class CadastroProdutoService {
	
	@Autowired
    private ProdutoRepository produtoRepository;
    
    @Transactional
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }
    
    public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
        return produtoRepository.findById(restauranteId, produtoId)
            .orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
    }   

}
