package co.edu.uniquindio.model;

public interface IGestionableCitas {
    public Cita solicitarCita(String tratamiento,String Diagnostico, Especialidad especialidad, String idCita, String idSala, Horario horario );
    public boolean cancelarCita(String idCitas);
}
