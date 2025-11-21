package org.example;

import com.formdev.flatlaf.FlatDarkLaf;
import org.example.Controlador.SessionController;
import org.example.Vista.VentanaLogin;

import javax.swing.UIManager;

public class Launcher {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.err.println("No se pudo aplicar el tema: " + e.getMessage());
        }

        SessionController session = new SessionController();

        new VentanaLogin(session).mostrarVentana();
    }
}

