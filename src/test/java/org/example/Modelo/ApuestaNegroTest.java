package org.example.Modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ApuestaNegroTest {

    @Test
    void negro_aciertaSoloCuandoColorEsNegro() {
        ApuestaNegro a = new ApuestaNegro(100);

        assertTrue(a.acierta(10, "Negro"));
        assertFalse(a.acierta(10, "Rojo"));
        assertFalse(a.acierta(0, "Verde"));
    }
}
