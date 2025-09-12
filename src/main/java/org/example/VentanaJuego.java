package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class VentanaJuego {
    private final JFrame frame = new JFrame("Ruleta Black Cat");
    private final JTextArea txtHistorial = new JTextArea();
    private final JComboBox<String> comboApuesta = new JComboBox<>(new String[]{"Rojo", "Negro", "Par", "Impar"});
    private final JTextField txtMonto = new JTextField();
    private final JButton btnJugar = new JButton("Jugar");


    private static final int MAX_HISTORIAL = 100;
    private static final int[] historialNumeros = new int[MAX_HISTORIAL];
    private static final int[] historialApuestas = new int[MAX_HISTORIAL];
    private static final boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    private static int historialSize = 0;
    private static final Random rng = new Random();
    private static final int[] numerosRojos =
            {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};

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
            char tipo = traducirApuesta(comboApuesta.getSelectedItem().toString());

            ResultadoRonda resultado = ruleta.jugar(tipo, monto);

            String mensaje = "\nLa ruleta giró: " + resultado.getNumero() + " (" + resultado.getColor() + ")\n";
            mensaje += resultado.isAcierto() ? "¡Ganaste! +" + monto + " créditos\n" : "Perdiste -" + monto + " créditos\n";

            JOptionPane.showMessageDialog(frame, mensaje);

            txtHistorial.append(mensaje);
            txtHistorial.append("-----------------------------\n");

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

    private int girarRuleta() {
        return rng.nextInt(37);
    }

    private boolean evaluarResultado(int numero, char tipo) {
        return switch (tipo) {
            case 'R' -> esRojo(numero);
            case 'N' -> numero != 0 && !esRojo(numero);
            case 'P' -> numero != 0 && numero % 2 == 0;
            case 'I' -> numero % 2 != 0;
            default -> false;
        };
    }

    private boolean esRojo(int n) {
        for (int rojo : numerosRojos) {
            if (rojo == n) return true;
        }
        return false;
    }

    private void registrarResultado(int numero, int apuesta, boolean acierto) {
        if (historialSize < MAX_HISTORIAL) {
            historialNumeros[historialSize] = numero;
            historialApuestas[historialSize] = apuesta;
            historialAciertos[historialSize] = acierto;
            historialSize++;
        }
    }

    private void mostrarResultado(int numero, char tipo, int monto, boolean acierto) {
        String color = (numero == 0) ? "Verde" : (esRojo(numero) ? "Rojo" : "Negro");
        String mensaje = "\nLa ruleta giró: " + numero + " (" + color + ")\n";
        mensaje += acierto ? "¡Ganaste! +" + monto + " créditos\n" : "Perdiste -" + monto + " créditos\n";

        JOptionPane.showMessageDialog(frame, mensaje);

        txtHistorial.append(mensaje);
        txtHistorial.append("-----------------------------\n");
    }
    private final Ruleta ruleta = new Ruleta();

}
