package co.edu.uniquindio;

import javax.management.Notification;
import java.util.LinkedList;
import java.util.UUID;

public class Paciente extends Usuario implements IGestionableCitas {
    private HistorialMedico historialMedico;
    private Medico medicoAsignado;
    private LinkedList<Cita> citas;
    private LinkedList<DiagnosticoTratamiento> diagnosticos;
    private HospitalUQ hospital;

    public Paciente(String nombre, String id, String correo, String telefono) {
        super(nombre, id, correo, telefono);
        this.citas = new LinkedList<>();
        this.historialMedico = null;
        this.diagnosticos = new LinkedList<>();
        this.hospital = hospital;
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

    public void agregarDiagnostico(DiagnosticoTratamiento diagnostico) {
        diagnosticos.add(diagnostico);
    }
    public void setHospital(HospitalUQ hospital) {
        this.hospital = hospital;
    }



    public boolean solicitarCita(Especialidad especialidad) {
       Medico medico = hospital.buscarMedicoPorEspecialidad(especialidad);
       if (medico == null) {
           return false;
       }
       if(medico.getEstado().equals(EstadoMedico.DISPONIBLE)){
           return true ;
        }
       return false;

    }


    //Cancalar cita
    public boolean cancelarCita(String idCita) {
        for (Cita cita : citas) {
            if (cita.getIdCita().equals(idCita)) {
                if (cita.getEstado() != EstadoCita.CANCELADA) {
                    cita.setEstado(EstadoCita.CANCELADA);

                    // Liberar sala y médico
                    if (cita.getMedico() != null) {
                        cita.getMedico().setEstado(EstadoMedico.DISPONIBLE);
                    }
                    if (cita.getSala() != null) {
                        cita.getSala().setEstado(EstadoSala.DISPONIBLE);
                    }

                    return true;
                }
            }
        }
        return false;
    }

}