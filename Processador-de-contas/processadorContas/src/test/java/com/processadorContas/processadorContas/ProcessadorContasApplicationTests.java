package com.processadorContas.processadorContas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProcessadorContasTests {

	private ProcessadorContas processadorContas;
	private List<Conta> contas;
	private Fatura fatura;

	@Test
	void shouldInitializeFaturaCorrectly(){
		Fatura fatura = new Fatura(new Date(2024, Calendar.DECEMBER, 24), 100.00, "Rafael");

		assertAll(
				() -> assertEquals(fatura.getData(), new Date(2024, Calendar.DECEMBER, 24)),
				() -> assertEquals(fatura.getValorTotal(), 100.00),
				() -> assertEquals(fatura.getNomeCliente(), "Rafael")
		);
	}

	@Test
	void shouldInitializeContaCorrectly(){
		Conta conta = new Conta("999", new Date(2024, Calendar.DECEMBER, 29), 500.00, TipoPagamento.CARTAO_CREDITO);


		assertAll(
			() -> assertEquals(conta.getCodigo(), "999"),
			() -> assertEquals(conta.getData(), new Date(2024, Calendar.DECEMBER, 29)),
			() -> assertEquals(conta.getValorPago(), 500.00),
			() -> assertEquals(conta.getTipoPagamento(), TipoPagamento.CARTAO_CREDITO)
		);
	}

	@BeforeEach
	void setUp(){
		this.processadorContas = new ProcessadorContas();
		this.contas = new ArrayList<>();
		this.contas.add(new Conta("001", new Date(2024, Calendar.DECEMBER, 9), 100.00, TipoPagamento.CARTAO_CREDITO));
		this.fatura = new Fatura(new Date(2024, Calendar.DECEMBER, 9), 100.00, "Rafael");
	}

	@Test
	void shouldProcessContasOfAFaturaWithStatusPaga(){
		// Arrange
		// Act
		this.processadorContas.processarContas(fatura, contas, new Date(2024, Calendar.DECEMBER, 9));

		// Assert
		assertEquals(this.fatura.getStatusPagamento(), StatusPagamento.PAGA);
	}
	@Test
	void shouldProcessContasOfAFaturaWithStatusPendente(){
		// Arrange
		this.fatura = new Fatura(new Date(2024, Calendar.DECEMBER, 30), 500.00, "Ingrid");

		// Act
		this.processadorContas.processarContas(fatura, contas, new Date(2024, Calendar.DECEMBER, 9));

		// Assert
		assertEquals(this.fatura.getStatusPagamento(), StatusPagamento.PENDENTE);
	}

	@Test
	void shouldProcessContaBefore15DaysWithCreditCard(){
		// Arrange
		this.contas = new ArrayList<>();
		this.contas.add(new Conta("001", new Date(2024, Calendar.NOVEMBER, 1), 100.00, TipoPagamento.CARTAO_CREDITO));

		// Act
		this.processadorContas.processarContas(fatura, contas, new Date(2024, Calendar.DECEMBER, 9));

		// Assert
		assertEquals(StatusPagamento.PAGA, fatura.getStatusPagamento());

	}
	@Test
	void shouldReturnPendenteIfContaIsPaidNotBefore15DaysWithCreditCard(){
		// Arrange
		this.contas = new ArrayList<>();
		this.contas.add(new Conta("001", new Date(2024, Calendar.DECEMBER, 1), 100.00, TipoPagamento.CARTAO_CREDITO));

		// Act
		this.processadorContas.processarContas(fatura, contas, new Date(2024, Calendar.DECEMBER, 9));

		// Assert
		assertEquals(StatusPagamento.PENDENTE, fatura.getStatusPagamento());

	}

}
