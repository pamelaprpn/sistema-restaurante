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
            System.out.println("3 - Modificar pedido ");
            System.out.println("4 - Pagar pedido");
            System.out.println("5 - Fechar restaurante ");
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
                    modificarPedido();
                    break;
                case 4:
                    pagarPedido();
                    break;
                case 5:
                    if (fecharRestaurante()) {
                        return;
                    }
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

        Pedido pedido;

        try {
            pedido = restauranteFacade.criarPedido(nomeCliente);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        while (continuar == Resposta.S) {
            Lanche lanche = null;

            while (lanche == null) {
                restauranteFacade.exibirCardapio();

                System.out.print("Escolha a opção do lanche: ");

                try {
                    int opcaoLanche = Integer.parseInt(sc.nextLine());
                    lanche = restauranteFacade.escolherlanche(opcaoLanche);
                } catch (NumberFormatException e) {
                    System.out.println("Digite um número válido!");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage() + " Escolha uma opção válida!");
                }
            }

            lanche = personalizarLanche(lanche);

            ItemCardapio item = lanche;

            int opcaoExtra = -1;

            while (opcaoExtra != 0) {
                System.out.println("\n--- ADICIONAIS ---");
                System.out.println("1 - Bacon extra R$ 6,00");
                System.out.println("2 - Cheddar extra R$ 4,00");
                System.out.println("3 - Molho extra R$ 3,00");
                System.out.println("0 - Não desejo adicional");

                System.out.print("Escolha uma opção: ");

                try {
                    opcaoExtra = Integer.parseInt(sc.nextLine());

                    if (opcaoExtra != 0) {
                        item = restauranteFacade.adicionarExtra(item, opcaoExtra);
                        System.out.println("Adicional incluído!");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Digite um número válido!");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }

            pedido.adicionarItem(item);

            System.out.println("Item adicionado: " + item.getDescricao());
            System.out.printf("Preço: R$ %.2f%n", item.getPreco());

            while (true) {
                System.out.print("Deseja adicionar outro lanche? (S/N): ");
                String entrada = sc.nextLine().trim().toUpperCase();

                try {
                    continuar = Resposta.valueOf(entrada);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Resposta inválida! Digite S ou N.");
                }
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

    private void modificarPedido(){
        System.out.println("Digite o numero do pedido que quer modificar: ");
        int nPedido;

        try {
            nPedido = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Digite um número válido!");
            return;
        }

        Pedido pedido = restauranteFacade.buscarPedido(nPedido);

        if (pedido == null) {
            System.out.println("Pedido não encontrado!");
            return;
        }

        if (restauranteFacade.pedidoEstaPago(nPedido)) {
            System.out.println("Este pedido já foi pago e não pode ser modificado!");
            return;
        }

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- MODIFICAR PEDIDO ---");
            System.out.println("Pedido nº " + pedido.getNumero());
            System.out.println("Cliente: " + pedido.getNomeCliente());

            System.out.println("1 - Acrescentar item");
            System.out.println("2 - Remover item");
            System.out.println("3 - Cancelar pedido");
            System.out.println("0 - Voltar");
            System.out.println("\nEscolha uma operação: ");

            try {
                opcao = Integer.parseInt(sc.nextLine());

                switch (opcao) {
                    case 1 -> acrescentarItem(pedido);
                    case 2 -> {
                        System.out.println("Itens do pedido:");

                        for (int i = 0; i < pedido.getItens().size(); i++) {
                            System.out.println((i + 1) + " - " + pedido.getItens().get(i).getDescricao());
                        }

                        System.out.println("Digite o número do item que deseja remover:");

                        try {
                            int numeroItem = Integer.parseInt(sc.nextLine());

                            pedido.removerItem(numeroItem);

                            System.out.println("Item removido com sucesso!");

                        } catch (NumberFormatException e) {
                            System.out.println("Digite um número válido!");
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    case 3 -> {
                        restauranteFacade.cancelarPedido(nPedido);
                        System.out.println("Pedido cancelado!");
                        return;
                    }

                    case 0 -> System.out.println("Voltando ao menu principal...");

                    default -> System.out.println("Opção inválida!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }



    }

    private void pagarPedido(){
        System.out.print("Digite o número do pedido que deseja pagar: ");

        try {
            int numero = Integer.parseInt(sc.nextLine().trim());

            Pedido pedido = restauranteFacade.buscarPedido(numero);

            if (pedido == null) {
                System.out.println("Pedido não encontrado!");
                return;
            }

            if (restauranteFacade.pedidoEstaPago(numero)) {
                System.out.println("Este pedido já foi pago!");
                return;
            }

            System.out.println("\n--- PAGAMENTO ---");
            System.out.println("Pedido nº " + pedido.getNumero());
            System.out.println("Cliente: " + pedido.getNomeCliente());
            System.out.printf("Total: R$ %.2f%n", pedido.calcularTotal());

            System.out.print("Deseja adicionar gorjeta? (S/N): ");
            String resposta = sc.nextLine().trim();

            double gorjeta = 0;

            if (resposta.equalsIgnoreCase("S")) {
                System.out.print("Digite o valor da gorjeta: R$ ");
                gorjeta = Double.parseDouble(
                        sc.nextLine().trim().replace(",", ".")
                );
            } else if (!resposta.equalsIgnoreCase("N")) {
                System.out.println("Resposta inválida! Pagamento não realizado.");
                return;
            }

            boolean registrado = restauranteFacade.pagarPedido(numero, gorjeta);

            if (registrado) {
                System.out.println("\nPagamento realizado com sucesso!");
                System.out.printf(
                        "Total pago: R$ %.2f%n",
                        pedido.calcularTotal() + gorjeta
                );
            } else {
                System.out.println("Este pedido já foi pago!");
            }

        } catch (NumberFormatException e) {
            System.out.println("Digite um valor numérico válido!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void exibirFechamento(){
        System.out.println("\n===== FECHAMENTO DO RESTAURANTE =====");

        System.out.printf(
                "Total dos pedidos: R$ %.2f%n",
                restauranteFacade.calcularTotalDoDia()
        );

        System.out.printf(
                "Total de vendas pagas: R$ %.2f%n",
                restauranteFacade.calcularTotalVendasPagas()
        );

        System.out.printf(
                "Total pendente: R$ %.2f%n",
                restauranteFacade.calcularTotalPendente()
        );

        System.out.printf(
                "Total de gorjetas: R$ %.2f%n",
                restauranteFacade.calcularTotalGorjetas()
        );

        System.out.println("------------------------------------");

        System.out.printf(
                "Total recebido no caixa: R$ %.2f%n",
                restauranteFacade.calcularTotalCaixa()
        );
    }


    private Lanche personalizarLanche(Lanche lanche) {
        int opcao = -1;

        System.out.println("\n--- PERSONALIZAR LANCHE ---");

        while (opcao != 0) {
            System.out.println("Deseja retirar algum ingrediente?");
            System.out.println("1 - Queijo");
            System.out.println("2 - Alface");
            System.out.println("3 - Tomate");
            System.out.println("4 - Cebola");
            System.out.println("0 - Não desejo retirar");

            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(sc.nextLine().trim());

                if (opcao != 0) {
                    lanche = restauranteFacade.personalizarLanche(lanche, opcao);
                    System.out.println("Ingrediente removido!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Lanche personalizado: " + lanche.getDescricao());

        return lanche;
    }

    private void acrescentarItem(Pedido pedido) {
        restauranteFacade.exibirCardapio();

        System.out.print("Escolha o número do lanche: ");

        try {
            int opcao = Integer.parseInt(sc.nextLine());

            Lanche lanche = restauranteFacade.escolherlanche(opcao);

            lanche = personalizarLanche(lanche);

            pedido.adicionarItem(lanche);

            System.out.println("Lanche adicionado ao pedido!");

        } catch (NumberFormatException e) {
            System.out.println("Digite um número válido!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean fecharRestaurante() {

        double totalPendente = restauranteFacade.calcularTotalPendente();

        if (totalPendente > 0) {
            System.out.println("\n⚠ ATENÇÃO!");
            System.out.printf(
                    "Existem R$ %.2f em pedidos não pagos!%n",
                    totalPendente
            );

            System.out.print("Deseja fechar o restaurante mesmo assim? (S/N): ");

            String resposta = sc.nextLine().trim();

            if (resposta.equalsIgnoreCase("N")) {
                System.out.println("Fechamento cancelado!");
                return false;
            }

            if (!resposta.equalsIgnoreCase("S")) {
                System.out.println("Resposta inválida! Fechamento cancelado.");
                return false;
            }
        }

        exibirFechamento();

        System.out.println("Restaurante fechado!");
        return true;
    }

}

