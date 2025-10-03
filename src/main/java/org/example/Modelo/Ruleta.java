package org.example.Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ruleta {
    private final Random rng = new Random();
    private static final int[] numerosRojos =
            {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};

    private final List<Resultado> historial = new ArrayList<>();

    public Ruleta() { }
    public Ruleta(int saldoInicial) { }

    public Resultado jugar(TipoApuesta tipo, int monto) {
        int numero = girarRuleta();
        boolean acierto = evaluarResultado(numero, tipo);

        Resultado resultado = new Resultado(numero, tipo, monto, acierto);
        historial.add(resultado);
        return resultado;
    }

    private int girarRuleta() {
        return rng.nextInt(37);
    }

    private boolean evaluarResultado(int numero, TipoApuesta tipo) {
        return switch (tipo) {
            case ROJO -> esRojo(numero);
            case NEGRO -> numero != 0 && !esRojo(numero);
            case PAR -> numero != 0 && numero % 2 == 0;
            case IMPAR -> numero % 2 != 0;
        };
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

