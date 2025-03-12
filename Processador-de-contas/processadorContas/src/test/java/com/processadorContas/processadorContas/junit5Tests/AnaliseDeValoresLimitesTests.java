package com.processadorContas.processadorContas.junit5Tests;

import com.processadorContas.processadorContas.Pagamento;
import com.processadorContas.processadorContas.ProcessadorContas;
import com.processadorContas.processadorContas.TipoPagamento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class AnaliseDeValoresLimitesTests {
    private ProcessadorContas processadorContas;
    private Date data;

    @BeforeEach
    void setUp() {
        this.processadorContas = new ProcessadorContas();
        this.data = new Date(2025, Calendar.FEBRUARY, 15);
    }

    @ParameterizedTest
    @CsvSource({
            "0.00, false",
            "0.01, true",
            "2000.00, true",
            "5000.00, true",
            "5000.01, false"
    })
    @DisplayName("Validação de pagamento para diferentes valores de Boleto")
    void testPagamentoValidoComDiferentesValores(double valor, boolean esperado) {
        Pagamento pagamento = new Pagamento(valor, this.data, TipoPagamento.BOLETO);
        Date dataFatura = this.data;

        boolean output = this.processadorContas.checaPagamentoValido(pagamento, dataFatura);

        assertEquals(output, esperado);
    }
}
