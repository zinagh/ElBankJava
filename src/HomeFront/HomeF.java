package HomeFront;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class HomeF extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("HomeFront.fxml"));
            Scene scene = new Scene(root, 1000, 680);
            scene.setFill(Color.TRANSPARENT);
            Image icon = new Image(getClass().getResourceAsStream("../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setTitle("EL Bank");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch();
    }
}
