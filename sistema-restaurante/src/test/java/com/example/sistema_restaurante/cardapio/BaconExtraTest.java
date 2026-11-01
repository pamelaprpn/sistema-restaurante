package com.example.sistema_restaurante.cardapio;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BaconExtraTest {
    @Test
    void deveAdicionarPrecoDoBaconAoLanche() {

        ItemCardapio lanche = new Lanche("X-Salada", 18.00);

        ItemCardapio lancheComBacon = new BaconExtra(lanche);

        assertEquals(24.00, lancheComBacon.getPreco(), 0.001);
    }

    @Test
    void deveAdicionarBaconNaDescricao() {

        ItemCardapio lanche = new Lanche("X-Salada", 18.00);

        ItemCardapio lancheComBacon = new BaconExtra(lanche);

        assertTrue(lancheComBacon.getDescricao().contains("Bacon"));
    }


}
