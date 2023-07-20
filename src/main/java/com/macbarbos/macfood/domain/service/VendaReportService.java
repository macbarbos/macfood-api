package com.macbarbos.macfood.domain.service;

import com.macbarbos.macfood.domain.filter.VendaDiariaFilter;

public interface VendaReportService {

	byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
}