package co.edu.uniquindio.viewController;

import co.edu.uniquindio.Controller.HospitalController;
import co.edu.uniquindio.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class PrincipalViewController implements Initializable {

    // Referencias a elementos del FXML
    @FXML private ImageView logoImageView;
    @FXML private Label welcomeLabel;
    @FXML private Circle statusIndicator;
    @FXML private Button logoutButton;
    @FXML private Button historialButton;
    @FXML private Button solicitarCitaButton;
    @FXML private Button gestionarCitasButton;
    @FXML private Button perfilButton;
    @FXML private ListView<String> notificacionesListView;
    @FXML private Label proximaCitaLabel;
    @FXML private Label totalCitasLabel;
    @FXML private Label ultimoAccesoLabel;

    // Referencias al controller y modelo
    private HospitalController hospitalController;
    private Paciente pacienteActual;
    private HospitalUQ hospital;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Inicializar controller y hospital
        hospital = new HospitalUQ("Hospital UQ", "HOSP001");
        hospitalController = new HospitalController(hospital);

        // Configurar interfaz inicial
        configurarInterfazInicial();
        actualizarEstadisticas();
        cargarNotificaciones();
    }

    /**
     * Establece el paciente actual que está usando la interfaz
     */
    public void setPacienteActual(Paciente paciente) {
        this.pacienteActual = paciente;
        paciente.setHospital(hospital); // Importante: conectar paciente con hospital
        actualizarInformacionPaciente();
        actualizarEstadisticas();
    }

    private void configurarInterfazInicial() {
        // Configurar último acceso
        ultimoAccesoLabel.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
    }

    private void actualizarInformacionPaciente() {
        if (pacienteActual != null) {
            welcomeLabel.setText("Bienvenido, " + pacienteActual.getNombre());
        }
    }

    private void actualizarEstadisticas() {
        if (pacienteActual != null) {
            // Actualizar total de citas
            int totalCitas = pacienteActual.getCitas().size();
            totalCitasLabel.setText(String.valueOf(totalCitas));

            // Buscar próxima cita
            Cita proximaCita = buscarProximaCita();
            if (proximaCita != null) {
                proximaCitaLabel.setText(proximaCita.getHorario().toString());
            } else {
                proximaCitaLabel.setText("No programada");
            }
        }
    }

    private Cita buscarProximaCita() {
        if (pacienteActual == null || pacienteActual.getCitas().isEmpty()) {
            return null;
        }

        // Buscar la primera cita con estado PROGRAMADA
        for (Cita cita : pacienteActual.getCitas()) {
            if (cita.getEstado() == EstadoCita.PROGRAMADA) {
                return cita;
            }
        }
        return null;
    }

    private void cargarNotificaciones() {
        ObservableList<String> notificaciones = FXCollections.observableArrayList();

        if (pacienteActual != null && !pacienteActual.getCitas().isEmpty()) {
            notificaciones.add("✅ Tiene " + pacienteActual.getCitas().size() + " cita(s) registrada(s)");

            Cita proximaCita = buscarProximaCita();
            if (proximaCita != null) {
                notificaciones.add("📅 Próxima cita: " + proximaCita.getHorario().toString());
            }
        } else {
            notificaciones.add("ℹ️ No tiene citas programadas");
        }

        notificacionesListView.setItems(notificaciones);
    }

    // MANEJADORES DE EVENTOS DE LA INTERFAZ

    @FXML
    private void handleVerHistorial() {
        try {
            if (pacienteActual == null) {
                mostrarAlerta("Error", "No hay paciente activo", Alert.AlertType.ERROR);
                return;
            }

            // Aquí podrías abrir una nueva ventana para mostrar el historial
            mostrarInformacion("Historial Médico",
                    "Paciente: " + pacienteActual.getNombre() +
                            "\nID: " + pacienteActual.getId() +
                            "\nMédico Asignado: " +
                            (pacienteActual.getMedicoAsignado() != null ?
                                    pacienteActual.getMedicoAsignado().getNombre() : "No asignado"));

        } catch (Exception e) {
            mostrarAlerta("Error", "Error al cargar historial: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleSolicitarCita() {
        try {
            if (pacienteActual == null) {
                mostrarAlerta("Error", "No hay paciente activo", Alert.AlertType.ERROR);
                return;
            }

            // Crear diálogo para solicitar cita
            Dialog<Cita> dialog = crearDialogoSolicitarCita();
            dialog.showAndWait().ifPresent(cita -> {
                // Agregar la cita a la lista del paciente
                pacienteActual.getCitas().add(cita);
                actualizarEstadisticas();
                cargarNotificaciones();
                mostrarAlerta("Éxito", "Cita solicitada correctamente", Alert.AlertType.INFORMATION);
            });

        } catch (Exception e) {
            mostrarAlerta("Error", "Error al solicitar cita: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleGestionarCitas() {
        try {
            if (pacienteActual == null || pacienteActual.getCitas().isEmpty()) {
                mostrarAlerta("Información", "No tiene citas para gestionar", Alert.AlertType.INFORMATION);
                return;
            }

            // Mostrar lista de citas para gestionar
            StringBuilder citasInfo = new StringBuilder("Sus citas:\n\n");
            for (int i = 0; i < pacienteActual.getCitas().size(); i++) {
                Cita cita = pacienteActual.getCitas().get(i);
                citasInfo.append((i + 1)).append(". ID: ").append(cita.getIdCita())
                        .append(" - Estado: ").append(cita.getEstado())
                        .append(" - Horario: ").append(cita.getHorario().toString())
                        .append("\n");
            }

            mostrarInformacion("Gestión de Citas", citasInfo.toString());

        } catch (Exception e) {
            mostrarAlerta("Error", "Error al gestionar citas: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleEditarPerfil() {
        try {
            if (pacienteActual == null) {
                mostrarAlerta("Error", "No hay paciente activo", Alert.AlertType.ERROR);
                return;
            }

            // Mostrar información del perfil actual
            String perfilInfo = "Información del Perfil:\n\n" +
                    "Nombre: " + pacienteActual.getNombre() + "\n" +
                    "ID: " + pacienteActual.getId() + "\n" +
                    "Correo: " + pacienteActual.getCorreo() + "\n" +
                    "Teléfono: " + pacienteActual.getTelefono() + "\n" +
                    "Médico Asignado: " +
                    (pacienteActual.getMedicoAsignado() != null ?
                            pacienteActual.getMedicoAsignado().getNombre() : "No asignado");

            mostrarInformacion("Mi Perfil", perfilInfo);

        } catch (Exception e) {
            mostrarAlerta("Error", "Error al cargar perfil: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleLogout() {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Cerrar Sesión");
        confirmacion.setHeaderText("¿Está seguro que desea cerrar sesión?");
        confirmacion.setContentText("Se cerrará la aplicación");

        confirmacion.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                // Cerrar la aplicación
                Stage stage = (Stage) logoutButton.getScene().getWindow();
                stage.close();
            }
        });
    }

    // MÉTODOS AUXILIARES

    private Dialog<Cita> crearDialogoSolicitarCita() {
        Dialog<Cita> dialog = new Dialog<>();
        dialog.setTitle("Solicitar Nueva Cita");
        dialog.setHeaderText("Complete la información para solicitar una cita");

        // Crear campos del formulario
        TextField idCitaField = new TextField();
        idCitaField.setPromptText("ID de la cita");

        TextField tratamientoField = new TextField();
        tratamientoField.setPromptText("Tratamiento solicitado");

        TextField diagnosticoField = new TextField();
        diagnosticoField.setPromptText("Diagnóstico inicial");

        ComboBox<Especialidad> especialidadCombo = new ComboBox<>();
        especialidadCombo.getItems().addAll(Especialidad.values());
        especialidadCombo.setPromptText("Seleccione especialidad");

        TextField salaField = new TextField();
        salaField.setPromptText("ID de sala preferida");

        // Layout del formulario
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label("ID Cita:"), 0, 0);
        grid.add(idCitaField, 1, 0);
        grid.add(new Label("Tratamiento:"), 0, 1);
        grid.add(tratamientoField, 1, 1);
        grid.add(new Label("Diagnóstico:"), 0, 2);
        grid.add(diagnosticoField, 1, 2);
        grid.add(new Label("Especialidad:"), 0, 3);
        grid.add(especialidadCombo, 1, 3);
        grid.add(new Label("Sala:"), 0, 4);
        grid.add(salaField, 1, 4);

        dialog.getDialogPane().setContent(grid);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        // Convertir resultado a Cita
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                try {
                    // Crear horario básico (podrías mejorarlo con DatePicker)
                    Horario horario = new Horario("H001", LocalDate.now(), LocalTime.now(), Jornada.TARDE);

                    Cita cita = pacienteActual.solicitarCita(
                            tratamientoField.getText(),
                            diagnosticoField.getText(),
                            especialidadCombo.getValue(),
                            idCitaField.getText(),
                            salaField.getText(),
                            horario
                    );

                    return cita;
                } catch (Exception e) {
                    mostrarAlerta("Error", "Error al crear cita: " + e.getMessage(), Alert.AlertType.ERROR);
                    return null;
                }
            }
            return null;
        });

        return dialog;
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void mostrarInformacion(String titulo, String contenido) {
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setTitle(titulo);
        info.setHeaderText(null);
        info.setContentText(contenido);
        info.showAndWait();
    }
}