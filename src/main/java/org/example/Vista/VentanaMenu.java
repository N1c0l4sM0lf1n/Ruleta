package org.example.Vista;

import org.example.Controlador.SessionController;

import javax.swing.*;
import java.awt.*;

public class VentanaMenu {
    private final JFrame frame = new JFrame("Menú Principal - Casino Black Cat");
    private final JLabel lblBienvenida = new JLabel();
    private final JLabel lblSaldo = new JLabel();
    private final JButton btnRuleta = new JButton("Jugar Ruleta");
    private final JButton btnPerfil = new JButton("Perfil");
    private final JButton btnHistorial = new JButton("Historial");
    private final JButton btnCerrarSesion = new JButton("Cerrar Sesión");

    private final SessionController session;

    public VentanaMenu(SessionController session) {
        this.session = session;
        configurarVentana();
        configurarEventos();
        refrescar();
    }

    private void configurarVentana() {
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 1, 10, 10));

        frame.add(lblBienvenida);
        frame.add(lblSaldo);
        frame.add(btnRuleta);
        frame.add(btnPerfil);
        frame.add(btnHistorial);
        frame.add(btnCerrarSesion);

        frame.setLocationRelativeTo(null);
    }

    private void configurarEventos() {
        btnRuleta.addActionListener(e -> {
            frame.dispose();
            new VentanaRuleta(session).mostrarVentana();
        });
        btnPerfil.addActionListener(e -> {
            frame.dispose();
            new VentanaPerfil(session).mostrarVentana();
        });
        btnHistorial.addActionListener(e -> {
            frame.dispose();
            new VentanaHistorial(session).mostrarVentana();
        });
        btnCerrarSesion.addActionListener(e -> {
            session.cerrarSesion();
            frame.dispose();
            new VentanaLogin(session).mostrarVentana();
        });
    }

    private void refrescar() {
        lblBienvenida.setText("Bienvenido " + session.getUsuarioActual().getNombre());
        lblSaldo.setText("Saldo: $" + session.getUsuarioActual().getSaldo());
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }
}
