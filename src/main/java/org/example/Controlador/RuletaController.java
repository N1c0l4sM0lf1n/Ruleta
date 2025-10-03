package org.example.Controlador;

import org.example.Modelo.Resultado;
import org.example.Modelo.Ruleta;
import org.example.Modelo.TipoApuesta;
import org.example.Modelo.Usuario;

public class RuletaController {
    private final Ruleta ruleta;
    private final SessionController session;

    public RuletaController(Ruleta ruleta, SessionController session) {
        this.ruleta = ruleta;
        this.session = session;
    }

    public Resultado jugarApuesta(TipoApuesta tipo, int monto) {
        Usuario usuario = session.getUsuarioActual();
        if (usuario == null) {
            throw new IllegalStateException("No hay usuario en sesión");
        }

        usuario.apostar(monto);

        Resultado resultado = ruleta.jugar(tipo, monto);

        if (resultado.isAcierto()) {
            usuario.depositar(monto * 2);
        }

        usuario.agregarResultado(resultado);

        return resultado;
    }

    public SessionController getSession() {
        return session;
    }
}

