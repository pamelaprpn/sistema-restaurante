package com.example.sistema_restaurante.pedido;

import java.util.Collection;
import java.util.HashMap;

public class GerenciadorPedidos {
    private HashMap<Integer, Pedido> pedidos = new HashMap<>();
    private int contadorPedidos = 1;

    public Pedido criarPedido(String nomeCliente){
      Pedido novo = new Pedido(nomeCliente, contadorPedidos);
      pedidos.put(novo.getNumero(), novo);
      contadorPedidos++;
      return novo;
    }

    public Pedido buscarPedido(int numero){
        return pedidos.get(numero);
    }

    public Collection<Pedido> getPedidos() {
        return pedidos.values();
    }

    public double calcularTotalDoDia(){

        double total = 0;

        System.out.println("\n--- LISTA DE PEDIDOS NO DIA ---");
        System.out.println("Quantidade de pedidos: " + pedidos.size());

        for (Pedido pedido : pedidos.values()) {
            System.out.println("\n--- Pedido: "+ pedido.getNumero() + " ---");
            System.out.println("Nome do cliente: " + pedido.getNomeCliente());
            System.out.println("Quantidade de lanches: " + pedido.getItens().size());
            System.out.println("Total do pedido: " + pedido.calcularTotal());

            total += pedido.calcularTotal();
        }
         return total;

    }

}
