package org.example;

import org.example.Modelo.Estadisticas;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EstadisticasTests {

    @Test
    void estadisticasDevuelvenDatosCorrectos() {
        Estadisticas est = new Estadisticas(
                10, 300, 4, 40.0, 120
        );

        assertEquals(10, est.getRondasJugadas());
        assertEquals(300, est.getTotalApostado());
        assertEquals(4, est.getTotalAciertos());
        assertEquals(40.0, est.getPorcentajeAciertos());
        assertEquals(120, est.getGananciaNeta());
    }
}
