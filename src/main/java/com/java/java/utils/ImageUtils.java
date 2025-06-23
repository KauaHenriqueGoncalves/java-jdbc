package com.java.java.utils;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Utility class for converting images between JavaFX and byte array formats.
 */
public class ImageUtils {

    /**
     * Converts a byte array into a JavaFX Image.
     *
     * @param imageData the byte array representing the image
     * @return the JavaFX Image
     */
    public static Image byteArrayToJavaFXImage(byte[] imageData) {
        return new Image(new ByteArrayInputStream(imageData));
    }

    /**
     * Converts a JavaFX Image into a byte array in PNG format.
     *
     * @param image the JavaFX Image to convert
     * @return the byte array representing the image
     * @throws IOException if an error occurs during writing
     */
    public static byte[] javaFXImageToByteArray(Image image) throws IOException {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

}
