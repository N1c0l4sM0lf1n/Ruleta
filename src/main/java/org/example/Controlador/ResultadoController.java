package org.example.Controlador;

import org.example.Modelo.IRepositorioResultados;
import org.example.Modelo.Resultado;

import java.util.List;

public class ResultadoController {
    private final SessionController session;
    private final IRepositorioResultados repositorio;

    public ResultadoController(SessionController session, IRepositorioResultados repositorio) {
        this.session = session;
        this.repositorio = repositorio;
    }

    public List<Resultado> obtenerHistorial() {
        if (!session.hayUsuario())
            throw new IllegalStateException("No hay usuario en sesión");
        return repositorio.obtenerHistorial();
    }

    public SessionController getSession() {
        return session;
    }
}
