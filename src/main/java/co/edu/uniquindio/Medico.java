package co.edu.uniquindio;

import javax.management.Notification;
import java.util.LinkedList;

public class Medico extends Usuario {
    private Especialidad especialidad;
    private EstadoMedico estado;
    private Horario horario;
    private Cita citaAsignadas;
    private Paciente pacienteAsignado;



    public Medico(String id, String nombre, String correo, String telefono, Notification notificacion, Especialidad especialidad, EstadoMedico estado, Horario horario, Cita citaAsignadas, Paciente pacienteAsignado) {
        super(id, nombre, correo, telefono, notificacion);
        this.especialidad = especialidad;
        this.estado = estado;
        this.horario = horario;
        this.citaAsignadas = citaAsignadas;
        this.pacienteAsignado = pacienteAsignado;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public EstadoMedico getEstado() {
        return estado;
    }

    public void setEstado(EstadoMedico estado) {
        this.estado = estado;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Cita getCitaAsignadas() {
        return citaAsignadas;
    }

    public void setCitaAsignadas(Cita citaAsignadas) {
        this.citaAsignadas = citaAsignadas;
    }

    public Paciente getPacienteAsignado() {
        return pacienteAsignado;
    }

    public void setPacienteAsignado(Paciente pacienteAsignado) {
        this.pacienteAsignado = pacienteAsignado;
    }
}

