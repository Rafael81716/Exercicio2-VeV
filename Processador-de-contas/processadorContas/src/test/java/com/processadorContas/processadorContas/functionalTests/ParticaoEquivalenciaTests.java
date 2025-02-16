package com.processadorContas.processadorContas.functionalTests;


import com.processadorContas.processadorContas.*;
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
public class ParticaoEquivalenciaTests {
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
        this.fatura = new Fatura(new Date(2025, Calendar.FEBRUARY, 16), 990.00, "Rafael");
        // Act
        this.processadorContas.processarContas(fatura, contas, new Date(2025, Calendar.FEBRUARY, 15));

        // Assert
        assertEquals(StatusPagamento.PAGA, fatura.getStatusPagamento());
    }

    @Test
    void shouldProcessContasOfAFaturaWithOtherPaymentTypes(){
        // Arrange
        Conta conta1 = new Conta("001", new Date(2025, Calendar.FEBRUARY, 14), 500.00, TipoPagamento.TRANSFERENCIA_BANCARIA);
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
        this.fatura = new Fatura(new Date(2025, Calendar.FEBRUARY, 16), 900.00, "Rafael");
        // Act
        this.processadorContas.processarContas(fatura, contas, new Date(2025, Calendar.FEBRUARY, 15));

        // Assert
        assertEquals(StatusPagamento.PENDENTE, fatura.getStatusPagamento());
    }

    @Test
    void shouldNotProcessBoletoWithValueOver5k(){
        // Arrange
        Conta conta1 = new Conta("001", new Date(2025, Calendar.FEBRUARY, 15), 6000.00, TipoPagamento.BOLETO);
        this.contas.add(conta1);
        this.fatura = new Fatura(new Date(2025, Calendar.FEBRUARY, 15), 6000.00, "Rafael");
        // Act
        this.processadorContas.processarContas(fatura, contas, new Date(2025, Calendar.FEBRUARY, 15));

        // Assert
        assertEquals(StatusPagamento.PENDENTE, fatura.getStatusPagamento());
    }
}