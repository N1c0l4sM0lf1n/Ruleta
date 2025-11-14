package org.example.Vista;

import org.example.Controlador.SessionController;

import javax.swing.*;
import java.awt.*;

public class VentanaSaludo {
    private final JFrame frame = new JFrame("Bienvenido");
    private final SessionController session;

    public VentanaSaludo(SessionController session) {
        this.session = session;

        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        String nombre = session.getUsuarioActual().getNombre();
        JLabel lblSaludo = new JLabel("¡Hola, " + nombre + "!", SwingConstants.CENTER);
        JButton btnJugar = new JButton("Ir a la Ruleta");

        btnJugar.addActionListener(e -> {
            frame.dispose();
            new VentanaRuleta(session).mostrarVentana();
        });

        frame.add(lblSaludo, BorderLayout.CENTER);
        frame.add(btnJugar, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }
}
