package com.example.sistema_restaurante.cardapio;

public  abstract class AdicionalDecorator implements ItemCardapio {

    protected ItemCardapio item;

    public AdicionalDecorator(ItemCardapio item){
        this.item = item;
    }


}
