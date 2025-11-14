package org.example.Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RepositorioEnMemoria implements IRepositorioResultados {

    private final List<Resultado> historial = new ArrayList<>();

    @Override
    public void guardarResultado(Resultado resultado) {
        historial.add(resultado);
    }

    @Override
    public List<Resultado> obtenerHistorial() {
        return Collections.unmodifiableList(historial);
    }
}
