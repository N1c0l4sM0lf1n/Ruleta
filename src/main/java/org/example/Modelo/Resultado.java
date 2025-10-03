package org.example.Modelo;

public class Resultado {
    private final int numero;
    private final TipoApuesta tipoApuesta;
    private final int monto;
    private final boolean acierto;

    public Resultado(int numero, TipoApuesta tipoApuesta, int monto, boolean acierto) {
        this.numero = numero;
        this.tipoApuesta = tipoApuesta;
        this.monto = monto;
        this.acierto = acierto;
    }

    public int getNumero() { return numero; }
    public TipoApuesta getTipoApuesta() { return tipoApuesta; }
    public int getMonto() { return monto; }
    public boolean isAcierto() { return acierto; }

    public String getColor() {
        if (numero == 0) return "Verde";
        return switch (tipoApuesta) {
            case ROJO -> "Rojo";
            case NEGRO -> "Negro";
            case PAR -> "Par";
            case IMPAR -> "Impar";
        };
    }
}
