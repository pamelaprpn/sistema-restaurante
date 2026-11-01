package com.example.sistema_restaurante.app;

import com.example.sistema_restaurante.cardapio.BaconExtra;
import com.example.sistema_restaurante.cardapio.CheddarExtra;
import com.example.sistema_restaurante.cardapio.ItemCardapio;
import com.example.sistema_restaurante.cardapio.Lanche;
import com.example.sistema_restaurante.pedido.GerenciadorPedidos;
import com.example.sistema_restaurante.pedido.Pedido;

import java.util.Scanner;

public class MenuConsole {

    Scanner sc = new Scanner(System.in);
    RestauranteFacade restauranteFacade = new RestauranteFacade();

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
        enum Resposta {S, N}
        Resposta continuar = Resposta.S;

        System.out.println("Nome do Cliente: ");
        String nomeCliente = sc.nextLine();

        Pedido pedido = restauranteFacade.criarPedido(nomeCliente);

        while (continuar == Resposta.S){
            ItemCardapio lanche = null;

            while (lanche == null){
                restauranteFacade.exibirCardapio();

                System.out.print("Escolha a opção do lanche: ");
                int opcaolanche = sc.nextInt();
                sc.nextLine();

                try{
                    lanche = restauranteFacade.escolherlanche(opcaolanche);
                }catch(IllegalArgumentException e){
                    System.out.println(e.getMessage() + " Escolha uma opção válida!");
                }

            }

            int opcaoExtra = -1;

            while (opcaoExtra != 0) {
                System.out.println("\n--- ADICIONAIS ---");
                System.out.println("1 - Bacon extra R$ 6,00");
                System.out.println("2 - Cheddar extra R$ 4,00");
                System.out.println("3 - Molho extra R$ 3,00");
                System.out.println("0 - Não desejo adicional");

                System.out.print("Escolha uma opção: ");
                opcaoExtra = sc.nextInt();
                sc.nextLine();

                if (opcaoExtra != 0) {
                    try {
                        lanche = restauranteFacade.adicionarExtra(lanche, opcaoExtra);
                        System.out.println("Adicional incluído!");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            pedido.adicionarItem(lanche);

            System.out.print("Deseja adicionar outro lanche? (S/N): ");
            String entrada = sc.nextLine().toUpperCase();

            try {
                continuar = Resposta.valueOf(entrada);
            }catch (IllegalArgumentException e){
                System.out.println("Resposta inválida! Digite S ou N.");
            }

        }

    }

    private void listarPedidosDia(){

        System.out.println("\n--- LISTA DE PEDIDOS NO DIA ---");
        System.out.println("Quantidade de pedidos: " + restauranteFacade.getPedidos().size());

        for (Pedido pedido : restauranteFacade.getPedidos().stream().toList()) {
            System.out.println("\n--- Pedido: "+ pedido.getNumero() + " ---");
            System.out.println("Nome do cliente: " + pedido.getNomeCliente());
            System.out.println("Quantidade de lanches: " + pedido.getItens().size());
            System.out.println("\nLanches: ");
            for(ItemCardapio itemCardapio : pedido.getItens()){
                System.out.println("- " + itemCardapio.getDescricao() + " R$ " + itemCardapio.getPreco());
            }
            System.out.println("\nTotal do pedido: " + pedido.calcularTotal());

        }
    }

    private void fecharRestaurante(){
        System.out.println("\n--- FECHAMENTO DO RESTAURANTE ---");
        System.out.println("Total de Pedidos: " + restauranteFacade.getPedidos().size());
        System.out.printf("Faturamento do dia: R$ %.2f%n", restauranteFacade.calcularTotalDoDia());
        System.out.println("\n--- FECHAMENTO CONCLUÍDO ---");

    }

}

