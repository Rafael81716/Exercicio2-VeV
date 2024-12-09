package com.processadorContas.processadorContas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class PagamentoTests {
    private ProcessadorContas processadorContas;

    @BeforeEach
    void setUp() {
        processadorContas = new ProcessadorContas();
    }

    @Test
    void ShouldIncreaseValueIfPaymentIsWithBoletoAndIsExpired() {
        // Arrange
        Conta conta = new Conta("001", new Date(2024, Calendar.DECEMBER, 24), 100.00, TipoPagamento.BOLETO);

        // Act
        Pagamento pagamento = processadorContas.realizarPagamento(conta, new Date(2024, Calendar.DECEMBER, 25));

        // Assert
        assertNotNull(pagamento);

        Double valorEsperado = 110.00;
        Double valorPago = pagamento.getValorPago();
        double margemErro = 0.01;

        assertAll(
                () -> assertEquals(valorEsperado, valorPago, margemErro),
                () -> assertEquals(TipoPagamento.BOLETO, pagamento.getTipoPagamento())
        );

    }

    @Test
    void ShouldNotIncreaseValueIfPaymentIsWithBoletoAndIsNotExpired() {
        // Arrange
        Date dataVencimento = new Date(2024, Calendar.DECEMBER, 24);
        Conta conta = new Conta("001", dataVencimento, 100.00, TipoPagamento.BOLETO);

        // Act
        Pagamento pagamento = processadorContas.realizarPagamento(conta, dataVencimento);

        // Assert
        assertNotNull(pagamento);
        double valorEsperado = 100.00;
        double margemErro = 0.01;

        assertEquals(valorEsperado, pagamento.getValorPago(), margemErro);
        assertEquals(TipoPagamento.BOLETO, pagamento.getTipoPagamento());
    }

    @Test
    void shouldDoPaymentAsNormalWithCartaoDeCredito() {
        // Arrange
        Date dataVencimento =  new Date(2024, Calendar.DECEMBER, 24);
        Conta conta = new Conta("001", dataVencimento, 100.00, TipoPagamento.CARTAO_CREDITO);

        // Act
        Pagamento pagamento = processadorContas.realizarPagamento(conta, dataVencimento);

        // Assert
        assertNotNull(pagamento);
        double valorEsperado = 100.00;
        double margemErro = 0.01;
        assertEquals(valorEsperado, pagamento.getValorPago(), margemErro);
        assertEquals(TipoPagamento.CARTAO_CREDITO, pagamento.getTipoPagamento());
    }

    @Test
    void shouldDoPaymentAsNormalWithTransferenciaBancaria() {
        // Arrange
        Date dataVencimento =  new Date(2024, Calendar.DECEMBER, 24);
        Conta conta = new Conta("001", dataVencimento, 100.00, TipoPagamento.TRANSFERENCIA_BANCARIA);

        // Act
        Pagamento pagamento = processadorContas.realizarPagamento(conta, dataVencimento);

        // Assert
        assertNotNull(pagamento);
        double valorEsperado = 100.00;
        double margemErro = 0.01;
        assertEquals(valorEsperado, pagamento.getValorPago(), margemErro);
        assertEquals(TipoPagamento.TRANSFERENCIA_BANCARIA, pagamento.getTipoPagamento());
    }

    @Test
    public void shouldDoPaymentAsNormalWithCredictCard() {
        // Arrange

        // Act
        Date dataPagamento =  new Date(2024, Calendar.DECEMBER, 9); // 15 dias antes
        Date vencimentoFatura =  new Date(2024, Calendar.DECEMBER, 24);
        Pagamento pagamento = new Pagamento(100.0, dataPagamento, TipoPagamento.CARTAO_CREDITO);

        boolean resultado = processadorContas.pagamentoValido(pagamento, vencimentoFatura);

        // Assert
        assertTrue(resultado);
    }

    @Test
    public void shouldNotIncludeInPaymentIfCartaoDeCreditoHasNot15DaysPriorPayment() {
        // Arrange
        ProcessadorContas processador = new ProcessadorContas();

        // Act
        Date dataPagamento = new Date(2024, Calendar.DECEMBER, 10); // 14 dias antes
        Date vencimentoFatura = new Date(2024, Calendar.DECEMBER, 24);
        Pagamento pagamento = new Pagamento(100.0, dataPagamento, TipoPagamento.CARTAO_CREDITO);

        boolean resultado = processador.pagamentoValido(pagamento, vencimentoFatura);

        // Assert
        assertFalse(resultado);
    }

    @Test
    public void shouldReturnTrueIfPaymentIfBoletoIsPaidOnTime() {
        // Arrange
        ProcessadorContas processador = new ProcessadorContas();

        Date dataPagamento = new Date(2024, Calendar.DECEMBER, 10);
        Date vencimentoFatura = new Date(2024, Calendar.DECEMBER, 11); // 1 dia depois
        Pagamento pagamento = new Pagamento(100.0, dataPagamento, TipoPagamento.BOLETO);

        // Act
        boolean resultado = processador.pagamentoValido(pagamento, vencimentoFatura);

        // Assert
        assertTrue(resultado);
    }

    @Test
    public void shouldReturnFalseIfPaymentIfBoletoIsNotPaidOnTime() {
        // Arrange
        ProcessadorContas processador = new ProcessadorContas();

        Date dataPagamento = new Date(2024, Calendar.DECEMBER, 11); // 1 dia depois
        Date vencimentoFatura = new Date(2024, Calendar.DECEMBER, 10);
        Pagamento pagamento = new Pagamento(100.0, dataPagamento, TipoPagamento.BOLETO);

        // Act
        boolean resultado = processador.pagamentoValido(pagamento, vencimentoFatura);

        // Assert
        assertFalse(resultado);
    }
}
