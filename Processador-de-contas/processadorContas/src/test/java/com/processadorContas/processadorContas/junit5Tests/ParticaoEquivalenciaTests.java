package com.processadorContas.processadorContas.junit5Tests;

import com.processadorContas.processadorContas.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ParticaoEquivalenciaTests {
    private ProcessadorContas processadorContas;
    private List<Conta> contas;
    private Fatura fatura;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    void setUp() {
        this.processadorContas = new ProcessadorContas();
        this.contas = new ArrayList<>();
    }

    @ParameterizedTest
    @CsvSource({
            "500.00, 2025-02-15, BOLETO, 400.00, 2025-02-15, BOLETO, 900.00, 2025-02-15, PAGA",
            "500.00, 2025-02-15, BOLETO, 400.00, 2025-02-15, BOLETO, 990.00, 2025-02-16, PAGA",
            "500.00, 2025-02-14, TRANSFERENCIA_BANCARIA, 1100.00, 2025-01-01, CARTAO_CREDITO, 1600.00, 2025-02-14, PAGA",
            "500.00, 2025-02-15, TRANSFERENCIA_BANCARIA, 1100.00, 2025-02-15, CARTAO_CREDITO, 1600.00, 2025-02-14, PENDENTE",
            "500.00, 2025-02-15, BOLETO, 400.00, 2025-02-15, BOLETO, 990.00, 2025-02-16, PENDENTE"
    })
    @DisplayName("Tests for processarContas with differents accounts and values and dates")
    void testProcessamentoDeContas(double valor1, String data1, String tipo1,
                                   double valor2, String data2, String tipo2,
                                   double valorFatura, String dataFatura, StatusPagamento esperado) throws ParseException {
        Date parsedData1 = dateFormat.parse(data1);
        Date parsedData2 = dateFormat.parse(data2);
        Date parsedDataFatura = dateFormat.parse(dataFatura);

        Conta conta1 = new Conta("001", parsedData1, valor1, TipoPagamento.valueOf(tipo1));
        Conta conta2 = new Conta("002", parsedData2, valor2, TipoPagamento.valueOf(tipo2));
        this.contas.add(conta1);
        this.contas.add(conta2);
        this.fatura = new Fatura(parsedDataFatura, valorFatura, "Rafael");

        this.processadorContas.processarContas(fatura, contas, parsedDataFatura);

        assertEquals(esperado, fatura.getStatusPagamento());
    }
}
