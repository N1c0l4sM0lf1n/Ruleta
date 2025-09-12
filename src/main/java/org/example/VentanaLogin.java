package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaLogin extends JFrame {
    public static final List<Usuario> USUARIOS = new ArrayList<>();
    private JTextField txtUsuario;
    private JPasswordField txtClave;
    private JButton btnIngresar;
    private JButton btnRegistrar;

    public VentanaLogin() {
        setTitle("Login - Ruleta");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2, 10, 10));

        JLabel lblUsuario = new JLabel("Usuario:");
        JLabel lblClave = new JLabel("Clave:");

        txtUsuario = new JTextField();
        txtClave = new JPasswordField();

        btnIngresar = new JButton("Ingresar");
        btnRegistrar = new JButton("Registrar");


        btnIngresar.setBackground(new Color(102, 0, 153));
        btnIngresar.setForeground(Color.WHITE);

        txtUsuario.setBackground(new Color(40, 40, 40));
        txtUsuario.setForeground(Color.WHITE);
        txtUsuario.setCaretColor(Color.WHITE);

        txtClave.setBackground(new Color(40, 40, 40));
        txtClave.setForeground(Color.WHITE);
        txtClave.setCaretColor(Color.WHITE);

        getContentPane().setBackground(new Color(25, 25, 35));

        add(lblUsuario); add(txtUsuario);
        add(lblClave); add(txtClave);
        add(btnIngresar); add(btnRegistrar);

        configurarEventos();
    }

    private void configurarEventos() {
        btnIngresar.addActionListener(e -> intentarLogin());
        btnRegistrar.addActionListener(e -> {
            dispose();
            new VentanaRegistro().mostrarVentana();
        });
    }

    private void intentarLogin() {
        String usuario = txtUsuario.getText().trim();
        String clave = new String(txtClave.getPassword());

        for (Usuario u : USUARIOS) {
            if (u.validarCredenciales(usuario, clave)) {
                dispose();
                new VentanaSaludo(u.getNombre()).mostrarVentana();
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Usuario o clave incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void mostrarVentana() {
        setVisible(true);
    }
}



