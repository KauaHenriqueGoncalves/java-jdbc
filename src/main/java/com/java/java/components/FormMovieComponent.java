package com.java.java.components;

import com.java.java.controllers.ApplicationNavigationController;
import com.java.java.controllers.MovieController;
import com.java.java.model.Movie;
import com.java.java.service.MovieService;
import com.java.java.utils.ImageUtils;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * FormMovieComponent is responsible for building and managing the movie form UI
 * in a JavaFX application. It provides functionalities to create, update, and
 * delete movie records, including image handling and form field management.
 */
public class FormMovieComponent {

    private AnchorPane rootNode;
    private Pane paneMovieForm;
    private Integer id;

    private ImageView dynamicImageViewMovie;
    private File fileImage;
    private TextField dynamicInTitle;
    private TextField dynamicInDirector;
    private TextArea dynamicInSynopsis;
    private ChoiceBox<String> dynamicInMovieGenre;

    /**
     * Builds a blank movie form for creating a new movie.
     *
     * @param paneMovieForm the pane where the form will be rendered
     */
    public void buildMovieForm(Pane paneMovieForm) {
        paneMovieForm.getChildren().clear();

        this.rootNode = (AnchorPane) paneMovieForm.getScene().getRoot();
        this.paneMovieForm = paneMovieForm;

        paneMovieForm.setStyle(
                "-fx-background-color: #00000088;"
                        + "-fx-background-radius: 6px;"
        );

        Pane imagePane = new Pane();
        imagePane.setLayoutX(32.0);
        imagePane.setLayoutY(28.0);
        imagePane.setPrefSize(200, 220);

        dynamicImageViewMovie = new ImageView();
        dynamicImageViewMovie.setFitHeight(220);
        dynamicImageViewMovie.setFitWidth(200);
        dynamicImageViewMovie.setLayoutY(-14.0);
        dynamicImageViewMovie.setPickOnBounds(true);
        dynamicImageViewMovie.setPreserveRatio(true);

        Button insertImageBtn = new Button("Inserir Imagem");
        insertImageBtn.setLayoutX(25.0);
        insertImageBtn.setLayoutY(90.0);
        insertImageBtn.setOnAction(e -> {
            this.onSelectImageBtnClick(e, insertImageBtn);
        });
        insertImageBtn.getStyleClass().add("btn2");
        insertImageBtn.getStylesheets().add(getClass().getResource("/static/css/components/btn2.css").toExternalForm());

        imagePane.getChildren().addAll(dynamicImageViewMovie, insertImageBtn);

        dynamicInTitle = new TextField();
        dynamicInTitle.setLayoutX(32.0);
        dynamicInTitle.setLayoutY(273.0);
        dynamicInTitle.setPrefWidth(200);
        dynamicInTitle.setPromptText("Title");
        dynamicInTitle.setId("inTitle");
        dynamicInTitle.getStyleClass().add("textField1");
        dynamicInTitle.getStylesheets().add(getClass().getResource("/static/css/components/textField1.css").toExternalForm());

        dynamicInMovieGenre = new ChoiceBox<>();
        dynamicInMovieGenre.setLayoutX(354.0);
        dynamicInMovieGenre.setLayoutY(97.0);
        dynamicInMovieGenre.setPrefWidth(190);
        dynamicInMovieGenre.getItems().addAll("Action", "Romance", "Science Fiction", "Horror", "Comedy", "Drama", "Adventure", "Animation", "Documentary");
        dynamicInMovieGenre.getSelectionModel().selectFirst();
        dynamicInMovieGenre.setId("inMovieGenre");
        dynamicInMovieGenre.getStyleClass().add("choiceBox0");
        dynamicInMovieGenre.getStylesheets().add(getClass().getResource("/static/css/components/choiceBox0.css").toExternalForm());

        dynamicInDirector = new TextField();
        dynamicInDirector.setLayoutX(354.0);
        dynamicInDirector.setLayoutY(28.0);
        dynamicInDirector.setPromptText("Director");
        dynamicInDirector.setId("inDirector");
        dynamicInDirector.getStyleClass().add("textField1");
        dynamicInDirector.getStylesheets().add(getClass().getResource("/static/css/components/textField1.css").toExternalForm());

        dynamicInSynopsis = new TextArea();
        dynamicInSynopsis.setLayoutX(354.0);
        dynamicInSynopsis.setLayoutY(166.0);
        dynamicInSynopsis.setWrapText(true);
        dynamicInSynopsis.setPrefSize(190, 150);
        dynamicInSynopsis.setPromptText("Synopsis");

        Button btnFinish = new Button("Finish Movie");
        btnFinish.setLayoutX(450);
        btnFinish.setLayoutY(330);
        btnFinish.setOnAction(e -> {
            try {
                this.onCreateMovie(e);
            } catch (IOException err) {
                throw new RuntimeException(err);
            }
        });
        btnFinish.getStyleClass().add("btn0");
        btnFinish.getStylesheets().add("/static/css/components/btn0.css");

        paneMovieForm.getChildren().addAll(imagePane, dynamicInTitle, dynamicInMovieGenre, dynamicInDirector, dynamicInSynopsis, btnFinish);
    }

    /**
     * Builds a movie form pre-filled with an existing movie's data
     * for editing or deletion.
     *
     * @param paneMovieForm the pane where the form will be rendered
     * @param movie the movie whose data will be loaded into the form
     */
    public void buildMovieForm(Pane paneMovieForm, Movie movie) {
        paneMovieForm.getChildren().clear();

        this.rootNode = (AnchorPane) paneMovieForm.getScene().getRoot();
        this.paneMovieForm = paneMovieForm;

        paneMovieForm.setStyle(
                "-fx-background-color: #00000088;"
                        + "-fx-background-radius: 6px;"
        );

        Pane imagePane = new Pane();
        imagePane.setLayoutX(32.0);
        imagePane.setLayoutY(28.0);
        imagePane.setPrefSize(200, 220);

        Image img = ImageUtils.byteArrayToJavaFXImage(movie.getMidia());

        dynamicImageViewMovie = new ImageView(img);
        dynamicImageViewMovie.setFitHeight(220);
        dynamicImageViewMovie.setFitWidth(200);
        dynamicImageViewMovie.setLayoutY(-14.0);
        dynamicImageViewMovie.setPickOnBounds(true);
        dynamicImageViewMovie.setPreserveRatio(false);

        Button insertImageBtn = new Button("Mudar Imagem");
        insertImageBtn.setLayoutX(25.0);
        insertImageBtn.setLayoutY(90.0);
        insertImageBtn.setOnAction(e -> {
            this.onSelectImageBtnClick(e, insertImageBtn);
        });
        insertImageBtn.getStyleClass().add("btn2");
        insertImageBtn.getStylesheets().add(getClass().getResource("/static/css/components/btn2.css").toExternalForm());

        imagePane.getChildren().addAll(dynamicImageViewMovie, insertImageBtn);

        this.id = movie.getId();

        dynamicInTitle = new TextField();
        dynamicInTitle.setText(movie.getTitulo());
        dynamicInTitle.setLayoutX(32.0);
        dynamicInTitle.setLayoutY(273.0);
        dynamicInTitle.setPrefWidth(200);
        dynamicInTitle.setPromptText("Title");
        dynamicInTitle.setId("inTitle");
        dynamicInTitle.getStyleClass().add("textField1");
        dynamicInTitle.getStylesheets().add(getClass().getResource("/static/css/components/textField1.css").toExternalForm());

        dynamicInMovieGenre = new ChoiceBox<>();
        dynamicInMovieGenre.setLayoutX(354.0);
        dynamicInMovieGenre.setLayoutY(97.0);
        dynamicInMovieGenre.setPrefWidth(190);
        dynamicInMovieGenre.getItems().addAll("Action", "Romance", "Science Fiction", "Horror", "Comedy", "Drama", "Adventure", "Animation", "Documentary");
        dynamicInMovieGenre.setValue(movie.getGenero());
        dynamicInMovieGenre.setId("inMovieGenre");
        dynamicInMovieGenre.getStyleClass().add("choiceBox0");
        dynamicInMovieGenre.getStylesheets().add(getClass().getResource("/static/css/components/choiceBox0.css").toExternalForm());

        dynamicInDirector = new TextField();
        dynamicInDirector.setText(movie.getDiretor());
        dynamicInDirector.setLayoutX(354.0);
        dynamicInDirector.setLayoutY(28.0);
        dynamicInDirector.setPromptText("Director");
        dynamicInDirector.setId("inDirector");
        dynamicInDirector.getStyleClass().add("textField1");
        dynamicInDirector.getStylesheets().add(getClass().getResource("/static/css/components/textField1.css").toExternalForm());

        dynamicInSynopsis = new TextArea();
        dynamicInSynopsis.setText(movie.getSinopse());
        dynamicInSynopsis.setLayoutX(354.0);
        dynamicInSynopsis.setLayoutY(166.0);
        dynamicInSynopsis.setWrapText(true);
        dynamicInSynopsis.setPrefSize(190, 150);
        dynamicInSynopsis.setPromptText("Synopsis");

        Button btnUpdate = new Button("Update");
        btnUpdate.setLayoutX(470);
        btnUpdate.setLayoutY(330);
        btnUpdate.setOnAction(event -> {
            try {
                this.onUpdateMovie(event);
            } catch (IOException e) {
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
                this.onDeleteMovie(event);
            }  catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        btnDelete.getStyleClass().add("btn0");
        btnDelete.getStylesheets().add("/static/css/components/btn0.css");

        paneMovieForm.getChildren().addAll(imagePane, dynamicInTitle, dynamicInMovieGenre, dynamicInDirector, dynamicInSynopsis, btnUpdate, btnDelete);
    }

    /**
     * Handles the creation of a new movie based on the form data.
     *
     * @param event the action event triggering the creation
     * @throws IOException if an I/O error occurs while processing the image
     */
    private void onCreateMovie(ActionEvent event) throws IOException {
        if (this.fileImage == null) {
            PopUp.createPopUp(this.rootNode, "Insirá uma imagem!", false);
            return;
        }

        byte[] data_image = Files.readAllBytes(this.fileImage.toPath());
        String title = dynamicInTitle.getText();
        String director = dynamicInDirector.getText();
        String synopsis = dynamicInSynopsis.getText();
        String movieGenre = dynamicInMovieGenre.getValue();

        Movie movie = new Movie(title, movieGenre, director, synopsis, data_image);

        MovieService movieService = new MovieService(rootNode);
        boolean status = movieService.createMovie(movie);

        if (status) this.reflashPage(event, "Movie Created Successfully", true);
    }

    /**
     * Handles the update of an existing movie using the form data.
     *
     * @param event the action event triggering the update
     * @throws IOException if an I/O error occurs while processing the image
     */
    private void onUpdateMovie(ActionEvent event) throws IOException {
        if (this.dynamicImageViewMovie == null) {
            PopUp.createPopUp(this.rootNode, "Insirá uma imagem!", false);
            return;
        }

        byte[] data_image = ImageUtils.javaFXImageToByteArray(dynamicImageViewMovie.getImage());
        String title = dynamicInTitle.getText();
        String director = dynamicInDirector.getText();
        String synopsis = dynamicInSynopsis.getText();
        String movieGenre = dynamicInMovieGenre.getValue();

        Movie movie = new Movie(this.id, title, movieGenre, director, synopsis, data_image);

        MovieService movieService = new MovieService(rootNode);
        boolean status = movieService.updateMovie(movie);

        if (status) this.reflashPage(event, "Movie Update Successfully", true);
    }

    /**
     * Handles the deletion of a movie.
     *
     * @param event the action event triggering the deletion
     * @throws IOException if an I/O error occurs
     */
    private void onDeleteMovie(ActionEvent event) throws IOException {
        MovieService movieService = new MovieService(rootNode);
        boolean status = movieService.deleteMovie(this.id);
        if (status) this.reflashPage(event, "Movie Delete Successfully", true);
    }

    /**
     * Opens a file chooser for selecting a movie image and updates the image view.
     *
     * @param event the action event triggering the selection
     * @param btn the button used to select the image
     */
    private void onSelectImageBtnClick(ActionEvent event, Button btn) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Selecionar Imagem");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imagens", "*.png", "*.jpg", "*.jpeg")
        );

        File fileSelected = fileChooser.showOpenDialog((Stage) ((Node) event.getSource()).getScene().getWindow());

        if (fileSelected != null) {
            this.fileImage = fileSelected;
            Image imagem = new Image(fileSelected.toURI().toString());
            dynamicImageViewMovie.setImage(imagem);
            dynamicImageViewMovie.setPreserveRatio(false);
            dynamicImageViewMovie.setFitWidth(200);
            dynamicImageViewMovie.setFitHeight(220);
            dynamicInTitle.setText(fileSelected.getName());
            btn.setText("Mudar imagem");
        }
    }

    /**
     * Refreshes the current page and displays a status popup.
     *
     * @param event the triggering event
     * @param msg the message to display in the popup
     * @param status the operation status (success or failure)
     * @throws IOException if an error occurs during navigation
     */
    private void reflashPage(ActionEvent event, String msg, boolean status) throws  IOException {
        ApplicationNavigationController navigate = new ApplicationNavigationController();
        MovieController movieController = navigate.navigateToMovie(event);
        PopUp.createPopUp(movieController.getRootPage(), msg, status);
    }

}
