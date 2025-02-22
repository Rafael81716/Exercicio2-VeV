package com.processadorContas.processadorContas.functionalTests;

import com.processadorContas.processadorContas.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AnaliseDeValoresLimitesTests {
    private ProcessadorContas processadorContas;

    @BeforeEach
    void setUp(){
        this.processadorContas = new ProcessadorContas();
    }

    @Test
    void shouldReturnFalseIfValueOfBoletoIsZero(){
        Pagamento pagamento = new Pagamento(0.00, new Date(2025, Calendar.FEBRUARY, 15), TipoPagamento.BOLETO);
        Date dataFatura = new Date(2025, Calendar.FEBRUARY, 15);

        Boolean output = this.processadorContas.checaPagamentoValido(pagamento, dataFatura);

        assertFalse(output);
    }

    @Test
    void shouldReturnTrueIfValueOfBoletoIsEqualToOneCent(){
        Pagamento pagamento = new Pagamento(0.01, new Date(2025, Calendar.FEBRUARY, 15), TipoPagamento.BOLETO);
        Date dataFatura = new Date(2025, Calendar.FEBRUARY, 15);

        Boolean output = this.processadorContas.checaPagamentoValido(pagamento, dataFatura);

        assertTrue(output);
    }

    @Test
    void shouldReturnTrueIfValueOfBoletoIsEqualToTwoThousand(){
        Pagamento pagamento = new Pagamento(2000.00, new Date(2025, Calendar.FEBRUARY, 15), TipoPagamento.BOLETO);
        Date dataFatura = new Date(2025, Calendar.FEBRUARY, 15);

        Boolean output = this.processadorContas.checaPagamentoValido(pagamento, dataFatura);

        assertTrue(output);
    }

    @Test
    void shouldReturnTrueIfValueOfBoletoIsEqualToFiveThousand(){
        Pagamento pagamento = new Pagamento(5000.00, new Date(2025, Calendar.FEBRUARY, 15), TipoPagamento.BOLETO);
        Date dataFatura = new Date(2025, Calendar.FEBRUARY, 15);

        Boolean output = this.processadorContas.checaPagamentoValido(pagamento, dataFatura);

        assertTrue(output);
    }

    @Test
    void shouldReturnFalseIfValueOfBoletoIsHigherThan5K(){
        Pagamento pagamento = new Pagamento(5000.01, new Date(2025, Calendar.FEBRUARY, 15), TipoPagamento.BOLETO);
        Date dataFatura = new Date(2025, Calendar.FEBRUARY, 15);

        Boolean output = this.processadorContas.checaPagamentoValido(pagamento, dataFatura);

        assertFalse(output);
    }
}
