package com.java.java.components;

import javafx.animation.Interpolator;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 * The {@code PopUp} class provides a static method to display animated
 * notification messages within a JavaFX AnchorPane. These pop-ups are used
 * to inform the user of success or error events.
 */
public class PopUp {

    /**
     * Creates and displays an animated pop-up message on the screen.
     *
     * @param rootPage the root AnchorPane where the pop-up will be attached
     * @param msg the message to be displayed in the pop-up
     * @param status the status of the message; {@code true} for success (green), {@code false} for error (red)
     */
    public static void createPopUp(AnchorPane rootPage, String msg, boolean status) {
        Pane popUpPane = new Pane();
        popUpPane.setLayoutX(667.0);
        popUpPane.setLayoutY(-74.0); //-74
        popUpPane.setPrefSize(219.0, 57.0);
        popUpPane.getStyleClass().add("pop-up");

        popUpPane.getStylesheets().add("/static/css/components/pop-up.css");

        Label label = new Label();
        label.setText(msg);
        label.setLayoutX(8.0);
        label.setLayoutY(-4.0);
        label.setPrefSize(219.0, 67.0); //209.0
        label.getStyleClass().add("text-pop-up");
        label.setTextAlignment(TextAlignment.CENTER);
        label.setTextFill(javafx.scene.paint.Color.WHITE);
        if (status){
            label.setStyle("-fx-text-fill: #295700;");
            popUpPane.setStyle("-fx-background-color: #68D14F;");
        } else {
            label.setStyle("-fx-text-fill: #ffd4d4;");
            popUpPane.setStyle("-fx-background-color: #DF0026;");
        }

        popUpPane.getChildren().add(label);
        rootPage.getChildren().add(popUpPane);

        // Slide-in animation
        TranslateTransition transition = new TranslateTransition(Duration.millis(500), popUpPane);
        transition.setToY(100);
        transition.setInterpolator(Interpolator.EASE_OUT);

        // Pause and slide-out animation
        transition.setOnFinished(event -> {
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(event1 -> {
                TranslateTransition reverse = new TranslateTransition(Duration.millis(500), popUpPane);
                reverse.setToY(0);
                reverse.setInterpolator(Interpolator.EASE_IN);
                reverse.play();
                reverse.setOnFinished(event2 -> {
                    rootPage.getChildren().remove(popUpPane);
                });
            });
            pause.play();
        });

        transition.play();
    }

}
