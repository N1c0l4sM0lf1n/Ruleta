package org.example;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistro {
    private final JFrame frame = new JFrame("Registro - Casino Black Cat");
    private final JTextField txtUsuario = new JTextField();
    private final JPasswordField txtClave = new JPasswordField();
    private final JTextField txtNombre = new JTextField();
    private final JButton btnRegistrar = new JButton("Registrar");
    private final JButton btnCancelar = new JButton("Cancelar");

    public VentanaRegistro() {
        configurarVentana();
        configurarEventos();
    }

    private void configurarVentana() {
        frame.setSize(350, 220);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 🔹 Importante
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
        String usuario = txtUsuario.getText().trim();
        String clave = new String(txtClave.getPassword());
        String nombre = txtNombre.getText().trim();

        if (usuario.isEmpty() || clave.isEmpty() || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar que no exista el usuario
        for (Usuario u : VentanaLogin.USUARIOS) {
            if (u.getUsuario().equals(usuario)) {
                JOptionPane.showMessageDialog(frame, "El usuario ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        // Registrar nuevo usuario
        VentanaLogin.USUARIOS.add(new Usuario(usuario, clave, nombre));
        JOptionPane.showMessageDialog(frame, "Usuario registrado con éxito");

        volverLogin();
    }

    private void volverLogin() {
        frame.dispose();
        new VentanaLogin().mostrarVentana();
    }
}

