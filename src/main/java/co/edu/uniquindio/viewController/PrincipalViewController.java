package co.edu.uniquindio.viewController;

import co.edu.uniquindio.MedicalSystemApp;
import co.edu.uniquindio.Controller.HospitalController;
import co.edu.uniquindio.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.ResourceBundle;

/**
 * Controlador para la vista principal del sistema hospitalario
 */
public class PrincipalViewController implements Initializable {

    // Elementos del header
    @FXML private ImageView logoImageView;
    @FXML private Label welcomeLabel;
    @FXML private Circle statusIndicator;
    @FXML private Button logoutButton;

    // Botones principales
    @FXML private Button historialButton;
    @FXML private Button solicitarCitaButton;
    @FXML private Button gestionarCitasButton;
    @FXML private Button perfilButton;

    // Elementos de notificaciones
    @FXML private ListView<String> notificacionesListView;

    // Elementos de estadísticas
    @FXML private Label proximaCitaLabel;
    @FXML private Label totalCitasLabel;
    @FXML private Label ultimoAccesoLabel;

    // Variables de control
    private Paciente pacienteActual;
    private HospitalController hospitalController;
    private HospitalUQ hospital;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializar el hospital y controlador
        inicializarSistema();

        // Configurar elementos de la interfaz
        configurarInterfaz();

        // Cargar notificaciones
        cargarNotificaciones();

        System.out.println("✅ Vista principal inicializada correctamente");
    }

    /**
     * Inicializa el sistema hospitalario
     */
    private void inicializarSistema() {
        try {
            // Crear instancia del hospital
            hospital = new HospitalUQ("Hospital Universidad del Quindío", "HOSP_UQ_2024");

            // Crear controlador del hospital
            hospitalController = new HospitalController(hospital);

            System.out.println("✅ Sistema hospitalario inicializado");

        } catch (Exception e) {
            System.err.println("❌ Error al inicializar sistema: " + e.getMessage());
            mostrarAlerta("Error", "Error al inicializar el sistema hospitalario", Alert.AlertType.ERROR);
        }
    }

    /**
     * Configura los elementos iniciales de la interfaz
     */
    private void configurarInterfaz() {
        // Configurar indicador de estado
        statusIndicator.setFill(javafx.scene.paint.Color.web("#4CAF50"));

        // Configurar fecha de último acceso
        ultimoAccesoLabel.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        // Configurar lista de notificaciones
        configurarListaNotificaciones();
    }

    /**
     * Configura la lista de notificaciones
     */
    private void configurarListaNotificaciones() {
        ObservableList<String> notificaciones = FXCollections.observableArrayList();
        notificaciones.add("🏥 Bienvenido al Sistema Hospitalario UQ");
        notificaciones.add("📅 Recuerda agendar tus citas médicas regulares");
        notificaciones.add("💊 Mantén tu historial médico actualizado");

        notificacionesListView.setItems(notificaciones);
    }

    /**
     * Establece el paciente actual y actualiza la interfaz
     */
    public void setPacienteActual(Paciente paciente) {
        this.pacienteActual = paciente;

        if (paciente != null) {
            // Registrar paciente en el sistema si no existe
            Paciente pacienteRegistrado = hospitalController.registrarPaciente(
                    paciente.getNombre(),
                    paciente.getId(),
                    paciente.getCorreo(),
                    paciente.getTelefono()
            );

            if (pacienteRegistrado != null) {
                this.pacienteActual = pacienteRegistrado;
            }

            // Actualizar interfaz con datos del paciente
            actualizarInterfazPaciente();

            // Cargar estadísticas del paciente
            actualizarEstadisticasPaciente();
        }
    }

    /**
     * Actualiza la interfaz con los datos del paciente
     */
    private void actualizarInterfazPaciente() {
        if (pacienteActual != null) {
            welcomeLabel.setText("Bienvenido, " + pacienteActual.getNombre());
        }
    }

    /**
     * Actualiza las estadísticas del paciente
     */
    private void actualizarEstadisticasPaciente() {
        if (pacienteActual != null) {
            // Obtener citas del paciente
            LinkedList<Cita> citasPaciente = hospitalController.obtenerCitasPaciente(pacienteActual.getId());

            // Actualizar total de citas
            totalCitasLabel.setText(String.valueOf(citasPaciente.size()));

            // Buscar próxima cita
            Cita proximaCita = encontrarProximaCita(citasPaciente);
            if (proximaCita != null && proximaCita.getHorario() != null) {
                proximaCitaLabel.setText(
                        proximaCita.getHorario().getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                );
            } else {
                proximaCitaLabel.setText("No programada");
            }
        }
    }

    /**
     * Encuentra la próxima cita del paciente
     */
    private Cita encontrarProximaCita(LinkedList<Cita> citas) {
        LocalDate hoy = LocalDate.now();

        return citas.stream()
                .filter(c -> c.getEstado() == EstadoCita.PROGRAMADA)
                .filter(c -> c.getHorario() != null && c.getHorario().getFecha().isAfter(hoy))
                .min((c1, c2) -> c1.getHorario().getFecha().compareTo(c2.getHorario().getFecha()))
                .orElse(null);
    }

    /**
     * Carga las notificaciones del paciente
     */
    private void cargarNotificaciones() {
        ObservableList<String> notificaciones = FXCollections.observableArrayList();

        notificaciones.add("🏥 Sistema Hospitalario UQ activo");
        notificaciones.add("👤 Perfil de paciente verificado");
        notificaciones.add("📋 Historial médico disponible");

        if (pacienteActual != null) {
            LinkedList<Cita> citas = hospitalController.obtenerCitasPaciente(pacienteActual.getId());
            if (!citas.isEmpty()) {
                notificaciones.add("📅 Tienes " + citas.size() + " cita(s) registrada(s)");
            }
        }

        notificacionesListView.setItems(notificaciones);
    }

    // ===== MANEJADORES DE EVENTOS =====

    /**
     * Maneja el clic en "Ver Historial"
     */
    @FXML
    private void handleVerHistorial(ActionEvent event) {
        if (pacienteActual == null) {
            mostrarAlerta("Error", "No hay paciente registrado", Alert.AlertType.WARNING);
            return;
        }

        try {
            // Obtener citas del paciente
            LinkedList<Cita> citasPaciente = hospitalController.obtenerCitasPaciente(pacienteActual.getId());

            StringBuilder historial = new StringBuilder();
            historial.append("=== HISTORIAL MÉDICO ===\n");
            historial.append("Paciente: ").append(pacienteActual.getNombre()).append("\n");
            historial.append("ID: ").append(pacienteActual.getId()).append("\n");
            historial.append("Correo: ").append(pacienteActual.getCorreo()).append("\n");
            historial.append("Teléfono: ").append(pacienteActual.getTelefono()).append("\n\n");

            if (citasPaciente.isEmpty()) {
                historial.append("No hay citas registradas en el historial.\n");
            } else {
                historial.append("CITAS REGISTRADAS:\n");
                historial.append("-".repeat(50)).append("\n");

                for (Cita cita : citasPaciente) {
                    historial.append("ID Cita: ").append(cita.getIdCita()).append("\n");
                    if (cita.getHorario() != null) {
                        historial.append("Fecha: ").append(cita.getHorario().getFecha()).append("\n");
                        historial.append("Hora: ").append(cita.getHorario().getHora()).append("\n");
                    }
                    if (cita.getMedico() != null) {
                        historial.append("Médico: ").append(cita.getMedico().getNombre()).append("\n");
                        historial.append("Especialidad: ").append(cita.getMedico().getEspecialidad()).append("\n");
                    }
                    historial.append("Estado: ").append(cita.getEstado()).append("\n");
                    if (cita.getDiagnostico() != null && !cita.getDiagnostico().isEmpty()) {
                        historial.append("Diagnóstico: ").append(cita.getDiagnostico()).append("\n");
                    }
                    if (cita.getTratamiento() != null && !cita.getTratamiento().isEmpty()) {
                        historial.append("Tratamiento: ").append(cita.getTratamiento()).append("\n");
                    }
                    historial.append("-".repeat(30)).append("\n");
                }
            }

            mostrarInformacion("Historial Médico", historial.toString());

        } catch (Exception e) {
            System.err.println("❌ Error al mostrar historial: " + e.getMessage());
            mostrarAlerta("Error", "Error al cargar el historial médico", Alert.AlertType.ERROR);
        }
    }

    /**
     * Maneja el clic en "Solicitar Cita"
     */
    @FXML
    private void handleSolicitarCita(ActionEvent event) {
        if (pacienteActual == null) {
            mostrarAlerta("Error", "No hay paciente registrado", Alert.AlertType.WARNING);
            return;
        }

        try {
            // Obtener médicos disponibles
            LinkedList<Medico> medicosDisponibles = hospitalController.obtenerMedicosDisponibles();

            if (medicosDisponibles.isEmpty()) {
                mostrarAlerta("Error", "No hay médicos disponibles en este momento", Alert.AlertType.WARNING);
                return;
            }

            // Crear cuadro de diálogo para solicitar cita
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Solicitar Nueva Cita");
            dialog.setHeaderText("Complete los datos para solicitar su cita médica");

            // Crear formulario
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            ComboBox<Medico> medicoCombo = new ComboBox<>();
            medicoCombo.getItems().addAll(medicosDisponibles);
            medicoCombo.setConverter(new StringConverter<Medico>() {
                @Override
                public String toString(Medico medico) {
                    return medico != null ? medico.getNombre() + " - " + medico.getEspecialidad() : "";
                }

                @Override
                public Medico fromString(String string) {
                    return null;
                }
            });

            DatePicker fechaPicker = new DatePicker(LocalDate.now().plusDays(1));
            ComboBox<String> horaCombo = new ComboBox<>();
            horaCombo.getItems().addAll("08:00", "09:00", "10:00", "11:00", "14:00", "15:00", "16:00", "17:00");

            TextArea motivoArea = new TextArea();
            motivoArea.setPrefRowCount(3);
            motivoArea.setPromptText("Motivo de la consulta...");

            grid.add(new Label("Médico:"), 0, 0);
            grid.add(medicoCombo, 1, 0);
            grid.add(new Label("Fecha:"), 0, 1);
            grid.add(fechaPicker, 1, 1);
            grid.add(new Label("Hora:"), 0, 2);
            grid.add(horaCombo, 1, 2);
            grid.add(new Label("Motivo:"), 0, 3);
            grid.add(motivoArea, 1, 3);

            dialog.getDialogPane().setContent(grid);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == ButtonType.OK) {
                    return "OK";
                }
                return null;
            });

            dialog.showAndWait().ifPresent(result -> {
                if ("OK".equals(result)) {
                    try {
                        Medico medicoSeleccionado = medicoCombo.getValue();
                        LocalDate fechaSeleccionada = fechaPicker.getValue();
                        String horaSeleccionada = horaCombo.getValue();
                        String motivo = motivoArea.getText();

                        if (medicoSeleccionado == null || fechaSeleccionada == null ||
                                horaSeleccionada == null || motivo.trim().isEmpty()) {
                            mostrarAlerta("Error", "Por favor complete todos los campos", Alert.AlertType.WARNING);
                            return;
                        }

                        // Crear cita
                        String idCita = "CITA_" + System.currentTimeMillis();

                        // Buscar horario disponible o crear uno nuevo
                        Horario horario = hospitalController.obtenerTodosHorarios().stream()
                                .filter(h -> h.getFecha().equals(fechaSeleccionada))
                                .findFirst()
                                .orElse(hospitalController.registrarHorario(
                                        "H_" + System.currentTimeMillis(),
                                        fechaSeleccionada,
                                        java.time.LocalTime.parse(horaSeleccionada),
                                        fechaSeleccionada.getDayOfMonth() < 12 ? Jornada.MANANA : Jornada.TARDE
                                ));

                        // Buscar sala disponible
                        LinkedList<Sala> salasDisponibles = hospitalController.obtenerSalasDisponibles();
                        if (salasDisponibles.isEmpty()) {
                            mostrarAlerta("Error", "No hay salas disponibles", Alert.AlertType.WARNING);
                            return;
                        }

                        Cita nuevaCita = hospitalController.crearCita(
                                idCita,
                                pacienteActual.getId(),
                                medicoSeleccionado.getId(),
                                salasDisponibles.get(0).getIdSala(),
                                horario,
                                motivo,
                                "Pendiente de diagnóstico"
                        );

                        if (nuevaCita != null) {
                            mostrarAlerta("Éxito", "Cita solicitada correctamente\nID: " + idCita, Alert.AlertType.INFORMATION);
                            actualizarEstadisticasPaciente();
                            cargarNotificaciones();
                        } else {
                            mostrarAlerta("Error", "No se pudo crear la cita", Alert.AlertType.ERROR);
                        }

                    } catch (Exception e) {
                        System.err.println("❌ Error al crear cita: " + e.getMessage());
                        mostrarAlerta("Error", "Error al procesar la solicitud de cita", Alert.AlertType.ERROR);
                    }
                }
            });

        } catch (Exception e) {
            System.err.println("❌ Error al solicitar cita: " + e.getMessage());
            mostrarAlerta("Error", "Error al abrir formulario de citas", Alert.AlertType.ERROR);
        }
    }

    /**
     * Maneja el clic en "Gestionar Citas"
     */
    @FXML
    private void handleGestionarCitas(ActionEvent event) {
        if (pacienteActual == null) {
            mostrarAlerta("Error", "No hay paciente registrado", Alert.AlertType.WARNING);
            return;
        }

        try {
            LinkedList<Cita> citasPaciente = hospitalController.obtenerCitasPaciente(pacienteActual.getId());

            if (citasPaciente.isEmpty()) {
                mostrarAlerta("Información", "No tienes citas registradas", Alert.AlertType.INFORMATION);
                return;
            }

            // Crear cuadro de diálogo para gestionar citas
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Gestionar Mis Citas");
            dialog.setHeaderText("Selecciona una cita para ver detalles o cancelar");

            ListView<Cita> citasListView = new ListView<>();
            citasListView.getItems().addAll(citasPaciente);
            citasListView.setCellFactory(param -> new ListCell<Cita>() {
                @Override
                protected void updateItem(Cita cita, boolean empty) {
                    super.updateItem(cita, empty);
                    if (empty || cita == null) {
                        setText(null);
                    } else {
                        String texto = String.format("ID: %s | Fecha: %s | Estado: %s | Médico: %s",
                                cita.getIdCita(),
                                cita.getHorario() != null ? cita.getHorario().getFecha() : "N/A",
                                cita.getEstado(),
                                cita.getMedico() != null ? cita.getMedico().getNombre() : "N/A"
                        );
                        setText(texto);
                    }
                }
            });

            VBox content = new VBox(10);
            content.getChildren().addAll(
                    new Label("Tus citas:"),
                    citasListView
            );

            dialog.getDialogPane().setContent(content);
            dialog.getDialogPane().getButtonTypes().addAll(
                    new ButtonType("Ver Detalles"),
                    new ButtonType("Cancelar Cita"),
                    ButtonType.CLOSE
            );

            dialog.setResultConverter(ButtonType::getText);

            dialog.showAndWait().ifPresent(result -> {
                Cita citaSeleccionada = citasListView.getSelectionModel().getSelectedItem();

                if ("Ver Detalles".equals(result) && citaSeleccionada != null) {
                    mostrarDetallesCita(citaSeleccionada);
                } else if ("Cancelar Cita".equals(result) && citaSeleccionada != null) {
                    if (citaSeleccionada.getEstado() == EstadoCita.PROGRAMADA) {
                        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                        confirmacion.setTitle("Confirmar Cancelación");
                        confirmacion.setHeaderText("¿Está seguro de cancelar esta cita?");
                        confirmacion.setContentText("Esta acción no se puede deshacer.");

                        confirmacion.showAndWait().ifPresent(response -> {
                            if (response == ButtonType.OK) {
                                if (hospitalController.cancelarCita(citaSeleccionada.getIdCita())) {
                                    mostrarAlerta("Éxito", "Cita cancelada correctamente", Alert.AlertType.INFORMATION);
                                    actualizarEstadisticasPaciente();
                                } else {
                                    mostrarAlerta("Error", "No se pudo cancelar la cita", Alert.AlertType.ERROR);
                                }
                            }
                        });
                    } else {
                        mostrarAlerta("Error", "Solo se pueden cancelar citas programadas", Alert.AlertType.WARNING);
                    }
                }
            });

        } catch (Exception e) {
            System.err.println("❌ Error al gestionar citas: " + e.getMessage());
            mostrarAlerta("Error", "Error al cargar las citas", Alert.AlertType.ERROR);
        }
    }

    /**
     * Muestra los detalles de una cita específica
     */
    private void mostrarDetallesCita(Cita cita) {
        StringBuilder detalles = new StringBuilder();
        detalles.append("=== DETALLES DE LA CITA ===\n\n");
        detalles.append("ID: ").append(cita.getIdCita()).append("\n");
        detalles.append("Estado: ").append(cita.getEstado()).append("\n");

        if (cita.getHorario() != null) {
            detalles.append("Fecha: ").append(cita.getHorario().getFecha()).append("\n");
            detalles.append("Hora: ").append(cita.getHorario().getHora()).append("\n");
            detalles.append("Jornada: ").append(cita.getHorario().getJornada()).append("\n");
        }

        if (cita.getMedico() != null) {
            detalles.append("Médico: ").append(cita.getMedico().getNombre()).append("\n");
            detalles.append("Especialidad: ").append(cita.getMedico().getEspecialidad()).append("\n");
        }

        if (cita.getSala() != null) {
            detalles.append("Sala: ").append(cita.getSala().getIdSala()).append("\n");
            detalles.append("Tipo de Sala: ").append(cita.getSala().getTipoSala()).append("\n");
        }

        if (cita.getTratamiento() != null && !cita.getTratamiento().isEmpty()) {
            detalles.append("Tratamiento: ").append(cita.getTratamiento()).append("\n");
        }

        if (cita.getDiagnostico() != null && !cita.getDiagnostico().isEmpty()) {
            detalles.append("Diagnóstico: ").append(cita.getDiagnostico()).append("\n");
        }

        mostrarInformacion("Detalles de la Cita", detalles.toString());
    }

    /**
     * Maneja el clic en "Editar Perfil"
     */
    @FXML
    private void handleEditarPerfil(ActionEvent event) {
        if (pacienteActual == null) {
            mostrarAlerta("Error", "No hay paciente registrado", Alert.AlertType.WARNING);
            return;
        }

        try {
            // Crear cuadro de diálogo para editar perfil
            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle("Editar Perfil");
            dialog.setHeaderText("Actualiza tu información personal");

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            TextField nombreField = new TextField(pacienteActual.getNombre());
            TextField correoField = new TextField(pacienteActual.getCorreo());
            TextField telefonoField = new TextField(pacienteActual.getTelefono());
            Label idLabel = new Label(pacienteActual.getId());

            grid.add(new Label("ID (no modificable):"), 0, 0);
            grid.add(idLabel, 1, 0);
            grid.add(new Label("Nombre:"), 0, 1);
            grid.add(nombreField, 1, 1);
            grid.add(new Label("Correo:"), 0, 2);
            grid.add(correoField, 1, 2);
            grid.add(new Label("Teléfono:"), 0, 3);
            grid.add(telefonoField, 1, 3);

            dialog.getDialogPane().setContent(grid);
            dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == ButtonType.OK) {
                    return "SAVE";
                }
                return null;
            });

            dialog.showAndWait().ifPresent(result -> {
                if ("SAVE".equals(result)) {
                    String nuevoNombre = nombreField.getText().trim();
                    String nuevoCorreo = correoField.getText().trim();
                    String nuevoTelefono = telefonoField.getText().trim();

                    if (nuevoNombre.isEmpty() || nuevoCorreo.isEmpty() || nuevoTelefono.isEmpty()) {
                        mostrarAlerta("Error", "Todos los campos son obligatorios", Alert.AlertType.WARNING);
                        return;
                    }

                    // Actualizar datos del paciente
                    pacienteActual.setNombre(nuevoNombre);
                    pacienteActual.setCorreo(nuevoCorreo);
                    pacienteActual.setTelefono(nuevoTelefono);

                    // Actualizar interfaz
                    actualizarInterfazPaciente();

                    mostrarAlerta("Éxito", "Perfil actualizado correctamente", Alert.AlertType.INFORMATION);
                }
            });

        } catch (Exception e) {
            System.err.println("❌ Error al editar perfil: " + e.getMessage());
            mostrarAlerta("Error", "Error al editar el perfil", Alert.AlertType.ERROR);
        }
    }

    /**
     * Maneja el clic en "Cerrar Sesión"
     */
    @FXML
    private void handleLogout(ActionEvent event) {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar Cierre de Sesión");
        confirmacion.setHeaderText("¿Está seguro de cerrar sesión?");
        confirmacion.setContentText("Será redirigido a la pantalla de login.");

        confirmacion.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    // Limpiar datos del paciente actual
                    pacienteActual = null;

                    // Volver al login
                    MedicalSystemApp.volverAlLogin();

                    System.out.println("✅ Sesión cerrada correctamente");

                } catch (Exception e) {
                    System.err.println("❌ Error al cerrar sesión: " + e.getMessage());
                    mostrarAlerta("Error", "Error al cerrar sesión", Alert.AlertType.ERROR);
                }
            }
        });
    }

    // ===== MÉTODOS DE UTILIDAD =====

    /**
     * Muestra una alerta
     */
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Muestra información en un cuadro de diálogo más grande
     */
    private void mostrarInformacion(String titulo, String contenido) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);

        TextArea textArea = new TextArea(contenido);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setPrefRowCount(20);
        textArea.setPrefColumnCount(60);

        alert.getDialogPane().setContent(textArea);
        alert.showAndWait();
    }

    // ===== GETTERS Y SETTERS =====

    public Paciente getPacienteActual() {
        return pacienteActual;
    }

    public Label getWelcomeLabel() {
        return welcomeLabel;
    }

    public void setWelcomeLabel(Label welcomeLabel) {
        this.welcomeLabel = welcomeLabel;
    }

    public Circle getStatusIndicator() {
        return statusIndicator;
    }

    public void setStatusIndicator(Circle statusIndicator) {
        this.statusIndicator = statusIndicator;
    }

    public ListView<String> getNotificacionesListView() {
        return notificacionesListView;
    }

    public void setNotificacionesListView(ListView<String> notificacionesListView) {
        this.notificacionesListView = notificacionesListView;
    }

    public Label getProximaCitaLabel() {
        return proximaCitaLabel;
    }

    public void setProximaCitaLabel(Label proximaCitaLabel) {
        this.proximaCitaLabel = proximaCitaLabel;
    }

    public Label getTotalCitasLabel() {
        return totalCitasLabel;
    }

    public void setTotalCitasLabel(Label totalCitasLabel) {
        this.totalCitasLabel = totalCitasLabel;
    }

    public Label getUltimoAccesoLabel() {
        return ultimoAccesoLabel;
    }

    public void setUltimoAccesoLabel(Label ultimoAccesoLabel) {
        this.ultimoAccesoLabel = ultimoAccesoLabel;
    }

    public void setHospitalController(HospitalController hospitalController) {
        this.hospitalController = hospitalController;
    }

    public HospitalUQ getHospital() {
        return hospital;
    }

    public void setHospital(HospitalUQ hospital) {
        this.hospital = hospital;
    }

    public HospitalController getHospitalController() {
        return hospitalController;
    }

    public Button getGestionarCitasButton() {
        return gestionarCitasButton;
    }

    public void setGestionarCitasButton(Button gestionarCitasButton) {
        this.gestionarCitasButton = gestionarCitasButton;
    }

    public Button getPerfilButton() {
        return perfilButton;
    }

    public void setPerfilButton(Button perfilButton) {
        this.perfilButton = perfilButton;
    }

    public Button getSolicitarCitaButton() {
        return solicitarCitaButton;
    }

    public void setSolicitarCitaButton(Button solicitarCitaButton) {
        this.solicitarCitaButton = solicitarCitaButton;
    }

    public Button getHistorialButton() {
        return historialButton;
    }

    public void setHistorialButton(Button historialButton) {
        this.historialButton = historialButton;
    }

    public ImageView getLogoImageView() {
        return logoImageView;
    }

    public void setLogoImageView(ImageView logoImageView) {
        this.logoImageView = logoImageView;
    }

    public Button getLogoutButton() {
        return logoutButton;
    }

    public void setLogoutButton(Button logoutButton) {
        this.logoutButton = logoutButton;
    }
}