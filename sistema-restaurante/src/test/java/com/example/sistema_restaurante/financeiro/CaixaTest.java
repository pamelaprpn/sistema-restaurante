package com.example.sistema_restaurante.financeiro;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CaixaTest {

    @Test
    void deveRetornarMesmaInstancia() {
        Caixa primeira = Caixa.getInstancia();
        Caixa segunda = Caixa.getInstancia();

        assertSame(primeira, segunda);
    }

    @Test
    void deveRegistrarPagamento() {
        Caixa caixa = Caixa.getInstancia();
        Transacao transacao = new Transacao(1001, 50.0, 5.0);

        boolean registrado = caixa.registrarPagamento(transacao);

        assertTrue(registrado);
        assertTrue(caixa.pedidoEstaPago(1001));
    }

    @Test
    void naoDeveRegistrarPagamentoDuplicado() {
        Caixa caixa = Caixa.getInstancia();

        Transacao primeira = new Transacao(1002, 50.0, 5.0);
        Transacao segunda = new Transacao(1002, 80.0, 10.0);

        assertTrue(caixa.registrarPagamento(primeira));
        assertFalse(caixa.registrarPagamento(segunda));
    }

    @Test
    void naoDeveAceitarTransacaoNula() {
        Caixa caixa = Caixa.getInstancia();

        assertThrows(IllegalArgumentException.class,
                () -> caixa.registrarPagamento(null));
    }
}
