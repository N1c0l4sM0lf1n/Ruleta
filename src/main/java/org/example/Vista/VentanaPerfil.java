package org.example.Vista;

import org.example.Controlador.SessionController;

import javax.swing.*;
import java.awt.*;

public class VentanaPerfil {
    private final JFrame frame = new JFrame("Perfil de Usuario");
    private final JTextField txtNombre = new JTextField();
    private final JLabel lblUsuario = new JLabel();
    private final JLabel lblSaldo = new JLabel();
    private final JButton btnGuardar = new JButton("Guardar Nombre");
    private final JButton btnRecargar = new JButton("Recargar Saldo");
    private final JButton btnVolver = new JButton("Volver al Menú");

    private final SessionController session;

    public VentanaPerfil(SessionController session) {
        this.session = session;
        configurarVentana();
        configurarEventos();
        refrescar();
    }

    private void configurarVentana() {
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2, 10, 10));

        frame.add(new JLabel("Usuario:"));
        frame.add(lblUsuario);
        frame.add(new JLabel("Nombre:"));
        frame.add(txtNombre);
        frame.add(new JLabel("Saldo:"));
        frame.add(lblSaldo);

        frame.add(btnGuardar);
        frame.add(btnRecargar);
        frame.add(btnVolver);

        frame.setLocationRelativeTo(null);
    }

    private void configurarEventos() {
        btnGuardar.addActionListener(e -> {
            try {
                session.getUsuarioActual().setNombre(txtNombre.getText().trim());
                JOptionPane.showMessageDialog(frame, "Nombre actualizado con éxito");
                refrescar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnRecargar.addActionListener(e -> {
            String montoStr = JOptionPane.showInputDialog("Ingrese monto a recargar:");
            try {
                int monto = Integer.parseInt(montoStr);
                session.getUsuarioActual().depositar(monto);
                JOptionPane.showMessageDialog(frame, "Saldo recargado");
                refrescar();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Monto inválido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnVolver.addActionListener(e -> {
            frame.dispose();
            new VentanaMenu(session).mostrarVentana();
        });
    }

    private void refrescar() {
        lblUsuario.setText(session.getUsuarioActual().getUsuario());
        txtNombre.setText(session.getUsuarioActual().getNombre());
        lblSaldo.setText("$" + session.getUsuarioActual().getSaldo());
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }
}
