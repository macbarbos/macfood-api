package com.macbarbos.macfood.api.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
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

import com.macbarbos.macfood.api.converters.FormaPagamentoConverter;
import com.macbarbos.macfood.api.converters.FormaPagamentoModelConverter;
import com.macbarbos.macfood.api.model.FormaPagamentoModel;
import com.macbarbos.macfood.api.model.input.FormaPagamentoInput;
import com.macbarbos.macfood.domain.model.FormaPagamento;
import com.macbarbos.macfood.domain.repository.FormaPagamentoRepository;
import com.macbarbos.macfood.domain.service.CadastroFormaPagamentoService;

@RestController
@RequestMapping("/formas-pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;
    
    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamento;
    
    @Autowired
    private FormaPagamentoModelConverter formaPagamentoModelConverter;
    
    @Autowired
    private FormaPagamentoConverter formaPagamentoConverter;
    
//    @GetMapping
//    public List<FormaPagamentoModel> listar() {
//        List<FormaPagamento> todasFormasPagamentos = formaPagamentoRepository.findAll();
//        
//        return formaPagamentoModelConverter.toCollectionModel(todasFormasPagamentos);
//    }
    
    @GetMapping
	public ResponseEntity<List<FormaPagamentoModel>> listar() {
		List<FormaPagamento> todasFormasPagamentos = formaPagamentoRepository.findAll();
		
		List<FormaPagamentoModel> formasPagamentosModel = formaPagamentoModelConverter
				.toCollectionModel(todasFormasPagamentos);
		
		return ResponseEntity.ok()
				.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
				.body(formasPagamentosModel);
	}
    
//    @GetMapping("/{formaPagamentoId}")
//    public FormaPagamentoModel buscar(@PathVariable Long formaPagamentoId) {
//        FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);
//        
//        return formaPagamentoModelConverter.toModel(formaPagamento);
//    }
    
    @GetMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoModel> buscar(@PathVariable Long formaPagamentoId) {
        FormaPagamento formaPagamento = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);
        
        FormaPagamentoModel formaPagamentoModel = formaPagamentoModelConverter.toModel(formaPagamento);
        
        return ResponseEntity.ok()
        		.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
 //       		.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePrivate()) //somente no browser
 //       		.cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic()) //já vem por padrão quando não usamos
 //       		.cacheControl(CacheControl.noCache()) //não quer dizer que não tem cache mas sim que precisa validar o chache, ou seja, precisa do ETags
//        		.cacheControl(CacheControl.noStore()) //Não permite armazenamento de cache, nem localmente
        		.body(formaPagamentoModel);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FormaPagamentoModel adicionar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento = formaPagamentoConverter.toDomainObject(formaPagamentoInput);
        
        formaPagamento = cadastroFormaPagamento.salvar(formaPagamento);
        
        return formaPagamentoModelConverter.toModel(formaPagamento);
    }
    
    @PutMapping("/{formaPagamentoId}")
    public FormaPagamentoModel atualizar(@PathVariable Long formaPagamentoId,
            @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamentoAtual = cadastroFormaPagamento.buscarOuFalhar(formaPagamentoId);
        
        formaPagamentoConverter.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);
        
        formaPagamentoAtual = cadastroFormaPagamento.salvar(formaPagamentoAtual);
        
        return formaPagamentoModelConverter.toModel(formaPagamentoAtual);
    }
    
    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long formaPagamentoId) {
        cadastroFormaPagamento.excluir(formaPagamentoId);	
    }   
}
