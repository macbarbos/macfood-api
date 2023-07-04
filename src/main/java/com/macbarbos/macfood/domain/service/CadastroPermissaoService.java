package com.macbarbos.macfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macbarbos.macfood.domain.exception.PermissaoNaoEncontradaException;
import com.macbarbos.macfood.domain.model.Permissao;
import com.macbarbos.macfood.domain.repository.PermissaoRepository;

@Service
public class CadastroPermissaoService {

	@Autowired
    private PermissaoRepository permissaoRepository;
    
    public Permissao buscarOuFalhar(Long permissaoId) {
        return permissaoRepository.findById(permissaoId)
            .orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
    }
	
}
