package com.example.sistema_restaurante.cardapio;

public class CheddarExtra extends AdicionalDecorator {

    public CheddarExtra(ItemCardapio item){
        super(item);
    }

    @Override
    public String getDescricao() {
        return item.getDescricao() + " + Cheddar extra";
    }

    @Override
    public double getPreco() {
        return item.getPreco() + 4.00;
    }
}
