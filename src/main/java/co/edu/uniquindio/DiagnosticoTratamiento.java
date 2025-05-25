package co.edu.uniquindio;

import java.time.LocalDate;

public class DiagnosticoTratamiento {
    private String diagnostico;
    private String tratamiento;
    private LocalDate fechaRegistro;
    private Medico registradoPor;

    public DiagnosticoTratamiento(String diagnostico, String tratamiento, LocalDate fechaRegistro, Medico registradoPor) {
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.fechaRegistro = fechaRegistro;
        this.registradoPor = registradoPor;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public Medico getRegistradoPor() {
        return registradoPor;

    }
    @Override
    public String toString(){
        return "Diagnostico: "+ diagnostico + "| Tratamiento: "+ tratamiento;

    }
}

