package com.java.java.service;

import com.java.java.components.PopUp;
import com.java.java.model.Movie;
import com.java.java.repositories.MovieDAO;
import javafx.scene.layout.AnchorPane;

/**
 * Service class responsible for handling movie creation, update, and deletion logic.
 * Includes basic validation and integrates with the data access layer and UI feedback.
 */
public class MovieService {

    private AnchorPane rootPage;

    /**
     * Default constructor.
     */
    public MovieService() {}

    /**
     * Constructor with UI root pane.
     *
     * @param rootPage the root UI pane used for displaying popups
     */
    public MovieService(AnchorPane rootPage) {
        this.rootPage = rootPage;
    }

    /**
     * Gets the root page used for UI interactions.
     *
     * @return the root AnchorPane
     */
    public AnchorPane getRootPage() {
        return rootPage;
    }

    /**
     * Sets the root page used for UI interactions.
     *
     * @param rootPage the AnchorPane to set
     */
    public void setRootPage(AnchorPane rootPage) {
        this.rootPage = rootPage;
    }

    /**
     * Validates and creates a new movie.
     * Displays error messages via popup if validation fails.
     *
     * @param movie the Movie object to create
     * @return true if creation was successful, false otherwise
     */
    public boolean createMovie(Movie movie) {

        if (movie.getTitulo().isEmpty() || movie.getDiretor().isEmpty() || movie.getSinopse().isEmpty()) {
            PopUp.createPopUp(this.rootPage, "Preencha todos os campos", false);
            return false;
        }
        else if (movie.getMidia() == null) {
            PopUp.createPopUp(this.rootPage, "Envie uma imagem", false);
            return false;
        }

        MovieDAO movieDAO = new MovieDAO();
        boolean status = movieDAO.insert(movie);
        return status;
    }

    /**
     * Validates and updates an existing movie.
     * Displays error messages via popup if validation fails.
     *
     * @param movie the Movie object to update
     * @return true if update was successful, false otherwise
     */
    public boolean updateMovie(Movie movie) {

        if (movie.getTitulo().isEmpty() || movie.getDiretor().isEmpty() || movie.getSinopse().isEmpty()) {
            PopUp.createPopUp(this.rootPage, "Preencha todos os campos", false);
            return false;
        }
        else if (movie.getMidia() == null) {
            PopUp.createPopUp(this.rootPage, "Envie uma imagem", false);
            return false;
        }

        MovieDAO movieDAO = new MovieDAO();
        boolean status = movieDAO.update(movie);

        return status;
    }

    /**
     * Deletes a movie by its ID.
     *
     * @param id the ID of the movie to delete
     * @return true if deletion was successful, false otherwise
     */
    public boolean deleteMovie(Integer id) {
        MovieDAO movieDAO = new MovieDAO();
        boolean status = movieDAO.delete(id);
        return status;
    }

}
