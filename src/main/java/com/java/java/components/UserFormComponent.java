package com.java.java.components;

import com.java.java.controllers.ApplicationNavigationController;
import com.java.java.controllers.UserController;
import com.java.java.model.User;
import com.java.java.model.enums.JobPosition;
import com.java.java.repositories.UserDAO;
import com.java.java.service.UserService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * The {@code UserFormComponent} class is responsible for dynamically building
 * user forms in the interface. It supports both creation and update modes
 * for user entities, handling interaction logic and form submission.
 */
public class UserFormComponent {

    private AnchorPane rootNode;
    private Pane paneUserForm;
    private Integer id;

    private TextField dynamicInName;
    private TextField dynamicInCPF;
    private DatePicker dynamicInDateOfBirth;
    private ChoiceBox<String> dynamicInJobPosition;

    /**
     * Builds and displays a form for creating a new user.
     *
     * @param paneUserForm the pane where the form will be rendered
     */
    public void buildUserForm(Pane paneUserForm)  {
        paneUserForm.getChildren().clear();

        this.rootNode = (AnchorPane) paneUserForm.getScene().getRoot();
        this.paneUserForm = paneUserForm;

        paneUserForm.setStyle(
                "-fx-background-color: #00000088;"
                        + "-fx-background-radius: 6px;"
        );

        dynamicInCPF = new TextField();
        dynamicInCPF.setPromptText("Insert your CPF:");
        dynamicInCPF.setLayoutX(10);
        dynamicInCPF.setLayoutY(26);
        dynamicInCPF.getStyleClass().add("textField0");
        dynamicInCPF.getStylesheets().add("/static/css/components/textField0.css");

        dynamicInName = new TextField();
        dynamicInName.setPromptText("Insert User Name:");
        dynamicInName.setLayoutX(10);
        dynamicInName.setLayoutY(83);
        dynamicInName.getStyleClass().add("textField0");
        dynamicInName.getStylesheets().add("/static/css/components/textField0.css");

        dynamicInDateOfBirth = new DatePicker();
        dynamicInDateOfBirth.setPromptText("Your Date of Birth:");
        dynamicInDateOfBirth.setLayoutX(10);
        dynamicInDateOfBirth.setLayoutY(142);
        dynamicInDateOfBirth.getStyleClass().add("datePicker0");
        dynamicInDateOfBirth.getStylesheets().add("/static/css/components/datePicker0.css");

        dynamicInJobPosition = new ChoiceBox<>();
        dynamicInJobPosition.setLayoutX(10);
        dynamicInJobPosition.setLayoutY(204);
        dynamicInJobPosition.getStyleClass().add("choiceBox0");
        dynamicInJobPosition.getItems().addAll("Admin", "Comum");
        dynamicInJobPosition.getSelectionModel().selectFirst();
        dynamicInJobPosition.getStylesheets().add("/static/css/components/choiceBox0.css");

        Button btnFinish = new Button("Finish Create");
        btnFinish.setLayoutX(470);
        btnFinish.setLayoutY(330);
        btnFinish.setOnAction(event -> {
            try {
                this.onCreateUser(event);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        btnFinish.getStyleClass().add("btn0");
        btnFinish.getStylesheets().add("/static/css/components/btn0.css");

        paneUserForm.getChildren().addAll(dynamicInCPF, dynamicInName, dynamicInDateOfBirth, dynamicInJobPosition, btnFinish);
    }

    /**
     * Builds and displays a form for updating an existing user.
     *
     * @param paneUserForm the pane where the form will be rendered
     * @param user the user to be edited
     */
    public void buildUserForm(Pane paneUserForm, User user) {
        paneUserForm.getChildren().clear();

        this.rootNode = (AnchorPane) paneUserForm.getScene().getRoot();
        this.paneUserForm = paneUserForm;

        paneUserForm.setStyle(
                "-fx-background-color: #00000088;"
                        + "-fx-background-radius: 6px;"
        );

        id = user.getId();

        dynamicInCPF = new TextField();
        dynamicInCPF.setPromptText("Insert your CPF:");
        dynamicInCPF.setText(user.getCpf());
        dynamicInCPF.setEditable(false);
        dynamicInCPF.setLayoutX(10);
        dynamicInCPF.setLayoutY(26);
        dynamicInCPF.getStyleClass().add("textField0");
        dynamicInCPF.getStylesheets().add("/static/css/components/textField0.css");
        dynamicInCPF.setStyle(
                "-fx-background-color: #aaa;"
                    + "-fx-text-fill: #555;"
                        + "-fx-cursor: none;"
        );

        dynamicInName = new TextField();
        dynamicInName.setPromptText("Insert User Name:");
        dynamicInName.setText(user.getName());
        dynamicInName.setLayoutX(10);
        dynamicInName.setLayoutY(83);
        dynamicInName.getStyleClass().add("textField0");
        dynamicInName.getStylesheets().add("/static/css/components/textField0.css");

        dynamicInDateOfBirth = new DatePicker();
        dynamicInDateOfBirth.setPromptText("Your Date of Birth:");
        String[] dateUser = user.getDateOfBirth().toString().split("-");
        LocalDate userDateOfBirth = LocalDate.parse(dateUser[0] + "-" + dateUser[1] + "-" + dateUser[2], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        dynamicInDateOfBirth.setValue(userDateOfBirth);
        dynamicInDateOfBirth.setLayoutX(10);
        dynamicInDateOfBirth.setLayoutY(142);
        dynamicInDateOfBirth.getStyleClass().add("datePicker0");
        dynamicInDateOfBirth.getStylesheets().add("/static/css/components/datePicker0.css");

        dynamicInJobPosition = new ChoiceBox<>();
        dynamicInJobPosition.setLayoutX(10);
        dynamicInJobPosition.setLayoutY(204);
        dynamicInJobPosition.getStyleClass().add("choiceBox0");
        dynamicInJobPosition.getItems().addAll("Admin", "Comum");
        if (user.getJobPosition().toString().equals("admin")) {
            dynamicInJobPosition.getSelectionModel().selectFirst();
        } else {
            dynamicInJobPosition.getSelectionModel().selectLast();
        }
        dynamicInJobPosition.getStylesheets().add("/static/css/components/choiceBox0.css");

        Button btnUpdate = new Button("Update");
        btnUpdate.setLayoutX(470);
        btnUpdate.setLayoutY(330);
        btnUpdate.setOnAction(event -> {
            try {
                this.onUpdateUser(event);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        btnUpdate.getStyleClass().add("btn2");
        btnUpdate.getStylesheets().add("/static/css/components/btn2.css");

        Button btnDelete = new Button("Delete");
        btnDelete.setLayoutX(310);
        btnDelete.setLayoutY(330);
        btnDelete.setOnAction(event -> {
            try {
                this.onDeleteUser(event);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        btnDelete.getStyleClass().add("btn0");
        btnDelete.getStylesheets().add("/static/css/components/btn0.css");

        paneUserForm.getChildren().addAll(dynamicInCPF, dynamicInName, dynamicInDateOfBirth, dynamicInJobPosition, btnUpdate, btnDelete);
    }

    /**
     * The action for creating a new user.
     */
    private void onCreateUser(ActionEvent event) throws Exception {
        String cpf = dynamicInCPF.getText();
        String name = dynamicInName.getText();
        LocalDate localDate = dynamicInDateOfBirth.getValue();
        Date dob = java.sql.Date.valueOf(localDate);

        JobPosition jobPosition = null;
        
        if (dynamicInJobPosition.getValue().equals("Admin")) {
            jobPosition = JobPosition.admin;
        }
        else {
            jobPosition = JobPosition.comum;
        }
        
        User newUser = new User(cpf, name, dob, jobPosition);

        UserService userService = new UserService(this.rootNode);
        boolean status = userService.createUser(newUser);

        if (status) this.refreshPage(event, "User created successfully!", true);
    }

    /**
     * The action for updating an existing user.
     */
    private void onUpdateUser(ActionEvent event) throws Exception {
        Integer id = this.id;
        String cpf = dynamicInCPF.getText();
        String name = dynamicInName.getText();
        LocalDate localDate = dynamicInDateOfBirth.getValue();
        Date dob = java.sql.Date.valueOf(localDate);

        JobPosition jobPosition = null;

        if (dynamicInJobPosition.getValue().equals("Admin")) {
            jobPosition = JobPosition.admin;
        }
        else {
            jobPosition = JobPosition.comum;
        }

        User newUser = new User(id, cpf, name, dob, jobPosition);
        UserService userService = new UserService(this.rootNode);
        boolean status = userService.updateUser(newUser);

        if (status) this.refreshPage(event, "User updated successfully!", true);
    }

    /**
     * The action for deleting a user.
     */
    private void onDeleteUser(ActionEvent event) throws Exception {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.get(this.id);
        UserService userService = new UserService(this.rootNode);
        boolean status = userService.deleteUser(user);

        if (status) this.refreshPage(event, "User deleted successfully!", true);
    }

    /**
     * Refreshes the user page after a CRUD action and shows a status pop-up.
     */
    private void refreshPage(ActionEvent event, String msg, boolean status) throws IOException {
        ApplicationNavigationController navigate = new ApplicationNavigationController();
        UserController userController = navigate.navigateToUser(event);
        PopUp.createPopUp(userController.getRootPage(), msg, status);
    }

}
