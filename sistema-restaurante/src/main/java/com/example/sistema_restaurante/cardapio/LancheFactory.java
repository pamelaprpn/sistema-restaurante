package com.example.sistema_restaurante.cardapio;

import com.example.sistema_restaurante.app.RestauranteFacade;

import java.util.List;

public class LancheFactory {

    private final List<Lanche> lanches = List.of(
            new Lanche("X-Tudo", 25.00),
            new Lanche("X-Calabresa", 20.00),
            new Lanche("X-Salada", 18.00)
    );

    public Lanche criarLanche(int opcao) {
        if (opcao < 1 || opcao > lanches.size()) {
            throw new IllegalArgumentException("Lanche inexistente.");
        }

        return lanches.get(opcao - 1);
    }

    public ItemCardapio adicionarExtra(ItemCardapio item, int opcao) {
        return switch (opcao) {
            case 1 -> new BaconExtra(item);
            case 2 -> new CheddarExtra(item);
            case 3 -> new MolhoExtra(item);
            default -> throw new IllegalArgumentException("Adicional inexistente.");
        };
    }

    public int quantidadeLanches() {
        return lanches.size();
    }
}
