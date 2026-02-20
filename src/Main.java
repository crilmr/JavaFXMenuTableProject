import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    private TableView<Student> table;
    private ObservableList<Student> students;
    private int idCounter = 1;

    @Override
    public void start(Stage stage) {

        BorderPane root = new BorderPane();

        // MENU BAR
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu themeMenu = new Menu("Theme");
        Menu helpMenu = new Menu("Help");

        menuBar.getMenus().addAll(fileMenu, editMenu, themeMenu, helpMenu);
        root.setTop(menuBar);

        // LEFT PANEL
        VBox leftPanel = new VBox();
        leftPanel.setPrefWidth(150);
        leftPanel.setPadding(new Insets(20));
        leftPanel.getStyleClass().add("left-panel");

        Label profileIcon = new Label("👤");
        profileIcon.setStyle("-fx-font-size: 70;");
        leftPanel.getChildren().add(profileIcon);

        root.setLeft(leftPanel);

        // CENTER TABLE
        table = new TableView<>();
        students = FXCollections.observableArrayList();
        table.setItems(students);

        TableColumn<Student, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(data -> data.getValue().idProperty().asObject());

        TableColumn<Student, String> firstCol = new TableColumn<>("First Name");
        firstCol.setCellValueFactory(data -> data.getValue().firstNameProperty());

        TableColumn<Student, String> lastCol = new TableColumn<>("Last Name");
        lastCol.setCellValueFactory(data -> data.getValue().lastNameProperty());

        TableColumn<Student, String> deptCol = new TableColumn<>("Department");
        deptCol.setCellValueFactory(data -> data.getValue().departmentProperty());

        TableColumn<Student, String> majorCol = new TableColumn<>("Major");
        majorCol.setCellValueFactory(data -> data.getValue().majorProperty());

        TableColumn<Student, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(data -> data.getValue().emailProperty());

        table.getColumns().addAll(idCol, firstCol, lastCol, deptCol, majorCol, emailCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("No content in table"));

        root.setCenter(table);

        // RIGHT PANEL
        VBox rightPanel = new VBox(10);
        rightPanel.setPrefWidth(250);
        rightPanel.setPadding(new Insets(15));
        rightPanel.getStyleClass().add("right-panel");

        TextField firstName = new TextField();
        firstName.setPromptText("First Name");

        TextField lastName = new TextField();
        lastName.setPromptText("Last Name");

        TextField department = new TextField();
        department.setPromptText("Department");

        TextField major = new TextField();
        major.setPromptText("Major");

        TextField email = new TextField();
        email.setPromptText("Email");

        TextField imageUrl = new TextField();
        imageUrl.setPromptText("Image URL");

        Button clearBtn = new Button("Clear");
        Button addBtn = new Button("Add");
        Button deleteBtn = new Button("Delete");
        Button editBtn = new Button("Edit");

        clearBtn.setMaxWidth(Double.MAX_VALUE);
        addBtn.setMaxWidth(Double.MAX_VALUE);
        deleteBtn.setMaxWidth(Double.MAX_VALUE);
        editBtn.setMaxWidth(Double.MAX_VALUE);

        rightPanel.getChildren().addAll(
                firstName, lastName, department,
                major, email, imageUrl,
                clearBtn, addBtn, deleteBtn, editBtn
        );

        root.setRight(rightPanel);

        // BUTTON FUNCTIONALITY

        addBtn.setOnAction(e -> {
            Student student = new Student(
                    idCounter++,
                    firstName.getText(),
                    lastName.getText(),
                    department.getText(),
                    major.getText(),
                    email.getText()
            );
            students.add(student);
            clearFields(firstName, lastName, department, major, email, imageUrl);
        });

        deleteBtn.setOnAction(e -> {
            Student selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                students.remove(selected);
            }
        });

        editBtn.setOnAction(e -> {
            Student selected = table.getSelectionModel().getSelectedItem();
            if (selected != null) {
                selected.setFirstName(firstName.getText());
                selected.setLastName(lastName.getText());
                selected.setDepartment(department.getText());
                selected.setMajor(major.getText());
                selected.setEmail(email.getText());
                table.refresh();
            }
        });

        clearBtn.setOnAction(e ->
                clearFields(firstName, lastName, department, major, email, imageUrl)
        );

        // SCENE
        Scene scene = new Scene(root, 1100, 650);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        stage.setTitle("FSC CSC325 — Full Stack Project");
        stage.setScene(scene);
        stage.show();
    }

    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}

