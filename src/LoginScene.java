import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.Map;

public class LoginScene {
    private Scene scene;
    private RegistrationAP app;

    public LoginScene(RegistrationAP app) {
        this.app = app;
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");

        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            Map<String, String> credentials = DataManager.loadCredentials();
            if (credentials.containsKey(username) && credentials.get(username).equals(password)) {
                app.setCurrentUsername(username);
                app.showDashboard();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid username or password");
                alert.showAndWait();
            }
        });

        scene = new Scene(grid, 300, 200);
    }

    public Scene getScene() {
        return scene;
    }
}