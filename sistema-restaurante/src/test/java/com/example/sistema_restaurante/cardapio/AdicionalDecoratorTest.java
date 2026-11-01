package com.example.sistema_restaurante.cardapio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AdicionalDecoratorTest {
    @Test
    void deveAdicionarBacon() {
        ItemCardapio lanche = new Lanche("X-Salada", 18.00);
        ItemCardapio resultado = new BaconExtra(lanche);

        assertEquals(24.00, resultado.getPreco(), 0.001);
    }

    @Test
    void deveAdicionarCheddar() {
        ItemCardapio lanche = new Lanche("X-Salada", 18.00);
        ItemCardapio resultado = new CheddarExtra(lanche);

        assertEquals(22.00, resultado.getPreco(), 0.001);
    }

    @Test
    void deveAdicionarMolho() {
        ItemCardapio lanche = new Lanche("X-Salada", 18.00);
        ItemCardapio resultado = new MolhoExtra(lanche);

        assertEquals(21.00, resultado.getPreco(), 0.001);
    }

    @Test
    void deveCombinarAdicionais() {
        ItemCardapio lanche = new Lanche("X-Salada", 18.00);

        lanche = new BaconExtra(lanche);
        lanche = new CheddarExtra(lanche);
        lanche = new MolhoExtra(lanche);

        assertEquals(31.00, lanche.getPreco(), 0.001);
    }
}
