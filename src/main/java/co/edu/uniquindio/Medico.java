package co.edu.uniquindio;

import javax.management.Notification;
import java.util.LinkedList;

public class Medico extends Usuario {
    private Especialidad especialidad;
    private EstadoMedico estado;
    private LinkedList<Horario> horario;
    private LinkedList<Cita> citaAsignadas;
    private Paciente pacienteAsignado;



    public Medico(String id, String nombre, String correo, String telefono, LinkedList<Notificacion> notificacion, Especialidad especialidad, EstadoMedico estado, LinkedList<Horario> horario, LinkedList<Cita> citaAsignadas, Paciente pacienteAsignado) {
        super(id, nombre, correo, telefono, notificacion);
        this.especialidad = especialidad;
        this.estado = estado;
        this.horario = new LinkedList<>();
        this.citaAsignadas = new LinkedList<>();
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

    public LinkedList<Horario> getHorario() {
        return horario;
    }

    public void setHorario(LinkedList<Horario> horario) {
        this.horario = horario;
    }

    public LinkedList<Cita> getCitaAsignadas() {
        return citaAsignadas;
    }

    public void setCitaAsignadas(LinkedList<Cita> citaAsignadas) {
        this.citaAsignadas = citaAsignadas;
    }

    public Paciente getPacienteAsignado() {
        return pacienteAsignado;
    }

    public void setPacienteAsignado(Paciente pacienteAsignado) {
        this.pacienteAsignado = pacienteAsignado;
    }
}

