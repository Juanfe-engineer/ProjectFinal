package co.edu.uniquindio;

import co.edu.uniquindio.viewController.PrincipalViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MedicalSystemApp extends Application {

    // Variable estática para manejar la ventana principal
    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;

        try {
            // Cargar el archivo FXML del login
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co.edu.uniquindio.javafx/login-view.fxml")
            );

            Scene scene = new Scene(loader.load());

            // Cargar CSS si existe
            try {
                String cssPath = getClass().getResource("/styles/styles.css").toExternalForm();
                scene.getStylesheets().add(cssPath);
            } catch (Exception e) {
                System.out.println("Archivo CSS no encontrado, continuando sin estilos externos...");
            }

            // Configurar la ventana
            stage.setScene(scene);
            stage.setTitle("Sistema Hospitalario UQ - Login");
            stage.setResizable(false);
            stage.centerOnScreen();

            // Cargar icono si existe
            try {
                Image icon = new Image(getClass().getResourceAsStream("/assets/hospital-icon.png"));
                stage.getIcons().add(icon);
            } catch (Exception e) {
                System.out.println("Icono no encontrado, continuando sin icono...");
            }

            stage.show();
            System.out.println("✅ Aplicación iniciada correctamente!");

        } catch (IOException e) {
            System.err.println("❌ Error al cargar la interfaz: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Método estático para cambiar a la vista principal después del login
     * @param nombrePaciente Nombre del paciente que se logueó
     */
    public static void abrirMenuPrincipal(String nombrePaciente) {
        try {
            // Cargar la vista principal
            FXMLLoader loader = new FXMLLoader(
                    MedicalSystemApp.class.getResource("/co.edu.uniquindio.javafx/principal-view.fxml")
            );

            Scene principalScene = new Scene(loader.load());

            // Obtener el controlador y establecer el nombre del paciente
            PrincipalViewController controller = loader.getController();
            controller.setPacienteNombre(nombrePaciente);

            // Cambiar la escena
            primaryStage.setScene(principalScene);
            primaryStage.setTitle("Sistema Hospitalario UQ - Panel Principal");
            primaryStage.setMaximized(true); // Maximizar para mejor experiencia
            primaryStage.centerOnScreen();

            System.out.println("✅ Menu principal cargado para: " + nombrePaciente);

        } catch (IOException e) {
            System.err.println("❌ Error al cargar el menu principal: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Método estático para volver al login
     */
    public static void volverAlLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    MedicalSystemApp.class.getResource("/co.edu.uniquindio.javafx/login-view.fxml")
            );

            Scene loginScene = new Scene(loader.load());

            primaryStage.setScene(loginScene);
            primaryStage.setTitle("Sistema Hospitalario UQ - Login");
            primaryStage.setMaximized(false);
            primaryStage.setResizable(false);
            primaryStage.centerOnScreen();

            System.out.println("✅ Vuelto al login correctamente");

        } catch (IOException e) {
            System.err.println("❌ Error al volver al login: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Obtiene la ventana principal
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}