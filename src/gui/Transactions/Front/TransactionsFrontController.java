//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package gui.Transactions.Front;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import entities.Compte;
import entities.Session;
import entities.Transaction;
import services.CompteController;
import services.TransactionController;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;

public class TransactionsFrontController implements Initializable {
    @FXML
    private Button btnCartesFront;
    @FXML
    private Button btnCreditFront;
    @FXML
    private Button btnProfileFront;
    @FXML
    private Button btnAccueilFront;
    @FXML
    private Button btnTransFront;
    @FXML
    private Button btnForumFront;
    @FXML
    private Button btnCompteFront;
    @FXML
    private Button btnLogoutFront;
    @FXML
    private Button btnChequesFront;
    @FXML
    private Button btnAjouterTransCompteFront;
    @FXML
    private TextArea DescriptionTransCompteFront;
    @FXML
    private TextField MontantTransCompteFront;
    @FXML
    private TextField TransCompteRribReceptFront;
    @FXML
    private ComboBox TypeTransCompteFront;
    @FXML
    private TextField TransCompteNomRecepFront;
    @FXML
    private Label controleDescription;
    @FXML
    private Label controleTypeTransFront;
    @FXML
    private Label controleMontantTransFront;
    @FXML
    private Label controleRibRecepteurFront1;
    @FXML
    private Label controleRibRecepteurFront2;
    @FXML
    private Label controleNomRecepteurFront;
    @FXML
    private Button btnDownloadQr;
    @FXML
    private AnchorPane AnchorPaneTransFront;
    @FXML
    private Button btnCancelQrCode;

    public TransactionsFrontController() {
    }

    @FXML
    void clearFieldsTransFront() {
        this.TransCompteNomRecepFront.clear();
        this.DescriptionTransCompteFront.clear();
        this.MontantTransCompteFront.clear();
        this.TransCompteRribReceptFront.clear();
        this.TypeTransCompteFront.getSelectionModel().selectFirst();
    }

    @FXML
    public void fillComboboxTransFront() {
        this.TypeTransCompteFront.getSelectionModel().selectFirst();
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        this.fillComboboxTransFront();
        this.clearFieldsTransFront();
        this.TransCompteRribReceptFront.setStyle((String)null);
        this.controleRibRecepteurFront1.setVisible(false);
        this.controleRibRecepteurFront2.setVisible(false);
        this.TransCompteNomRecepFront.setStyle((String)null);
        this.controleNomRecepteurFront.setVisible(false);
        this.MontantTransCompteFront.setStyle((String)null);
        this.controleMontantTransFront.setVisible(false);
        this.DescriptionTransCompteFront.setStyle((String)null);
        this.controleDescription.setVisible(false);
        this.TypeTransCompteFront.setStyle((String)null);
        this.controleTypeTransFront.setVisible(false);
        CompteController comptCont = new CompteController();
        Compte cmpt = comptCont.afficherMonCompte(Session.getId());
        this.MontantTransCompteFront.setPromptText("Montant < " + cmpt.getSolde_compte());
    }



    @FXML
    public void redirectLogoutFront(ActionEvent actionEvent) {

    }

    @FXML
    public void redirectCreditFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les Crédits");
            Parent root = FXMLLoader.load(getClass().getResource("../../credits/Frontusercredit.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void redirectProfileFront(ActionEvent actionEvent) {


    }

    @FXML
    public void redirectForumFront(ActionEvent actionEvent) {

    }

    @FXML
    public void redirectChequesFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Mes cheques & chequiers");
            Parent root = FXMLLoader.load(getClass().getResource("../../ChequesChequiers/FrontuserchequeController.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    public void ValiderTransactionCompteFront(ActionEvent actionEvent) {
        TransactionController transCont = new TransactionController();
        CompteController comptCont = new CompteController();
        Compte cmpt = comptCont.afficherMonCompte(Session.getId());
            String description = this.DescriptionTransCompteFront.getText();
            String nomRecepteur = this.TransCompteNomRecepFront.getText();
            String ribRecepteur = this.TransCompteRribReceptFront.getText();
            Float montant = Float.valueOf(this.MontantTransCompteFront.getText());
            String type = this.TypeTransCompteFront.getSelectionModel().getSelectedItem().toString();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Transaction Bancaire Via Compte");
            alert.setHeaderText((String)null);
            alert.setContentText("Transaction Bancaire effectuée avec succès !");
            Stage stage = (Stage)alert.getDialogPane().getScene().getWindow();
            alert.showAndWait();
            Transaction trans = new Transaction(cmpt.getId(), ribRecepteur, montant, dtf.format(now), description, cmpt.getId(), nomRecepteur, type, 1);
            transCont.ajouterTransaction2(trans);
            comptCont.modificationTransaction(trans.getRIB_emetteur(), trans.getRIB_recepteur(), trans.getMontant_transaction());
            this.clearFieldsTransFront();
            this.TransCompteRribReceptFront.setStyle((String)null);
            this.controleRibRecepteurFront1.setVisible(false);
            this.controleRibRecepteurFront2.setVisible(false);
            this.TransCompteNomRecepFront.setStyle((String)null);
            this.controleNomRecepteurFront.setVisible(false);
            this.MontantTransCompteFront.setStyle((String)null);
            this.controleMontantTransFront.setVisible(false);
            this.DescriptionTransCompteFront.setStyle((String)null);
            this.controleDescription.setVisible(false);
            this.TypeTransCompteFront.setStyle((String)null);
            this.controleTypeTransFront.setVisible(false);



    }

    @FXML
    public void RedirectAccueilFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Acceuil");
            Parent root = FXMLLoader.load(getClass().getResource("../../../HomeFront/HomeFront.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void redirectCompteFront(ActionEvent actionEvent) {


    }
    @FXML
    public void redirectTransactionsFront(ActionEvent actionEvent) {


    }    @FXML
    public void redirectCartesFront(ActionEvent actionEvent) {


    }    @FXML
    public void redirectReclamationFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Passer Reclamations");
            Parent root = FXMLLoader.load(getClass().getResource("../../Reclamations/AjouterRecFront.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }


}
