package org.example.Vista;

import org.example.Controlador.ResultadoController;
import org.example.Controlador.SessionController;
import org.example.Modelo.Resultado;
import org.example.Modelo.Usuario;
import org.example.Modelo.Estadisticas;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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
        Usuario usuario = controller.getSession().getUsuarioActual();
        if (usuario == null) {
            txtHistorial.setText("No hay usuario en sesión.");
            return;
        }

        List<Resultado> historial = controller.obtenerHistorial();

        if (historial.isEmpty()) {
            txtHistorial.setText("Aún no hay jugadas registradas.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        int totalAciertos = 0;
        int totalApostado = 0;

        for (Resultado r : historial) {
            sb.append("Número: ").append(r.getNumero())
                    .append(" - Apuesta: ").append(r.getEtiqueta())
                    .append(" - ").append(r.isAcierto() ? "Ganó" : "Perdió")
                    .append(" - Monto: $").append(r.getMonto())
                    .append("\n");

            totalApostado += r.getMonto();
            if (r.isAcierto()) totalAciertos++;
        }

        int rondasJugadas = historial.size();
        double porcentaje = (rondasJugadas > 0) ? (totalAciertos * 100.0 / rondasJugadas) : 0;
        int gananciaNeta = usuario.getSaldo(); // simplificado

        Estadisticas est = new Estadisticas(rondasJugadas, totalApostado, totalAciertos, porcentaje, gananciaNeta);

        sb.append("\n📊 Estadísticas:\n");
        sb.append("Rondas jugadas: ").append(est.getRondasJugadas()).append("\n");
        sb.append("Total apostado: $").append(est.getTotalApostado()).append("\n");
        sb.append("Aciertos: ").append(est.getTotalAciertos()).append("\n");
        sb.append("Porcentaje de aciertos: ").append(String.format("%.2f", est.getPorcentajeAciertos())).append("%\n");
        sb.append("Ganancia neta (saldo actual): $").append(est.getGananciaNeta()).append("\n");

        txtHistorial.setText(sb.toString());
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }
}
