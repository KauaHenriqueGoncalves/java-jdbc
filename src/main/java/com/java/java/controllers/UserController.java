package com.java.java.controllers;

import com.java.java.components.UserFormComponent;
import com.java.java.model.User;
import com.java.java.repositories.UserDAO;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.util.List;

/**
 * The {@code UserController} class is responsible for handling the logic
 * related to the User view in the application. It manages user form creation,
 * user selection, and navigation between views.
 */
public class UserController {

    private final ApplicationNavigationController navigate = new ApplicationNavigationController();

    private final UserFormComponent userFormComponent = new UserFormComponent();

    private UserDAO userDAO;

    @FXML private AnchorPane rootPage;

    @FXML private Pane menu;

    @FXML private ComboBox<String> comboBox;

    @FXML public Pane paneUserForm;

    /**
     * Navigates to the Home view.
     *
     * @param event the action event that triggered the navigation
     * @throws Exception if the navigation fails
     */
    @FXML
    public void onNavigateToHome(ActionEvent event) throws Exception {
        navigate.navigateToHome(event);
    }

    /**
     * Navigates to the User view.
     *
     * @param event the action event that triggered the navigation
     * @throws Exception if the navigation fails
     */
    @FXML
    public void onNavigateToUser(ActionEvent event) throws Exception {
        navigate.navigateToUser(event);
    }

    /**
     * Navigates to the Movie view.
     *
     * @param event the action event that triggered the navigation
     * @throws Exception if the navigation fails
     */
    @FXML
    public void onNavigateToMovie(ActionEvent event) throws Exception {
        navigate.navigateToMovie(event);
    }

    /**
     * Displays a blank user form in the designated pane.
     */
    @FXML
    public void onCreateFormUser() {
        userFormComponent.buildUserForm(paneUserForm);
    }

    /**
     * Populates the user form with data from the selected user in the ComboBox.
     */
    @FXML
    public void onComboBoxSelectUser() {
        String userString = comboBox.getSelectionModel().getSelectedItem();
        Integer id = Integer.parseInt(userString.split("-")[ 0 ].trim());
        this.userDAO = new UserDAO();
        User user = userDAO.get(id);
        userFormComponent.buildUserForm(paneUserForm, user);
    }

    /**
     * Returns the root page of the User view.
     *
     * @return the {@link AnchorPane} representing the root
     */
    public AnchorPane getRootPage() {
        return rootPage;
    }

    /**
     * Loads all users from the database and adds them to the ComboBox.
     */
    private void loadUsersIntoComboBox() {
        this.userDAO = new UserDAO();
        List<User> users = userDAO.selectAll();
        for (User user : users) {
            comboBox.getItems().add(user.toString());
        }
    }

    /**
     * Configures the slide-in animation for the menu pane.
     */
    private void setupAnimationMenu() {
        menu.toFront();
        menu.setOnMouseEntered(event -> {
            TranslateTransition transition = new TranslateTransition(Duration.millis(250), menu);
            transition.setToX(161);
            transition.setInterpolator(Interpolator.EASE_IN);
            transition.play();
        });
        menu.setOnMouseExited(event -> {
            TranslateTransition transition = new TranslateTransition(Duration.millis(250), menu);
            transition.setToX(0);
            transition.setInterpolator(Interpolator.EASE_IN);
            transition.play();
        });
    }

    /**
     * Initializes the controller and initalize some functions
     */
    @FXML
    public void initialize() {
        this.setupAnimationMenu();
        this.loadUsersIntoComboBox();
    }

}
