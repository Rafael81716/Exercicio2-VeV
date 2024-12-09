package com.processadorContas.processadorContas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ProcessadorContasTests {

	private ProcessadorContas processadorContas;
	private List<Conta> contas;
	private Fatura fatura;

	@BeforeEach
	void setUp(){
		this.processadorContas = new ProcessadorContas();
		this.contas = new ArrayList<>();
		this.fatura = new Fatura();
	}
}
