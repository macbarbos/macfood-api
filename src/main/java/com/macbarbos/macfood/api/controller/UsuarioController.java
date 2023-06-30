package com.macbarbos.macfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.macbarbos.macfood.api.converters.UsuarioConverter;
import com.macbarbos.macfood.api.converters.UsuarioModelConverter;
import com.macbarbos.macfood.api.model.UsuarioModel;
import com.macbarbos.macfood.api.model.input.SenhaInput;
import com.macbarbos.macfood.api.model.input.UsuarioComSenhaInput;
import com.macbarbos.macfood.api.model.input.UsuarioInput;
import com.macbarbos.macfood.domain.model.Usuario;
import com.macbarbos.macfood.domain.repository.UsuarioRepository;
import com.macbarbos.macfood.domain.service.CadastroUsuarioService;


@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private CadastroUsuarioService cadastroUsuario;
    
    @Autowired
    private UsuarioModelConverter usuarioModelConverter;
    
    @Autowired
    private UsuarioConverter usuarioConverter;
    
    @GetMapping
    public List<UsuarioModel> listar() {
        List<Usuario> todasUsuarios = usuarioRepository.findAll();
        
        return usuarioModelConverter.toCollectionModel(todasUsuarios);
    }
    
    @GetMapping("/{usuarioId}")
    public UsuarioModel buscar(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuario.buscarOuFalhar(usuarioId);
        
        return usuarioModelConverter.toModel(usuario);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioModel adicionar(@RequestBody @Valid UsuarioComSenhaInput usuarioInput) {
        Usuario usuario = usuarioConverter.toDomainObject(usuarioInput);
        usuario = cadastroUsuario.salvar(usuario);
        
        return usuarioModelConverter.toModel(usuario);
    }
    
    @PutMapping("/{usuarioId}")
    public UsuarioModel atualizar(@PathVariable Long usuarioId,
            @RequestBody @Valid UsuarioInput usuarioInput) {
        Usuario usuarioAtual = cadastroUsuario.buscarOuFalhar(usuarioId);
        usuarioConverter.copyToDomainObject(usuarioInput, usuarioAtual);
        usuarioAtual = cadastroUsuario.salvar(usuarioAtual);
        
        return usuarioModelConverter.toModel(usuarioAtual);
    }
    
    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInput senha) {
        cadastroUsuario.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }         
    
    @DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long usuarioId) {
    	cadastroUsuario.excluir(usuarioId);
	}
}
