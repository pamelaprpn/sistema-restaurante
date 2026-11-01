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

        for (Pedido pedido : pedidos.values()) {
            total += pedido.calcularTotal();
        }
        return total;




    }

}
