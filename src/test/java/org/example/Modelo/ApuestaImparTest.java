package org.example.Modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ApuestaImparTest {

    @Test
    void impar_aciertaSoloSiNumeroEsImpar() {
        ApuestaImpar a = new ApuestaImpar(100);

        assertTrue(a.acierta(7, "Rojo"));
        assertFalse(a.acierta(8, "Negro"));
        assertFalse(a.acierta(0, "Verde"));
    }
}
