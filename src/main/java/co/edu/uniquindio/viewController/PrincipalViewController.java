package co.edu.uniquindio.viewController;

import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class PrincipalViewController implements Initializable {

    // Elementos del Header
    @FXML private ImageView logoImageView;
    @FXML private Label welcomeLabel;
    @FXML private Circle statusIndicator;
    @FXML private Button logoutButton;

    // Botones principales
    @FXML private Button historialButton;
    @FXML private Button solicitarCitaButton;
    @FXML private Button gestionarCitasButton;
    @FXML private Button perfilButton;

    // Sección de notificaciones
    @FXML private ListView<String> notificacionesListView;

    // Labels de estadísticas
    @FXML private Label proximaCitaLabel;
    @FXML private Label totalCitasLabel;
    @FXML private Label ultimoAccesoLabel;

    // Variables de estado
    private String nombrePaciente = "Juan Pérez"; // Se actualizará desde el login
    private ObservableList<String> notificaciones = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarInterfaz();
        inicializarAnimaciones();
        cargarDatosIniciales();
        configurarNotificaciones();
    }

    /**
     * Configura la interfaz inicial
     */
    private void configurarInterfaz() {
        // Configurar label de bienvenida
        welcomeLabel.setText("Bienvenido, " + nombrePaciente);

        // Configurar fecha actual
        ultimoAccesoLabel.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        // Efectos visuales para botones
        configurarEfectosBotones();

        // Configurar indicador de estado
        configurarIndicadorEstado();
    }

    /**
     * Configura efectos visuales para los botones
     */
    private void configurarEfectosBotones() {
        Button[] botones = {historialButton, solicitarCitaButton, gestionarCitasButton, perfilButton, logoutButton};

        for (Button boton : botones) {
            // Efecto hover
            boton.setOnMouseEntered(e -> {
                ScaleTransition scale = new ScaleTransition(Duration.millis(200), boton);
                scale.setToX(1.1);
                scale.setToY(1.1);
                scale.play();

                // Aumentar sombra
                DropShadow shadow = new DropShadow();
                shadow.setColor(Color.rgb(0, 0, 0, 0.3));
                shadow.setRadius(20);
                shadow.setOffsetY(8);
                boton.setEffect(shadow);
            });

            boton.setOnMouseExited(e -> {
                ScaleTransition scale = new ScaleTransition(Duration.millis(200), boton);
                scale.setToX(1.0);
                scale.setToY(1.0);
                scale.play();

                // Restaurar sombra original
                DropShadow shadow = new DropShadow();
                shadow.setColor(Color.rgb(0, 0, 0, 0.1));
                shadow.setRadius(10);
                shadow.setOffsetY(5);
                boton.setEffect(shadow);
            });
        }
    }

    /**
     * Configura el indicador de estado con animación
     */
    private void configurarIndicadorEstado() {
        // Animación pulsante para el indicador
        ScaleTransition pulso = new ScaleTransition(Duration.seconds(1), statusIndicator);
        pulso.setFromX(1.0);
        pulso.setFromY(1.0);
        pulso.setToX(1.3);
        pulso.setToY(1.3);
        pulso.setAutoReverse(true);
        pulso.setCycleCount(Timeline.INDEFINITE);
        pulso.play();
    }

    /**
     * Inicializa animaciones de entrada
     */
    private void inicializarAnimaciones() {
        // Animación de entrada fade-in
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5));
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.setNode(logoImageView.getParent());
        fadeIn.play();
    }

    /**
     * Carga datos iniciales simulados
     */
    private void cargarDatosIniciales() {
        // Simular datos de citas
        proximaCitaLabel.setText("15/06/2024 - 10:30 AM");
        totalCitasLabel.setText("12");

        // Agregar notificaciones de ejemplo
        agregarNotificacion("💊 Recordatorio: Tomar medicamento a las 8:00 PM");
        agregarNotificacion("📅 Su cita del 15/06 ha sido confirmada");
        agregarNotificacion("✅ Resultados de laboratorio disponibles");
    }

    /**
     * Configura el sistema de notificaciones
     */
    private void configurarNotificaciones() {
        notificacionesListView.setItems(notificaciones);

        // Personalizar celdas de notificaciones
        notificacionesListView.setCellFactory(listView -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    setStyle("-fx-padding: 10; -fx-border-color: #E2E8F0; -fx-border-width: 0 0 1 0;");
                }
            }
        });
    }

    /**
     * Agrega una nueva notificación
     */
    private void agregarNotificacion(String mensaje) {
        notificaciones.add(0, mensaje); // Agregar al principio
        if (notificaciones.size() > 5) {
            notificaciones.remove(notificaciones.size() - 1); // Mantener solo 5
        }

        // Animación para nueva notificación
        if (!notificaciones.isEmpty()) {
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(0.5), e -> {
                        notificacionesListView.getSelectionModel().select(0);
                        notificacionesListView.scrollTo(0);
                    })
            );
            timeline.play();
        }
    }

    // ================================
    // MÉTODOS DE MANEJO DE EVENTOS
    // ================================

    /**
     * Maneja la acción de ver historial
     */
    @FXML
    private void handleVerHistorial() {
        mostrarMensaje("Historial Médico", "Abriendo historial médico completo...", Alert.AlertType.INFORMATION);
        // Aquí cargarías la vista del historial
        System.out.println("🏥 Abriendo historial médico del paciente: " + nombrePaciente);
    }

    /**
     * Maneja la acción de solicitar cita
     */
    @FXML
    private void handleSolicitarCita() {
        mostrarMensaje("Solicitar Cita", "Abriendo formulario para solicitar nueva cita médica...", Alert.AlertType.INFORMATION);
        // Aquí cargarías la vista de solicitud de citas
        System.out.println("📅 Solicitando nueva cita para: " + nombrePaciente);
        agregarNotificacion("📝 Nueva solicitud de cita en proceso...");
    }

    /**
     * Maneja la gestión de citas existentes
     */
    @FXML
    private void handleGestionarCitas() {
        mostrarMensaje("Gestionar Citas", "Abriendo panel de gestión de citas...", Alert.AlertType.INFORMATION);
        // Aquí cargarías la vista de gestión de citas
        System.out.println("⚙️ Gestionando citas de: " + nombrePaciente);
    }

    /**
     * Maneja la edición del perfil
     */
    @FXML
    private void handleEditarPerfil() {
        mostrarMensaje("Editar Perfil", "Abriendo configuración del perfil de usuario...", Alert.AlertType.INFORMATION);
        // Aquí cargarías la vista de edición de perfil
        System.out.println("👤 Editando perfil de: " + nombrePaciente);
    }

    /**
     * Maneja el cierre de sesión
     */
    @FXML
    private void handleLogout() {
        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar Cierre de Sesión");
        confirmacion.setHeaderText("¿Estás seguro de que deseas cerrar sesión?");
        confirmacion.setContentText("Se perderán los datos no guardados.");

        // Personalizar botones
        ButtonType btnSi = new ButtonType("Sí, cerrar sesión");
        ButtonType btnNo = new ButtonType("Cancelar");
        confirmacion.getButtonTypes().setAll(btnSi, btnNo);

        confirmacion.showAndWait().ifPresent(response -> {
            if (response == btnSi) {
                cerrarSesionYVolverAlLogin();
            }
        });
    }

    /**
     * Cierra la sesión actual y vuelve al login
     */
    private void cerrarSesionYVolverAlLogin() {
        try {
            // Animación de salida
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.8));
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setNode(logoImageView.getScene().getRoot());

            fadeOut.setOnFinished(e -> {
                try {
                    // Cargar la vista de login
                    FXMLLoader loader = new FXMLLoader(
                            getClass().getResource("/co.edu.uniquindio.javafx/login-view.fxml")
                    );
                    Scene loginScene = new Scene(loader.load());

                    // Obtener la ventana actual
                    Stage currentStage = (Stage) logoutButton.getScene().getWindow();

                    // Configurar nueva escena
                    currentStage.setScene(loginScene);
                    currentStage.setTitle("Sistema Hospitalario UQ - Login");
                    currentStage.centerOnScreen();

                    System.out.println("✅ Sesión cerrada correctamente");

                } catch (IOException ex) {
                    System.err.println("❌ Error al cargar el login: " + ex.getMessage());
                    mostrarMensaje("Error", "No se pudo cargar la pantalla de login", Alert.AlertType.ERROR);
                }
            });

            fadeOut.play();

        } catch (Exception e) {
            System.err.println("❌ Error al cerrar sesión: " + e.getMessage());
            mostrarMensaje("Error", "Error al cerrar sesión", Alert.AlertType.ERROR);
        }
    }

    /**
     * Muestra un mensaje al usuario
     */
    private void mostrarMensaje(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        // Personalizar el diseño del alert
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-background-color: white; -fx-border-color: #E2E8F0; -fx-border-radius: 10;");

        alert.showAndWait();
    }

    // ================================
    // MÉTODOS PÚBLICOS PARA COMUNICACIÓN
    // ================================

    /**
     * Establece el nombre del paciente logueado
     */
    public void setPacienteNombre(String nombre) {
        this.nombrePaciente = nombre;
        if (welcomeLabel != null) {
            welcomeLabel.setText("Bienvenido, " + nombre);
        }
    }

    /**
     * Actualiza las estadísticas del dashboard
     */
    public void actualizarEstadisticas(String proximaCita, int totalCitas) {
        if (proximaCitaLabel != null) {
            proximaCitaLabel.setText(proximaCita);
        }
        if (totalCitasLabel != null) {
            totalCitasLabel.setText(String.valueOf(totalCitas));
        }
    }

    /**
     * Agrega una notificación desde el exterior
     */
    public void agregarNotificacionExterna(String mensaje) {
        agregarNotificacion(mensaje);
    }
}
