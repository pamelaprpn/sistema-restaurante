package com.example.sistema_restaurante.financeiro;

import java.util.Objects;

public class Transacao {

    private final int numeroPedido;
    private final double valor;
    private final double gorjeta;

    public Transacao(int numeroPedido, double valor, double gorjeta) {

        if (numeroPedido <= 0) {
            throw new IllegalArgumentException("Número do pedido inválido!");
        }

        if (valor < 0 || gorjeta < 0) {
            throw new IllegalArgumentException("Valor ou gorjeta inválidos!");
        }

        this.numeroPedido = numeroPedido;
        this.valor = valor;
        this.gorjeta = gorjeta;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public double getValor() {
        return valor;
    }

    public double getGorjeta() {
        return gorjeta;
    }

    public double getTotalPago() {
        return valor + gorjeta;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (!(obj instanceof Transacao outra)) return false;

        return numeroPedido == outra.numeroPedido;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroPedido);
    }
}
