package com.macbarbos.macfood.infrastructure.service.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import com.macbarbos.macfood.core.storage.StorageProperties;
import com.macbarbos.macfood.domain.service.FotoStorageService;

public class LocalFotoStorageService implements FotoStorageService {

	@Autowired
	private StorageProperties localFotoStorageService;
	
	@Override
	public void armazenar(NovaFoto novaFoto) {
		try {
			Path arquivoPath = getArquivoPath(novaFoto.getNomeAquivo());
			
			FileCopyUtils.copy(novaFoto.getInputStream(), 
					Files.newOutputStream(arquivoPath));
		} catch (Exception e) {
			throw new StorageException("Não foi possível armazenar arquivo.", e);
		}
	}
	
	private Path getArquivoPath(String nomeArquivo) {
		return localFotoStorageService.getLocal().getDiretorioFotos().resolve(Path.of(nomeArquivo));
	}

	@Override
	public void remover(String nomeArquivo) {
		try {
			Path arquivoPath = getArquivoPath(nomeArquivo);
			
			Files.deleteIfExists(arquivoPath);
		} catch (Exception e) {
			throw new StorageException("Não foi possível excluir arquivo.", e);
		}
		
	}

	@Override
	public FotoRecuperada recuperar(String nomeArquivo) {
		try {
			Path arquivoPath = getArquivoPath(nomeArquivo);
			
			FotoRecuperada fotoRecuperada = FotoRecuperada.builder()
					.inputStream(Files.newInputStream(arquivoPath))
					.build();
			
			return fotoRecuperada;
		} catch (IOException e) {
			throw new StorageException("Não foi possível recuperar arquivo", e);
		}
	}

}