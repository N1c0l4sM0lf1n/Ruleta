package org.example;

import org.example.Modelo.Usuario;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTests {

    @Test
    void depositoValidoIncrementaSaldo() {
        Usuario u = new Usuario("u", "p", "name");
        u.depositar(100);
        assertEquals(100, u.getSaldo());
    }

    @Test
    void apostarMontoMayorSaldoDebeFallar() {
        Usuario u = new Usuario("u", "p", "name");
        u.depositar(50);

        assertThrows(IllegalArgumentException.class, () -> {
            u.apostar(100);
        });
    }
}
