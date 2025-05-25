package co.edu.uniquindio.viewController;

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
        if (usuario.equals("admin") && password.equals("123")) {
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
        // Por ahora solo mostrar mensaje de éxito
        // Más adelante puedes cargar otra ventana aquí
        System.out.println("¡Login exitoso! Aquí cargarías la ventana principal.");

        // Opcional: cerrar la ventana actual
        // Stage currentStage = (Stage) btnLogin.getScene().getWindow();
        // currentStage.close();
    }
}
