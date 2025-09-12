package org.example;

public class Usuario {
    private String usuario;
    private String clave;
    private String nombre;

    public Usuario(String usuario, String clave, String nombre) {
        this.usuario = usuario;
        this.clave = clave;
        this.nombre = nombre;
    }

    public String getUsuario() { return usuario; }
    public String getClave() { return clave; }
    public String getNombre() { return nombre; }

    public boolean validarCredenciales(String usuario, String clave) {
        return this.usuario.equals(usuario) && this.clave.equals(clave);
    }
}
