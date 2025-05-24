package co.edu.uniquindio;

public class Administrador extends Usuario {
    private HospitalUQ hospitalUQ;

    public Administrador(String nombre, String id, String correo, String telefono) {
        super(nombre, id, correo, telefono);
        this.hospitalUQ = hospitalUQ;
    }

    public HospitalUQ getHospitalUQ() {
        return hospitalUQ;
    }

    public void setHospitalUQ(HospitalUQ hospitalUQ) {
        this.hospitalUQ = hospitalUQ;
    }

    public boolean registrarSala (Sala sala) {
        return hospitalUQ.registrarSala(sala);
    }

    public boolean eliminarSala (String idSala) {
        return hospitalUQ.eliminarSala(idSala);
    }

    public boolean registrarPaciente (Paciente paciente) {
        return hospitalUQ.registrarPaciente(paciente);
    }

    public Paciente modificarPaciente(Paciente paciente){
        return hospitalUQ.modificarPaciente(paciente);
    }

    public boolean eliminarPaciente(String idPaciente){
        return hospitalUQ.eliminarSala(idPaciente);
    }

    public boolean registrarMetodo(Medico medico){
        return hospitalUQ.registrarMedico(medico);
    }

    public Medico modificarMedico(Medico medico){
        return hospitalUQ.modificarMedico(medico);
    }

    public boolean eliminarMedico(String idMedico) {
        return hospitalUQ.eliminarSala(idMedico);
    }

    public boolean registrarHorario(Horario horario){
        return hospitalUQ.registrarHorario(horario);
    }

    public boolean eliminarHorario(String idHorario){
        return hospitalUQ.eliminarHorario(idHorario);
    }
}
