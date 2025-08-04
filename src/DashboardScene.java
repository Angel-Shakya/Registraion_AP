import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class DashboardScene {
    private Scene scene;
    private RegistrationAP app;
    private String username;

    public DashboardScene(RegistrationAP app, String username) {
        this.app = app;
        this.username = username;
        Student student = DataManager.loadStudent(username);
        String name = student.getName();

        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem exitItem = new MenuItem("Exit");
        fileMenu.getItems().add(exitItem);
        menuBar.getMenus().add(fileMenu);

        exitItem.setOnAction(e -> {
            Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to exit?");
            Optional<ButtonType> result = confirmAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                app.primaryStage.close();
            }
        });

        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        Label welcomeLabel = new Label("Welcome, " + name);
        Button profileButton = new Button("Profile");
        Button registerCourseButton = new Button("Register Course");
        Button viewCoursesButton = new Button("View Registered Courses");
        Button logoutButton = new Button("Logout");

        profileButton.setOnAction(e -> app.showProfile());
        registerCourseButton.setOnAction(e -> app.showRegisterCourse());
        viewCoursesButton.setOnAction(e -> app.showViewCourses());
        logoutButton.setOnAction(e -> app.showLoginScene());

        vbox.getChildren().addAll(welcomeLabel, profileButton, registerCourseButton, viewCoursesButton, logoutButton);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        borderPane.setCenter(vbox);

        scene = new Scene(borderPane, 400, 300);
    }

    public Scene getScene() {
        return scene;
    }
}