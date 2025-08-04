import javafx.application.Application;
import javafx.stage.Stage;

public class RegistrationAP extends Application {
    private Stage primaryStage;
    private String currentUsername;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Student Course Registration System");
        showLoginScene();
    }

    public void showLoginScene() {
        LoginScene loginScene = new LoginScene(this);
        primaryStage.setScene(loginScene.getScene());
        primaryStage.show();
    }

    public void showDashboard() {
        DashboardScene dashboardScene = new DashboardScene(this, currentUsername);
        primaryStage.setScene(dashboardScene.getScene());
    }

    public void showProfile() {
        ProfileScene profileScene = new ProfileScene(this, currentUsername);
        primaryStage.setScene(profileScene.getScene());
    }

    public void showRegisterCourse() {
        RegisterCourseScene registerCourseScene = new RegisterCourseScene(this, currentUsername);
        primaryStage.setScene(registerCourseScene.getScene());
    }

    public void showViewCourses() {
        ViewCoursesScene viewCoursesScene = new ViewCoursesScene(this, currentUsername);
        primaryStage.setScene(viewCoursesScene.getScene());
    }

    public void setCurrentUsername(String username) {
        this.currentUsername = username;
    }

    public static void main(String[] args) {
        launch(args);
    }
}