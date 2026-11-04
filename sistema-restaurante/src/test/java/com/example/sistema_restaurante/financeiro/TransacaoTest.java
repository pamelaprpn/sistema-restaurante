package com.example.sistema_restaurante.financeiro;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TransacaoTest {
    @Test
    void deveCalcularTotalPagoComGorjeta() {
        Transacao transacao = new Transacao(1, 50.0, 5.0);

        double total = transacao.getTotalPago();

        assertEquals(55.0, total);
    }

    @Test
    void naoDeveAceitarNumeroPedidoInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> new Transacao(0, 50.0, 5.0));
    }

    @Test
    void naoDeveAceitarValorNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> new Transacao(1, -50.0, 5.0));
    }

    @Test
    void naoDeveAceitarGorjetaNegativa() {
        assertThrows(IllegalArgumentException.class,
                () -> new Transacao(1, 50.0, -5.0));
    }

    @Test
    void transacoesComMesmoNumeroPedidoDevemSerIguais() {
        Transacao primeira = new Transacao(1, 50.0, 5.0);
        Transacao segunda = new Transacao(1, 80.0, 10.0);

        assertEquals(primeira, segunda);
        assertEquals(primeira.hashCode(), segunda.hashCode());
    }

    @Test
    void transacoesComNumerosDiferentesNaoDevemSerIguais() {
        Transacao primeira = new Transacao(1, 50.0, 5.0);
        Transacao segunda = new Transacao(2, 50.0, 5.0);

        assertNotEquals(primeira, segunda);
    }
}
