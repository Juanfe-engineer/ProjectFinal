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

    public HospitalUQ(String nombre, String id) {
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

    //Eliminar paciente
    public void eliminarPaciente(String idPaciente) {
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getId().equals(idPaciente)) {
                pacientes.set(i, null); // marca el elemento como null
                return;
            }
        }
    }

    //Actualizar Paciente
    public boolean modificarPaciente(Paciente pacienteModificado) {
        for (int i = 0; i < pacientes.size(); i++) {
            if (pacientes.get(i).getId().equals(pacienteModificado.getId())) {
                pacientes.set(i, pacienteModificado);
                return true;
            }
        }
        return false;
    }

    //Agregar el Medico
    public void registrarMedico(Medico medico) {
        medicos.add(medico);

    }

    //Buscar Medico por ID
    public Medico buscarMedicoID(String idMedico) {
        for(Medico medico : medicos){
            if(medico.getId().equals(idMedico)){
                return medico;
            }
        }
        return null;
    }

    //Eliminar medidco
    public void eliminarMedico(String idMedico) {
        for (int i = 0; i < medicos.size(); i++) {
            if (medicos.get(i).getId().equals(idMedico)) {
                medicos.set(i, null); // marca el elemento como null
                return;
            }
        }
    }

    //Actualizar Medico
    public boolean modificarMedico(Medico medicoModificado) {
        for (int i = 0; i < medicos.size(); i++) {
            if (medicos.get(i).getId().equals(medicoModificado.getId())) {
                medicos.set(i, medicoModificado);
                return true;
            }
        }
        return false;
    }

    // Asignar medico a paciente

    public boolean asignarMedicoAPaciente(String idPaciente, String idMedico) {
        for (Paciente paciente : pacientes) {
            if (paciente.getId().equals(idPaciente)) {
                for (Medico medico : medicos) {
                    if (medico.getId().equals(idMedico)) {
                        if (medico.getEstado() == EstadoMedico.DISPONIBLE) {
                            paciente.setMedicoAsignado(medico);
                            medico.setEstado(EstadoMedico.NO_DISPONIBLE); // cambiar el estado
                            return true;
                        } else {
                            return false; // Médico no está disponible
                        }
                    }
                }
            }
        }
        return false;
    }


    // Liberar medico de paciente

    public boolean liberarMedicoDePaciente(String idPaciente) {
        for (Paciente paciente : pacientes) {
            if (paciente.getId().equals(idPaciente)) {
                Medico medicoAsignado = paciente.getMedicoAsignado();

                if (medicoAsignado != null) {
                    medicoAsignado.setEstado(EstadoMedico.DISPONIBLE);
                    paciente.setMedicoAsignado(null); // quita el médico del paciente
                    return true;
                } else {
                    return false; // el paciente no tiene médico asignado
                }
            }
        }
        return false; // paciente no encontrado
    }


    // METODO 3: "Buscar soldados por especialidad

    public Especialidad obtenerEspecialidadDesdeTexto(String texto) {
        try {
            return Especialidad.valueOf(texto.toUpperCase());//Value0f convierte el string en valor de Enum y toUpp para que no tenga errores por mayuscula
        } catch (IllegalArgumentException e) {
            return null; // o lanza tu propia excepción o muestra mensaje de error
        }
    }



}
