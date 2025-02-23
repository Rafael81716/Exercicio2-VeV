package com.processadorContas.processadorContas.functionalTests;

import com.processadorContas.processadorContas.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TabelaDecisaoTests {

    @Nested
    class TestsIfProcessadorDeContasIsMadeSuccessfuly{
        private ProcessadorContas processadorContas;
        private List<Conta> contas;
        private Fatura fatura;

        @BeforeEach
        void setUp(){
            this.processadorContas = new ProcessadorContas();
            this.contas = new ArrayList<>();
        }

        @Test
        void shouldProcessContasOfAFaturaWithStatusPaga(){
            // Arrange
            Conta conta1 = new Conta("001", new Date(2025, Calendar.FEBRUARY, 15), 500.00, TipoPagamento.BOLETO);
            Conta conta2 = new Conta("002", new Date(2025, Calendar.FEBRUARY, 15), 400.00, TipoPagamento.BOLETO);
            this.contas.add(conta1);
            this.contas.add(conta2);
            this.fatura = new Fatura(new Date(2025, Calendar.FEBRUARY, 15), 900.00, "Rafael");
            // Act
            this.processadorContas.processarContas(fatura, contas, new Date(2025, Calendar.FEBRUARY, 15));

            // Assert
            assertEquals(StatusPagamento.PAGA, fatura.getStatusPagamento());
        }
        @Test
        void shouldProcessContasOfAFaturaWithStatusPagaWithDeleyedPaymentWithBoleto(){
            // Arrange
            Conta conta1 = new Conta("001", new Date(2025, Calendar.FEBRUARY, 15), 500.00, TipoPagamento.BOLETO);
            Conta conta2 = new Conta("002", new Date(2025, Calendar.FEBRUARY, 15), 400.00, TipoPagamento.BOLETO);
            this.contas.add(conta1);
            this.contas.add(conta2);
            this.fatura = new Fatura(new Date(2025, Calendar.FEBRUARY, 14), 990.00, "Rafael");
            // Act
            this.processadorContas.processarContas(fatura, contas, new Date(2025, Calendar.FEBRUARY, 15));

            // Assert
            assertEquals(StatusPagamento.PAGA, fatura.getStatusPagamento());
        }

        @Test
        void shouldProcessContasOfAFaturaWithOtherPaymentTypes(){
            // Arrange
            Conta conta1 = new Conta("001", new Date(2025, Calendar.JANUARY, 1), 500.00, TipoPagamento.TRANSFERENCIA_BANCARIA);
            Conta conta2 = new Conta("002", new Date(2025, Calendar.JANUARY, 1), 1100.00, TipoPagamento.CARTAO_CREDITO);
            this.contas.add(conta1);
            this.contas.add(conta2);
            this.fatura = new Fatura(new Date(2025, Calendar.FEBRUARY, 14), 1600.00, "Rafael");
            // Act
            this.processadorContas.processarContas(fatura, contas, new Date(2025, Calendar.FEBRUARY, 15));

            // Assert
            assertEquals(StatusPagamento.PAGA, fatura.getStatusPagamento());
        }

        @Test
        void shouldProcessContasOfAFaturaButReturnPendingCauseValueIsNotMatched(){
            // Arrange
            Conta conta1 = new Conta("001", new Date(2025, Calendar.FEBRUARY, 14), 500.00, TipoPagamento.TRANSFERENCIA_BANCARIA);
            Conta conta2 = new Conta("002", new Date(2025, Calendar.JANUARY, 1), 500.00, TipoPagamento.CARTAO_CREDITO);
            Conta conta3 = new Conta("003", new Date(2025, Calendar.FEBRUARY, 14), 50.00, TipoPagamento.BOLETO);
            this.contas.add(conta1);
            this.contas.add(conta2);
            this.contas.add(conta3);
            this.fatura = new Fatura(new Date(2025, Calendar.FEBRUARY, 14), 1600.00, "Rafael");
            // Act
            this.processadorContas.processarContas(fatura, contas, new Date(2025, Calendar.FEBRUARY, 15));

            // Assert
            assertEquals(StatusPagamento.PENDENTE, fatura.getStatusPagamento());
        }
        @Test
        void shouldProcessContasOfAFaturaWithPendenteStatusWithCreditCardBefore15Days(){
            // Arrange
            Conta conta1 = new Conta("001", new Date(2025, Calendar.FEBRUARY, 14), 500.00, TipoPagamento.TRANSFERENCIA_BANCARIA);
            Conta conta2 = new Conta("002", new Date(2025, Calendar.FEBRUARY, 14), 1100.00, TipoPagamento.CARTAO_CREDITO);
            this.contas.add(conta1);
            this.contas.add(conta2);
            this.fatura = new Fatura(new Date(2025, Calendar.FEBRUARY, 14), 1600.00, "Rafael");
            // Act
            this.processadorContas.processarContas(fatura, contas, new Date(2025, Calendar.FEBRUARY, 15));

            // Assert
            assertEquals(StatusPagamento.PENDENTE, fatura.getStatusPagamento());
        }

        @Test
        void shouldProcessContasOfAFaturaWithStatusPagaWithDeleyedPaymentWithBoletoWithout10Porcent(){
            // Arrange
            Conta conta1 = new Conta("001", new Date(2025, Calendar.FEBRUARY, 15), 500.00, TipoPagamento.BOLETO);
            Conta conta2 = new Conta("002", new Date(2025, Calendar.FEBRUARY, 15), 400.00, TipoPagamento.BOLETO);
            this.contas.add(conta1);
            this.contas.add(conta2);
            this.fatura = new Fatura(new Date(2025, Calendar.FEBRUARY, 14), 900.00, "Rafael");
            // Act
            this.processadorContas.processarContas(fatura, contas, new Date(2025, Calendar.FEBRUARY, 15));

            // Assert
            assertEquals(StatusPagamento.PENDENTE, fatura.getStatusPagamento());
        }
    }

    @Nested
    class TestsForBoletoIncreasePayment{
        private ProcessadorContas processadorContas;
        private Conta conta;
        private Date dataPagamento;
        private Date dataProcesso;

        @BeforeEach
        void setUp(){
            this.processadorContas = new ProcessadorContas();
            this.conta = new Conta("001", new Date(2025, Calendar.FEBRUARY, 15), 900.00, TipoPagamento.BOLETO);
        }

        @Test
        void shouldNotIncreasePaymentIfBoletoIsPaidOnTime(){
            this.dataPagamento = new Date(2025, Calendar.FEBRUARY, 15);
            this.dataProcesso = new Date(2025, Calendar.FEBRUARY, 15);

            Pagamento pagamento = this.processadorContas.criarPagamento(conta, dataPagamento, dataProcesso);

            assertEquals(pagamento.getValorPago(), 900.00);
        }

        @Test
        void shouldIncreasePaymentIfBoletoIsNotPaidOnTime(){
            this.dataPagamento = new Date(2025, Calendar.FEBRUARY, 14);
            this.dataProcesso = new Date(2025, Calendar.FEBRUARY, 14);

            Pagamento pagamento = this.processadorContas.criarPagamento(conta, dataPagamento, dataProcesso);

            assertEquals(pagamento.getValorPago(), 990.00, 0.01);
        }
    }

    @Nested
    class TestsForCartaoDeCreditoProcess{
        private ProcessadorContas processadorContas;

        @BeforeEach
        void setUp(){
            this.processadorContas = new ProcessadorContas();
        }

        @Test
        void shouldReturnTrueIfDateOfCartaoDeCreditoIsPaidBefore15Days(){
            Pagamento pagamento = new Pagamento(100.00, new Date(2025, Calendar.FEBRUARY, 05), TipoPagamento.CARTAO_CREDITO);
            Date dataFatura = new Date(2025, Calendar.FEBRUARY, 20);

            Boolean output = this.processadorContas.checaPagamentoValido(pagamento, dataFatura);

            assertTrue(output);
        }
        @Test
        void shouldReturnFalseIfDateOfCartaoDeCreditoIsNotPaidBefore15Days(){
            Pagamento pagamento = new Pagamento(1000.00, new Date(2025, Calendar.FEBRUARY, 06), TipoPagamento.CARTAO_CREDITO);
            Date dataFatura = new Date(2025, Calendar.FEBRUARY, 20);

            Boolean output = this.processadorContas.checaPagamentoValido(pagamento, dataFatura);

            assertFalse(output);
        }
    }
}
