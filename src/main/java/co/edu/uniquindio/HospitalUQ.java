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


    // CRUD Paciente

    //Agregar el paciente
    public boolean registrarPaciente(Paciente paciente) {
        if (paciente == null) {
            return false;
        }
        for (Paciente p : pacientes) {
            if (p.getId().equals(paciente.getId())) {
                return false;
            }
        }
        pacientes.add(paciente);
        return true;
    }

    //Buscar Paciente por ID
    public Paciente buscarPacienteID(String idPaciente) {
        if (idPaciente == null)
            return null;

        for (Paciente paciente : pacientes) {
            if (paciente != null && idPaciente.equals(paciente.getId())) {
                return paciente;
            }
        }
        return null;
    }

    //Eliminar paciente
    public boolean eliminarPaciente(String idPaciente) {
        if (idPaciente == null)
            return false;

        for (int i = 0; i < pacientes.size(); i++) {
            Paciente actual = pacientes.get(i);
            if (actual != null && idPaciente.equals(actual.getId())) {
                pacientes.remove(i);
                return true;
            }
        }
        return false;
    }

    //Actualizar Paciente
    public Paciente modificarPaciente(Paciente pacienteModificado) {
        if (pacienteModificado == null || pacienteModificado.getId() == null) {
            return null;
        }

        for (int i = 0; i < pacientes.size(); i++) {
            Paciente actual = pacientes.get(i);
            if (actual != null && actual.getId() != null &&
                    actual.getId().equals(pacienteModificado.getId())) {
                pacientes.set(i, pacienteModificado);
                return pacienteModificado;
            }
        }
        return null;
    }



    // CRUD Medico

    //Agregar el Medico
    public boolean registrarMedico(Medico medico) {
        if (medico == null) {
            return false;
        }
        for (Medico m : medicos) {
            if (m.getId().equals(medico.getId())) {
                return false;
            }
        }
        medicos.add(medico);
        return true;
    }

    //Buscar Medico por ID
    public Medico buscarMedicoID(String idMedico) {
        if (idMedico == null)
            return null;

        for (Medico medico : medicos) {
            if (medico != null && idMedico.equals(medico.getId())) {
                return medico;
            }
        }
        return null;
    }

    //Eliminar medidco
    public boolean eliminarMedico(String idMedico) {
        if (idMedico == null)
            return false;

        for (int i = 0; i < medicos.size(); i++) {
            Medico actual = medicos.get(i);
            if (actual != null && idMedico.equals(actual.getId())) {
                medicos.remove(i);
                return true;
            }
        }
        return false;
    }

    //Actualizar Medico
    public Medico modificarMedico(Medico medicoModificado) {
        if (medicoModificado == null || medicoModificado.getId() == null) {
            return null;
        }

        for (int i = 0; i < medicos.size(); i++) {
            Medico actual = medicos.get(i);
            if (actual != null && actual.getId() != null &&
                    actual.getId().equals(medicoModificado.getId())) {
                medicos.set(i, medicoModificado);
                return medicoModificado;
            }
        }
        return null;
    }



//--------------------------------------------------------------------------------------------------------------------//



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




}
