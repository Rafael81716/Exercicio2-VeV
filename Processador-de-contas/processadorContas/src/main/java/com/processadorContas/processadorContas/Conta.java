package com.processadorContas.processadorContas;

import java.util.Date;

public class Conta {
    private String codigo;
    private Date data;
    private Double valorPago;
    private TipoPagamento tipoPagamento;
    public Conta(String codigo, Date data, Double valorPago, TipoPagamento tipoPagamento){
        this.codigo = codigo;
        this.data = data;
        this.valorPago = valorPago;
        this.tipoPagamento = tipoPagamento;
    }

    public Date getData() {
        return data;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public String getCodigo() {
        return codigo;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }
}
