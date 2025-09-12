package org.example;

import javax.swing.*;
import java.awt.*;

public class VentanaJuego {
    private final JFrame frame = new JFrame("Ruleta Black Cat");
    private final JTextArea txtHistorial = new JTextArea();
    private final JComboBox<String> comboApuesta = new JComboBox<>(new String[]{"Rojo", "Negro", "Par", "Impar"});
    private final JTextField txtMonto = new JTextField();
    private final JButton btnJugar = new JButton("Jugar");

    private final Ruleta ruleta = new Ruleta();

    public VentanaJuego(String nombre) {
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new GridLayout(2, 2, 5, 5));
        panelSuperior.add(new JLabel("Tipo de apuesta:"));
        panelSuperior.add(comboApuesta);
        panelSuperior.add(new JLabel("Monto:"));
        panelSuperior.add(txtMonto);

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnJugar);

        txtHistorial.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtHistorial);

        frame.add(new JLabel("Jugador: " + nombre, SwingConstants.CENTER), BorderLayout.NORTH);
        frame.add(panelSuperior, BorderLayout.CENTER);
        frame.add(panelBoton, BorderLayout.SOUTH);
        frame.add(scroll, BorderLayout.EAST);

        configurarEventos();
        frame.setLocationRelativeTo(null);
    }

    private void configurarEventos() {
        btnJugar.addActionListener(e -> iniciarRonda());
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }

    private void iniciarRonda() {
        try {
            int monto = Integer.parseInt(txtMonto.getText());
            char tipo = traducirApuesta((String) comboApuesta.getSelectedItem());

            ResultadoRonda resultado = ruleta.jugar(tipo, monto);

            String mensaje = "\nLa ruleta giró: " + resultado.getNumero() + " (" + resultado.getColor() + ")\n";
            mensaje += resultado.isAcierto() ? "¡Ganaste! +" + monto + " créditos\n" : "Perdiste -" + monto + " créditos\n";

            JOptionPane.showMessageDialog(frame, mensaje);
            txtHistorial.append(mensaje + "-----------------------------\n");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Ingrese un monto válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private char traducirApuesta(String opcion) {
        return switch (opcion) {
            case "Rojo" -> 'R';
            case "Negro" -> 'N';
            case "Par" -> 'P';
            case "Impar" -> 'I';
            default -> ' ';
        };
    }
}

