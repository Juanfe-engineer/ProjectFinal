package co.edu.uniquindio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MedicalSystemApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            // Cargar el archivo FXML con la ruta correcta
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/co.edu.uniquindio.javafx/login-view.fxml")
            );

            Scene scene = new Scene(loader.load());

            // Cargar CSS si existe
            try {
                String cssPath = getClass().getResource("styles/styles.css").toExternalForm();
                scene.getStylesheets().add(cssPath);
            } catch (Exception e) {
                System.out.println("Archivo CSS no encontrado, continuando sin estilos externos...");
            }

            // Configurar la ventana
            stage.setScene(scene);
            stage.setTitle("Sistema Hospitalario UQ - Login");
            stage.setResizable(false);

            // Cargar icono si existe
            try {
                Image icon = new Image(getClass().getResourceAsStream("/co.edu.uniquindio.javafx/assets/hospital-icon.png"));
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

    public static void main(String[] args) {
        launch();
    }
}
