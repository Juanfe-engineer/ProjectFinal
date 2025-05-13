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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LinkedList<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setNotificaciones(LinkedList<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }
}
