package org.example.Vista;

import org.example.Controlador.RuletaController;
import org.example.Controlador.SessionController;
import org.example.Modelo.Resultado;
import org.example.Modelo.Ruleta;
import org.example.Modelo.TipoApuesta;

import javax.swing.*;
import java.awt.*;

public class VentanaRuleta {
    private final JFrame frame = new JFrame("Ruleta Black Cat");
    private final JTextField txtMonto = new JTextField();
    private final JComboBox<TipoApuesta> comboApuesta = new JComboBox<>(TipoApuesta.values());
    private final JTextArea txtHistorial = new JTextArea();
    private final JButton btnJugar = new JButton("Jugar");
    private final JButton btnVolver = new JButton("Volver");

    private final RuletaController controller;

    public VentanaRuleta(SessionController session) {
        this.controller = new RuletaController(new Ruleta(), session);
        configurarVentana();
        configurarEventos();
    }

    private void configurarVentana() {
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new GridLayout(2, 2, 5, 5));
        panelSuperior.add(new JLabel("Tipo de apuesta:"));
        panelSuperior.add(comboApuesta);
        panelSuperior.add(new JLabel("Monto:"));
        panelSuperior.add(txtMonto);

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnJugar);
        panelBoton.add(btnVolver);

        txtHistorial.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtHistorial);

        frame.add(panelSuperior, BorderLayout.NORTH);
        frame.add(scroll, BorderLayout.CENTER);
        frame.add(panelBoton, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
    }

    private void configurarEventos() {
        btnJugar.addActionListener(e -> iniciarRonda());
        btnVolver.addActionListener(e -> {
            frame.dispose();
            new VentanaMenu(controller.getSession()).mostrarVentana();
        });
    }

    private void iniciarRonda() {
        try {
            int monto = Integer.parseInt(txtMonto.getText());
            TipoApuesta tipo = (TipoApuesta) comboApuesta.getSelectedItem();

            Resultado resultado = controller.jugarApuesta(tipo, monto);

            String mensaje = "Número: " + resultado.getNumero() + " (" + resultado.getColor() + ")\n";
            mensaje += resultado.isAcierto() ? "¡Ganaste!\n" : "Perdiste.\n";

            JOptionPane.showMessageDialog(frame, mensaje);
            txtHistorial.append(mensaje + "-----------------------------\n");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }
}
