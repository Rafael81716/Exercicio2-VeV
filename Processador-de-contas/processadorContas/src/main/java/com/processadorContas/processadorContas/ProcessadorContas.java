package com.processadorContas.processadorContas;

import java.util.Date;
import java.util.List;

public class ProcessadorContas {
    public ProcessadorContas(){

    }

    public void processarContas(Fatura fatura, List<Conta> contas, Date dataProcesso){
        if(fatura.getValorTotal() < 0){
            return;
        }

        Double valorTotalPagar = 0.0;

        for(Conta conta : contas){
            Pagamento pagamento = this.realizarPagamento(conta, fatura.getData(), dataProcesso);

            if(pagamento != null){
                if(this.pagamentoValido(pagamento, fatura.getData())){
                    valorTotalPagar += pagamento.getValorPago();
                }
            }
        }
        validarPagamento(valorTotalPagar, fatura);
    }

    public void validarPagamento(Double valorTotalPagar, Fatura fatura){
        if(valorTotalPagar >= fatura.getValorTotal()){
            fatura.setStatusPagamento(StatusPagamento.PAGA);
        }else{
            fatura.setStatusPagamento(StatusPagamento.PENDENTE);
        }
    }

    public boolean pagamentoValido(Pagamento pagamento, Date dataFatura){
        if(pagamento.getTipoPagamento().equals(TipoPagamento.CARTAO_CREDITO)){
            Long diferencaTempo = Math.abs(dataFatura.getTime() - pagamento.getDataPagamento().getTime());
            Long dias = diferencaTempo / (1000 * 60 * 60 * 24);

            return dias >= 15;
        }else{
            return !pagamento.getDataPagamento().after(dataFatura);
        }
    }

    public Pagamento realizarPagamento(Conta conta, Date dataPagamento, Date dataProcesso){
        Double valorPago = conta.getValorPago();

        if(conta.getTipoPagamento().equals(TipoPagamento.BOLETO)){
            if(dataPagamento.after(conta.getData())){
                valorPago = valorPago * 1.1;
            }
        }

        return new Pagamento(valorPago, dataProcesso, conta.getTipoPagamento());
    }
}
