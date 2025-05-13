package co.edu.uniquindio;

import java.util.LinkedList;

public class HistorialMedico {
    private Paciente paciente;
    private LinkedList<DiagnosticoTratamiento> registros;

    public HistorialMedico(Paciente paciente, LinkedList<DiagnosticoTratamiento> registros) {
        this.paciente = paciente;
        this.registros = registros;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LinkedList<DiagnosticoTratamiento> getRegistros() {
        return registros;
    }

    public void setRegistros(LinkedList<DiagnosticoTratamiento> registros) {
        this.registros = registros;
    }
}

