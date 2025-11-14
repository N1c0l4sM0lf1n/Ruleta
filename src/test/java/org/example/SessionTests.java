package org.example;

import org.example.Controlador.SessionController;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SessionTests {

    @Test
    void loginUsuarioNoRegistradoDebeFallar() {
        SessionController s = new SessionController();

        boolean login = s.iniciarSesion("noExiste", "123");

        assertFalse(login);
        assertFalse(s.hayUsuario());
    }

    @Test
    void registrarUsuarioConUsernameNuloDebeFallar() {
        SessionController s = new SessionController();

        assertThrows(IllegalArgumentException.class, () -> {
            s.registrarUsuario(null, "123", "Pepe");
        });
    }

    @Test
    void loginConUsernameNuloDebeRechazarse() {
        SessionController s = new SessionController();

        assertFalse(s.iniciarSesion(null, "123"));
        assertFalse(s.hayUsuario());
    }
}
