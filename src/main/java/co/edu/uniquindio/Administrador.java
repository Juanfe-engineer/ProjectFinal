package co.edu.uniquindio;

public class Administrador extends Usuario {
    private HospitalUQ hospitalUQ;

    public Administrador(String nombre, String id, String correo, String telefono, HospitalUQ hospitalUQ) {
        super(nombre, id, correo, telefono);
        this.hospitalUQ = hospitalUQ;
    }

    public HospitalUQ getHospitalUQ() {
        return hospitalUQ;
    }

    public void setHospitalUQ(HospitalUQ hospitalUQ) {
        this.hospitalUQ = hospitalUQ;
    }

    // Metodos

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

    public boolean registrarMedico(Medico medico){

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

    public boolean asignarMedicoAPaciente(String idPaciente, String idMedico){
        return hospitalUQ.asignarMedicoAPaciente(idPaciente, idMedico);
    }

    public boolean asignarSalaACita(String idCita, String idSala){
        return hospitalUQ.asignarSalaACita(idCita, idSala);
    }

    public boolean asignarHorarioACita(String idCita, Horario horario){
        return hospitalUQ.asignarHorarioACita(idCita, horario);
    }

    public String verReporte(String idCita){
        return hospitalUQ.verReporteCitas(idCita);
    }

    public String generarReporteOcupacionSalas(){
        return hospitalUQ.generarReporteOcupacionSalas();
    }



}
