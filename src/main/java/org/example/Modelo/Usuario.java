package org.example.Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Usuario {

    private String usuario;
    private String clave;
    private String nombre;
    private int saldo;

    private final List<Resultado> historial = new ArrayList<>();

    public Usuario(String usuario, String clave, String nombre) {
        if (usuario == null || usuario.isBlank())
            throw new IllegalArgumentException("Usuario inválido");
        if (clave == null || clave.isBlank())
            throw new IllegalArgumentException("Clave inválida");
        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("Nombre inválido");

        this.usuario = usuario;
        this.clave = clave;
        this.nombre = nombre;
        this.saldo = 0; // saldo inicial por defecto
    }

    // Constructor usado solo para iniciar con usuario invitado si quieres
    public Usuario() {
        this("invitado", "1234", "Invitado");
    }

    public String getUsuario() { return usuario; }
    public String getClave() { return clave; }
    public String getNombre() { return nombre; }
    public int getSaldo() { return saldo; }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }

    public void depositar(int monto) {
        if (monto <= 0)
            throw new IllegalArgumentException("El depósito debe ser positivo");
        saldo += monto;
    }

    public void apostar(int monto) {
        if (monto <= 0)
            throw new IllegalArgumentException("Monto de apuesta inválido");
        if (monto > saldo)
            throw new IllegalArgumentException("Saldo insuficiente");
        saldo -= monto;
    }

    public void agregarResultado(Resultado r) {
        historial.add(r);
    }

    public List<Resultado> getHistorial() {
        return Collections.unmodifiableList(historial);
    }

    public boolean validarCredenciales(String usuario, String clave) {
        return this.usuario.equals(usuario) && this.clave.equals(clave);
    }
}
