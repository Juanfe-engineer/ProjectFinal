package co.edu.uniquindio.model;

import java.time.LocalDate;
import java.util.LinkedList;


public class Medico extends Usuario {
    public Especialidad especialidad;
    public EstadoMedico estado;
    public LinkedList<Horario> horario;
    public LinkedList<Cita> citaAsignadas;
    public Paciente pacienteAsignado;
    public LinkedList<DiagnosticoTratamiento>  diagnosticoTratamiento;



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

    //Diagnosticar
    public DiagnosticoTratamiento diagnosticar(Paciente paciente, String diagnostico, String tratamiento, LocalDate fechaDeRegistro, Medico registradoPor) {
        DiagnosticoTratamiento diagnost = new DiagnosticoTratamiento(diagnostico, tratamiento, fechaDeRegistro, registradoPor);
        paciente.agregarDiagnostico(diagnost);
        return diagnost;
    }



    // Metodo para consultar el historial de  un paciente

    public String consultarHistorialPaciente (Paciente paciente) {
        StringBuilder historial = new StringBuilder();
        for (Cita c : paciente.getCitas()) {
            if (c.getPaciente().equals(paciente)) {
                historial.append("ID Cita: ").append(c.getIdCita()).append("\n");
                historial.append("Fecha: ").append(c.getHorario()).append("\n");
                historial.append("Diagnóstico y tratamiento: ")
                        .append(c.getDiagnostico() != null ? c.getDiagnostico() : "Sin diagnóstico")
                        .append(" / ")
                        .append(c.getTratamiento() != null ? c.getTratamiento() : "Sin tratamiento")
                        .append("\n");
                historial.append("Sala: ").append(c.getSala().getIdSala()).append("\n");
                historial.append("-----------------------------\n");
            }
        }
        if (historial.isEmpty()) {
            return "No hay historial disponible para este paciente.";
        }
        return historial.toString();
    }


    //




}

