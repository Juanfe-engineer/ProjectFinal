package co.edu.uniquindio;

import javax.management.Notification;
import java.util.LinkedList;

public class Paciente extends Usuario{
    private HistorialMedico historialMedico;
    private Medico medicoAsignado;
    private LinkedList<Cita> citas;

    public Paciente(String nombre,String id, String correo, String telefono) {
        super(nombre, id, correo, telefono);
        this.citas = new LinkedList<>();
        this.historialMedico = null;
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

    public Medico getMedicoAsignado() {
        return medicoAsignado;
    }

    public void setMedicoAsignado(Medico medicoAsignado) {
        this.medicoAsignado = medicoAsignado;
    }
}

