package org.example.Modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ApuestaParTest {

    @Test
    void par_aciertaSoloSiNumeroEsPar() {
        ApuestaPar a = new ApuestaPar(100);

        assertTrue(a.acierta(4, "Rojo"));
        assertTrue(a.acierta(36, "Negro"));
        assertFalse(a.acierta(5, "Rojo"));
        assertFalse(a.acierta(0, "Verde"));
    }
}
