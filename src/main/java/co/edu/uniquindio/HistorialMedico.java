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



    public String generarHistorialMedico(Paciente paciente, LinkedList<Cita> citas) {
        StringBuilder historial = new StringBuilder();
        historial.append("Historial Médico de: ").append(paciente.getNombre()).append("\n");

        int contador = 0;
        for (Cita cita : citas) {
            if (cita.getPaciente().equals(paciente)) {
                paciente.getCitas().add(cita); // Asignar la cita al paciente
                historial.append("\nCita ").append(++contador).append(":\n");
                historial.append("ID: ").append(cita.getIdCita()).append("\n");
                historial.append("Fecha y Hora: ").append(cita.getHorario()).append("\n");
                historial.append("Diagnóstico: ").append(
                        cita.getDiagnostico() != null ? cita.getDiagnostico() : "No asignado"
                ).append("\n");
                historial.append("Tratamiento: ").append(
                        cita.getTratamiento() != null ? cita.getTratamiento() : "No asignado"
                ).append("\n");
                historial.append("Sala: ").append(cita.getSala().getIdSala()).append("\n");
            }
        }

        if (contador == 0) {
            return "No se encontraron citas registradas para este paciente.";
        }

        return historial.toString();
    }

}

