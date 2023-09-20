package com.macbarbos.macfood.api.v1.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.macbarbos.macfood.api.v1.MacFoodLinks;
import com.macbarbos.macfood.api.v1.controller.UsuarioController;
import com.macbarbos.macfood.api.v1.model.UsuarioModel;
import com.macbarbos.macfood.domain.model.Usuario;

@Component
public class UsuarioModelConverter 
		extends RepresentationModelAssemblerSupport<Usuario, UsuarioModel> {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private MacFoodLinks macFoodLinks;
	
	public UsuarioModelConverter() {
		super(UsuarioController.class, UsuarioModel.class);
	}
	
	@Override
	public UsuarioModel toModel(Usuario usuario) {
		UsuarioModel usuarioModel = createModelWithId(usuario.getId(), usuario);
		modelMapper.map(usuario, usuarioModel);
		
		usuarioModel.add(macFoodLinks.linkToUsuarios("usuarios"));
		
		usuarioModel.add(macFoodLinks.linkToGruposUsuario(usuario.getId(), "grupos-usuario"));
		
		return usuarioModel;
	}
	
	@Override
	public CollectionModel<UsuarioModel> toCollectionModel(Iterable<? extends Usuario> entities) {
		return super.toCollectionModel(entities)
			.add(macFoodLinks.linkToUsuarios());
	}
	
}
