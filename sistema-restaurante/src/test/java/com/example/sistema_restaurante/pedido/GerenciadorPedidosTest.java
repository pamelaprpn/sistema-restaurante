package com.example.sistema_restaurante.pedido;

import com.example.sistema_restaurante.cardapio.Lanche;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class GerenciadorPedidosTest {
    @Test
    void deveCriarPedido() {
        GerenciadorPedidos gerenciador = new GerenciadorPedidos();

        Pedido pedido = gerenciador.criarPedido("Maria");

        assertNotNull(pedido);
        assertEquals("Maria", pedido.getNomeCliente());
        assertEquals(1, gerenciador.getPedidos().size());
    }

    @Test
    void deveBuscarPedidoPeloNumero() {
        GerenciadorPedidos gerenciador = new GerenciadorPedidos();
        Pedido pedido = gerenciador.criarPedido("João");

        Pedido encontrado = gerenciador.buscarPedido(pedido.getNumero());

        assertSame(pedido, encontrado);
    }

    @Test
    void deveCalcularTotalDoDia() {
        GerenciadorPedidos gerenciador = new GerenciadorPedidos();

        Pedido pedido1 = gerenciador.criarPedido("Maria");
        pedido1.adicionarItem(new Lanche("X-Tudo", 25.00));

        Pedido pedido2 = gerenciador.criarPedido("João");
        pedido2.adicionarItem(new Lanche("X-Salada", 18.00));

        double total = gerenciador.calcularTotalDoDia();

        assertEquals(43.00, total, 0.001);
    }

    @Test
    void deveRetornarZeroQuandoNaoExistemPedidos() {
        GerenciadorPedidos gerenciador = new GerenciadorPedidos();

        double total = gerenciador.calcularTotalDoDia();

        assertEquals(0.00, total, 0.001);
    }

//    @Test
//    void deveCancelarPedidoERemoverDoTotal() {
//        GerenciadorPedidos gerenciador = new GerenciadorPedidos();
//
//        Pedido pedido = gerenciador.criarPedido("Maria");
//        pedido.adicionarItem(new Lanche("X-Tudo", 25.00));
//
//        gerenciador.cancelarPedido(pedido.getNumero());
//
//        assertEquals(0, gerenciador.getPedidos().size());
//        assertEquals(0.00, gerenciador.calcularTotalDoDia(), 0.001);
//    }
}
