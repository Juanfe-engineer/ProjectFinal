package co.edu.uniquindio.model;

import java.util.LinkedList;

public class DatosHospital {
    private LinkedList<Paciente> pacientes;
    private LinkedList<Medico> medicos;
    private LinkedList<Cita> citas;
    private LinkedList<Sala> salas;
    private LinkedList<Horario> horarios;

    public DatosHospital() {
        pacientes = new LinkedList<>();
        medicos = new LinkedList<>();
        citas = new LinkedList<>();
        salas = new LinkedList<>();
        horarios = new LinkedList<>();
    }

    public LinkedList<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(LinkedList<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public LinkedList<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(LinkedList<Medico> medicos) {
        this.medicos = medicos;
    }

    public LinkedList<Cita> getCitas() {
        return citas;
    }

    public void setCitas(LinkedList<Cita> citas) {
        this.citas = citas;
    }

    public LinkedList<Sala> getSalas() {
        return salas;
    }

    public void setSalas(LinkedList<Sala> salas) {
        this.salas = salas;
    }

    public LinkedList<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(LinkedList<Horario> horarios) {
        this.horarios = horarios;
    }
}
