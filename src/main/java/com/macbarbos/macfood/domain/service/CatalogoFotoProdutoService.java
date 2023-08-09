package com.macbarbos.macfood.domain.service;

import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.macbarbos.macfood.domain.exception.FotoProdutoNaoEncontradaException;
import com.macbarbos.macfood.domain.model.FotoProduto;
import com.macbarbos.macfood.domain.repository.ProdutoRepository;
import com.macbarbos.macfood.domain.service.FotoStorageService.NovaFoto;

@Service
public class CatalogoFotoProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FotoStorageService fotoStorage;
	
	@Transactional
	public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo) {
		Long restauranteId = foto.getRestauranteId();
		Long produtoId = foto.getProduto().getId();
		String nomeNovoArquivo = fotoStorage.gerarNomeArquivo(foto.getNomeArquivo());
		String nomeArquivoExistente = null;
		
		Optional<FotoProduto> fotoExistente = produtoRepository
				.findFotoById(restauranteId, produtoId);
		
		if (fotoExistente.isPresent()) {
			nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
			produtoRepository.delete(fotoExistente.get());
		}
		
		foto.setNomeArquivo(nomeNovoArquivo);
		foto =  produtoRepository.save(foto);
		produtoRepository.flush();
		
		NovaFoto novaFoto = NovaFoto.builder()
				.nomeAquivo(foto.getNomeArquivo())
				.inputStream(dadosArquivo)
				.build();
				
		//fotoStorage.armazenar(novaFoto);
		fotoStorage.substituir(nomeArquivoExistente, novaFoto);
		
		return foto;
	}
	
	public FotoProduto buscarOuFalhar(Long restaurantId, Long produtoId) {
		return produtoRepository.findFotoById(restaurantId, produtoId)
				.orElseThrow(() -> new FotoProdutoNaoEncontradaException(restaurantId, produtoId));
	}
	
	@Transactional
	public void excluir(Long restauranteId, Long produtoId) {
	    FotoProduto foto = buscarOuFalhar(restauranteId, produtoId);
	    
	    produtoRepository.delete(foto);
	    produtoRepository.flush();

	    fotoStorage.remover(foto.getNomeArquivo());
	}
	
}