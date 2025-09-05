package org.example;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaLogin {
    public static final List<Usuario> USUARIOS = new ArrayList<>();

    private final JFrame frame = new JFrame("Login - Casino Black Cat");
    private final JTextField txtUsuario = new JTextField();
    private final JPasswordField txtClave = new JPasswordField();
    private final JButton btnIngresar = new JButton("Ingresar");
    private final JButton btnRegistrar = new JButton("Registrar");

    public VentanaLogin() {
        inicializarUsuarios();
        configurarVentana();
        configurarEventos();
    }

    private void inicializarUsuarios() {
        USUARIOS.add(new Usuario("admin", "1234", "Administrador"));
        USUARIOS.add(new Usuario("nico", "abcd", "Nicolás"));
    }

    private void configurarVentana() {
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2, 5, 5));

        frame.add(new JLabel("Usuario:"));
        frame.add(txtUsuario);
        frame.add(new JLabel("Clave:"));
        frame.add(txtClave);
        frame.add(btnIngresar);
        frame.add(btnRegistrar);

        frame.setLocationRelativeTo(null);
    }

    private void configurarEventos() {
        btnIngresar.addActionListener(e -> login());
        btnRegistrar.addActionListener(e -> abrirRegistro());
    }

    public void mostrarVentana() {
        frame.setVisible(true);
    }

    private void login() {
        String usuario = txtUsuario.getText();
        String clave = new String(txtClave.getPassword());

        String nombre = validarCredenciales(usuario, clave);

        if (!nombre.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Bienvenido, " + nombre);
            frame.dispose();
            new VentanaSaludo(nombre).mostrarVentana();
        } else {
            JOptionPane.showMessageDialog(frame, "Credenciales inválidas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String validarCredenciales(String u, String p) {
        for (Usuario user : USUARIOS) {
            if (user.validarCredenciales(u, p)) {
                return user.getNombre();
            }
        }
        return "";
    }

    // ✅ Abrir ventana de registro
    private void abrirRegistro() {
        frame.dispose();
        new VentanaRegistro().mostrarVentana();
    }
}

