import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class ViewCoursesScene {
    private Scene scene;
    private RegistrationAP app;
    private String username;

    public ViewCoursesScene(RegistrationAP app, String username) {
        this.app = app;
        this.username = username;
        Student student = DataManager.loadStudent(username);

        TableView<String> tableView = new TableView<>();
        TableColumn<String, String> courseColumn = new TableColumn<>("Course");
        courseColumn.setCellValueFactory(param -> new javafx.beans.property.SimpleStringProperty(param.getValue()));

        TableColumn<String, Void> deleteColumn = new TableColumn<>("Action");
        deleteColumn.setCellFactory(param -> new TableCell<String, Void>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(e -> {
                    String course = getTableView().getItems().get(getIndex());
                    student.getRegisteredCourses().remove(course);
                    DataManager.saveStudent(student);
                    getTableView().getItems().remove(course);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : deleteButton);
            }
        });

        tableView.getColumns().addAll(courseColumn, deleteColumn);
        tableView.getItems().addAll(student.getRegisteredCourses());

        scene = new Scene(tableView, 400, 300);
    }

    public Scene getScene() {
        return scene;
    }
}