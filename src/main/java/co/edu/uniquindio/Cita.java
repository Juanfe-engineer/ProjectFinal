package co.edu.uniquindio;

import java.util.LinkedList;

public class Cita {
    private String idCita;
    private Paciente paciente;
    private Medico medico;
    private Sala sala;
    private Horario horario;
    private EstadoCita estado;
    private DiagnosticoTratamiento diagnosticotratamiento;

    public Cita(String idCita, Paciente paciente, Medico medico, Sala sala, Horario horario, EstadoCita estado) {
        this.idCita = idCita;
        this.paciente = paciente;
        this.medico = medico;
        this.sala = sala;
        this.horario = horario;
        this.estado = estado;
    }

    public String getIdCita() {
        return idCita;
    }

    public void setIdCita(String idCita) {
        this.idCita = idCita;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public EstadoCita getEstado() {
        return estado;
    }

    public void setEstado(EstadoCita estado) {
        this.estado = estado;
    }

    public DiagnosticoTratamiento getDiagnosticotratamiento() {
        return diagnosticotratamiento;
    }

    public void setDiagnostico_tratamiento(DiagnosticoTratamiento diagnosticotratamiento) {
        this.diagnosticotratamiento = diagnosticotratamiento;
    }


    //
}
