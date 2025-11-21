package org.example.Modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ResultadoTest {

    @Test
    void colorDeResultadoEsCorrecto() {
        Resultado r = new Resultado(5, "Rojo", 100, false);
        assertEquals("Rojo", r.getColor());

        Resultado r2 = new Resultado(10, "Negro", 100, false);
        assertEquals("Negro", r2.getColor());

        Resultado r3 = new Resultado(0, "Par", 100, false);
        assertEquals("Verde", r3.getColor());
    }
}
