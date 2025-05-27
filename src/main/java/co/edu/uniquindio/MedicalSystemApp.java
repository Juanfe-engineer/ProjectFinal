package co.edu.uniquindio;

import co.edu.uniquindio.viewController.PrincipalViewController;
import co.edu.uniquindio.model.Paciente;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import static jdk.xml.internal.SecuritySupport.getClassLoader;

/**
 * Clase principal de la aplicación del Sistema Hospitalario
 * Maneja la navegación entre ventanas y el estado global de la aplicación
 */
public class MedicalSystemApp extends Application {

    // Variable estática para manejar la ventana principal
    private static Stage primaryStage;

    // Variable estática para manejar el paciente actual
    private static Paciente pacienteActual;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;

        try {
            InputStream fxmlStream = getClass().getClassLoader().getResourceAsStream("login-view.fxml");

            if (fxmlStream == null) {
                throw new RuntimeException("No se encontró el archivo FXML");
            }

            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(fxmlStream);
            Scene scene = new Scene(root);

            // Cargar CSS si existe
            try {
                String cssPath = getClass().getResource("/styles/styles.css").toExternalForm();
                scene.getStylesheets().add(cssPath);
                System.out.println("✅ Estilos CSS cargados correctamente");
            } catch (Exception e) {
                System.out.println("⚠️ Archivo CSS no encontrado, continuando sin estilos externos...");
            }

            // Configurar la ventana
            stage.setScene(scene);
            stage.setTitle("Sistema Hospitalario UQ - Login");
            stage.setResizable(false);
            stage.setWidth(800);
            stage.setHeight(600);
            stage.centerOnScreen();

            // Cargar icono si existe
            try {
                Image icon = new Image(getClass().getResourceAsStream("/assets/hospital-icon.png"));
                stage.getIcons().add(icon);
                System.out.println("✅ Icono de la aplicación cargado");
            } catch (Exception e) {
                System.out.println("⚠️ Icono no encontrado, continuando sin icono...");
            }

            // Configurar evento de cierre
            stage.setOnCloseRequest(event -> {
                System.out.println("✅ Aplicación cerrada por el usuario");
                System.exit(0);
            });

            stage.show();
            System.out.println("✅ Aplicación iniciada correctamente!");

        } catch (IOException e) {
            System.err.println("❌ Error al cargar la interfaz: " + e.getMessage());
            mostrarErrorCritico("Error de Inicio", "No se pudo cargar la interfaz de login.", e);
            e.printStackTrace();
        }
    }

    /**
     * Método estático para cambiar a la vista principal después del login
     * @param paciente Paciente que se logueó
     */
    public static void abrirMenuPrincipal(Paciente paciente) {
        try {
            if (paciente == null) {
                throw new IllegalArgumentException("El paciente no puede ser null");
            }

            // Guardar referencia del paciente
            pacienteActual = paciente;

            // Cargar la vista principal
            FXMLLoader loader;
            try {
                // Verificar que el archivo existe
                URL fxmlLocation = MedicalSystemApp.class.getResource("/principal-view.fxml");

                if (fxmlLocation == null) {
                    System.err.println("❌ Archivo no encontrado. Probando rutas alternativas...");

                    // Probar rutas alternativas
                    String[] rutasAlternativas = {
                            "/principal-view.fxml",
                            "/views/principal-view.fxml",
                            "/co/edu/uniquindio/views/principal-view.fxml",
                            "principal-view.fxml",
                            "src/main/resources/co.edu.uniquindio.javafx/views/principal-view.fxml",
                            "co/edu/uniquindio/views/principal-view.fxml",
                            "co/edu/uniquindio/javafx/views/principal-view.fxml",
                            "/fxml/principal-view.fxml",
                            "principal-view.fxml",
                            "src/main/resources/principal-view.fxml"
                    };

                    for (String ruta : rutasAlternativas) {
                        fxmlLocation = MedicalSystemApp.class.getResource(ruta);
                        if (fxmlLocation != null) {
                            System.out.println("✅ Archivo encontrado en: " + ruta);
                            break;
                        }
                    }

                    if (fxmlLocation == null) {
                        throw new IOException("No se pudo encontrar principal-view.fxml en ninguna ubicación");
                    }
                }

                System.out.println("🔍 Cargando FXML desde: " + fxmlLocation);

                // Crear FXMLLoader
                loader = new FXMLLoader(fxmlLocation);


                System.out.println("✅ FXML cargado correctamente");

            } catch (IOException e) {
                System.err.println("❌ Error detallado al cargar FXML:");
                System.err.println("Mensaje: " + e.getMessage());
                System.err.println("Causa: " + (e.getCause() != null ? e.getCause().getMessage() : "Desconocida"));
                e.printStackTrace();

                // Mostrar error específico al usuario
                mostrarErrorCritico("Error de Carga",
                        "No se pudo cargar la interfaz principal.\n" +
                                "Detalles: " + e.getMessage(), e);
                return; // Salir del método si hay error
            }

            Parent root = loader.load();
            Scene principalScene = new Scene(root);

            // Cargar CSS para la vista principal si existe
            try {
                String cssPath = MedicalSystemApp.class.getResource("/styles/styles.css").toExternalForm();
                principalScene.getStylesheets().add(cssPath);
            } catch (Exception e) {
                System.out.println("⚠️ CSS no encontrado para vista principal");
            }

            // Obtener el controlador y establecer el paciente
            PrincipalViewController controller = loader.getController();
            if (controller != null) {
                controller.setPacienteActual(pacienteActual);
                System.out.println("✅ Paciente establecido en el controlador: " + paciente.getNombre());
            } else {
                System.err.println("❌ No se pudo obtener el controlador de la vista principal");
            }

            // Cambiar la escena
            primaryStage.setScene(principalScene);
            primaryStage.setTitle("Sistema Hospitalario UQ - Panel Principal - " + paciente.getNombre());
            primaryStage.setMaximized(true); // Maximizar para mejor experiencia
            primaryStage.setResizable(true);
            primaryStage.centerOnScreen();

            System.out.println("✅ Menu principal cargado para: " + paciente.getNombre());

        } catch (IOException e) {
            System.err.println("❌ Error al cargar el menu principal: " + e.getMessage());
            mostrarErrorCritico("Error de Navegación", "No se pudo cargar el menu principal.", e);
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ Error inesperado al abrir menu principal: " + e.getMessage());
            mostrarErrorCritico("Error Inesperado", "Error al procesar el acceso al sistema.", e);
            e.printStackTrace();
        }
    }

    /**
     * Método alternativo para abrir menu principal con datos del paciente
     * @param nombrePaciente Nombre del paciente
     * @param idPaciente ID del paciente
     * @param correo Correo del paciente
     * @param telefono Teléfono del paciente
     */
    public static void abrirMenuPrincipal(String nombrePaciente, String idPaciente, String correo, String telefono) {
        try {
            // Validar datos de entrada
            if (nombrePaciente == null || nombrePaciente.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre del paciente es requerido");
            }
            if (idPaciente == null || idPaciente.trim().isEmpty()) {
                throw new IllegalArgumentException("El ID del paciente es requerido");
            }

            // Crear paciente con los datos proporcionados
            Paciente paciente = new Paciente(nombrePaciente, idPaciente,
                    correo != null ? correo : "",
                    telefono != null ? telefono : "");

            System.out.println("✅ Paciente creado: " + nombrePaciente + " (ID: " + idPaciente + ")");
            abrirMenuPrincipal(paciente);

        } catch (Exception e) {
            System.err.println("❌ Error al crear paciente: " + e.getMessage());
            mostrarErrorCritico("Error de Datos", "No se pudo crear el paciente con los datos proporcionados.", e);
        }
    }

    /**
     * Método estático para volver al login
     */
    public static void volverAlLogin() {
        try {
            // Limpiar paciente actual
            pacienteActual = null;
            System.out.println("✅ Sesión de paciente limpiada");

            // CAMBIO AQUÍ: Usar getClass().getClassLoader() en lugar de la clase interna
            InputStream fxmlStream = MedicalSystemApp.class.getClassLoader().getResourceAsStream("login-view.fxml");

            if (fxmlStream == null) {
                // Probar rutas alternativas
                String[] rutasAlternativas = {
                        "login-view.fxml",
                        "/login-view.fxml",
                        "views/login-view.fxml",
                        "/views/login-view.fxml"
                };

                for (String ruta : rutasAlternativas) {
                    fxmlStream = MedicalSystemApp.class.getClassLoader().getResourceAsStream(ruta);
                    if (fxmlStream != null) {
                        System.out.println("✅ Archivo login encontrado en: " + ruta);
                        break;
                    }
                }

                if (fxmlStream == null) {
                    throw new RuntimeException("No se encontró el archivo login-view.fxml en ninguna ubicación");
                }
            }

            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(fxmlStream);
            Scene loginScene = new Scene(root);

            // Cargar CSS para login si existe
            try {
                String cssPath = MedicalSystemApp.class.getResource("/styles/styles.css").toExternalForm();
                loginScene.getStylesheets().add(cssPath);
            } catch (Exception e) {
                System.out.println("⚠️ CSS no encontrado para vista de login");
            }

            primaryStage.setScene(loginScene);
            primaryStage.setTitle("Sistema Hospitalario UQ - Login");
            primaryStage.setMaximized(false);
            primaryStage.setResizable(false);
            primaryStage.setWidth(800);
            primaryStage.setHeight(600);
            primaryStage.centerOnScreen();

            System.out.println("✅ Vuelto al login correctamente");

        } catch (IOException e) {
            System.err.println("❌ Error al volver al login: " + e.getMessage());
            mostrarErrorCritico("Error de Navegación", "No se pudo volver a la pantalla de login.", e);
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ Error inesperado al volver al login: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Método para mostrar errores críticos al usuario
     */
    private static void mostrarErrorCritico(String titulo, String mensaje, Exception e) {
        try {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Crítico - " + titulo);
            alert.setHeaderText(mensaje);
            alert.setContentText("Detalles del error: " + e.getMessage() +
                    "\n\nLa aplicación podría no funcionar correctamente.");
            alert.showAndWait();
        } catch (Exception alertException) {
            System.err.println("❌ No se pudo mostrar el error al usuario: " + alertException.getMessage());
        }
    }

    /**
     * Método para mostrar información al usuario
     */
    public static void mostrarInformacion(String titulo, String mensaje) {
        try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(titulo);
            alert.setHeaderText(null);
            alert.setContentText(mensaje);
            alert.showAndWait();
        } catch (Exception e) {
            System.err.println("❌ Error al mostrar información: " + e.getMessage());
        }
    }

    /**
     * Método para mostrar advertencias al usuario
     */
    public static void mostrarAdvertencia(String titulo, String mensaje) {
        try {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(titulo);
            alert.setHeaderText(null);
            alert.setContentText(mensaje);
            alert.showAndWait();
        } catch (Exception e) {
            System.err.println("❌ Error al mostrar advertencia: " + e.getMessage());
        }
    }

    /**
     * Obtiene la ventana principal
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Obtiene el paciente actual
     */
    public static Paciente getPacienteActual() {
        return pacienteActual;
    }

    /**
     * Establece el paciente actual
     */
    public static void setPacienteActual(Paciente paciente) {
        pacienteActual = paciente;
        if (paciente != null) {
            System.out.println("✅ Paciente actual establecido: " + paciente.getNombre());
        } else {
            System.out.println("✅ Paciente actual limpiado");
        }
    }

    /**
     * Verifica si hay un paciente logueado
     */
    public static boolean hayPacienteLogueado() {
        return pacienteActual != null;
    }

    /**
     * Obtiene información básica de la aplicación
     */
    public static String obtenerInfoAplicacion() {
        return "Sistema Hospitalario Universidad del Quindío v1.0\n" +
                "Desarrollado para la gestión de citas médicas\n" +
                "JavaFX Application";
    }

    /**
     * Método principal de la aplicación
     */
    public static void main(String[] args) {
        try {
            System.out.println("🏥 Iniciando Sistema Hospitalario UQ...");
            System.out.println("📋 Versión: 1.0");
            System.out.println("🔧 JavaFX Application");
            System.out.println("=" .repeat(50));

            launch(args);

        } catch (Exception e) {
            System.err.println("❌ Error crítico al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}