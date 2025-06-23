package com.java.java.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The {@code App} class is the main entry point for the JavaFX application.
 * It loads the initial scene from an FXML file.
 */
public class App extends Application {

    /**
     * The main method to init the JavaFX application.
     *
     * @param args the command-line arguments passed to the application
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * Starts the JavaFX application by initializing the main stage.
     * Loads the "home.fxml" file to set up the initial scene.
     * Set the application icon, title, and display settings.
     *
     * @param primatyStage the primary stage for this application
     * @throws IOException if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage primatyStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        primatyStage.setTitle("JavaProject");
        primatyStage.setResizable(false);
        primatyStage.centerOnScreen();

        primatyStage.setScene(scene);
        primatyStage.show();
    }

}