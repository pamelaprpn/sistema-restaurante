package com.example.sistema_restaurante.cardapio;

public class MolhoExtra extends AdicionalDecorator{

    public MolhoExtra(ItemCardapio item){
        super(item);
    }

    @Override
    public String getDescricao() {
        return item.getDescricao() + " + Molho extra";
    }

    @Override
    public double getPreco() {
        return item.getPreco() + 3.00;
    }
}
