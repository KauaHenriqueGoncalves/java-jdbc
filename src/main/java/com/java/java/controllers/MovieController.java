package com.java.java.controllers;

import com.java.java.components.FormMovieComponent;
import com.java.java.model.Movie;
import com.java.java.repositories.MovieDAO;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.List;

/**
 * Controller class for managing movie-related UI actions in the application.
 * Handles navigation, form creation, and interaction with the movie combo box.
 */
public class MovieController {

    private final ApplicationNavigationController navigate = new ApplicationNavigationController();

    private final FormMovieComponent formMovieComponent = new FormMovieComponent();

    @FXML private AnchorPane rootPage;

    @FXML private Pane menu;

    @FXML private Pane paneMovieForm;

    @FXML private ComboBox<String> comboBox;

    /**
     * Navigates to the Home view.
     *
     * @param event the action event triggering the navigation
     * @throws Exception if an error occurs during navigation
     */
    @FXML
    public void onNavigateToHome(ActionEvent event) throws Exception {
        navigate.navigateToHome(event);
    }

    /**
     * Navigates to the User view.
     *
     * @param event the action event triggering the navigation
     * @throws Exception if an error occurs during navigation
     */
    @FXML
    public void onNavigateToUser(ActionEvent event) throws Exception {
        navigate.navigateToUser(event);
    }

    /**
     * Navigates to the Movie view.
     *
     * @param event the action event triggering the navigation
     * @throws Exception if an error occurs during navigation
     */    @FXML
    public void onNavigateToMovie(ActionEvent event) throws Exception {
        navigate.navigateToMovie(event);
    }

    /**
     * Displays an empty form to create a new movie.
     */
    @FXML
    public void createFormMovie() {
        formMovieComponent.buildMovieForm(paneMovieForm);
    }

    /**
     * Loads the selected movie from the combo box into the form for editing.
     */
    @FXML
    public void onComboBoxSelectMovie() {
        String movieString = comboBox.getSelectionModel().getSelectedItem();
        Integer id = Integer.parseInt(movieString.split("-")[ 0 ].trim());
        MovieDAO movieDAO = new MovieDAO();
        Movie movie = movieDAO.get(id);
        formMovieComponent.buildMovieForm(paneMovieForm, movie);
    }

    /**
     * Returns the root page container of the view.
     *
     * @return the AnchorPane representing the root of the page
     */
    public AnchorPane getRootPage() {
        return rootPage;
    }

    /**
     * Loads all movies from the database and adds them to the ComboBox.
     */
    private void loadMoviesIntoComboBox() {
        MovieDAO movieDAO = new MovieDAO();
        List<Movie> movies = movieDAO.selectAll();
        for (Movie movie : movies) {
            comboBox.getItems().add(movie.toString());
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

    @FXML
    public void initialize() {
        this.setupAnimationMenu();
        this.loadMoviesIntoComboBox();
    }

}
