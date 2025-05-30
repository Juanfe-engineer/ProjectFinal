package co.edu.uniquindio.model;

public class Cita {
    public String idCita;
    public Paciente paciente;
    public Medico medico;
    public Sala sala;
    public Horario horario;
    public EstadoCita estado;
    public String Diagnostico;
    public String tratamiento;

    public Cita(String Diagnostico, String tratamiento, String idCita, Paciente paciente, Medico medico, Sala sala, Horario horario, EstadoCita estado ) {
        this.idCita = idCita;
        this.paciente = paciente;
        this.medico = medico;
        this.sala = sala;
        this.horario = horario;
        this.estado = estado;
        this.Diagnostico = Diagnostico;
        this.tratamiento = tratamiento;
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

    public String getDiagnostico() {
        return Diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setDiagnostico(String diagnostico) {
        this.Diagnostico = Diagnostico;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }


    //
}
