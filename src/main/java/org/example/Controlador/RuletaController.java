package org.example.Controlador;

import org.example.Modelo.*;

public class RuletaController {
    private final Ruleta ruleta;
    private final SessionController session;

    public RuletaController(Ruleta ruleta, SessionController session) {
        this.ruleta = ruleta;
        this.session = session;
    }


    public Resultado jugarApuesta(String tipo, int monto) {
        var usuario = session.getUsuarioActual();
        if (usuario == null) throw new IllegalStateException("No hay usuario en sesión");

        usuario.apostar(monto);

        ApuestaBase apuesta = switch (tipo) {
            case "Rojo" -> new ApuestaRojo(monto);
            case "Negro" -> new ApuestaNegro(monto);
            case "Par" -> new ApuestaPar(monto);
            case "Impar" -> new ApuestaImpar(monto);
            default -> throw new IllegalArgumentException("Tipo de apuesta no válido");
        };

        Resultado resultado = ruleta.jugar(apuesta);

        if (resultado.isAcierto())
            usuario.depositar(monto * 2);

        return resultado;
    }

    public SessionController getSession() {
        return session;
    }
}
