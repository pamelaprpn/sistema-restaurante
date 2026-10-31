package com.example.sistema_restaurante.app;

import com.example.sistema_restaurante.cardapio.Lanche;

public class RestauranteFacade {
    private Lanche xTudo = new Lanche("X-Tudo", 25.00);
    private Lanche xCalabresa = new Lanche("X-Calabresa", 20.00);
    private Lanche xSalada = new Lanche("X-Salada", 18.00);


    public void exibirCardapio(){
        System.out.println("\n--- CARDÁPIO ---");
        System.out.println("1 - " + xTudo.getNome() + " R$ " + xTudo.getPreco());
        System.out.println("2 - " + xCalabresa.getNome() + " R$ " + xCalabresa.getPreco());
        System.out.println("3 - " + xSalada.getNome() + " R$ " + xSalada.getPreco());
    }

    public Lanche escolherlanche(int opcao){
        return switch (opcao){
            case 1 -> xTudo;
            case 2 -> xCalabresa;
            case 3 -> xSalada;
            default -> throw new IllegalArgumentException("Lanche inexistente");
        };
    }


}
