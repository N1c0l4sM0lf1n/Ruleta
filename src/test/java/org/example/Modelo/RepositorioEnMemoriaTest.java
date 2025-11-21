package org.example.Modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RepositorioEnMemoriaTest {

    @Test
    void guardarYLeerHistorial() {
        RepositorioEnMemoria repo = new RepositorioEnMemoria();

        Resultado r1 = new Resultado(5, "Rojo", 100, true);
        Resultado r2 = new Resultado(8, "Par", 50, false);

        repo.guardarResultado(r1);
        repo.guardarResultado(r2);

        var lista = repo.obtenerHistorial();

        assertEquals(2, lista.size());
        assertEquals("Rojo", lista.get(0).getEtiqueta());
    }
}
