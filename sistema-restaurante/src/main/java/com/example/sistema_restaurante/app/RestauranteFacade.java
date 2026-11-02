package com.example.sistema_restaurante.app;

import com.example.sistema_restaurante.cardapio.ItemCardapio;
import com.example.sistema_restaurante.cardapio.Lanche;
import com.example.sistema_restaurante.cardapio.LancheFactory;
import com.example.sistema_restaurante.pedido.GerenciadorPedidos;
import com.example.sistema_restaurante.pedido.Pedido;

import com.example.sistema_restaurante.financeiro.Caixa;
import com.example.sistema_restaurante.financeiro.Transacao;

import java.util.Collection;


public class RestauranteFacade {
    private GerenciadorPedidos gerenciador = new GerenciadorPedidos();
    private LancheFactory lancheFactory = new LancheFactory();

    private final Caixa caixa = Caixa.getInstancia();

    public Pedido criarPedido(String nomeCliente){
        return gerenciador.criarPedido(nomeCliente);
    }


    public void exibirCardapio(){
        System.out.println("\n--- CARDÁPIO ---");
        for(int i=1; i<=lancheFactory.quantidadeLanches(); i++){
            Lanche lanche = lancheFactory.criarLanche(i);

            System.out.println(i + " - " + lanche.getDescricao() + " R$ " + lanche.getPreco());
        }
    }

    public ItemCardapio adicionarExtra(ItemCardapio item, int opcao) {
        return lancheFactory.adicionarExtra(item, opcao);
    }

    public Lanche escolherlanche(int opcao){
      return lancheFactory.criarLanche(opcao);
    }

    public Collection<Pedido> getPedidos(){
        return gerenciador.getPedidos();
    }

    public Pedido buscarPedido(int numero){
        return gerenciador.buscarPedido(numero);
    }


    public void cancelarPedido(int numero){
        gerenciador.cancelarPedido(numero);
    }


    public double calcularTotalDoDia(){
        return gerenciador.calcularTotalDoDia();
    }

    public Lanche personalizarLanche(Lanche lanche, int opcao) {
        return lancheFactory.personalizarLanche(lanche, opcao);
    }

    public boolean pedidoEstaPago(int numeroPedido) {
        return caixa.pedidoEstaPago(numeroPedido);
    }

    public boolean pagarPedido(int numeroPedido, double gorjeta) {
        Pedido pedido = gerenciador.buscarPedido(numeroPedido);

        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado!");
        }

        if (gorjeta < 0) {
            throw new IllegalArgumentException("Gorjeta inválida!");
        }

        if (pedido.getItens().isEmpty()) {
            throw new IllegalArgumentException("Não é possível pagar um pedido vazio!");
        }

        if (caixa.pedidoEstaPago(numeroPedido)) {
            return false;
        }

        Transacao transacao = new Transacao(
                numeroPedido,
                pedido.calcularTotal(),
                gorjeta
        );

        return caixa.registrarPagamento(transacao);
    }

    public double calcularTotalVendasPagas() {
        return caixa.calcularTotalVendas();
    }

    public double calcularTotalGorjetas() {
        return caixa.calcularTotalGorjetas();
    }

    public double calcularTotalCaixa() {
        return caixa.calcularTotalCaixa();
    }

    public double calcularTotalPendente() {
        return gerenciador.calcularTotalDoDia()
                - caixa.calcularTotalVendas();
    }
}
