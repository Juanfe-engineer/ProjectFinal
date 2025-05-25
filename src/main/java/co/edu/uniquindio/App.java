package co.edu.uniquindio;

import co.edu.uniquindio.viewController.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Sistema de Gestión HospitalUQ");

        mostrarLogin(); // Primer vista que se muestra
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void mostrarLogin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/co/edu/uniquindio/javafx/login-view.fxml"));
            AnchorPane pane = loader.load();

            LoginViewController controller = loader.getController();
            controller.setApp(this);

            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarMenuPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/co/edu/uniquindio/javafx/principalMenu-view.fxml"));
            AnchorPane pane = loader.load();

            // Si tuvieras un controlador para el menú, agrégalo aquí:
            // PrincipalMenuViewController controller = loader.getController();
            // controller.setApp(this);

            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVistaPacientes() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/co/edu/uniquindio/javafx/Paciente-view.fxml"));
            AnchorPane pane = loader.load();

            PacienteViewController controller = loader.getController();
            controller.setApp(this);

            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVistaMedicos() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/co/edu/uniquindio/javafx/Medico-view.fxml"));
            AnchorPane pane = loader.load();

            MedicoViewController controller = loader.getController();
            controller.setApp(this);

            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVistaCitas() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/co/edu/uniquindio/javafx/Cita-view.fxml"));
            AnchorPane pane = loader.load();

            CitaViewController controller = loader.getController();
            controller.setApp(this);

            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVistaHistorial() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/co/edu/uniquindio/javafx/Historial-view.fxml"));
            AnchorPane pane = loader.load();

            HistorialViewController controller = loader.getController();
            controller.setApp(this);

            Scene scene = new Scene(pane);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
