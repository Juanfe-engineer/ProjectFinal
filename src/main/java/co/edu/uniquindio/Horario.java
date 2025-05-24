package co.edu.uniquindio;

import java.time.LocalDate;
import java.time.LocalTime;

public class Horario {
    private String idHorario;
    private LocalDate fecha;
    private LocalTime hora;
    private Jornada jornada;

    public Horario(String idHorario, LocalDate fecha, LocalTime hora, Jornada jornada) {
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

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }
}

