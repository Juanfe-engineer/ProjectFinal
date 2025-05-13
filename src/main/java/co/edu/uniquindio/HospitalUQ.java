package co.edu.uniquindio;

import java.util.LinkedList;

public class HospitalUQ {
    private String nombre;
    private String id;
    private LinkedList<Medico> medicos;
    private LinkedList<Paciente> pacientes;
    private LinkedList<Sala> salas;
    private LinkedList<Cita> citas;
    private LinkedList<Notificacion> notificaciones;

    public HospitalUQ(String nombre, String id, LinkedList<Medico> medicos, LinkedList<Paciente> pacientes, LinkedList<Sala> salas, LinkedList<Cita> citas, LinkedList<Notificacion> notificaciones) {
        this.nombre = nombre;
        this.id = id;
        this.medicos = new LinkedList<>();
        this.pacientes = new LinkedList<>();
        this.salas = new LinkedList<>();
        this.citas = new LinkedList<>();
        this.notificaciones = new LinkedList<>();
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public LinkedList<Medico> getMedicos() {
        return medicos;
    }

    public LinkedList<Paciente> getPacientes() {
        return pacientes;
    }

    public LinkedList<Sala> getSalas() {
        return salas;
    }

    public LinkedList<Cita> getCitas() {
        return citas;
    }

    public LinkedList<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public void setMedicos(LinkedList<Medico> medicos) {
        this.medicos = medicos;
    }

    public void setPacientes(LinkedList<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public void setSalas(LinkedList<Sala> salas) {
        this.salas = salas;
    }

    public void setCitas(LinkedList<Cita> citas) {
        this.citas = citas;
    }

    public void setNotificaciones(LinkedList<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }


    //Metodos

    //Agregar el paciente
    public void registrarPaciente(Paciente paciente) {
        pacientes.add(paciente);

    }

    //Buscar Paciente por ID
    public Paciente buscarPacienteID(String idPaciente) {
        for(Paciente paciente : pacientes){
            if(paciente.getId().equals(idPaciente)){
                return paciente;
            }
        }
        return null;
    }

    public boolean eliminarEmpleado(String idEliminar) {
        int indiceEmpleado = buscarPacienteID(idEliminar); // debe devolver índice

        if (indiceEmpleado != -1) {
            pacientes.set(indiceEmpleado, null); // si es ArrayList
            return true;
        }
        return false;

    }

    //Actualizar Paciente
    public void actualizarPaciente

}
