package org.example.Modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ApuestaRojoTest {

    @Test
    void rojo_aciertaSoloCuandoColorEsRojo() {
        ApuestaRojo a = new ApuestaRojo(100);

        assertTrue(a.acierta(7, "Rojo"));
        assertFalse(a.acierta(7, "Negro"));
        assertFalse(a.acierta(0, "Verde"));
    }
}
