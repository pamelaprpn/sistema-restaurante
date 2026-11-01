package com.example.sistema_restaurante.pedido;

import com.example.sistema_restaurante.cardapio.ItemCardapio;
import com.example.sistema_restaurante.cardapio.Lanche;

import java.util.ArrayList;

public class Pedido {
    private int numero;
    private String nomeCliente;
    private ArrayList<ItemCardapio> itens;


    public Pedido(String nomeCliente, int numero){
        setNomeCliente(nomeCliente);
        this.numero = numero;
        this.itens = new ArrayList<>();
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public int getNumero() {
        return numero;
    }

    public void setNomeCliente(String nomeCliente) {
        if(nomeCliente == null || nomeCliente.trim().isEmpty()){
            throw new IllegalArgumentException("Nome Inválido");
        }

        this.nomeCliente = nomeCliente;
    }

    public void adicionarItem(ItemCardapio itemCardapio){
        itens.add(itemCardapio);
    }

    public ArrayList<ItemCardapio> getItens(){
        return itens;
    }

    public double calcularTotal(){
        double total = 0;

        for(int i=0; i < itens.size(); i ++){
            total += itens.get(i).getPreco();
        }
        return total;

    }

}
