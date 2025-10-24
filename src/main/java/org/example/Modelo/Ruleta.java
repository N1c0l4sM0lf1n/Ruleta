package org.example.Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ruleta {
    private final Random rng = new Random();
    private static final int[] numerosRojos =
            {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};

    private final List<Resultado> historial = new ArrayList<>();

    public Ruleta() {}

    public Resultado jugar(ApuestaBase apuesta) {
        int numero = girarRuleta();
        String color = colorDe(numero);

        boolean acierto = apuesta.acierta(numero, color);

        Resultado resultado = new Resultado(numero, apuesta.getEtiqueta(), apuesta.getMonto(), acierto);
        historial.add(resultado);
        return resultado;
    }

    private int girarRuleta() {
        return rng.nextInt(37);
    }

    private String colorDe(int numero) {
        if (numero == 0) return "Verde";
        return esRojo(numero) ? "Rojo" : "Negro";
    }

    private boolean esRojo(int n) {
        for (int rojo : numerosRojos) {
            if (rojo == n) return true;
        }
        return false;
    }

    public List<Resultado> getHistorial() {
        return List.copyOf(historial);
    }
}
