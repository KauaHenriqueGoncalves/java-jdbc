package com.java.java.components;

import com.java.java.model.Movie;
import com.java.java.repositories.MovieDAO;
import com.java.java.utils.ImageUtils;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.List;

/**
 * This class is responsible for displaying a list of movies in a scrollable UI component.
 * It dynamically creates visual cards for each movie using JavaFX components.
 */
public class ViewMovieComponent {

    /**
     * Constructs Default
     */
    public ViewMovieComponent() {}

    /**
     * Displays all movies retrieved from the database in a scrollable pane.
     * Each movie is represented by a styled card containing an image, title,
     * director, genre, and a truncated synopsis.
     *
     * @param scrollPane the {@link ScrollPane} where the movie cards will be displayed
     */
    public void showMovies(ScrollPane  scrollPane) {
        Pane showMovie = new Pane();
        showMovie.setPrefSize(730.0, 576.0);
        showMovie.setStyle(
                "-fx-background-color: #00000077;"
                + "-fx-background-radius: 15px;"
        );

        MovieDAO movieDAO = new MovieDAO();
        List<Movie> movies = movieDAO.selectAll();

        double positionCardY = 14.0;
        double spacing = 163.0;

        for (Movie movie : movies) {
            Pane card = createCardMovie(movie, positionCardY);
            showMovie.getChildren().add(card);
            positionCardY += spacing;
        }

        showMovie.setPrefHeight(positionCardY);

        scrollPane.setContent(showMovie);
    }

    /**
     * Creates a styled card UI component representing a single movie.
     * The card includes the movie's image, title, director, genre, and a shortened synopsis.
     *
     * @param movie the {@link Movie} object containing the movie's data
     * @param positionCardY the vertical Y position to place the card in the parent pane
     * @return a {@link Pane} representing the movie card
     */
    private Pane createCardMovie(Movie movie, double positionCardY) {
        Pane movieCard = new Pane();
        movieCard.setLayoutX(17.0);
        movieCard.setLayoutY(positionCardY);
        movieCard.setPrefSize(696.0, 150.0);
        movieCard.getStyleClass().add("movieCard");
        movieCard.getStylesheets().add(getClass().getResource("/static/css/components/movieCard.css").toExternalForm());

        Image image = ImageUtils.byteArrayToJavaFXImage(movie.getMidia());
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        );


        Pane imageMovie = new Pane();
        imageMovie.setLayoutX(5.0);
        imageMovie.setLayoutY(9.0);
        imageMovie.setPrefSize(130.0, 130.0);
        imageMovie.setBackground(new Background(backgroundImage));

        Label titleLabel = new Label(movie.getTitulo());
        titleLabel.setLayoutX(143.0);
        titleLabel.setLayoutY(14.0);
        titleLabel.setPrefSize(500.0, 19.0);
        titleLabel.setFont(Font.font(24.0));

        Label directorGenreLabel = new Label(movie.getDiretor() + " - " + movie.getGenero());
        directorGenreLabel.setLayoutX(143.0);
        directorGenreLabel.setLayoutY(49.0);

        String synopsis;

        if (movie.getSinopse().length() > 200) {
            synopsis = movie.getSinopse().substring(0, 200).concat("...");
        }
        else {
            synopsis = movie.getSinopse();
        }

        Label descriptionLabel = new Label(synopsis);
        descriptionLabel.setLayoutX(143.0);
        descriptionLabel.setLayoutY(76.0);
        descriptionLabel.setPrefSize(532.0, 63.0);
        descriptionLabel.setWrapText(true);
        descriptionLabel.setTextOverrun(javafx.scene.control.OverrunStyle.CLIP);
        descriptionLabel.setContentDisplay(javafx.scene.control.ContentDisplay.TOP);
        descriptionLabel.setAlignment(javafx.geometry.Pos.TOP_LEFT);

        movieCard.getChildren().addAll(imageMovie, titleLabel, directorGenreLabel, descriptionLabel);

        return movieCard;
    }

}
