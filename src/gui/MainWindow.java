package gui;

import fuctions.Functions1;
import fuctions.Functions2;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindow implements Initializable {
    @FXML
    public AnchorPane rootPane;
    @FXML
    public Canvas canvas;
    @FXML
    Button buttonBrighter;
    @FXML
    Button buttonDarker;
    @FXML
    TextField textField;
    @FXML
    Button buttonByte;

    Functions1 functions1;
    Functions2 function2;
    GraphicsContext gc;

    private String imagePath = "assets/obrazekDark.bmp";
    private Image image;
    private boolean byteCheck;

    public void makeBrighter() {
        functions1 = new Functions1();
        gc.clearRect(0, 0, 600, 330);
        image = functions1.makeBrighterOrDarker(image, 0, Double.parseDouble(textField.getText()));
        gc.drawImage(image, 0, 0);
    }

    public void makeDarker() {
        functions1 = new Functions1();
        gc.clearRect(0, 0, 600, 330);
        image = functions1.makeBrighterOrDarker(image, 1, Double.parseDouble(textField.getText()));
        // gc.drawImage(functions.makeBrighterOrDarker(image, 1, Integer.parseInt(textField.getText())), 0, 0);
        gc.drawImage(image, 0, 0);
    }


    public void reset() {
        image = new Image(imagePath);
        gc.clearRect(0, 0, 600, 330);
        gc.drawImage(image, 0, 0);

        byteCheck = false;
    }

    public void changeBmp() {
        gc.clearRect(0, 0, 600, 330);
        if (imagePath.equals("assets/obrazekDark.bmp"))
            imagePath = "assets/obrazek.bmp";
        else
            imagePath = "assets/obrazekDark.bmp";
        image = new Image(imagePath);
        gc.drawImage(image, 0, 0);
    }

    public void lowerFilter() {
        gc.clearRect(0, 0, 600, 330);
        function2 = new Functions2();
        var list = function2.makeMatrixList(image);

        image = function2.filter(image, list, 1);
        gc.drawImage(image, 0, 0);
    }

    public void higherFilter() {
        gc.clearRect(0, 0, 600, 330);
        function2 = new Functions2();
        var list = function2.makeMatrixList(image);

        image = function2.filter(image, list, 2);
        gc.drawImage(image, 0, 0);
    }

    public void gaussianFilter() {
        gc.clearRect(0, 0, 600, 330);
        function2 = new Functions2();
        var list = function2.makeMatrixList(image);

        image = function2.filter(image, list, 3);
        gc.drawImage(image, 0, 0);
    }

    public void makeByte() {
        functions1 = new Functions1();

        gc.clearRect(0, 0, 600, 330);
        image = functions1.makeByte(image, Double.parseDouble(textField.getText()));
        gc.drawImage(image, 0, 0);
        byteCheck = true;
        // gc.drawImage(functions1.makeByte(image,Double.parseDouble(textField.getText())),0, 0);
    }

    public void erosion() {
        functions1 = new Functions1();
        function2 = new Functions2();
        gc.clearRect(0, 0, 600, 330);
        if (byteCheck == false) {
            image = functions1.makeByte(image, Double.parseDouble(textField.getText()));
        }
        var list = function2.makeMatrixList(image);
        image = function2.filter(image, list, 4);
        gc.drawImage(image, 0, 0);
        byteCheck = true;
    }

    public void dilatation() {
        functions1 = new Functions1();
        function2 = new Functions2();
        gc.clearRect(0, 0, 600, 330);
        if (byteCheck == false) {
            image = functions1.makeByte(image, Double.parseDouble(textField.getText()));
            System.out.println("jertsyshf");
        }
        var list = function2.makeMatrixList(image);
        image = function2.filter(image, list, 5);
        gc.drawImage(image, 0, 0);
        byteCheck = true;
    }


    public void initialize(URL url, ResourceBundle resourceBundle) {
        textField.setText("0.7");
        image = new Image((imagePath));
        gc = canvas.getGraphicsContext2D();
        gc.drawImage(image, 0, 0);
        byteCheck = false;
    }

    public void morphologicalOpening() {
        gc.clearRect(0, 0, 600, 330);
        erosion();
        dilatation();
    }

    public void morphologicalClosing() {
        gc.clearRect(0, 0, 600, 330);
        dilatation();
        erosion();
    }



}
