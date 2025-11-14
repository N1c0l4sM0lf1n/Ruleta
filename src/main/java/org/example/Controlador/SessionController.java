package org.example.Controlador;

import org.example.Modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class SessionController {

    private final List<Usuario> usuarios = new ArrayList<>();
    private Usuario usuarioActual;

    public void registrarUsuario(String u, String p, String n) {

        if (u == null || u.isBlank() ||
                p == null || p.isBlank() ||
                n == null || n.isBlank()) {
            throw new IllegalArgumentException("Todos los campos son obligatorios");
        }

        for (Usuario user : usuarios) {
            if (user.getUsuario().equals(u)) {
                throw new IllegalArgumentException("El usuario ya existe");
            }
        }
        usuarios.add(new Usuario(u, p, n));
    }

    public boolean iniciarSesion(String u, String p) {

        for (Usuario user : usuarios) {
            if (user.validarCredenciales(u, p)) {
                usuarioActual = user;
                return true;
            }
        }
        return false;
    }

    public void cerrarSesion() {
        usuarioActual = null;
    }

    public boolean hayUsuario() {
        return usuarioActual != null;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}
