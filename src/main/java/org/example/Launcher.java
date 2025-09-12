package org.example;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.UIManager;

public class Launcher {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception e) {
            System.err.println("No se pudo aplicar el tema: " + e.getMessage());
        }

        new VentanaLogin().mostrarVentana();
    }
}

