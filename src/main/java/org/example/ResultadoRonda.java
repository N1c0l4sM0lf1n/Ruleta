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
    public int getNumero() { return numero; }
    public char getTipo() { return tipo; }
    public int getMonto() { return monto; }
    public boolean isAcierto() { return acierto; }

    public String getColor() {
        if (numero == 0) return "Verde";
        return switch (tipo) {
            case 'R' -> "Rojo";
            case 'N' -> "Negro";
            default -> (numero % 2 == 0) ? "Par" : "Impar";
        };
    }
}
