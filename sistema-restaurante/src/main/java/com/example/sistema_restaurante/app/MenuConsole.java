package com.example.sistema_restaurante.app;

import com.example.sistema_restaurante.cardapio.Lanche;
import com.example.sistema_restaurante.pedido.GerenciadorPedidos;
import com.example.sistema_restaurante.pedido.Pedido;

import java.util.Scanner;

public class MenuConsole {

    Scanner sc = new Scanner(System.in);
    GerenciadorPedidos gerenciador = new GerenciadorPedidos();

    public void iniciar(){
        int opcao = -1;

        while (opcao != 0){

            System.out.println("\n--- RESTAURANTE  ---");
            System.out.println("1 - Registrar novo pedido");
            System.out.println("2 - Listar pedidos do dia ");
            System.out.println("3 - Fechar restaurante ");
            System.out.println("0 - Sair ");

            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao){
                case 1:
                    novoPedido();
                    break;
                case 2:
                    listarPedidosDia();
                    break;
                case 3:
                    fecharRestaurante();
                    break;
                case 0:
                    System.out.println("Saindo....");
                    break;
                default:
                    System.out.println("Opção inválida");
            }

        }
    }

    private void novoPedido(){
        RestauranteFacade restauranteFacade = new RestauranteFacade();

        System.out.println("Nome do Cliente: ");
        String nomeCliente = sc.nextLine();

        Pedido pedido = gerenciador.criarPedido(nomeCliente);

        restauranteFacade.exibirCardapio();
        System.out.print("Escolha a opção do lanche: ");
        int opcaolanche = sc.nextInt();
        sc.nextLine();

        Lanche lanche = restauranteFacade.escolherlanche(opcaolanche);
        pedido.adicionarItem(lanche);

    }

    private void listarPedidosDia(){
        gerenciador.calcularTotalDoDia();
    }

    private void fecharRestaurante(){

    }


}

