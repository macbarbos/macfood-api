package com.macbarbos.macfood.domain.service;

import java.util.List;

import com.macbarbos.macfood.api.v1.model.dto.VendaDiaria;
import com.macbarbos.macfood.domain.filter.VendaDiariaFilter;

public interface VendaQueryService {

	List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
	
}
