package co.edu.uniquindio.model;

public class Sala {
    public String idSala;
    public TipoSala tipoSala;
    public EstadoSala estado;
    public int capacidad;

    public Sala(String idSala, TipoSala tipoSala, EstadoSala estado, int capacidad) {
        this.idSala = idSala;
        this.tipoSala = tipoSala;
        this.estado = estado;
        this.capacidad = capacidad;

    }

    public String getIdSala() {
        return idSala;
    }

    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }

    public TipoSala getTipoSala(TipoSala urgencias) {
        return tipoSala;
    }

    public void setTipoSala(TipoSala tipoSala) {
        this.tipoSala = tipoSala;
    }

    public EstadoSala getEstado() {
        return estado;
    }

    public void setEstado(EstadoSala estado) {
        this.estado = estado;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public TipoSala getTipoSala() {
        return tipoSala;
    }
}
