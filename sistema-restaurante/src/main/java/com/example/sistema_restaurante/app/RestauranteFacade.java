package com.example.sistema_restaurante.app;

import com.example.sistema_restaurante.cardapio.ItemCardapio;
import com.example.sistema_restaurante.cardapio.Lanche;
import com.example.sistema_restaurante.cardapio.LancheFactory;
import com.example.sistema_restaurante.pedido.GerenciadorPedidos;
import com.example.sistema_restaurante.pedido.Pedido;

import java.util.Collection;


public class RestauranteFacade {
    private GerenciadorPedidos gerenciador = new GerenciadorPedidos();
    private LancheFactory lancheFactory = new LancheFactory();



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

    public double calcularTotalDoDia(){
        return gerenciador.calcularTotalDoDia();
    }
}
