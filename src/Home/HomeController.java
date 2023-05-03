package Home;

import javafx.event.ActionEvent;
import javafx.event.Event;

import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomeController implements Initializable {
    private Parent fxml;

    @javafx.fxml.FXML
    private Button pubBack;
    @FXML
    private AnchorPane root;
    @FXML
    private Button ComptesBack;
    @FXML
    private Button TransactionsBack;
    @FXML
    private Button btnCreditsBack;
    @FXML
    private AnchorPane contenuBack;
    @FXML
    private Button btnChequiersBack;

    @FXML
    private Button btnOpCredBack;
    @FXML
    private Button btnUtilisateursBack;
    @FXML
    private Button TransactionsBack1;
    @FXML
    private Button btnLogoutBack;
    @FXML
    private Button btnCartesBack;
    @FXML
    private Button btnReclamationsBack;
    @FXML
    private Button btnCommentaireBack;

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }



    @FXML
    public void versComptesBack(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Transactions ");
            Parent root = FXMLLoader.load(getClass().getResource("../gui/Comptes/Back/ComptesBack.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void versTransactionsBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Transactions ");
            Parent root = FXMLLoader.load(getClass().getResource("../gui/Transactions/Back/TransactionsBack.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }



    @FXML
    public void versCreditsBack(ActionEvent actionEvent) {
       try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les Crédits");
            Parent root = FXMLLoader.load(getClass().getResource("../gui/credits/Credit.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    public void versOps(ActionEvent actionEvent) {
    }


    @FXML
    public void versCartesBack(ActionEvent actionEvent) {
       try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les cartes ");
            Parent root = FXMLLoader.load(getClass().getResource("../gui/carte/AjoutCarte.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void versChequiersBack(ActionEvent actionEvent) {
        /* try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les chequiers ");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/ChequesChequiers/Home.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } */
    }

    @FXML
    public void versUtilisateursBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Liste Utilisateurs");
            Parent root = FXMLLoader.load(getClass().getResource("/gui/utilisateur/UtilisateurBack.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void versChequesBack(ActionEvent actionEvent) {
       /* try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les chèques ");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/ChequesChequiers/Baccheque.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } */
    }

    @FXML
    public void logoutBack(Event event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Autentification");
            Parent root = FXMLLoader.load(getClass().getResource("/gui/utilisateur/Autentification.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void openReclamationBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Reclamations ");
            Parent root = FXMLLoader.load(getClass().getResource("../gui/Reclamations/ReclamationBack.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


}
