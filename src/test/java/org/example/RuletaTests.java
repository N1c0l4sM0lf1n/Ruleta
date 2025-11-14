package org.example;

import org.example.Modelo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RuletaTests {

    @Test
    void constructorDebeRechazarSaldoInicialNegativo() {
        IRepositorioResultados repo = mock(IRepositorioResultados.class);

        assertThrows(IllegalArgumentException.class, () -> {
            new Ruleta(repo, -10);
        });
    }

    @Test
    void depositoValidoIncrementaSaldo() {
        Ruleta r = new Ruleta(new RepositorioEnMemoria(), 0);
        r.depositar(100);
        assertEquals(100, r.getSaldo());
    }

    @Test
    void jugarDebeRechazarApuestaNula() {
        Ruleta r = new Ruleta(new RepositorioEnMemoria(), 100);
        assertThrows(IllegalArgumentException.class, () -> {
            r.jugar(null);
        });
    }

    @Test
    void apuestaMayorSaldoDebeFallar() {
        Ruleta r = new Ruleta(new RepositorioEnMemoria(), 50);
        ApuestaBase apuesta = new ApuestaPar(100);
        assertThrows(IllegalArgumentException.class, () -> r.jugar(apuesta));
    }
}
