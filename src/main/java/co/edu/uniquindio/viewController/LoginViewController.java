package co.edu.uniquindio.viewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginViewController {
    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private void handleLogin(ActionEvent event) {
        String usuario = txtUsuario.getText().trim();
        String password = txtPassword.getText().trim();

        // Validaciones básicas
        if (usuario.isEmpty() || password.isEmpty()) {
            mostrarAlerta("Error", "Por favor complete todos los campos", Alert.AlertType.WARNING);
            return;
        }

        // Aquí puedes agregar tu lógica de autenticación
        // Por ahora, validación simple para pruebas
        if (usuario.equals("Juan") && password.equals("1234")) {
            mostrarAlerta("Éxito", "Inicio de sesión exitoso", Alert.AlertType.INFORMATION);

            // Aquí cargarías la siguiente ventana
            try {
                cargarVentanaPrincipal();
            } catch (IOException e) {
                mostrarAlerta("Error", "No se pudo cargar la ventana principal", Alert.AlertType.ERROR);
            }
        } else {
            mostrarAlerta("Error", "Usuario o contraseña incorrectos", Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void cargarVentanaPrincipal() throws IOException {
        System.out.println("¡Login exitoso! Cargando ventana principal...");

        // Importar la clase necesaria
        co.edu.uniquindio.MedicalSystemApp.abrirMenuPrincipal("Juan", "12345", "juan@email.com", "300123456");

        System.out.println("✅ Ventana principal cargada correctamente");
    }
}
