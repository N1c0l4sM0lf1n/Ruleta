package org.example.Modelo;

public class Resultado {
    private final int numero;
    private final String etiqueta;
    private final int monto;
    private final boolean acierto;

    public Resultado(int numero, String etiqueta, int monto, boolean acierto) {
        this.numero = numero;
        this.etiqueta = etiqueta;
        this.monto = monto;
        this.acierto = acierto;
    }

    public int getNumero() { return numero; }
    public String getEtiqueta() { return etiqueta; }
    public int getMonto() { return monto; }
    public boolean isAcierto() { return acierto; }

    public String getColor() {
        if (numero == 0) return "Verde";
        return switch (etiqueta.toUpperCase()) {
            case "ROJO" -> "Rojo";
            case "NEGRO" -> "Negro";
            case "PAR" -> "Rojo/Negro";
            case "IMPAR" -> "Rojo/Negro";
            default -> "Desconocido";
        };
    }
}
