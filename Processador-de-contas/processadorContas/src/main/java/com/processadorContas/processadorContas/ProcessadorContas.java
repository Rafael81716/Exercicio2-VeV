package com.processadorContas.processadorContas;

import java.util.Date;
import java.util.List;

public class ProcessadorContas {
    public ProcessadorContas(){

    }

    public void processarContas(Fatura fatura, List<Conta> contas){

    }

    public void validarPagamento(Double valorTotalPagar, Fatura fatura){

    }

    public boolean pagamentoValido(Pagamento pagamento, Date dataNascimento){
        return false;
    }

    public Pagamento realizarPagamento(Conta conta, Date dataPagamento){
        return null;
    }
}
