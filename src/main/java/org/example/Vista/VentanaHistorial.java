package org.example.Vista;

import org.example.Controlador.ResultadoController;
import org.example.Controlador.SessionController;
import org.example.Modelo.Resultado;

import javax.swing.*;
import java.awt.*;

public class VentanaHistorial {
    private final JFrame frame = new JFrame("Historial de Jugadas");
    private final JTextArea txtHistorial = new JTextArea();
    private final JButton btnVolver = new JButton("Volver");

    private final ResultadoController controller;

    public VentanaHistorial(SessionController session) {
        this.controller = new ResultadoController(session);
        configurarVentana();
        configurarEventos();
        cargarHistorial();
    }

    private void configurarVentana() {
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        txtHistorial.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtHistorial);

        frame.add(scroll, BorderLayout.CENTER);
        frame.add(btnVolver, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
    }

    private void configurarEventos() {
        btnVolver.addActionListener(e -> {
            frame.dispose();
            new VentanaMenu(controller.getSession()).mostrarVentana();
        });
    }

    private void cargarHistorial() {
        StringBuilder sb = new StringBuilder();
        for (Resultado r : controller.obtenerHistorial()) {
            sb.append("Número: ").append(r.getNumero())
                    .append(" - Apuesta: ").append(r.getTipoApuesta())
                    .append(" - ").append(r.isAcierto() ? "Ganó" : "Perdió")
                    .append(" - Monto: ").append(r.getMonto())
                    .append("\n");
        }
        txtHistorial.setText(sb.toString());
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }
}
