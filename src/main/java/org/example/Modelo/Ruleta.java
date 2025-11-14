package org.example.Modelo;

import java.util.Random;

public class Ruleta {
    private final Random rng = new Random();
    private final IRepositorioResultados repositorio;

    private int saldo;

    private static final int[] numerosRojos =
            {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};

    public Ruleta(IRepositorioResultados repositorio, int saldoInicial) {
        if (repositorio == null)
            throw new NullPointerException("Repositorio requerido");

        if (saldoInicial < 0)
            throw new IllegalArgumentException("Saldo inicial inválido");

        this.repositorio = repositorio;
        this.saldo = saldoInicial;
    }

    public Ruleta(IRepositorioResultados repositorio) {
        this(repositorio, 0);
    }

    public int getSaldo() {
        return saldo;
    }

    public void depositar(int monto) {
        if (monto <= 0)
            throw new IllegalArgumentException("Depósito debe ser positivo");
        saldo += monto;
    }

    private void apostar(int monto) {
        if (monto <= 0)
            throw new IllegalArgumentException("Monto inválido");

        if (monto > saldo)
            throw new IllegalArgumentException("Saldo insuficiente");

        saldo -= monto;
    }

    public Resultado jugar(ApuestaBase apuesta) {
        if (apuesta == null)
            throw new IllegalArgumentException("Apuesta requerida");

        apostar(apuesta.getMonto());

        int numero = rng.nextInt(37);
        String color = numero == 0 ? "Verde" : (esRojo(numero) ? "Rojo" : "Negro");

        boolean acierto = apuesta.acierta(numero, color);

        Resultado resultado = new Resultado(numero, apuesta.getEtiqueta(), apuesta.getMonto(), acierto);
        repositorio.guardarResultado(resultado);

        if (acierto)
            saldo += apuesta.getMonto() * 2;

        return resultado;
    }

    private boolean esRojo(int n) {
        for (int rojo : numerosRojos) if (rojo == n) return true;
        return false;
    }
}
