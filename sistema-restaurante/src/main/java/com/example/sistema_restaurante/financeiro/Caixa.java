package com.example.sistema_restaurante.financeiro;

import java.util.HashSet;
import java.util.Set;

public class Caixa {
    private static final Caixa instancia = new Caixa();

    private final Set<Transacao> transacoes;

    private Caixa() {
        this.transacoes = new HashSet<>();
    }

    public static Caixa getInstancia() {
        return instancia;
    }

    public boolean registrarPagamento(Transacao transacao) {
        if (transacao == null) {
            throw new IllegalArgumentException("Transação inválida!");
        }

        return transacoes.add(transacao);
    }

    public boolean pedidoEstaPago(int numeroPedido) {
        for (Transacao transacao : transacoes) {
            if (transacao.getNumeroPedido() == numeroPedido) {
                return true;
            }
        }

        return false;
    }

    public double calcularTotalVendas() {
        double total = 0;

        for (Transacao transacao : transacoes) {
            total += transacao.getValor();
        }

        return total;
    }

    public double calcularTotalGorjetas() {
        double total = 0;

        for (Transacao transacao : transacoes) {
            total += transacao.getGorjeta();
        }

        return total;
    }

    public double calcularTotalCaixa() {
        return calcularTotalVendas() + calcularTotalGorjetas();
    }
}
