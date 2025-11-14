package org.example.Vista;

import org.example.Controlador.SessionController;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistro {
    private final JFrame frame = new JFrame("Registro - Casino Black Cat");
    private final JTextField txtUsuario = new JTextField();
    private final JPasswordField txtClave = new JPasswordField();
    private final JTextField txtNombre = new JTextField();
    private final JButton btnRegistrar = new JButton("Registrar");
    private final JButton btnCancelar = new JButton("Cancelar");

    private final SessionController session;

    public VentanaRegistro(SessionController session) {
        this.session = session;
        configurarVentana();
        configurarEventos();
    }

    private void configurarVentana() {
        frame.setSize(350, 220);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2, 5, 5));

        frame.add(new JLabel("Usuario:"));
        frame.add(txtUsuario);
        frame.add(new JLabel("Clave:"));
        frame.add(txtClave);
        frame.add(new JLabel("Nombre completo:"));
        frame.add(txtNombre);

        frame.add(btnRegistrar);
        frame.add(btnCancelar);

        frame.setLocationRelativeTo(null);
    }

    private void configurarEventos() {
        btnRegistrar.addActionListener(e -> registrarUsuario());
        btnCancelar.addActionListener(e -> volverLogin());
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }

    private void registrarUsuario() {
        try {
            String usuario = txtUsuario.getText().trim();
            String clave = new String(txtClave.getPassword());
            String nombre = txtNombre.getText().trim();

            session.registrarUsuario(usuario, clave, nombre);

            JOptionPane.showMessageDialog(frame, "Usuario registrado con éxito");
            volverLogin();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void volverLogin() {
        frame.dispose();
        new VentanaLogin(session).mostrarVentana();
    }
}

