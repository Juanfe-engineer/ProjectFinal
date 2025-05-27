package co.edu.uniquindio.model;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Horario {
    public String idHorario;
    public LocalDate fecha;
    public LocalTime hora;
    public Jornada jornada;

    public Horario(String idHorario, LocalDate fecha, LocalTime hora, Jornada jornada) {
        this.idHorario = idHorario;
        this.fecha = fecha;
        this.hora = hora;
        this.jornada = jornada;
    }

    // Método toString() implementado
    @Override
    public String toString() {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

        return fecha.format(formatoFecha) + " a las " + hora.format(formatoHora);
    }

    // Método alternativo más completo
    public String toStringCompleto() {
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

        return "Fecha: " + fecha.format(formatoFecha) +
                ", Hora: " + hora.format(formatoHora) +
                ", Jornada: " + jornada;
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



