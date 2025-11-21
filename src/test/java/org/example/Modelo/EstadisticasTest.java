package org.example.Modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EstadisticasTest {

    @Test
    void estadisticasCalculadasCorrectamente() {
        int rondas = 3;
        int totalApostado = 300;
        int aciertos = 1;
        double porcentaje = aciertos * 100.0 / rondas;
        int gananciaNeta = 500;

        Estadisticas e = new Estadisticas(rondas, totalApostado, aciertos, porcentaje, gananciaNeta);

        assertEquals(rondas, e.getRondasJugadas());
        assertEquals(totalApostado, e.getTotalApostado());
        assertEquals(aciertos, e.getTotalAciertos());
        assertEquals(porcentaje, e.getPorcentajeAciertos());
        assertEquals(gananciaNeta, e.getGananciaNeta());
    }
}
