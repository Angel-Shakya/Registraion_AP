import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;

public class RegisterCourseScene {
    private Scene scene;
    private RegistrationAP app;
    private String username;

    public RegisterCourseScene(RegistrationAP app, String username) {
        this.app = app;
        this.username = username;

        List<String> availableCourses = DataManager.loadAvailableCourses();
        ListView<String> courseListView = new ListView<>();
        courseListView.getItems().addAll(availableCourses);
        courseListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        Button registerButton = new Button("Register");
        registerButton.setOnAction(e -> {
            ObservableList<String> selectedCourses = courseListView.getSelectionModel().getSelectedItems();
            Student student = DataManager.loadStudent(username);
            for (String course : selectedCourses) {
                if (!student.getRegisteredCourses().contains(course)) {
                    student.getRegisteredCourses().add(course);
                }
            }
            DataManager.saveStudent(student);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Courses registered successfully");
            alert.showAndWait();
        });

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(new Label("Select courses to register:"), courseListView, registerButton);

        scene = new Scene(vbox, 300, 400);
    }

    public Scene getScene() {
        return scene;
    }
}