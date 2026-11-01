package com.example.sistema_restaurante.cardapio;

public class Lanche implements ItemCardapio {
    private String nome;
    private double preco;

    public Lanche(String nome, double preco){
        setNome(nome);
        setPreco(preco);
    }

    @Override
    public String getDescricao() {
        return nome;
    }

    @Override
    public double getPreco() {
        return preco;
    }


    public void setNome(String nome) {
        if(nome == null || nome.trim().isEmpty()){
            throw new IllegalArgumentException("O nome não pode ser nulo nem vazio.");
        }
        this.nome = nome;
    }

    public void setPreco(double preco) {
        if(preco < 0 ){
            throw new IllegalArgumentException("O preço não pode ser negativo.");
        }
        this.preco = preco;
    }


}
