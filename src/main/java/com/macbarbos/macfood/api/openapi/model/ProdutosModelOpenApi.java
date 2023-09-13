package com.macbarbos.macfood.api.openapi.model;

import java.util.List;

import org.springframework.hateoas.Links;

import com.macbarbos.macfood.api.model.ProdutoModel;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("ProdutosModel")
@Data
public class ProdutosModelOpenApi {

    private ProdutosEmbeddedModelOpenApi _embedded;
    private Links _links;
    
    @ApiModel("ProdutosEmbeddedModel")
    @Data
    public class ProdutosEmbeddedModelOpenApi {
        
        private List<ProdutoModel> produtos;
        
    }    
}
