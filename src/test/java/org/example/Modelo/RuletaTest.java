package org.example.Modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RuletaTest {

    @Test
    void constructor_rechazaSaldoInicialNegativo() {
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                new Ruleta(new RepositorioEnMemoria(), -10));

        assertEquals("Saldo inicial inválido", ex.getMessage());
    }

    @Test
    void depositoValidoIncrementaSaldo() {
        Ruleta r = new Ruleta(new RepositorioEnMemoria(), 100);

        r.depositar(50);

        assertEquals(150, r.getSaldo());
    }

    @Test
    void jugar_conApuestaNull_lanzaExcepcion() {
        Ruleta r = new Ruleta(new RepositorioEnMemoria(), 100);

        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                r.jugar(null));

        assertEquals("Apuesta requerida", ex.getMessage());
    }

    @Test
    void jugar_conMontoMayorAlSaldo_lanzaExcepcion() {
        Ruleta r = new Ruleta(new RepositorioEnMemoria(), 20);

        // Apuesta de 50 > saldo actual (20)
        ApuestaPar apuesta = new ApuestaPar(50);

        Exception ex = assertThrows(IllegalArgumentException.class, () ->
                r.jugar(apuesta));

        assertEquals("Saldo insuficiente", ex.getMessage());
    }
    @Test
    void depositarMontoNegativo_lanzaExcepcion() {
        Ruleta r = new Ruleta(new RepositorioEnMemoria(), 100);
        assertThrows(IllegalArgumentException.class, () -> r.depositar(-5));
    }

    @Test
    void depositarCero_lanzaExcepcion() {
        Ruleta r = new Ruleta(new RepositorioEnMemoria(), 100);
        assertThrows(IllegalArgumentException.class, () -> r.depositar(0));
    }
    @Test
    void apostar_conSaldoExacto_funciona() {
        Ruleta r = new Ruleta(new RepositorioEnMemoria(), 50);
        ApuestaPar ap = new ApuestaPar(50);

        Resultado res = r.jugar(ap);

        assertNotNull(res);
    }

}
