package com.example.sistema_restaurante.pedido;

import com.example.sistema_restaurante.cardapio.Lanche;

import java.util.ArrayList;

public class Pedido {
    int id;
    String nomeCliente;
    ArrayList<Lanche> itens;


    public Pedido(String nomeCliente, int id){
        setNomeCliente(nomeCliente);
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        if(nomeCliente == null || nomeCliente.trim().isEmpty()){
            throw new IllegalArgumentException("Nome Inválido");
        }

        this.nomeCliente = nomeCliente;
    }

    public void setItens(ArrayList<Lanche> itens) {
        this.itens = itens;
    }

    public void adicionarItem(Lanche lache){
        itens.add(lache);
    }

    public ArrayList<Lanche> getItens(){
        return itens;
    }

    public void calcularTotal(){
        for(int i=0; i <= itens.size(); i ++){
            double total = itens.get
        }
    }

}
