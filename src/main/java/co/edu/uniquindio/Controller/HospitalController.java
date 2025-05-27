package co.edu.uniquindio.Controller;

import co.edu.uniquindio.model.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Controlador principal del sistema hospitalario
 * Maneja todas las operaciones relacionadas con pacientes, médicos, citas, horarios y salas
 */
public class HospitalController {

    private static HospitalController instance;


    private static HospitalUQ hospital;

    private HospitalController() {
        hospital = new HospitalUQ("Hospital Uq","1234");
    }

    public static HospitalController getInstance(){
        if(instance == null){
            instance = new HospitalController();
        }
        return instance;
    }

    /**
     * Constructor del controlador
     * @param hospital Instancia del hospital a controlar
     */
    public HospitalController(HospitalUQ hospital) {
        this.hospital = hospital;
        inicializarDatosBasicos();
    }


    /**
     * Inicializa datos básicos del hospital para pruebas
     */
    private void inicializarDatosBasicos() {
        try {
            // Registrar algunos médicos básicos si no existen
            if (hospital.getMedicos() == null || hospital.getMedicos().isEmpty()) {
                registrarMedico("Dr. Juan Pérez", "MED001", "juan.perez@hospital.com", "3001234567", Especialidad.GENERAL,EstadoMedico.DISPONIBLE);
                registrarMedico("Dra. María García", "MED002", "maria.garcia@hospital.com", "3001234568", Especialidad.CARDIOLOGO,EstadoMedico.DISPONIBLE);
                registrarMedico("Dr. Carlos López", "MED003", "carlos.lopez@hospital.com", "3001234569", Especialidad.NEUROLOGO,EstadoMedico.DISPONIBLE);
                registrarMedico("Dra. Ana Rodríguez", "MED004", "ana.rodriguez@hospital.com", "3001234570", Especialidad.PEDIATRIA,EstadoMedico.DISPONIBLE);
                registrarMedico("Dr. Luis Martínez", "MED005", "luis.martinez@hospital.com", "3001234571", Especialidad.OFTAGMOLOGO,EstadoMedico.DISPONIBLE);
                registrarMedico("Dra. Patricia Silva", "MED006", "patricia.silva@hospital.com", "3001234572", Especialidad.CIRUJANO,EstadoMedico.DISPONIBLE);
            }

            // Registrar algunas salas básicas si no existen
            if (hospital.getSalas() == null || hospital.getSalas().isEmpty()) {
                registrarSala("SALA001", TipoSala.CONSULTA,EstadoSala.DISPONIBLE,3);
                registrarSala("SALA002", TipoSala.URGENCIAS,EstadoSala.DISPONIBLE,4);
                registrarSala("SALA003", TipoSala.CIRUGIA,EstadoSala.DISPONIBLE,6);
                registrarSala("SALA004", TipoSala.HOSPITALIZACION,EstadoSala.DISPONIBLE,4);
                registrarSala("SALA005", TipoSala.PEDIATRIA,EstadoSala.DISPONIBLE,3);
                registrarSala("SALA006", TipoSala.OTRA,EstadoSala.DISPONIBLE,4);
            }

            // Registrar algunos horarios básicos si no existen
            if (hospital.getHorarios() == null || hospital.getHorarios().isEmpty()) {
                // Horarios para hoy y próximos días
                LocalDate hoy = LocalDate.now();
                for (int dia = 1; dia <= 7; dia++) {
                    LocalDate fecha = hoy.plusDays(dia);
                    registrarHorario("H" + dia + "M1", fecha, LocalTime.of(8, 0), Jornada.MANANA);
                    registrarHorario("H" + dia + "M2", fecha, LocalTime.of(9, 0), Jornada.MANANA);
                    registrarHorario("H" + dia + "M3", fecha, LocalTime.of(10, 0), Jornada.MANANA);
                    registrarHorario("H" + dia + "M4", fecha, LocalTime.of(11, 0), Jornada.MANANA);
                    registrarHorario("H" + dia + "T1", fecha, LocalTime.of(14, 0), Jornada.TARDE);
                    registrarHorario("H" + dia + "T2", fecha, LocalTime.of(15, 0), Jornada.TARDE);
                    registrarHorario("H" + dia + "T3", fecha, LocalTime.of(16, 0), Jornada.TARDE);
                    registrarHorario("H" + dia + "T4", fecha, LocalTime.of(17, 0), Jornada.TARDE);
                }
            }

            System.out.println("✅ Datos básicos del hospital inicializados");

        } catch (Exception e) {
            System.err.println("❌ Error al inicializar datos básicos: " + e.getMessage());
        }
    }

    // ===== MÉTODOS PARA PACIENTES =====

    /**
     * Registra un nuevo paciente en el sistema
     */
    public Paciente registrarPaciente(String nombre, String id, String correo, String telefono) {
        try {
            // Verificar si el paciente ya existe
            Paciente pacienteExistente = buscarPaciente(id);
            if (pacienteExistente != null) {
                System.out.println("✅ Paciente ya existe: " + id);
                return pacienteExistente;
            }

            Paciente nuevoPaciente = new Paciente(nombre, id, correo, telefono);
            nuevoPaciente.setHospital(hospital);

            if (hospital.getPacientes() == null) {
                hospital.setPacientes(new LinkedList<>());
            }

            hospital.getPacientes().add(nuevoPaciente);
            System.out.println("✅ Paciente registrado: " + nombre);
            return nuevoPaciente;

        } catch (Exception e) {
            System.err.println("❌ Error al registrar paciente: " + e.getMessage());
            return null;
        }
    }

    /**
     * Busca un paciente por su ID
     */
    public Paciente buscarPaciente(String idPaciente) {
        try {
            if (hospital.getPacientes() == null || idPaciente == null) {
                return null;
            }

            return hospital.getPacientes().stream()
                    .filter(p -> p.getId().equals(idPaciente))
                    .findFirst()
                    .orElse(null);

        } catch (Exception e) {
            System.err.println("❌ Error al buscar paciente: " + e.getMessage());
            return null;
        }
    }

    /**
     * Actualiza los datos de un paciente existente
     */
    public boolean actualizarPaciente(String idPaciente, String nuevoNombre, String nuevoCorreo, String nuevoTelefono) {
        try {
            Paciente paciente = buscarPaciente(idPaciente);
            if (paciente != null) {
                if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                    paciente.setNombre(nuevoNombre);
                }
                if (nuevoCorreo != null && !nuevoCorreo.trim().isEmpty()) {
                    paciente.setCorreo(nuevoCorreo);
                }
                if (nuevoTelefono != null && !nuevoTelefono.trim().isEmpty()) {
                    paciente.setTelefono(nuevoTelefono);
                }
                System.out.println("✅ Paciente actualizado: " + idPaciente);
                return true;
            }
            return false;

        } catch (Exception e) {
            System.err.println("❌ Error al actualizar paciente: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene todas las citas de un paciente específico
     */
    public LinkedList<Cita> obtenerCitasPaciente(String idPaciente) {
        try {
            if (hospital.getCitas() == null || idPaciente == null) {
                return new LinkedList<>();
            }

            return hospital.getCitas().stream()
                    .filter(c -> c.getPaciente() != null && c.getPaciente().getId().equals(idPaciente))
                    .collect(Collectors.toCollection(LinkedList::new));

        } catch (Exception e) {
            System.err.println("❌ Error al obtener citas del paciente: " + e.getMessage());
            return new LinkedList<>();
        }
    }

    // ===== MÉTODOS PARA MÉDICOS =====

    /**
     * Registra un nuevo médico en el sistema
     */
    public Medico registrarMedico(String nombre, String id, String correo, String telefono, Especialidad especialidad, EstadoMedico estadoMedico) {
        try {
            // Verificar si el médico ya existe
            if (buscarMedico(id) != null) {
                System.out.println("✅ Médico ya existe: " + id);
                return buscarMedico(id);
            }

            Medico nuevoMedico = new Medico(nombre, id, correo, telefono, especialidad,estadoMedico);

            if (hospital.getMedicos() == null) {
                hospital.setMedicos(new LinkedList<>());
            }

            hospital.getMedicos().add(nuevoMedico);
            System.out.println("✅ Médico registrado: " + nombre);
            return nuevoMedico;

        } catch (Exception e) {
            System.err.println("❌ Error al registrar médico: " + e.getMessage());
            return null;
        }
    }

    /**
     * Busca un médico por su ID
     */
    public Medico buscarMedico(String idMedico) {
        try {
            if (hospital.getMedicos() == null || idMedico == null) {
                return null;
            }

            return hospital.getMedicos().stream()
                    .filter(m -> m.getId().equals(idMedico))
                    .findFirst()
                    .orElse(null);

        } catch (Exception e) {
            System.err.println("❌ Error al buscar médico: " + e.getMessage());
            return null;
        }
    }

    /**
     * Obtiene todos los médicos disponibles
     */
    public LinkedList<Medico> obtenerMedicosDisponibles() {
        try {
            if (hospital.getMedicos() == null) {
                return new LinkedList<>();
            }

            return new LinkedList<>(hospital.getMedicos());

        } catch (Exception e) {
            System.err.println("❌ Error al obtener médicos disponibles: " + e.getMessage());
            return new LinkedList<>();
        }
    }

    /**
     * Obtiene médicos por especialidad
     */
    public LinkedList<Medico> obtenerMedicosPorEspecialidad(Especialidad especialidad) {
        try {
            if (hospital.getMedicos() == null) {
                return new LinkedList<>();
            }

            return hospital.getMedicos().stream()
                    .filter(m -> m.getEspecialidad() == especialidad)
                    .collect(Collectors.toCollection(LinkedList::new));

        } catch (Exception e) {
            System.err.println("❌ Error al obtener médicos por especialidad: " + e.getMessage());
            return new LinkedList<>();
        }
    }

    // ===== MÉTODOS PARA SALAS =====

    /**
     * Registra una nueva sala en el sistema
     */
    public Sala registrarSala(String idSala, TipoSala tipo, EstadoSala estado, int capacidad) {
        try {
            // Verificar si la sala ya existe
            if (buscarSala(idSala) != null) {
                System.out.println("✅ Sala ya existe: " + idSala);
                return buscarSala(idSala);
            }

            Sala nuevaSala = new Sala(idSala,tipo,estado,capacidad);
            nuevaSala.setEstado(EstadoSala.DISPONIBLE); // Por defecto disponible

            if (hospital.getSalas() == null) {
                hospital.setSalas(new LinkedList<>());
            }

            hospital.getSalas().add(nuevaSala);
            System.out.println("✅ Sala registrada: " + idSala);
            return nuevaSala;

        } catch (Exception e) {
            System.err.println("❌ Error al registrar sala: " + e.getMessage());
            return null;
        }
    }

    /**
     * Busca una sala por su ID
     */
    public Sala buscarSala(String idSala) {
        try {
            if (hospital.getSalas() == null || idSala == null) {
                return null;
            }

            return hospital.getSalas().stream()
                    .filter(s -> s.getIdSala().equals(idSala))
                    .findFirst()
                    .orElse(null);

        } catch (Exception e) {
            System.err.println("❌ Error al buscar sala: " + e.getMessage());
            return null;
        }
    }

    /**
     * Obtiene todas las salas disponibles
     */
    public LinkedList<Sala> obtenerSalasDisponibles() {
        try {
            if (hospital.getSalas() == null) {
                return new LinkedList<>();
            }

            return hospital.getSalas().stream()
                    .filter(s -> s.getEstado() == EstadoSala.DISPONIBLE)
                    .collect(Collectors.toCollection(LinkedList::new));

        } catch (Exception e) {
            System.err.println("❌ Error al obtener salas disponibles: " + e.getMessage());
            return new LinkedList<>();
        }
    }

    // ===== MÉTODOS PARA HORARIOS =====

    /**
     * Registra un nuevo horario en el sistema
     */
    public Horario registrarHorario(String idHorario, LocalDate fecha, LocalTime hora, Jornada jornada) {
        try {
            // Verificar si el horario ya existe
            if (buscarHorario(idHorario) != null) {
                return buscarHorario(idHorario);
            }

            Horario nuevoHorario = new Horario(idHorario, fecha, hora, jornada);

            if (hospital.getHorarios() == null) {
                hospital.setHorarios(new LinkedList<>());
            }

            hospital.getHorarios().add(nuevoHorario);
            return nuevoHorario;

        } catch (Exception e) {
            System.err.println("❌ Error al registrar horario: " + e.getMessage());
            return null;
        }
    }

    /**
     * Busca un horario por su ID
     */
    public Horario buscarHorario(String idHorario) {
        try {
            if (hospital.getHorarios() == null || idHorario == null) {
                return null;
            }

            return hospital.getHorarios().stream()
                    .filter(h -> h.getIdHorario().equals(idHorario))
                    .findFirst()
                    .orElse(null);

        } catch (Exception e) {
            System.err.println("❌ Error al buscar horario: " + e.getMessage());
            return null;
        }
    }

    /**
     * Obtiene todos los horarios del sistema
     */
    public LinkedList<Horario> obtenerTodosHorarios() {
        try {
            if (hospital.getHorarios() == null) {
                return new LinkedList<>();
            }

            return new LinkedList<>(hospital.getHorarios());

        } catch (Exception e) {
            System.err.println("❌ Error al obtener horarios: " + e.getMessage());
            return new LinkedList<>();
        }
    }

    /**
     * Obtiene horarios disponibles para una fecha específica
     */
    public LinkedList<Horario> obtenerHorariosDisponibles(LocalDate fecha) {
        try {
            if (hospital.getHorarios() == null) {
                return new LinkedList<>();
            }

            return hospital.getHorarios().stream()
                    .filter(h -> h.getFecha().equals(fecha))
                    .filter(this::esHorarioDisponible)
                    .collect(Collectors.toCollection(LinkedList::new));

        } catch (Exception e) {
            System.err.println("❌ Error al obtener horarios disponibles: " + e.getMessage());
            return new LinkedList<>();
        }
    }

    /**
     * Verifica si un horario está disponible (no tiene citas programadas)
     */
    private boolean esHorarioDisponible(Horario horario) {
        try {
            if (hospital.getCitas() == null) {
                return true;
            }

            return hospital.getCitas().stream()
                    .filter(c -> c.getEstado() == EstadoCita.PROGRAMADA)
                    .noneMatch(c -> c.getHorario() != null &&
                            c.getHorario().getIdHorario().equals(horario.getIdHorario()));

        } catch (Exception e) {
            return false;
        }
    }

    // ===== MÉTODOS PARA CITAS =====

    /**
     * Crea una nueva cita médica
     */
    public Cita crearCita(String idCita, String idPaciente, String idMedico, String idSala,
                          Horario horario, String tratamiento, String diagnostico) {
        try {
            // Buscar paciente, médico y sala
            Paciente paciente = buscarPaciente(idPaciente);
            Medico medico = buscarMedico(idMedico);
            Sala sala = buscarSala(idSala);

            if (paciente == null) {
                throw new IllegalArgumentException("Paciente no encontrado: " + idPaciente);
            }
            if (medico == null) {
                throw new IllegalArgumentException("Médico no encontrado: " + idMedico);
            }
            if (sala == null) {
                throw new IllegalArgumentException("Sala no encontrada: " + idSala);
            }

            // Verificar disponibilidad del horario
            if (!esHorarioDisponible(horario)) {
                throw new IllegalArgumentException("El horario ya está ocupado");
            }

            // Crear la cita
            Cita nuevaCita = new Cita(diagnostico,tratamiento,idCita,paciente,medico,sala,horario,EstadoCita.PROGRAMADA);
            nuevaCita.setEstado(EstadoCita.PROGRAMADA);

            // Agregar al hospital
            if (hospital.getCitas() == null) {
                hospital.setCitas(new LinkedList<>());
            }

            hospital.getCitas().add(nuevaCita);

            // Asignar médico al paciente si no tiene uno
            if (paciente.getMedicoAsignado() == null) {
                paciente.setMedicoAsignado(medico);
            }

            System.out.println("✅ Cita creada: " + idCita);
            return nuevaCita;

        } catch (Exception e) {
            System.err.println("❌ Error al crear cita: " + e.getMessage());
            return null;
        }
    }

    /**
     * Busca una cita por su ID
     */
    public Cita buscarCita(String idCita) {
        try {
            if (hospital.getCitas() == null || idCita == null) {
                return null;
            }

            return hospital.getCitas().stream()
                    .filter(c -> c.getIdCita().equals(idCita))
                    .findFirst()
                    .orElse(null);

        } catch (Exception e) {
            System.err.println("❌ Error al buscar cita: " + e.getMessage());
            return null;
        }
    }

    /**
     * Cancela una cita existente
     */
    public boolean cancelarCita(String idCita) {
        try {
            Cita cita = buscarCita(idCita);
            if (cita != null && cita.getEstado() == EstadoCita.PROGRAMADA) {
                cita.setEstado(EstadoCita.CANCELADA);

                // Liberar la sala
                if (cita.getSala() != null) {
                    cita.getSala().setEstado(EstadoSala.DISPONIBLE);
                }

                System.out.println("✅ Cita cancelada: " + idCita);
                return true;
            }
            return false;

        } catch (Exception e) {
            System.err.println("❌ Error al cancelar cita: " + e.getMessage());
            return false;
        }
    }

    /**
     * Completa una cita existente
     */
    public boolean completarCita(String idCita, String tratamientoFinal, String diagnosticoFinal) {
        try {
            Cita cita = buscarCita(idCita);
            if (cita != null && cita.getEstado() == EstadoCita.PROGRAMADA) {
                cita.setEstado(EstadoCita.FINALIZADA);

                if (tratamientoFinal != null && !tratamientoFinal.trim().isEmpty()) {
                    cita.setTratamiento(tratamientoFinal);
                }
                if (diagnosticoFinal != null && !diagnosticoFinal.trim().isEmpty()) {
                    cita.setDiagnostico(diagnosticoFinal);
                }

                // Liberar la sala
                if (cita.getSala() != null) {
                    cita.getSala().setEstado(EstadoSala.DISPONIBLE);
                }

                System.out.println("✅ Cita completada: " + idCita);
                return true;
            }
            return false;

        } catch (Exception e) {
            System.err.println("❌ Error al completar cita: " + e.getMessage());
            return false;
        }
    }

    /**
     * Obtiene todas las citas del hospital
     */
    public LinkedList<Cita> obtenerTodasLasCitas() {
        try {
            if (hospital.getCitas() == null) {
                return new LinkedList<>();
            }

            return new LinkedList<>(hospital.getCitas());

        } catch (Exception e) {
            System.err.println("❌ Error al obtener todas las citas: " + e.getMessage());
            return new LinkedList<>();
        }
    }

    /**
     * Obtiene citas por estado
     */
    public LinkedList<Cita> obtenerCitasPorEstado(EstadoCita estado) {
        try {
            if (hospital.getCitas() == null) {
                return new LinkedList<>();
            }

            return hospital.getCitas().stream()
                    .filter(c -> c.getEstado() == estado)
                    .collect(Collectors.toCollection(LinkedList::new));

        } catch (Exception e) {
            System.err.println("❌ Error al obtener citas por estado: " + e.getMessage());
            return new LinkedList<>();
        }
    }

    // ===== MÉTODOS DE UTILIDAD =====

    /**
     * Obtiene estadísticas básicas del hospital
     */
    public String obtenerEstadisticasHospital() {
        try {
            StringBuilder stats = new StringBuilder();
            stats.append("=== ESTADÍSTICAS DEL HOSPITAL ===\n");
            stats.append("Nombre: ").append(hospital.getNombre()).append("\n");
            stats.append("ID: ").append(hospital.getId()).append("\n\n");

            stats.append("Pacientes registrados: ").append(
                    hospital.getPacientes() != null ? hospital.getPacientes().size() : 0
            ).append("\n");

            stats.append("Médicos registrados: ").append(
                    hospital.getMedicos() != null ? hospital.getMedicos().size() : 0
            ).append("\n");

            stats.append("Salas totales: ").append(
                    hospital.getSalas() != null ? hospital.getSalas().size() : 0
            ).append("\n");

            stats.append("Salas disponibles: ").append(
                    obtenerSalasDisponibles().size()
            ).append("\n");

            stats.append("Total de citas: ").append(
                    hospital.getCitas() != null ? hospital.getCitas().size() : 0
            ).append("\n");

            // Estadísticas de citas por estado
            if (hospital.getCitas() != null && !hospital.getCitas().isEmpty()) {
                long citasProgramadas = hospital.getCitas().stream()
                        .filter(c -> c.getEstado() == EstadoCita.PROGRAMADA)
                        .count();
                long citasCompletadas = hospital.getCitas().stream()
                        .filter(c -> c.getEstado() == EstadoCita.FINALIZADA)
                        .count();
                long citasCanceladas = hospital.getCitas().stream()
                        .filter(c -> c.getEstado() == EstadoCita.CANCELADA)
                        .count();

                stats.append("- Programadas: ").append(citasProgramadas).append("\n");
                stats.append("- Completadas: ").append(citasCompletadas).append("\n");
                stats.append("- Canceladas: ").append(citasCanceladas).append("\n");
            }

            return stats.toString();

        } catch (Exception e) {
            return "Error al obtener estadísticas: " + e.getMessage();
        }
    }

    /**
     * Valida si el sistema está configurado correctamente
     */
    public boolean validarSistema() {
        try {
            boolean valido = true;
            StringBuilder errores = new StringBuilder();

            if (hospital == null) {
                errores.append("- Hospital no inicializado\n");
                valido = false;
            }

            if (hospital.getMedicos() == null || hospital.getMedicos().isEmpty()) {
                errores.append("- No hay médicos registrados\n");
                valido = false;
            }

            if (hospital.getSalas() == null || hospital.getSalas().isEmpty()) {
                errores.append("- No hay salas registradas\n");
                valido = false;
            }

            if (hospital.getHorarios() == null || hospital.getHorarios().isEmpty()) {
                errores.append("- No hay horarios registrados\n");
                valido = false;
            }

            if (!valido) {
                System.err.println("❌ Errores en la validación del sistema:\n" + errores.toString());
            } else {
                System.out.println("✅ Sistema validado correctamente");
            }

            return valido;

        } catch (Exception e) {
            System.err.println("❌ Error al validar sistema: " + e.getMessage());
            return false;
        }
    }

    // Exportar Datos
    public static DatosHospital exportarDatos() {
        try {
            DatosHospital datos = new DatosHospital();

            if (hospital != null) {
                // Exportar información básica del hospital
                datos.setNombreHospital(hospital.getNombre());
                datos.setIdHospital(hospital.getId());

                // Exportar listas
                datos.setPacientes(hospital.getPacientes());
                datos.setMedicos(hospital.getMedicos());
                datos.setSalas(hospital.getSalas());
                datos.setHorarios(hospital.getHorarios());
                datos.setCitas(hospital.getCitas());

                System.out.println("✅ Datos exportados correctamente");
            }

            return datos;

        } catch (Exception e) {
            System.err.println("❌ Error al exportar datos: " + e.getMessage());
            e.printStackTrace();
            return new DatosHospital(); // Retornar datos vacíos si hay error
        }
    }

    public HospitalUQ cargarDesdeDatos(DatosHospital datos) {
        try {
            if (datos == null) {
                System.out.println("⚠️ Datos nulos, manteniendo datos actuales");
                return hospital;
            }

            // Cargar datos del hospital principal
            if (datos.getNombreHospital() != null) {
                hospital.setNombre(datos.getNombreHospital());
            }
            if (datos.getIdHospital() != null) {
                hospital.setId(datos.getIdHospital());
            }

            // Cargar pacientes si existen
            if (datos.getPacientes() != null && !datos.getPacientes().isEmpty()) {
                hospital.setPacientes(new LinkedList<>(datos.getPacientes()));
                System.out.println("✅ Cargados " + datos.getPacientes().size() + " pacientes");
            }

            // Cargar médicos si existen
            if (datos.getMedicos() != null && !datos.getMedicos().isEmpty()) {
                hospital.setMedicos(new LinkedList<>(datos.getMedicos()));
                System.out.println("✅ Cargados " + datos.getMedicos().size() + " médicos");
            }

            // Cargar salas si existen
            if (datos.getSalas() != null && !datos.getSalas().isEmpty()) {
                hospital.setSalas(new LinkedList<>(datos.getSalas()));
                System.out.println("✅ Cargadas " + datos.getSalas().size() + " salas");
            }

            // Cargar horarios si existen
            if (datos.getHorarios() != null && !datos.getHorarios().isEmpty()) {
                hospital.setHorarios(new LinkedList<>(datos.getHorarios()));
                System.out.println("✅ Cargados " + datos.getHorarios().size() + " horarios");
            }

            // Cargar citas si existen
            if (datos.getCitas() != null && !datos.getCitas().isEmpty()) {
                hospital.setCitas(new LinkedList<>(datos.getCitas()));
                System.out.println("✅ Cargadas " + datos.getCitas().size() + " citas");
            }

            System.out.println("✅ Datos del hospital cargados correctamente");
            return hospital;

        } catch (Exception e) {
            System.err.println("❌ Error al cargar datos: " + e.getMessage());
            e.printStackTrace();
            return hospital; // Retornar hospital actual si hay error
        }
    }



    // ===== GETTERS =====

    public HospitalUQ getHospital() {
        return hospital;
    }
}