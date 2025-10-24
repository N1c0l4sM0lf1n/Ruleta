package org.example.Controlador;

import org.example.Modelo.Resultado;
import org.example.Modelo.Usuario;

import java.util.List;

public class ResultadoController {
    private final SessionController session;

    public ResultadoController(SessionController session) {
        this.session = session;
    }

    public List<Resultado> obtenerHistorial() {
        Usuario usuario = session.getUsuarioActual();
        if (usuario == null) {
            throw new IllegalStateException("No hay usuario en sesión");
        }
        return usuario.getHistorial();
    }

    public SessionController getSession() {
        return session;
    }
}

