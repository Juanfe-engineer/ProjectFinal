package co.edu.uniquindio;

import java.time.LocalDate;

public class Horario {
    private String idHorario;
    private LocalDate fecha;
    private LocalDate hora;
    private Jornada jornada;

    public Horario(String idHorario, LocalDate fecha, LocalDate hora, Jornada jornada) {
        this.idHorario = idHorario;
        this.fecha = fecha;
        this.hora = hora;
        this.jornada = jornada;
    }

    public String getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(String idHorario) {
        this.idHorario = idHorario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalDate getHora() {
        return hora;
    }

    public void setHora(LocalDate hora) {
        this.hora = hora;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }
}

