package com.java.java.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The {@code ApplicationNavigationController} class handles navigation between
 * different views (Home, User, Movie) in the JavaFX application.
 */
public class ApplicationNavigationController {

    private Parent root;
    private Stage stage;
    private Scene scene;

    /**
     * Default constructor.
     */
    public ApplicationNavigationController() {}

    /**
     * Navigates to the Home view.
     *
     * @param event the action event that triggered the navigation
     * @return the controller associated with the Home view
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    public HomeController navigateToHome(ActionEvent event) throws IOException {
        return (HomeController) this.loadScene(event, "/com/java/java/application/home.fxml");
    }

    /**
     * Navigates to the User view.
     *
     * @param event the action event that triggered the navigation
     * @return the controller associated with the User view
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    public UserController navigateToUser(ActionEvent event) throws IOException {
        return (UserController) this.loadScene(event, "/com/java/java/application/user.fxml");
    }

    /**
     * Navigates to the Movie view.
     *
     * @param event the action event that triggered the navigation
     * @return the controller associated with the Movie view
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    public MovieController navigateToMovie(ActionEvent event) throws IOException {
        return (MovieController) this.loadScene(event, "/com/java/java/application/movie.fxml");
    }

    /**
     * Loads the specified FXML file and sets it as the current scene.
     *
     * @param event the action event that triggered the scene change
     * @param url the path to the FXML file to load
     * @return the controller associated with the loaded FXML
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    private Object loadScene(ActionEvent event, String url) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(url));
        this.root = loader.load();
        this.stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        this.scene = new Scene(this.root);
        this.stage.setScene(this.scene);
        this.stage.show();
        return loader.getController();
    }

}
