package org.example.Modelo;

import java.util.Random;

public class Ruleta {

    private final Random rng = new Random();
    private final IRepositorioResultados repositorio;

    private static final int[] numerosRojos =
            {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};

    public Ruleta(IRepositorioResultados repositorio) {
        if (repositorio == null)
            throw new NullPointerException("Repositorio requerido");

        this.repositorio = repositorio;
    }

    public Resultado jugar(ApuestaBase apuesta) {
        if (apuesta == null)
            throw new IllegalArgumentException("Apuesta requerida");

        int numero = rng.nextInt(37);
        String color = (numero == 0) ? "Verde" : (esRojo(numero) ? "Rojo" : "Negro");

        boolean acierto = apuesta.acierta(numero, color);

        Resultado resultado = new Resultado(
                numero,
                apuesta.getEtiqueta(),
                apuesta.getMonto(),
                acierto
        );

        repositorio.guardarResultado(resultado);
        return resultado;
    }

    private boolean esRojo(int n) {
        for (int r : numerosRojos) if (r == n) return true;
        return false;
    }
}
