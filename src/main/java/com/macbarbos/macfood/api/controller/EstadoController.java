package com.macbarbos.macfood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.macbarbos.macfood.domain.exception.EntidadeEmUsoException;
import com.macbarbos.macfood.domain.model.Estado;
import com.macbarbos.macfood.domain.repository.EstadoRepository;
import com.macbarbos.macfood.domain.service.CadastroEstadoService;
import com.macbarbos.macfood.domain.service.exception.EntidadeNaoEncontradaException;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroEstadoService cadastroEstado;

	@GetMapping
	public List<Estado> listar() {
		return estadoRepository.findAll();
	}

	@GetMapping("/{estadoId}")
	public ResponseEntity<?> buscar(@PathVariable Long estadoId) {
		Optional<Estado> estado = estadoRepository.findById(estadoId);

		if (estado.isPresent()) {
			return ResponseEntity.ok(estado);
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> adicionar(@RequestBody Estado estado) {
		estado = cadastroEstado.salvar(estado);

		return ResponseEntity.ok(estado);
	}

	@PutMapping("/{estadoId}")
	public ResponseEntity<Estado> atualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
		Estado estadoAtual = estadoRepository.findById(estadoId).orElse(null);

		if (estadoAtual != null) {
			BeanUtils.copyProperties(estado, estadoAtual, "id");

			estadoAtual = cadastroEstado.salvar(estadoAtual);
			return ResponseEntity.ok(estadoAtual);
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{estadoId}")
	public ResponseEntity<?> remover(@PathVariable Long estadoId) {
		try {
			cadastroEstado.excluir(estadoId);
			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}

}