package co.edu.uniquindio;

import javax.management.Notification;
import java.util.LinkedList;


public class Medico extends Usuario {
    private Especialidad especialidad;
    private EstadoMedico estado;
    private LinkedList<Horario> horario;
    private LinkedList<Cita> citaAsignadas;
    private Paciente pacienteAsignado;
    private LinkedList<DiagnosticoTratamiento>  diagnosticoTratamiento;



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

    public LinkedList<DiagnosticoTratamiento> getDiagnosticoTratamiento() {
        return diagnosticoTratamiento;
    }

    public void setDiagnosticoTratamiento(LinkedList<DiagnosticoTratamiento> diagnosticoTratamiento) {
        this.diagnosticoTratamiento = diagnosticoTratamiento;
    }


    //Metodo 
    public boolean diagnosticarPaciente(String idCita, String diagnostico) {
        for (Cita c : citaAsignadas) {
            if (c.getIdCita().equals(idCita)) {
                c.setDiagnostico(diagnostico);
                return true;
            }
        }
        return false; // Cita no encontrada
    }

    public boolean asignarTratamientoDiagnostico(String idCita, String tratamiento) {
        for (Cita c : citaAsignadas) {
            if (c.getIdCita().equals(idCita)) {
                c.setTratamiento(tratamiento);
                return true;
            }
        }
        return false; // Cita no encontrada
    }

    public boolean modificarDiagnostico(String idCita, String nuevoDiagnostico) {
        for (Cita c : citaAsignadas) {
            if (c.getIdCita().equals(idCita)) {
                c.setDiagnostico(nuevoDiagnostico);
                return true;
            }
        }
        return false; // Cita no encontrada
    }

    public boolean modificarTratamiento(String idCita, String nuevoTratamiento) {
        for (Cita c : citaAsignadas) {
            if (c.getIdCita().equals(idCita)) {
                c.setTratamiento(nuevoTratamiento);
                return true;
            }
        }
        return false; // Cita no encontrada
    }

    public String consultarHistorialPaciente(Paciente paciente) {
        StringBuilder historial = new StringBuilder();
        for (Cita c : citaAsignadas) {
            if (c.getPaciente().equals(paciente)) {
                historial.append("ID Cita: ").append(c.getIdCita()).append("\n");
                historial.append("Fecha: ").append(c.getHorario()).append("\n");
                historial.append("Diagnóstico: ").append(c.getDiagnostico()).append("\n");
                historial.append("Tratamiento: ").append(c.getTratamiento()).append("\n");
                historial.append("Sala: ").append(c.getSala().getIdSala()).append("\n");
                historial.append("-----------------------------\n");
            }
        }
        if (historial.length() == 0) {
            return "No hay historial disponible para este paciente.";
        }
        return historial.toString();
    }


    //




}

