import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class ProfileScene {
    private Scene scene;
    private RegistrationAP app;
    private String username;

    public ProfileScene(RegistrationAP app, String username) {
        this.app = app;
        this.username = username;
        Student student = DataManager.loadStudent(username);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField(student.getName());
        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField(student.getEmail());
        Label programLabel = new Label("Program:");
        TextField programField = new TextField(student.getProgram());
        Label semesterLabel = new Label("Semester:");
        TextField semesterField = new TextField(student.getSemester());
        Button saveButton = new Button("Save");

        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);
        grid.add(emailLabel, 0, 1);
        grid.add(emailField, 1, 1);
        grid.add(programLabel, 0, 2);
        grid.add(programField, 1, 2);
        grid.add(semesterLabel, 0, 3);
        grid.add(semesterField, 1, 3);
        grid.add(saveButton, 1, 4);

        saveButton.setOnAction(e -> {
            student.setName(nameField.getText());
            student.setEmail(emailField.getText());
            student.setProgram(programField.getText());
            student.setSemester(semesterField.getText());
            DataManager.saveStudent(student);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Profile updated successfully");
            alert.showAndWait();
        });

        scene = new Scene(grid, 300, 250);
    }

    public Scene getScene() {
        return scene;
    }
}