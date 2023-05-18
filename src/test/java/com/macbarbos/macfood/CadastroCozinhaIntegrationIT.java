package com.macbarbos.macfood;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.macbarbos.macfood.domain.exception.CozinhaNaoEncontradaException;
import com.macbarbos.macfood.domain.exception.EntidadeEmUsoException;
import com.macbarbos.macfood.domain.model.Cozinha;
import com.macbarbos.macfood.domain.service.CadastroCozinhaService;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroCozinhaIntegrationIT {

	/*
	 * @Autowired private CadastroCozinhaService cadastroCozinha;
	 */

	@LocalServerPort
	private int port;

	/*
	 * @Test public void testarCadastroCozinhaComSucesso() { // cenário Cozinha
	 * novaCozinha = new Cozinha(); novaCozinha.setNome("Chinesa");
	 * 
	 * // ação novaCozinha = cadastroCozinha.salvar(novaCozinha);
	 * 
	 * // validação assertThat(novaCozinha).isNotNull();
	 * assertThat(novaCozinha.getId()).isNotNull(); }
	 * 
	 * @Test public void testarCadastroCozinhaSemNome() {
	 * 
	 * ConstraintViolationException erroEsperado =
	 * Assertions.assertThrows(ConstraintViolationException.class, () -> { // Code
	 * under test Cozinha novaCozinha = new Cozinha(); novaCozinha.setNome(null);
	 * 
	 * novaCozinha = cadastroCozinha.salvar(novaCozinha); });
	 * assertThat(erroEsperado).isNotNull(); }
	 * 
	 * @Test public void deveFalhar_QuandoExcluirCozinhaEmUso() {
	 * 
	 * EntidadeEmUsoException erroEsperado =
	 * Assertions.assertThrows(EntidadeEmUsoException.class, () -> { // Code under
	 * test cadastroCozinha.excluir(1L); }); assertThat(erroEsperado).isNotNull(); }
	 * 
	 * @Test public void deveFalhar_QuandoExcluirCozinhaNaoExistente() {
	 * 
	 * CozinhaNaoEncontradaException erroEsperado =
	 * Assertions.assertThrows(CozinhaNaoEncontradaException.class, () -> {
	 * cadastroCozinha.excluir(100L); }); assertThat(erroEsperado).isNotNull(); }
	 */
	@Test
	public void deveRetornarStatus200_QuandoConsultarCozinhas() {
		//RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		given()
			.basePath("/cozinhas")
			.port(port).accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.statusCode(HttpStatus.OK.value());
	}

}
