package org.example;

public class ResultadoRonda {
    private final int numero;
    private final char tipo;
    private final int monto;
    private final boolean acierto;

    public ResultadoRonda(int numero, char tipo, int monto, boolean acierto) {
        this.numero = numero;
        this.tipo = tipo;
        this.monto = monto;
        this.acierto = acierto;
    }
}
