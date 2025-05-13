package co.edu.uniquindio;

import javax.management.Notification;
import java.util.LinkedList;

public class Paciente extends Usuario{
    private HistorialMedico historialMedico;
    private LinkedList<Cita> citas;

    public Paciente(String id, String nombre, String correo, String telefono, LinkedList<Notificacion> notificacion, HistorialMedico historialMedico, LinkedList<Cita> citas) {
        super(id, nombre, correo, telefono, notificacion);
        this.historialMedico = historialMedico;
        this.citas = new LinkedList<>();
    }

    public HistorialMedico getHistorialMedico() {
        return historialMedico;
    }

    public void setHistorialMedico(HistorialMedico historialMedico) {
        this.historialMedico = historialMedico;
    }

    public LinkedList<Cita> getCitas() {
        return citas;
    }

    public void setCitas(LinkedList<Cita> citas) {
        this.citas = citas;
    }
}

