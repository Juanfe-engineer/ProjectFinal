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
    private LinkedList<Horario> horarios;
    private Cita cita;

    public HospitalUQ(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
        this.medicos = new LinkedList<>();
        this.pacientes = new LinkedList<>();
        this.salas = new LinkedList<>();
        this.citas = new LinkedList<>();
        this.notificaciones = new LinkedList<>();
        this.horarios = new LinkedList<>();

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

    public LinkedList<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(LinkedList<Horario> horarios) {
        this.horarios = horarios;
    }
    //Gestion de Usuarios

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


    // Gestion de Salas y Horarios

    //RegistrarSala

    public boolean registrarSala(Sala sala) {
        if (sala == null) {
            return false;
        }
        for (Sala s : salas) {
            if (s.getIdSala().equals(sala.getIdSala())) {
                return false;
            }
        }
        salas.add(sala);
        return true;
    }


    // buscar sala por id

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

    // Actualizar Sala

    public Sala modificarSala(Sala salaModificada) {
        if (salaModificada == null || salaModificada.getIdSala() == null) {
            return null;
        }

        for (int i = 0; i < salas.size(); i++) {
            Sala actual = salas.get(i);
            if (actual != null && actual.getIdSala() != null &&
                    actual.getIdSala().equals(salaModificada.getIdSala())) {
                salas.set(i, salaModificada);
                return salaModificada;
            }
        }
        return null;
    }


    // Eliminar Sala

    public boolean eliminarSala(String idSala) {
        if (idSala == null)
            return false;

        for (int i = 0; i < salas.size(); i++) {
            Sala actual = salas.get(i);
            if (actual != null && idSala.equals(actual.getIdSala())) {
                salas.remove(i);
                return true;
            }
        }
        return false;
    }


    // Registrar Horario

    public boolean registrarHorario(Horario horario) {
        if (horario == null) {
            return false;
        }

        for (Horario h : horarios) {
            if (h.getIdHorario() == horario.getIdHorario()) {
                return false;
            }
        }
        horarios.add(horario);
        return true;
    }


    // buscar Horario por ID

    public Horario buscarHorarioPorId(String idHorario) {
        if (idHorario == null)
            return null;

        for (Horario horario : horarios) {
            if (horario != null && idHorario.equals(horario.getIdHorario())) {
                return horario;
            }
        }
        return null;
    }

    // Actualizar Horario

    public Horario modificarHorario(Horario horariomodificado) {
        if (horariomodificado == null || horariomodificado.getIdHorario() == null) {
            return null;
        }

        for (int i = 0; i < horarios.size(); i++) {
            Horario actual = horarios.get(i);
            if (actual != null && actual.getIdHorario() != null &&
                    actual.getIdHorario().equals(horariomodificado.getIdHorario())) {
                horarios.set(i, horariomodificado);
                return horariomodificado;
            }
        }
        return null;
    }


    // Eliminar Horario

    public boolean eliminarHorario(String idHorario) {
        if (idHorario == null)
            return false;

        for (int i = 0; i < horarios.size(); i++) {
            Horario actual = horarios.get(i);
            if (actual != null && idHorario.equals(actual.getIdHorario())) {
                horarios.remove(i);
                return true;
            }
        }
        return false;
    }


    //----------------------------------------------------------------------------------------------------------------//


    // Gestion de citas

    // Buscar Cita Por Id

    public Cita buscarCitaPorID(String idCita) {
        if (idCita == null)
            return null;

        for (Cita cita : citas) {
            if (cita != null && idCita.equals(cita.getIdCita())) {
                return cita;
            }
        }
        return null;
    }

    //Actualizar Cita

    public Cita modificarIDCita(Cita citaIDModificado) {
        if (citaIDModificado == null || citaIDModificado.getIdCita() == null) {
            return null;
        }
        for (int i = 0; i < citas.size(); i++) {
            Cita actual = citas.get(i);
            if (actual != null && actual.getIdCita() != null &&
                    actual.getIdCita().equals(citaIDModificado.getIdCita())) {
                citas.set(i, citaIDModificado);
                return citaIDModificado;
            }
        }
        return null;
    }

    public Cita modificarMedicoCita(Cita citaMedicoModificado) {
        if (citaMedicoModificado == null || citaMedicoModificado.getMedico().getEstado() != EstadoMedico.DISPONIBLE){
            return null;
        }
        for (int i = 0; i < citas.size(); i++) {
          Cita actual = citas.get(i);
          if (actual != null && actual.getMedico().getEstado() != EstadoMedico.DISPONIBLE &&
                  actual.getMedico().equals(citaMedicoModificado.getMedico()) ){
              citas.set(i, citaMedicoModificado);
              return citaMedicoModificado;
          }

        }
        return null;
    }

    public Cita modificarHorarioCita(Cita citaHorarioModificado){
        if (citaHorarioModificado == null || citaHorarioModificado.getHorario() != null){
            return null;
        }
        for (int i = 0; i < citas.size(); i++) {
          Cita actual = citas.get(i);
          if (actual != null && actual.getHorario().equals(citaHorarioModificado.getHorario())){
              citas.set(i, citaHorarioModificado);
              return citaHorarioModificado;
          }
        }
        return null;
    }
    public Cita modificarSalaCita(Cita citaSalaModificado){
        if (citaSalaModificado == null || citaSalaModificado.getSala().getEstado() != EstadoSala.DISPONIBLE){
            return null;
        }
        for (int i = 0; i < citas.size(); i++) {
            Cita actual = citas.get(i);
            if (actual != null && actual.getSala().getEstado() != EstadoSala.DISPONIBLE){
                citas.set(i, citaSalaModificado);
                return citaSalaModificado;
            }
        }
        return null;
    }








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

        boolean disponibilidad = false;

        if (idPaciente == null) disponibilidad = true;

        for (Paciente paciente : pacientes) {
            if (paciente != null && idPaciente.equals(paciente.getId())) {
                Medico medicoAsignado = paciente.getMedicoAsignado();
                if (medicoAsignado != null) {
                    medicoAsignado.setEstado(EstadoMedico.DISPONIBLE);
                    paciente.setMedicoAsignado(null);
                }
                disponibilidad  = false; // paciente sin médico asignado
            }
        }
        return disponibilidad; // paciente no encontrado
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









    //----------------------------------------------------------------------------------------------------------------//

    // Asignar Sala a Cita

    public boolean asignarSalaACita(String idSala, String idCita) {
        if (idSala == null || idCita == null)
            return false;

        Sala sala = buscarSalaPorId(idSala);
        Cita cita = buscarCitaPorID(idCita);

        if (sala == null || cita == null)
            return false;

        // Validar si ya hay sala asignada
        if (cita.getSala() != null)
            return false;

        // Validar si la sala está disponible
        if (sala.getEstado() != EstadoSala.DISPONIBLE)
            return false;

        // Asignar sala
        cita.setSala(sala);
        sala.setEstado(EstadoSala.OCUPADA);
        return true;
    }


    // liberar Sala de Cita

    public boolean liberarSalaDeCita(String idCita) {
        if (idCita == null) return false;

        Cita cita = buscarCitaPorID(idCita);
        if (cita == null) return false;

        Sala sala = cita.getSala();
        if (sala == null) return false;

        // Validar que la sala esté actualmente asignada a esa cita
        if (sala.getEstado() != EstadoSala.OCUPADA) {
            return false; // La sala no está ocupada, no tiene sentido liberarla
        }

        sala.setEstado(EstadoSala.DISPONIBLE);
        cita.setSala(null); // Desvincular sala de la cita
        return true;
    }





}

