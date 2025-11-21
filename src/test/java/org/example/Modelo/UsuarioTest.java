package org.example.Modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    void depositarMontoNegativo_lanzaExcepcion() {
        Usuario u = new Usuario("a", "b", "c");

        assertThrows(IllegalArgumentException.class, () ->
                u.depositar(-10));
    }

    @Test
    void depositarCero_lanzaExcepcion() {
        Usuario u = new Usuario("a", "b", "c");

        assertThrows(IllegalArgumentException.class, () ->
                u.depositar(0));
    }

    @Test
    void apostarMontoMayorAlSaldo_lanzaExcepcion() {
        Usuario u = new Usuario("a", "b", "c");

        u.depositar(50);

        assertThrows(IllegalArgumentException.class, () ->
                u.apostar(100));
    }

    @Test
    void apostarMontoValido_descuentaSaldo() {
        Usuario u = new Usuario("a", "b", "c");
        u.depositar(100);

        u.apostar(40);

        assertEquals(60, u.getSaldo());
    }

    @Test
    void setNombre_invalidoFalla() {
        Usuario u = new Usuario("a", "b", "c");

        assertThrows(IllegalArgumentException.class, () ->
                u.setNombre(""));
    }
}
