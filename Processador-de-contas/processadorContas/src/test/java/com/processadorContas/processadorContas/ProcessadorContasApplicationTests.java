package com.processadorContas.processadorContas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProcessadorContasTests {

	private ProcessadorContas processadorContas;
	private List<Conta> contas;
	private Fatura fatura;

	@Test
	void shouldInitializeFaturaCorrectly(){
		Fatura fatura = new Fatura(new Date(2024, 07, 24), 100.00, "Rafael");

		assertEquals(fatura.getData(), new Date(2024, 07, 24));
		assertEquals(fatura.getValorTotal(), 100.00);
		assertEquals(fatura.getNomeCliente(), "Rafael");
	}

	@BeforeEach
	void setUp(){
		this.processadorContas = new ProcessadorContas();
		this.contas = new ArrayList<>();
		this.fatura = new Fatura();
	}
}
