package com.processadorContas.processadorContas;

import java.util.Date;

public class Fatura {
    private Date data;
    private Double valorTotal;
    private String nomeCliente;
    private StatusPagamento statusPagamento;
    public Fatura(Date data, Double valorTotal, String nomeCliente){
        this.data = data;
        this.valorTotal = valorTotal;
        this.nomeCliente = nomeCliente;
        this.statusPagamento = StatusPagamento.PENDENTE;
    }

    public Date getData() {
        return data;
    }
    public Double getValorTotal() {
        return valorTotal;
    }
    public String getNomeCliente() {
        return nomeCliente;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = statusPagamento;
    }
}
