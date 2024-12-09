package com.processadorContas.processadorContas;

import java.util.Date;

public class Conta {
    private String codigo;
    private Date data;
    private Double valorPago;
    public Conta(String codigo, Date data, Double valorPago){
        this.codigo = codigo;
        this.data = data;
        this.valorPago = valorPago;
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
}
