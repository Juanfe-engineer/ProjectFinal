package co.edu.uniquindio.model;

import java.util.LinkedList;
import java.util.ArrayList;
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

    // Crear Cita

    public boolean crearCita(Cita nuevaCita) {

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

    public Cita actualizarCita(String idCitaOriginal, String nuevoId, Horario nuevoHorario, Medico nuevoMedico, Sala nuevaSala) {
        for (int i = 0; i < citas.size(); i++) {
            Cita actual = citas.get(i);

            if (actual != null && actual.getIdCita().equals(idCitaOriginal)) {

                // Validaciones
                if (nuevoId == null || nuevoHorario == null || nuevoMedico == null || nuevaSala == null) {
                    return null;
                }

                if (nuevoMedico.getEstado() != EstadoMedico.DISPONIBLE) {

                    return null;
                }

                if (nuevaSala.getEstado() != EstadoSala.DISPONIBLE) {

                    return null;
                }

                // Aplicar cambios
                actual.setIdCita(nuevoId);
                actual.setHorario(nuevoHorario);
                actual.setMedico(nuevoMedico);
                actual.setSala(nuevaSala);

                // Actualizar la lista
                citas.set(i, actual);
                return actual;
            }
        }

        return null;
    }
    // Eliminar Cita

    public boolean eliminarCita(String idCita) {
        if (idCita == null)
            return false;

        for (int i = 0; i < citas.size(); i++) {
            Cita actual = citas.get(i);
            if (actual != null && idCita.equals(actual.getIdCita())) {
                citas.remove(i);
                return true;
            }
        }
        return false;
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

    //Asignar horario a cita

    public boolean asignarHorarioACita(String idCita, Horario horario) {
        if (idCita == null || horario == null) return false;

        Cita cita = buscarCitaPorID(idCita);
        if (cita == null) return false;

        Medico medico = cita.getMedico();
        Sala sala = cita.getSala();

        if (medico == null || sala == null) return false;

        // Validar disponibilidad del médico en ese horario
        for (Horario h : medico.getHorario()) {
            if (h.equals(horario)) return false; // El médico ya tiene algo en ese horario
        }

        // Validar disponibilidad de la sala en ese horario
        for (Cita c : citas) {
            if (c.getSala() != null && c.getSala().equals(sala) && horario.equals(c.getHorario())) {
                return false; // La sala ya está ocupada en ese horario
            }
        }

        // Asignar horario y registrar el horario al médico
        cita.setHorario(horario);
        medico.getHorario().add(horario);
        return true;
    }


    //liberar horario de cita

    public boolean liberarHorarioDeCita(String idCita) {
        if (idCita == null) return false;

        Cita cita = buscarCitaPorID(idCita);
        if (cita == null || cita.getHorario() == null) return false;

        Horario horario = cita.getHorario();
        Medico medico = cita.getMedico();

        // Quitar el horario de la lista del médico
        if (medico != null) {
            medico.getHorario().removeIf(h -> h.equals(horario));
        }

        // Liberar horario de la cita
        cita.setHorario(null);

        return true;
    }


    //Generar reporte citas

    public String verReporteCitas (String idCita) {
        if(idCita == null)
            return "ID de cita invalido";
        Cita cita = buscarCitaPorID(idCita);
        if (cita == null)
            return "cita no encontrada";
        StringBuilder reporte = new StringBuilder();
        reporte.append("===Reporte cita===\n");
        reporte.append("ID cita: ").append(cita.getIdCita()).append("\n");
        //Paciente
        Paciente paciente = cita.getPaciente();
        if (paciente != null){
            reporte.append("Paciente: ").append(paciente.getNombre()).append(" (").append(paciente.getId()).append(" )\n");
        }else{
            reporte.append("paciente no asignado\n");
        }
        //Medico
        Medico medico = cita.getMedico();
        if (medico != null){
            reporte.append("Medico: ").append(medico.getNombre()).append(" (").append(medico.getId()).append(" )\n");
        }else{
            reporte.append("medico no asignado\n");
        }
        //Sala
        Sala sala = cita.getSala();
        if (sala != null){
            reporte.append("Sala: ").append(sala.getIdSala()).append(" (").append(" )\n");
        }else{
            reporte.append("Sala no asignada\n");
        }
        //Horario
        Horario horario = cita.getHorario();
        if (horario != null){
            reporte.append("Horario: ").append(horario.toString()).append("\n");
        }else{
            reporte.append("Horario no asignado\n");
        }

        return reporte.toString();

    }


    //Generar reporte de ocupacion salas
    public String generarReporteOcupacionSalas() {
        int totalSalas = salas.size();
        int ocupadas = 0;

        // Contadores por tipo de sala
        int pediatria = 0;
        int consulta = 0;
        int urgencias = 0;
        int cirugia = 0;
        int hospitalizacion = 0;
        int otra = 0;

        // Arreglo auxiliar para verificar qué salas están ocupadas (por código)
        ArrayList<String> codigosSalasOcupadas = new ArrayList<>();

        for (Cita cita : citas) {
            Sala sala = cita.getSala();
            if (sala != null) {
                String codigo = sala.getIdSala();

                // Evitar contar dos veces una misma sala como ocupada
                boolean yaContada = false;
                for (String cod : codigosSalasOcupadas) {
                    if (cod.equals(codigo)) {
                        yaContada = true;
                        break;
                    }
                }
                if (!yaContada) {
                    codigosSalasOcupadas.add(codigo);
                    ocupadas++;
                }

                // Contar la cita por tipo de sala
                TipoSala tipo = sala.getTipoSala();
                if (tipo == TipoSala.PEDIATRIA) {
                    pediatria++;
                } else if (tipo == TipoSala.CONSULTA) {
                    consulta++;
                } else if (tipo == TipoSala.URGENCIAS) {
                    urgencias++;
                } else if (tipo == TipoSala.CIRUGIA) {
                    cirugia++;
                } else if (tipo == TipoSala.HOSPITALIZACION) {
                    hospitalizacion++;
                } else if (tipo == TipoSala.OTRA) {
                    otra++;
                }
            }
        }

        int libres = totalSalas - ocupadas;

        // Construcción del reporte
        StringBuilder reporte = new StringBuilder();
        reporte.append("=== REPORTE DE OCUPACIÓN DE SALAS ===\n");
        reporte.append("Total de salas: ").append(totalSalas).append("\n");
        reporte.append("Salas ocupadas: ").append(ocupadas).append("\n");
        reporte.append("Salas libres: ").append(libres).append("\n\n");

        reporte.append("Citas por tipo de sala:\n");
        reporte.append(" - PEDIATRIA: ").append(pediatria).append(" cita(s)\n");
        reporte.append(" - CONSULTA: ").append(consulta).append(" cita(s)\n");
        reporte.append(" - URGENCIAS: ").append(urgencias).append(" cita(s)\n");
        reporte.append(" - CIRUGIA: ").append(cirugia).append(" cita(s)\n");
        reporte.append(" - HOSPITALIZACION: ").append(hospitalizacion).append(" cita(s)\n");
        reporte.append(" - OTRA: ").append(otra).append(" cita(s)\n");

        return reporte.toString();
    }


    //buscar medico por especialidad//
    public Medico buscarMedicoPorEspecialidad(Especialidad especialidad){
        for (Medico m1 : medicos ){
            if (m1.getEspecialidad().equals(especialidad)){
                return m1;
            }

        }
        return null;
    }


    //

    public boolean registrarCita(Cita cita) {
        if (cita == null) {
            return false;
        }
        for (Cita c : citas) {
            if (c.getIdCita().equals(cita.getIdCita())) {
                return false;
            }
        }
        citas.add(cita);
        return true;
    }



    public boolean registrarCita(String idCita, String idPaciente, String idMedico, String idSala, Horario horario) {
        // Verificar que el paciente exista
        Paciente paciente = buscarPacienteID(idPaciente);
        if (paciente == null) return false;

        // Verificar que el médico exista y esté disponible
        Medico medico = buscarMedicoID(idMedico);
        if (medico == null || medico.getEstado() != EstadoMedico.DISPONIBLE) return false;

        // Verificar que la sala exista
        Sala sala = buscarSalaPorId(idSala);
        if (sala == null) return false;

        // Verificar que no haya otra cita con esa sala y horario
        for (Cita c : citas) {
            if (c.getSala().getIdSala().equals(idSala) && c.getHorario().equals(horario)) {
                return false; // Sala ocupada
            }
            if (c.getMedico().getId().equals(idMedico) && c.getHorario().equals(horario)) {
                return false; // Médico ocupado
            }
        }

        // Crear la cita
        Cita nuevaCita = new Cita("","",idCita,paciente,medico,sala,horario,EstadoCita.PROGRAMADA);
        citas.add(nuevaCita);
        return true;
    }





}








