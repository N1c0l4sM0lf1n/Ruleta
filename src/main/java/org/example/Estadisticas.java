package org.example;

public class Estadisticas {
    private final int rondasJugadas;
    private final int totalApostado;
    private final int totalAciertos;
    private final double porcentajeAciertos;
    private final int gananciaNeta;

    public Estadisticas(int rondasJugadas, int totalApostado, int totalAciertos,
                        double porcentajeAciertos, int gananciaNeta) {
        this.rondasJugadas = rondasJugadas;
        this.totalApostado = totalApostado;
        this.totalAciertos = totalAciertos;
        this.porcentajeAciertos = porcentajeAciertos;
        this.gananciaNeta = gananciaNeta;
    }

    public int getRondasJugadas() { return rondasJugadas; }
    public int getTotalApostado() { return totalApostado; }
    public int getTotalAciertos() { return totalAciertos; }
    public double getPorcentajeAciertos() { return porcentajeAciertos; }
    public int getGananciaNeta() { return gananciaNeta; }
}

