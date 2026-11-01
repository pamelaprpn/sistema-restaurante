package com.example.sistema_restaurante.app;

import com.example.sistema_restaurante.cardapio.ItemCardapio;
import com.example.sistema_restaurante.cardapio.Lanche;
import com.example.sistema_restaurante.pedido.Pedido;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RestauranteFacadeTest {
    @Test
    void deveCriarPedido() {
        RestauranteFacade facade = new RestauranteFacade();

        Pedido pedido = facade.criarPedido("Maria");

        assertNotNull(pedido);
        assertEquals("Maria", pedido.getNomeCliente());
        assertEquals(1, facade.getPedidos().size());
    }

    @Test
    void deveEscolherLancheDoCardapio() {
        RestauranteFacade facade = new RestauranteFacade();

        ItemCardapio lanche = facade.escolherlanche(1);

        assertEquals("X-Tudo", lanche.getDescricao());
        assertEquals(25.00, lanche.getPreco(), 0.001);
    }

    @Test
    void deveAdicionarExtraAoLanche() {
        RestauranteFacade facade = new RestauranteFacade();
        ItemCardapio lanche = new Lanche("X-Salada", 18.00);

        ItemCardapio resultado = facade.adicionarExtra(lanche, 1);

        assertEquals(24.00, resultado.getPreco(), 0.001);
        assertTrue(resultado.getDescricao().contains("Bacon"));
    }

    @Test
    void deveCalcularTotalDoDia() {
        RestauranteFacade facade = new RestauranteFacade();

        Pedido pedido1 = facade.criarPedido("Maria");
        pedido1.adicionarItem(new Lanche("X-Tudo", 25.00));

        Pedido pedido2 = facade.criarPedido("João");
        pedido2.adicionarItem(new Lanche("X-Salada", 18.00));

        double total = facade.calcularTotalDoDia();

        assertEquals(43.00, total, 0.001);
    }
}
