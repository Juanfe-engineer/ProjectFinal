package co.edu.uniquindio.model;

public interface IGestionableCitas {
    public boolean solicitarCita(Especialidad especialidad);
    public boolean cancelarCita(String idCitas);
}
