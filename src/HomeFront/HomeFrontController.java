package HomeFront;


import gui.utilisateur.AutentificationController;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HomeFrontController {
    @javafx.fxml.FXML
    private Button btnCartesFront;
    @javafx.fxml.FXML
    private Button btnCreditFront;
    @javafx.fxml.FXML
    private Button btnProfileFront;
    @javafx.fxml.FXML
    private Button btnAccueilFront;
    @javafx.fxml.FXML
    private Button btnForumFront;
    @javafx.fxml.FXML
    private Button btnCompteFront;
    @javafx.fxml.FXML
    private Button btnLogoutFront;
    @javafx.fxml.FXML
    private Button btnChequesFront;
    @javafx.fxml.FXML
    private Button btnTransFront;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @javafx.fxml.FXML
    private Button btnReclamationFront;
    @javafx.fxml.FXML
    private Button btnCalendrier;

    private Stage popupStage;

    @javafx.fxml.FXML
    public void redirectCartesFront(ActionEvent actionEvent) {
       /* try {
//            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Calendrier");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/CartesCategories/Calendar.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } */
    }

    @javafx.fxml.FXML
    public void redirectLogoutFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

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

    @javafx.fxml.FXML
    public void redirectCreditFront(ActionEvent actionEvent) {
       /* try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Mes Crédits");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/CreditsOperations/affichercreditfront.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } */
    }

    @javafx.fxml.FXML
    public void redirectProfileFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            stage.setTitle("Mon Profile");
            Parent root = FXMLLoader.load(getClass().getResource("/gui/utilisateur/Profile.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void redirectForumFront(ActionEvent actionEvent) {
      /*  try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Forum Bankiz");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/PublicationFront/PublicationFront.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } */
    }

    @javafx.fxml.FXML
    public void redirectChequesFront(ActionEvent actionEvent) {
       try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Mes cheques & chequiers");
            Parent root = FXMLLoader.load(getClass().getResource("../gui/ChequesChequiers/FrontuserchequeController.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void redirectCompteFront(ActionEvent actionEvent) {
       /* try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Mon Compte Bancaire");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/Comptes/Front/MonCompteBancaire.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } */
    }

    @javafx.fxml.FXML
    public void redirectTransactionsFront(ActionEvent actionEvent) {
      /*  try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Effectuer une Transaction Bancaire");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/Transactions/Front/TransactionsFront.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } */
    }

    @javafx.fxml.FXML
    public void redirectReclamationFront(ActionEvent actionEvent) {
       /* try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            stage.setTitle("Effectuer une Reclamation");
            Parent root = FXMLLoader.load(getClass().getResource("/edu/bankiz/gui/Reclamations/AjouterRecFront.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } */
    }

    @javafx.fxml.FXML
    public void redirectPopUp(Event event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/utilisateur/PopUpProfile.fxml"));
            AnchorPane popupRoot = loader.load();

            // create a new stage for the popup
            popupStage = new Stage();
            popupStage.initStyle(StageStyle.TRANSPARENT);

            // set the popup content and show it
            Scene popupScene = new Scene(popupRoot);
            popupStage.setScene(popupScene);
            popupStage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }


    @javafx.fxml.FXML
    public void ciaoPopup(Event event) {
        try {
            // close the popup stage if it is not null
            if (popupStage != null) {
                popupStage.close();
            }

            root = FXMLLoader.load(getClass().getResource("HomeFront.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AutentificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
