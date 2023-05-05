package gui.credits;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class maintest  extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Credit.fxml")));
            //Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Frontusercredit.fxml")));

            Scene scene = new Scene(root, 1203, 671);
            scene.setFill(Color.TRANSPARENT);
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("../../assets/Images/logo-Final.png")));
            stage.getIcons().add(icon);
            stage.setTitle("Dashboard EL BAnk");
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