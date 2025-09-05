package org.example;
import javax.swing.*;
import java.awt.*;

public class VentanaSaludo {
    private final JFrame frame = new JFrame("Bienvenido");

    public VentanaSaludo(String nombre) {
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel lblSaludo = new JLabel("¡Hola, " + nombre + "!", SwingConstants.CENTER);
        JButton btnJugar = new JButton("Ir a la Ruleta");

        btnJugar.addActionListener(e -> {
            frame.dispose();
            new VentanaJuego(nombre).mostrarVentana();
        });

        frame.add(lblSaludo, BorderLayout.CENTER);
        frame.add(btnJugar, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }
}
