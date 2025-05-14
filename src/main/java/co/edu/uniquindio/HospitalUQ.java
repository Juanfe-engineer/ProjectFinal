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
        if (idPaciente == null || idMedico == null) return false;

        for (Paciente paciente : pacientes) {
            if (paciente != null && idPaciente.equals(paciente.getId())) {
                for (Medico medico : medicos) {
                    if (medico != null && idMedico.equals(medico.getId())) {
                        if (medico.getEstado() == EstadoMedico.DISPONIBLE) {
                            paciente.setMedicoAsignado(medico);
                            medico.setEstado(EstadoMedico.NO_DISPONIBLE);
                            return true;
                        } else {
                            return false; // médico no disponible
                        }
                    }
                }
            }
        }
        return false; // paciente o médico no encontrado
    }



    // Liberar medico de paciente

    public boolean liberarMedicoDePaciente(String idPaciente) {
        if (idPaciente == null) return false;

        for (Paciente paciente : pacientes) {
            if (paciente != null && idPaciente.equals(paciente.getId())) {
                Medico medicoAsignado = paciente.getMedicoAsignado();
                if (medicoAsignado != null) {
                    medicoAsignado.setEstado(EstadoMedico.DISPONIBLE);
                    paciente.setMedicoAsignado(null);
                    return true;
                }
                return false; // paciente sin médico asignado
            }
        }
        return false; // paciente no encontrado
    }



    //----------------------------------------------------------------------------------------------------------------//


    // Agregar Cita

    public boolean agregarCita(Cita nuevaCita) {
        if (nuevaCita == null || nuevaCita.getIdCita() == null) return false;

        // Validar si ya existe una cita con ese ID
        for (Cita cita : citas) {
            if (cita.getIdCita().equals(nuevaCita.getIdCita())) {
                return false;
            }
        }

        // Validar estado del médico
        if (nuevaCita.getMedico().getEstado() != EstadoMedico.DISPONIBLE) {
            return false;
        }

        // Validar estado de la sala
        if (nuevaCita.getSala().getEstado() != EstadoSala.DISPONIBLE) {
            return false;
        }

        // Validar que el horario no esté ocupado
        for (Cita cita : citas) {
            if (cita.getHorario().getIdHorario().equals(nuevaCita.getHorario().getIdHorario())) {
                return false;
            }
        }

        // Registrar cita
        citas.add(nuevaCita);
        nuevaCita.getMedico().setEstado(EstadoMedico.NO_DISPONIBLE);
        nuevaCita.getSala().setEstado(EstadoSala.OCUPADA);
        nuevaCita.setEstado(EstadoCita.PROGRAMADA);
        return true;
    }

    // Cancelar Cita

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




    //----------------------------------------------------------------------------------------------------------------//



    // Buscar Sala Por Id

    public Sala buscarSalaPorId(String idSala) {
        if (idSala == null)
            return null;

        for (Sala sala : salas) {
            if (sala != null && idSala.equals(sala.getIdSala())) {
                return sala;
            }
        }
        return null;
    }


}
