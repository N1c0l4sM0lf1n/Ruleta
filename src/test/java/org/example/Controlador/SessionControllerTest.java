package org.example.Controlador;

import org.junit.jupiter.api.Test;
import org.example.Modelo.Usuario;
import static org.junit.jupiter.api.Assertions.*;

public class SessionControllerTest {

    @Test
    void inicioSesion_usuarioNoRegistrado_esRechazado() {
        SessionController sc = new SessionController();

        boolean login = sc.iniciarSesion("fantasma", "1234");

        assertFalse(login);
        assertFalse(sc.hayUsuario());
    }

    @Test
    void inicioSesion_conUsernameNulo_esRechazado() {
        SessionController sc = new SessionController();

        // Registrar un usuario válido
        sc.registrarUsuario("juan", "1234", "Juanito");

        boolean login = sc.iniciarSesion(null, "1234");

        assertFalse(login);
        assertFalse(sc.hayUsuario());
    }
    @Test
    void registrarUsuarioDuplicado_falla() {
        SessionController sc = new SessionController();

        sc.registrarUsuario("juan", "123", "Juan");

        assertThrows(IllegalArgumentException.class, () ->
                sc.registrarUsuario("juan", "999", "OtroJuan"));
    }
    @Test
    void cerrarSesion_liberaUsuario() {
        SessionController sc = new SessionController();
        sc.registrarUsuario("x", "1", "X");
        sc.iniciarSesion("x", "1");

        sc.cerrarSesion();

        assertFalse(sc.hayUsuario());
        assertNull(sc.getUsuarioActual());
    }

}
