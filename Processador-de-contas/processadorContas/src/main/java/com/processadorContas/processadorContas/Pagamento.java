package com.processadorContas.processadorContas;

import java.util.Date;

public class Pagamento {
    private Double valorPago;
    private Date dataPagamento;
    private TipoPagamento tipoPagamento;
    public Pagamento(Double valorPago, Date dataPagamento, TipoPagamento tipoPagamento){
        this.valorPago = valorPago;
        this.dataPagamento = dataPagamento;
        this.tipoPagamento = tipoPagamento;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }
}
