package co.edu.uniquindio;

import javax.management.Notification;
import java.util.LinkedList;

public class Medico extends Usuario {
    private Especialidad especialidad;
    private EstadoMedico estado;
    private LinkedList<Horario> horario;
    private LinkedList<Cita> citaAsignadas;
    private Paciente pacienteAsignado;



    public Medico(String nombre, String id, String correo, String telefono, Especialidad especialidad, EstadoMedico estadoMedico) {
        super(nombre, id, correo, telefono);
        this.horario = new LinkedList<>();
        this.citaAsignadas = new LinkedList<>();
        this.especialidad = especialidad;
        this.estado = estadoMedico;

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

