package org.example.Controlador;

import org.example.Modelo.*;

public class RuletaController {

    private final SessionController session;
    private final Ruleta ruleta;

    public RuletaController(SessionController session, Ruleta ruleta) {
        if (session == null) throw new IllegalArgumentException("Sesión requerida");
        if (ruleta == null) throw new IllegalArgumentException("Ruleta requerida");

        this.session = session;
        this.ruleta = ruleta;
    }

    public Resultado jugarApuesta(String tipo, int monto) {

        var usuario = session.getUsuarioActual();
        if (usuario == null)
            throw new IllegalStateException("No hay usuario en sesión");

        if (monto <= 0)
            throw new IllegalArgumentException("Monto inválido");

        usuario.apostar(monto);

        ApuestaBase apuesta = switch (tipo) {
            case "Rojo" -> new ApuestaRojo(monto);
            case "Negro" -> new ApuestaNegro(monto);
            case "Par"   -> new ApuestaPar(monto);
            case "Impar" -> new ApuestaImpar(monto);
            default -> throw new IllegalArgumentException("Tipo de apuesta inválido");
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
