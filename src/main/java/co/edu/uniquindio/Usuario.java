package co.edu.uniquindio;

import java.util.LinkedList;

abstract class Usuario {
    protected String nombre;
    protected String id;
    protected String correo;
    protected String telefono;
    protected LinkedList<Notificacion> notificaciones;

    public Usuario(String nombre, String id, String correo, String telefono, LinkedList<Notificacion> notificaciones) {
        this.nombre = nombre;
        this.id = id;
        this.correo = correo;
        this.telefono = telefono;
        this.notificaciones = new LinkedList<>();
    }
}
