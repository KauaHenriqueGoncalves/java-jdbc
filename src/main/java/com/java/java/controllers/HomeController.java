package com.java.java.controllers;

import com.java.java.components.ViewMovieComponent;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 * Controller class for the Home view in a JavaFX application.
 * Handles navigation between views, initializes the movie listing.
 */
public class HomeController {

    private final ApplicationNavigationController navigate = new ApplicationNavigationController();

    private final ViewMovieComponent viewMovieComponent = new ViewMovieComponent();

    @FXML private AnchorPane rootPage;

    @FXML private Pane menu;

    @FXML private ScrollPane srollMovie;

    /**
     * Handles navigation to the Home view.
     *
     * @param event the action event triggered by the UI interaction
     * @throws Exception if an error occurs during navigation
     */
    @FXML
    public void onNavigateToHome(ActionEvent event) throws Exception {
        navigate.navigateToHome(event);
    }

    /**
     * Handles navigation to the User view.
     *
     * @param event the action event triggered by the UI interaction
     * @throws Exception if an error occurs during navigation
     */
    @FXML
    public void onNavigateToUser(ActionEvent event) throws Exception {
        navigate.navigateToUser(event);
    }

    /**
     * Handles navigation to the Movie view.
     *
     * @param event the action event triggered by the UI interaction
     * @throws Exception if an error occurs during navigation
     */
    @FXML
    public void onNavigateToMovie(ActionEvent event) throws Exception {
        navigate.navigateToMovie(event);
    }

    /**
     * Returns the root page of the Home view.
     *
     * @return the {@link AnchorPane} representing the root of this view
     */
    public AnchorPane getRootPage() {
        return rootPage;
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
        viewMovieComponent.showMovies(this.srollMovie);
    }

}