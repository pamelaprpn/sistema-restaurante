package com.example.sistema_restaurante.cardapio;

public class BaconExtra extends AdicionalDecorator{

    public BaconExtra(ItemCardapio item) {
        super(item);
    }

    @Override
    public String getDescricao() {
        return item.getDescricao() + " + Bacon extra";
    }

    @Override
    public double getPreco() {
        return item.getPreco() + 6.00;
    }


}
