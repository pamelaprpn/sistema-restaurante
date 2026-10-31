package com.example.sistema_restaurante.app;


import com.example.sistema_restaurante.cardapio.Lanche;
import com.example.sistema_restaurante.pedido.GerenciadorPedidos;
import com.example.sistema_restaurante.pedido.Pedido;

import java.util.Scanner;

public class Main {
    static void main() {

        MenuConsole menu = new MenuConsole();
        menu.iniciar();

//        GerenciadorPedidos gerenciadorPedidos = new GerenciadorPedidos();
//
//        Pedido pedido1 = gerenciadorPedidos.criarPedido("Maria");
//        Pedido pedido2 = gerenciadorPedidos.criarPedido("João");
//        Pedido pedido3 = gerenciadorPedidos.criarPedido("Julia");
//
//        pedido1.adicionarItem(new Lanche("X-Burger", 20.50));
//        pedido1.adicionarItem(new Lanche("Batata", 18.50));
//
//        pedido2.adicionarItem(new Lanche("X-Salada", 24.50));
//        pedido2.adicionarItem(new Lanche("Refrigerante", 10.50));
//
//        pedido3.adicionarItem(new Lanche("X-Burguer", 20.50));
//
//        System.out.println("Total do dia: " +
//                gerenciadorPedidos.calcularTotalDoDia());
//
//        System.out.println("*---------*");
//        System.out.println(gerenciadorPedidos.buscarPedido(2));


    }
//
//    private void novoPedido(){
//
//        RestauranteFacade restauranteFacade = new RestauranteFacade();
//
//        System.out.println("Nome do Cliente: ");
//        String nomeCliente = sc.nextLine();
//
//        restauranteFacade.exibirCardapio();
//        System.out.print("Escolha a opção do lanche: ");
//        int opcaolanche = sc.nextInt();
//        sc.nextLine();
//
//
//        System.out.println("Nome do Cliente: ");
//        String nomeCliente = sc.nextLine();
//        Pedido pedido = gerenciador.criarPedido(nomeCliente);
//
//        System.out.println("Nome do lanche: ");
//        String nomeLanche = sc.nextLine();
//
//        System.out.println("Preço do lanche: ");
//        double precoLanche = sc.nextDouble();
//
//        Lanche lanche = new Lanche(nomeLanche, precoLanche);
//        pedido.adicionarItem(lanche);
//
//
//
//    }
//
//    private void listarPedidosDia(){
//        gerenciador.calcularTotalDoDia();
//    }
}
