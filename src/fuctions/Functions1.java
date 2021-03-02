package fuctions;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Functions1 {

    public Image makeBrighterOrDarker(Image image, int a, double b) {
        WritableImage wImage = new WritableImage(
                (int) image.getWidth(),
                (int) image.getHeight());
        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = wImage.getPixelWriter();

        for (int hi = 0; hi < image.getHeight(); hi++) {
            for (int wi = 0; wi < image.getWidth(); wi++) {
                Color color = pixelReader.getColor(wi, hi);

                if (a == 0) {
                    try {
                        color = new Color(color.getBrightness() + 0.01 * b, color.getBrightness() + 0.01 * b, color.getBrightness() + 0.01 * b, color.getOpacity());
                    } catch (Exception e) {
                        color = new Color(1, 1, 1, color.getOpacity());
                    }

                } else if (a == 1) {
                    try {
                        color = new Color(color.getBrightness() - 0.01 * b, color.getBrightness() - 0.01 * b, color.getBrightness() - 0.01 * b, color.getOpacity());
                    } catch (Exception e) {
                        color = new Color(0, 0, 0, color.getOpacity());
                    }
                }
                pixelWriter.setColor(wi, hi, color);
            }
        }
        return wImage;
    }

    public Image makeByte(Image image, double threshold) {
        WritableImage wImage = new WritableImage(
                (int) image.getWidth(),
                (int) image.getHeight());
        PixelReader pixelReader = image.getPixelReader();
        PixelWriter pixelWriter = wImage.getPixelWriter();

        for (int hi = 0; hi < image.getHeight(); hi++) {
            for (int wi = 0; wi < image.getWidth(); wi++) {
                Color color = pixelReader.getColor(wi, hi);
                if (color.getBrightness() > threshold) {
                    color = color.BLACK;
                } else {
                    color = color.WHITE;
                }
                pixelWriter.setColor(wi, hi, color);
            }
        }
        return wImage;
    }



}


