package fuctions;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Functions2 {

    Double[][] tab;
    List list;
    int pomW = -1;
    int pomH = -1;
    int opacity = 1;

    public List<Double[][]> makeMatrixList(Image image) {
        list = new ArrayList();
        PixelReader pixelReader = image.getPixelReader();
        for (int hi = 1; hi < image.getHeight() - 1; hi++) {
            //tab = new Double[3][3];
            for (int wi = 1; wi < image.getWidth() - 1; wi++) {
                tab = new Double[3][3];
                for (int i = 0; i < tab[0].length; i++) {
                    for (int j = 0; j < tab.length; j++) {
                        tab[i][j] = pixelReader.getColor(wi + pomW, hi + pomH).getBrightness();
                        pomW++;
                    }
                    pomH++;
                    pomW = -1;
                }
                pomH = -1;
                list.add(tab);
            }
        }
        System.out.println("size "+list.size());
        return list;
    }

    public double getLower(int b, List<Double[][]> list) {
        double a = 0;
        for (int j = 0; j < tab[0].length; j++) {
            for (int k = 0; k < tab.length; k++) {
                a += list.get(b)[j][k];
            }
        }
        return a / 9;
    }

    public double getHigher(int b, List<Double[][]> list) {
        double a = 0;
        for (int j = 0; j < tab[0].length; j++) {
            for (int k = 0; k < tab.length; k++) {
                if (j == 1 && k == 1) {
                    a += 9 * list.get(b)[j][k];
                } else {
                    a += -1 * list.get(b)[j][k];
                }

            }
        }
        return a;
    }

    public double getGaussian(int b, List<Double[][]> list) {
        double a = 0;
        for (int j = 0; j < tab[0].length; j++) {
            for (int k = 0; k < tab.length; k++) {
                if (j == 1 && k == 1) {
                    a += 4 * list.get(b)[j][k];
                }
                if ((j == 0 && k == 0) || (j == 0 && k == 2) || (j == 2 && k == 0) || (j == 2 && k == 2)) {
                    a += 1 * list.get(b)[j][k];
                } else {
                    a += 2 * list.get(b)[j][k];
                }
            }
        }
        return a / 16;
    }

    public double getForErosion(int b, List<Double[][]> list) {

        for (int j = 0; j < tab[0].length; j++) {
            for (int k = 0; k < tab.length; k++) {
                if (list.get(b)[j][k] == 0) {
                    return 0;
                }
            }
        }
        return 1;
    }

    public double getForDilatation(int b, List<Double[][]> list) {

        for (int j = 0; j < tab[0].length; j++) {
            for (int k = 0; k < tab.length; k++) {
                if (list.get(b)[j][k] == 1) {
                    return 1;
                }
            }
        }
        return 0;
    }


    public Image filter(Image image, List<Double[][]> list, int ch) {

        int pom = 0;
        WritableImage wImage = new WritableImage(
                (int) image.getWidth(),
                (int) image.getHeight());
        PixelWriter pixelWriter = wImage.getPixelWriter();
        double a = 0;

        for (int hi = 1; hi < image.getHeight() - 1; hi++) {
            for (int wi = 1; wi < image.getWidth() - 1; wi++) {

                if (ch == 1)
                    a = getLower(pom, list);
                if (ch == 2)
                    a = getHigher(pom, list);
                if (ch == 3)
                    a = getGaussian(pom, list);
                if (ch == 4)
                    a = getForErosion(pom, list);
                if (ch == 5)
                    a = getForDilatation(pom, list);

                Color color;
                try {
                    color = new Color(a, a, a, opacity);
                } catch (Exception e) {
                    if (a < 0)
                        color = new Color(0, 0, 0, opacity);
                    else
                        color = new Color(1, 1, 1, opacity);
                }
                pixelWriter.setColor(wi, hi, color);
                pom += 1;
            }
        }
        return wImage;
    }


}


