package co.edu.uniquindio.Controller;

import co.edu.uniquindio.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;

/**
 * Controlador principal que maneja toda la lógica de negocio del hospital
 * Actúa como intermediario entre la vista y el modelo
 */
public class HospitalController {

    private HospitalUQ hospital;

    public HospitalController(HospitalUQ hospital) {
        this.hospital = hospital;
        inicializarDatosPrueba();
    }

    // ==================== GESTIÓN DE PACIENTES ====================

    public boolean registrarPaciente(String nombre, String id, String correo, String telefono) {
        try {
            Paciente nuevoPaciente = new Paciente(nombre, id, correo, telefono);
            nuevoPaciente.setHospital(hospital);
            return hospital.registrarPaciente(nuevoPaciente);
        } catch (Exception e) {
            System.err.println("Error al registrar paciente: " + e.getMessage());
            return false;
        }
    }

    public Paciente buscarPaciente(String idPaciente) {
        return hospital.buscarPacienteID(idPaciente);
    }

    public boolean actualizarPaciente(Paciente paciente) {
        return hospital.modificarPaciente(paciente) != null;
    }

    public boolean eliminarPaciente(String idPaciente) {
        return hospital.eliminarPaciente(idPaciente);
    }

    public LinkedList<Paciente> obtenerTodosPacientes() {
        return hospital.getPacientes();
    }

    // ==================== GESTIÓN DE MÉDICOS ====================

    public boolean registrarMedico(String nombre, String id, String correo, String telefono, Especialidad especialidad, EstadoMedico estadoMedico) {
        try {
            Medico nuevoMedico = new Medico(nombre,id,correo,telefono,especialidad,estadoMedico);
            return hospital.registrarMedico(nuevoMedico);
        } catch (Exception e) {
            System.err.println("Error al registrar médico: " + e.getMessage());
            return false;
        }
    }

    public Medico buscarMedico(String idMedico) {
        return hospital.buscarMedicoID(idMedico);
    }

    public Medico buscarMedicoPorEspecialidad(Especialidad especialidad) {
        return hospital.buscarMedicoPorEspecialidad(especialidad);
    }

    public LinkedList<Medico> obtenerTodosMedicos() {
        return hospital.getMedicos();
    }

    public LinkedList<Medico> obtenerMedicosDisponibles() {
        LinkedList<Medico> disponibles = new LinkedList<>();
        for (Medico medico : hospital.getMedicos()) {
            if (medico.getEstado() == EstadoMedico.DISPONIBLE) {
                disponibles.add(medico);
            }
        }
        return disponibles;
    }

    // ==================== GESTIÓN DE SALAS ====================

    public boolean registrarSala(String idSala, TipoSala tipo, EstadoSala estadoSala,int capacidad ) {
        try {
            Sala nuevaSala = new Sala(idSala,tipo,estadoSala,capacidad);
            return hospital.registrarSala(nuevaSala);
        } catch (Exception e) {
            System.err.println("Error al registrar sala: " + e.getMessage());
            return false;
        }
    }

    public Sala buscarSala(String idSala) {
        return hospital.buscarSalaPorId(idSala);
    }

    public LinkedList<Sala> obtenerTodasSalas() {
        return hospital.getSalas();
    }

    public LinkedList<Sala> obtenerSalasDisponibles() {
        LinkedList<Sala> disponibles = new LinkedList<>();
        for (Sala sala : hospital.getSalas()) {
            if (sala.getEstado() == EstadoSala.DISPONIBLE) {
                disponibles.add(sala);
            }
        }
        return disponibles;
    }

    // ==================== GESTIÓN DE CITAS ====================

    public Cita crearCita(String idCita, String idPaciente, String idMedico, String idSala,
                          Horario horario, String tratamiento, String diagnostico) {
        try {
            return hospital.registrarCita(tratamiento, diagnostico, idCita, idPaciente, idMedico, idSala, horario);
        } catch (Exception e) {
            System.err.println("Error al crear cita: " + e.getMessage());
            return null;
        }
    }

    public Cita buscarCita(String idCita) {
        return hospital.buscarCitaPorID(idCita);
    }

    public boolean cancelarCita(String idCita) {
        Cita cita = hospital.buscarCitaPorID(idCita);
        if (cita != null) {
            cita.setEstado(EstadoCita.CANCELADA);
            // Liberar recursos
            if (cita.getMedico() != null) {
                cita.getMedico().setEstado(EstadoMedico.DISPONIBLE);
            }
            if (cita.getSala() != null) {
                cita.getSala().setEstado(EstadoSala.DISPONIBLE);
            }
            return true;
        }
        return false;
    }

    public LinkedList<Cita> obtenerTodasCitas() {
        return hospital.getCitas();
    }

    public LinkedList<Cita> obtenerCitasPaciente(String idPaciente) {
        LinkedList<Cita> citasPaciente = new LinkedList<>();
        for (Cita cita : hospital.getCitas()) {
            if (cita.getPaciente().getId().equals(idPaciente)) {
                citasPaciente.add(cita);
            }
        }
        return citasPaciente;
    }

    // ==================== GESTIÓN DE HORARIOS ====================

    public boolean registrarHorario(String idHorario, LocalDate fecha, LocalTime hora, Jornada jornada) {
        try {
            Horario nuevoHorario = new Horario(idHorario, fecha, hora, jornada);
            return hospital.registrarHorario(nuevoHorario);
        } catch (Exception e) {
            System.err.println("Error al registrar horario: " + e.getMessage());
            return false;
        }
    }

    public LinkedList<Horario> obtenerTodosHorarios() {
        return hospital.getHorarios();
    }

    // ==================== REPORTES ====================

    public String generarReporteCita(String idCita) {
        return hospital.verReporteCitas(idCita);
    }

    public String generarReporteOcupacionSalas() {
        return hospital.generarReporteOcupacionSalas();
    }

    // ==================== ASIGNACIONES ====================

    public boolean asignarMedicoAPaciente(String idPaciente, String idMedico) {
        return hospital.asignarMedicoAPaciente(idPaciente, idMedico);
    }

    public boolean liberarMedicoDePaciente(String idPaciente) {
        return hospital.liberarMedicoDePaciente(idPaciente);
    }

    // ==================== VALIDACIONES DE NEGOCIO ====================

    public boolean validarDisponibilidadMedico(String idMedico, Horario horario) {
        Medico medico = hospital.buscarMedicoID(idMedico);
        if (medico == null || medico.getEstado() != EstadoMedico.DISPONIBLE) {
            return false;
        }

        // Verificar si el médico tiene alguna cita en ese horario
        for (Cita cita : hospital.getCitas()) {
            if (cita.getMedico().getId().equals(idMedico) &&
                    cita.getHorario().equals(horario) &&
                    cita.getEstado() == EstadoCita.PROGRAMADA) {
                return false;
            }
        }
        return true;
    }

    public boolean validarDisponibilidadSala(String idSala, Horario horario) {
        Sala sala = hospital.buscarSalaPorId(idSala);
        if (sala == null || sala.getEstado() != EstadoSala.DISPONIBLE) {
            return false;
        }

        // Verificar si la sala tiene alguna cita en ese horario
        for (Cita cita : hospital.getCitas()) {
            if (cita.getSala().getIdSala().equals(idSala) &&
                    cita.getHorario().equals(horario) &&
                    cita.getEstado() == EstadoCita.PROGRAMADA) {
                return false;
            }
        }
        return true;
    }

    // ==================== INICIALIZACIÓN DE DATOS DE PRUEBA ====================

    private void inicializarDatosPrueba() {
        // Crear algunos médicos de prueba
        registrarMedico("Dr. Juan Pérez", "MED001", "juan.perez@hospital.com", "3001234567", Especialidad.CARDIOLOGO,EstadoMedico.DISPONIBLE);
        registrarMedico("Dra. María González", "MED002", "maria.gonzalez@hospital.com", "3009876543", Especialidad.PEDIATRIA,EstadoMedico.DISPONIBLE);
        registrarMedico("Dr. Carlos Ruiz", "MED003", "carlos.ruiz@hospital.com", "3005555555", Especialidad.CIRUJANO,EstadoMedico.DISPONIBLE);

        // Crear algunas salas de prueba
        registrarSala("SALA001", TipoSala.CONSULTA, EstadoSala.DISPONIBLE,4);
        registrarSala("SALA002", TipoSala.PEDIATRIA, EstadoSala.DISPONIBLE,3);
        registrarSala("SALA003", TipoSala.CIRUGIA, EstadoSala.DISPONIBLE,5);

        // Crear algunos horarios de prueba
        registrarHorario("H001", LocalDate.of(2025,5,21), LocalTime.now(), Jornada.TARDE);
        registrarHorario("H002", LocalDate.of(2025,5,22), LocalTime.now(), Jornada.MANANA);
        registrarHorario("H003", LocalDate.of(2025,5,23), LocalTime.now(), Jornada.NOCHE);
        registrarHorario("H004", LocalDate.of(2025,5,24), LocalTime.now(), Jornada.TARDE);

        System.out.println("Datos de prueba inicializados correctamente");
    }

    // ==================== GETTERS ====================

    public HospitalUQ getHospital() {
        return hospital;
    }
}