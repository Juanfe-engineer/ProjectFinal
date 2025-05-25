package co.edu.uniquindio.model;

import java.time.LocalDate;

public class Notificacion {
    private String mensaje;
    private LocalDate fecha;
    private boolean leida;
    private Usuario receptor;

    public Notificacion(String mensaje, LocalDate fecha, boolean leida, Usuario receptor) {
        this.mensaje = mensaje;
        this.fecha = fecha;
        this.leida = leida;
        this.receptor = receptor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public boolean isLeida() {
        return leida;
    }

    public void setLeida(boolean leida) {
        this.leida = leida;
    }

    public Usuario getReceptor() {
        return receptor;
    }

    public void setReceptor(Usuario receptor) {
        this.receptor = receptor;
    }
}
