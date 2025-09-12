package org.example;

import java.util.Random;

public class Ruleta {
    public static final int MAX_HISTORIAL = 100;
    private final int[] historialNumeros = new int[MAX_HISTORIAL];
    private final int[] historialApuestas = new int[MAX_HISTORIAL];
    private final boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    private int historialSize = 0;

    private final Random rng = new Random();
    private static final int[] numerosRojos =
            {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};

    public ResultadoRonda jugar(char tipo, int monto) {
        int numero = girarRuleta();
        boolean acierto = evaluarResultado(numero, tipo);

        registrarResultado(numero, monto, acierto);

        return new ResultadoRonda(numero, tipo, monto, acierto);
    }

    private int girarRuleta() {
        return rng.nextInt(37);
    }

    private boolean evaluarResultado(int numero, char tipo) {
        return switch (tipo) {
            case 'R' -> esRojo(numero);
            case 'N' -> numero != 0 && !esRojo(numero);
            case 'P' -> numero != 0 && numero % 2 == 0;
            case 'I' -> numero % 2 != 0;
            default -> false;
        };
    }

    private boolean esRojo(int n) {
        for (int rojo : numerosRojos) {
            if (rojo == n) return true;
        }
        return false;
    }

    private void registrarResultado(int numero, int apuesta, boolean acierto) {
        if (historialSize < MAX_HISTORIAL) {
            historialNumeros[historialSize] = numero;
            historialApuestas[historialSize] = apuesta;
            historialAciertos[historialSize] = acierto;
            historialSize++;
        }
    }

    public Estadisticas obtenerEstadisticas() {
        int totalApostado = 0;
        int aciertos = 0;
        int ganancia = 0;

        for (int i = 0; i < historialSize; i++) {
            totalApostado += historialApuestas[i];
            if (historialAciertos[i]) {
                aciertos++;
                ganancia += historialApuestas[i];
            } else {
                ganancia -= historialApuestas[i];
            }
        }

        double porcentaje = historialSize > 0 ? (aciertos * 100.0) / historialSize : 0;

        return new Estadisticas(historialSize, totalApostado, aciertos, porcentaje, ganancia);
    }
}
