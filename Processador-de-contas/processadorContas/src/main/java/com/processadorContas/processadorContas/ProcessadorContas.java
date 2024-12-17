package com.processadorContas.processadorContas;

import java.util.Date;
import java.util.List;

public class ProcessadorContas {
    public void processarContas(Fatura fatura, List<Conta> contas, Date dataProcesso){
        if(fatura.getValorTotal() < 0){
            return;
        }

        Double valorTotalPagar = 0.0;
        for(Conta conta : contas){
            Pagamento pagamento = this.criarPagamento(conta, fatura.getData(), dataProcesso);

            if(this.checaPagamentoValido(pagamento, fatura.getData())){
                valorTotalPagar += pagamento.getValorPago();
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

    public boolean checaPagamentoValido(Pagamento pagamento, Date dataFatura){
        if(pagamento.getTipoPagamento().equals(TipoPagamento.CARTAO_CREDITO)){
            Long diferencaTempo = Math.abs(dataFatura.getTime() - pagamento.getDataPagamento().getTime());
            Long dias = diferencaTempo / (1000 * 60 * 60 * 24);

            return dias >= 15;
        }else if(pagamento.getTipoPagamento().equals(TipoPagamento.BOLETO)){
            return pagamento.getValorPago() > 0.00 && pagamento.getValorPago() <= 5000.00 && !pagamento.getDataPagamento().after(dataFatura);
        }else{
            return !pagamento.getDataPagamento().after(dataFatura);
        }
    }

    public Pagamento criarPagamento(Conta conta, Date dataPagamento, Date dataProcesso){
        Double valorPago = conta.getValorPago();

        if(conta.getTipoPagamento().equals(TipoPagamento.BOLETO) && conta.getValorPago() > 0.00 && conta.getValorPago() <= 5000.00){
            if(dataPagamento.after(conta.getData())){
                valorPago = valorPago * 1.1;
            }
        }

        return new Pagamento(valorPago, dataProcesso, conta.getTipoPagamento());
    }
}
