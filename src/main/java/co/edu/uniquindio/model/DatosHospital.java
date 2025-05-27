package co.edu.uniquindio.model;

import java.util.LinkedList;

/**
 * Clase para serializar/deserializar datos del hospital
 */
public class DatosHospital {

    public String nombreHospital;
    public String idHospital;
    public LinkedList<Paciente> pacientes;
    public LinkedList<Medico> medicos;
    public LinkedList<Sala> salas;
    public LinkedList<Horario> horarios;
    public LinkedList<Cita> citas;

    // Constructor vacío (necesario para JSON)
    public DatosHospital() {
        this.pacientes = new LinkedList<>();
        this.medicos = new LinkedList<>();
        this.salas = new LinkedList<>();
        this.horarios = new LinkedList<>();
        this.citas = new LinkedList<>();
    }

    // Getters y Setters
    public String getNombreHospital() {
        return nombreHospital;
    }

    public void setNombreHospital(String nombreHospital) {
        this.nombreHospital = nombreHospital;
    }

    public String getIdHospital() {
        return idHospital;
    }

    public void setIdHospital(String idHospital) {
        this.idHospital = idHospital;
    }

    public LinkedList<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(LinkedList<Paciente> pacientes) {
        this.pacientes = pacientes != null ? pacientes : new LinkedList<>();
    }

    public LinkedList<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(LinkedList<Medico> medicos) {
        this.medicos = medicos != null ? medicos : new LinkedList<>();
    }

    public LinkedList<Sala> getSalas() {
        return salas;
    }

    public void setSalas(LinkedList<Sala> salas) {
        this.salas = salas != null ? salas : new LinkedList<>();
    }

    public LinkedList<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(LinkedList<Horario> horarios) {
        this.horarios = horarios != null ? horarios : new LinkedList<>();
    }

    public LinkedList<Cita> getCitas() {
        return citas;
    }

    public void setCitas(LinkedList<Cita> citas) {
        this.citas = citas != null ? citas : new LinkedList<>();
    }
}