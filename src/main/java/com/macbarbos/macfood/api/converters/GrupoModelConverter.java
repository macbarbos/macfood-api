package com.macbarbos.macfood.api.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.macbarbos.macfood.api.MacFoodLinks;
import com.macbarbos.macfood.api.controller.GrupoController;
import com.macbarbos.macfood.api.model.GrupoModel;
import com.macbarbos.macfood.domain.model.Grupo;

@Component
public class GrupoModelConverter extends RepresentationModelAssemblerSupport<Grupo, GrupoModel> {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MacFoodLinks macFoodLinks;
	
	public GrupoModelConverter() {
		super(GrupoController.class, GrupoModel.class);
	}
	
	@Override
	public GrupoModel toModel(Grupo grupo) {
		GrupoModel grupoModel = createModelWithId(grupo.getId(), grupo);
        modelMapper.map(grupo, grupoModel);
        
        grupoModel.add(macFoodLinks.linkToGrupos("grupos"));
        
        grupoModel.add(macFoodLinks.linkToGrupoPermissoes(grupo.getId(), "permissoes"));
        
        return grupoModel;
	}
	
	@Override
    public CollectionModel<GrupoModel> toCollectionModel(Iterable<? extends Grupo> entities) {
        return super.toCollectionModel(entities)
                .add(macFoodLinks.linkToGrupos());
    }

}
