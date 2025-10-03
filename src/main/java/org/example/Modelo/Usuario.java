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
        this.usuario = usuario;
        this.clave = clave;
        setNombre(nombre);
        this.saldo = 0;
    }

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
        if (monto <= 0) {
            throw new IllegalArgumentException("El depósito debe ser positivo");
        }
        this.saldo += monto;
    }

    public void apostar(int monto) {
        if (monto <= 0 || monto > saldo) {
            throw new IllegalArgumentException("Monto de apuesta inválido");
        }
        this.saldo -= monto;
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

