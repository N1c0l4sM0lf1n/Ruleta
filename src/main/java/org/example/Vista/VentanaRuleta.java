package org.example.Vista;

import org.example.Controlador.RuletaController;
import org.example.Controlador.SessionController;
import org.example.Modelo.RepositorioArchivo;
import org.example.Modelo.Resultado;
import org.example.Modelo.Ruleta;

import javax.swing.*;
import java.awt.*;

public class VentanaRuleta {

    private final JFrame frame = new JFrame("Ruleta Black Cat");

    private final JTextField txtMonto = new JTextField();
    private final JComboBox<String> comboApuesta = new JComboBox<>(
            new String[]{"Rojo", "Negro", "Par", "Impar"}
    );
    private final JTextArea txtHistorial = new JTextArea();
    private final JLabel lblSaldo = new JLabel(); // ⭐ saldo real del usuario

    private final JButton btnJugar = new JButton("Jugar");
    private final JButton btnVolver = new JButton("Volver");

    private final RuletaController controller;

    public VentanaRuleta(SessionController session) {

        this.controller = new RuletaController(
                session,
                new Ruleta(new RepositorioArchivo("historial.csv"))
        );

        configurarVentana();
        configurarEventos();
        refrescarSaldo();
    }

    private void configurarVentana() {
        frame.setSize(580, 420);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new GridLayout(3, 2, 5, 5));
        panelSuperior.add(new JLabel("Saldo actual:"));
        panelSuperior.add(lblSaldo);

        panelSuperior.add(new JLabel("Tipo de apuesta:"));
        panelSuperior.add(comboApuesta);

        panelSuperior.add(new JLabel("Monto:"));
        panelSuperior.add(txtMonto);

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnJugar);
        panelBotones.add(btnVolver);

        txtHistorial.setEditable(false);

        frame.add(panelSuperior, BorderLayout.NORTH);
        frame.add(new JScrollPane(txtHistorial), BorderLayout.CENTER);
        frame.add(panelBotones, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
    }

    private void configurarEventos() {

        btnJugar.addActionListener(e -> jugar());

        btnVolver.addActionListener(e -> {
            frame.dispose();
            new VentanaMenu(controller.getSession()).mostrarVentana();
        });
    }

    private void refrescarSaldo() {
        int saldo = controller.getSession().getUsuarioActual().getSaldo();
        lblSaldo.setText("$" + saldo);
    }

    private void jugar() {
        try {
            int monto = Integer.parseInt(txtMonto.getText());
            String tipo = (String) comboApuesta.getSelectedItem();

            Resultado r = controller.jugarApuesta(tipo, monto);

            String mensaje = "Número: " + r.getNumero() + "\n" +
                    "Apuesta: " + r.getEtiqueta() + "\n" +
                    (r.isAcierto() ? "¡Ganaste!\n" : "Perdiste.\n");

            JOptionPane.showMessageDialog(frame, mensaje);
            txtHistorial.append(mensaje + "----------------------\n");

            refrescarSaldo();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }
}
