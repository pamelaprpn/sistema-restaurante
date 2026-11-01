package com.example.sistema_restaurante.pedido;

import com.example.sistema_restaurante.cardapio.ItemCardapio;
import com.example.sistema_restaurante.cardapio.Lanche;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoTest {
    @Test
    void deveAdicionarItemAoPedido() {
        Pedido pedido = new Pedido("Maria", 1);
        ItemCardapio lanche = new Lanche("X-Salada", 18.00);


        pedido.adicionarItem(lanche);


        assertEquals(1, pedido.getItens().size());
        assertSame(lanche, pedido.getItens().get(0));
    }

    @Test
    void deveCalcularTotalDoPedido() {

        Pedido pedido = new Pedido("Maria", 1);

        ItemCardapio lanche1 = new Lanche("X-Tudo", 25.00);
        ItemCardapio lanche2 = new Lanche("X-Salada", 18.00);

        pedido.adicionarItem(lanche1);
        pedido.adicionarItem(lanche2);


        double total = pedido.calcularTotal();

        assertEquals(43.00, total, 0.001);
    }
}
