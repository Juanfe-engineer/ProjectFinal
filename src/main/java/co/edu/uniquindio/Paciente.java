package co.edu.uniquindio;

import javax.management.Notification;

public class Paciente extends Usuario{
    private HistorialMedico historialMedico;
    private Cita citas;

    public Paciente(String id, String nombre, String correo, String telefono, Notification notificacion, HistorialMedico historialMedico, Cita citas) {
        super(id, nombre, correo, telefono, notificacion);
        this.historialMedico = historialMedico;
        this.citas = citas;
    }

    public HistorialMedico getHistorialMedico() {
        return historialMedico;
    }

    public void setHistorialMedico(HistorialMedico historialMedico) {
        this.historialMedico = historialMedico;
    }

    public Cita getCitas() {
        return citas;
    }

    public void setCitas(Cita citas) {
        this.citas = citas;
    }
}

