package gui.utilisateur;

import com.google.zxing.WriterException;
import entities.Reclamation;
import entities.Session;
import entities.Utilisateur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import services.ReclamationCRUD;
import services.UtilisateurCRUD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tools.MyQRcode;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class ProfileController {
    UtilisateurCRUD pcd = new UtilisateurCRUD();
    @javafx.fxml.FXML
    private TableView<Utilisateur> tabelViewProfile;
    @javafx.fxml.FXML
    private TableColumn date_naissance_col;
    @javafx.fxml.FXML
    private TableColumn cin_col;
    @javafx.fxml.FXML
    private TableColumn nom_col;
    @javafx.fxml.FXML
    private TableColumn prenom_col;
    @javafx.fxml.FXML
    private TableColumn tel_col;
    @javafx.fxml.FXML
    private TableColumn email_col;
    int idModifier;
    String emailModif;
    Utilisateur U = new Utilisateur();
    @javafx.fxml.FXML
    private TextField cin_profile;
    @javafx.fxml.FXML
    private TextField telephone_profile;
    @javafx.fxml.FXML
    private DatePicker date_naissance_profile;
    @javafx.fxml.FXML
    private Button modifier_profile;
    @javafx.fxml.FXML
    private TextField prenom_profile;
    @javafx.fxml.FXML
    private TextField nom_profile;
    @javafx.fxml.FXML
    private TextField email_profile;
    ReclamationCRUD rec = new ReclamationCRUD();
    ObservableList<Utilisateur> data=FXCollections.observableArrayList();
    @javafx.fxml.FXML
    private TableView<Reclamation> TableViewReclamtionProfile;
    @FXML
    private Button btnCreditFront;
    @FXML
    private Button btnCartesFront;
    @FXML
    private Button btnProfileFront;
    @FXML
    private Button btnAccueilFront;
    @FXML
    private Button btnTransFront;
    @FXML
    private Button btnForumFront;
    @FXML
    private Button btnLogoutFront;
    @FXML
    private Button btnChequesFront;
    @FXML
    private TableColumn desc_rec_col;
    @FXML
    private TableColumn type_rec_col;
    @FXML
    private TableColumn date_rec_col;
    @FXML
    private TableColumn etat_rec_col;
    @FXML
    private Button btnReclamationFront;
    @FXML
    private Button btnCompteFront;
    @FXML
    private Button supprimerRec;

    @FXML
    private Button qrcode;
    @FXML
    private VBox qrCodeContainer;
    static Reclamation selectionedReclamation;

    @Deprecated
    public void initialize() {

        data.clear();
        for (Utilisateur utilisateur : data = FXCollections.observableArrayList(pcd.afficherProfile())) {
            cin_col.setCellValueFactory(new PropertyValueFactory<>("cin_u"));
            nom_col.setCellValueFactory(new PropertyValueFactory<>("nom_u"));
            prenom_col.setCellValueFactory(new PropertyValueFactory<>("prenom_u"));
            date_naissance_col.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
            tel_col.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
            email_col.setCellValueFactory(new PropertyValueFactory<>("email_u"));
            tabelViewProfile.setItems((ObservableList) data);
        }

        afficherMesReclamationProfile();
    }
    public void afficherMesReclamationProfile(){
        ObservableList<Reclamation> donne=FXCollections.observableArrayList();
        donne.clear();
        for (Reclamation Reclamation : donne = FXCollections.observableArrayList(rec.afficherMesReclamations(Session.getId()))) {
            type_rec_col.setCellValueFactory(new PropertyValueFactory<>("type_rec"));
            date_rec_col.setCellValueFactory(new PropertyValueFactory<>("date_rec"));
            etat_rec_col.setCellValueFactory(new PropertyValueFactory<>("etat_rec"));
            desc_rec_col.setCellValueFactory(new PropertyValueFactory<>("desc_rec"));

            TableViewReclamtionProfile.setItems((ObservableList) donne);
        }
    }
    public void refresh(){
        cin_profile.setText("");
        nom_profile.setText("");
        prenom_profile.setText("");
        date_naissance_profile.setValue(null);
        email_profile.setText("");
        telephone_profile.setText("");

        cin_profile.setStyle("-fx-border-color: #B0C4DE ; -fx-border-width: 1.5");
        nom_profile.setStyle("-fx-border-color: #B0C4DE ; -fx-border-width: 1.5");
        prenom_profile.setStyle("-fx-border-color: #B0C4DE ; -fx-border-width: 1.5");
        date_naissance_profile.setStyle("-fx-border-color: #B0C4DE ; -fx-border-width: 1.5");
        email_profile.setStyle("-fx-border-color: #B0C4DE ; -fx-border-width: 1.5");
        telephone_profile.setStyle("-fx-border-color: #B0C4DE ; -fx-border-width: 1.5");
        afficherMesReclamationProfile();
    }
    public void Afficher(){
        UtilisateurCRUD pcd = new UtilisateurCRUD();
        Utilisateur u = pcd.afficherProfile2(Session.getId());
        System.out.println(u);
        data.clear();
        data.add(u);
        System.out.println(data);

        cin_col.setCellValueFactory(new PropertyValueFactory<>("cin_u"));
        nom_col.setCellValueFactory(new PropertyValueFactory<>("nom_u"));
        prenom_col.setCellValueFactory(new PropertyValueFactory<>("prenom_u"));
        date_naissance_col.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
        tel_col.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
        email_col.setCellValueFactory(new PropertyValueFactory<>("email_u"));
        tabelViewProfile.setItems(data);

    }

    @javafx.fxml.FXML
    public void On_Click_TableViewProfile(Event event) {
        String email =tabelViewProfile.getSelectionModel().getSelectedItem().getEmail_u();
        Utilisateur user= pcd.findByMail(email);
        idModifier=user.getId();

        cin_profile.setText(String.valueOf(user.getCin_u()));
        nom_profile.setText(user.getNom_u());
        prenom_profile.setText(user.getPrenom_u());
        date_naissance_profile.setValue(LocalDate.parse(String.valueOf(user.getDate_naissance())));
        email_profile.setText(user.getEmail_u());
        telephone_profile.setText(String.valueOf(user.getNum_tel()));

        emailModif= user.getEmail_u();
    }

    //********************Controle de saisie ***********************

    private boolean validateEmailForm(){
        if(pcd.email_Validation(email_profile.getText())){
            return true;
        }else {
            return false;
        }
    }
    private boolean validateNom() {
        if (!nom_profile.getText().isEmpty()) {
            if (nom_profile.getText().matches("(.*[a-z].*)")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    private boolean validatePrenom() {
        if (!prenom_profile.getText().isEmpty()) {
            if (prenom_profile.getText().matches("(.*[a-z].*)")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    private boolean validateCin() {
        if (!cin_profile.getText().isEmpty()) {
            if (cin_profile.getText().matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    private boolean validateTelephone() {
        if (!telephone_profile.getText().isEmpty()) {
            if (telephone_profile.getText().matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @javafx.fxml.FXML
    public void On_Click_Modifier_Profile(ActionEvent actionEvent) {
        if (validateEmailForm() && validateNom() && validatePrenom() && validateCin() && validateTelephone()) {
            U.setCin_u(Integer.parseInt(cin_profile.getText()));
            U.setNom_u(nom_profile.getText());
            U.setPrenom_u(prenom_profile.getText());
            U.setDate_naissance(Date.valueOf(date_naissance_profile.getValue()));
            U.setEmail_u(email_profile.getText());
            U.setNum_tel(Integer.parseInt(telephone_profile.getText()));

            UtilisateurCRUD pcd = new UtilisateurCRUD();
            pcd.modifierProfile(U, idModifier);
            JOptionPane.showMessageDialog(null, "Modification effectué avec success");
            refresh();
            //tabelViewProfile.getItems().clear();
            Afficher();
        }else {
            if (!validateEmailForm()){
                email_profile.setText("-From Email Incorrecte !");
                email_profile.setStyle("-fx-border-color: #f00020");
            }else {
                email_profile.setStyle("-fx-border-color: #22780F");
            }
            if (!validateNom()){
                nom_profile.setStyle("-fx-border-color: #f00020");
                nom_profile.setText("-Nom invalide !");
            }else {
                nom_profile.setStyle("-fx-border-color: #22780F");
            }
            if (!validatePrenom()){
                prenom_profile.setStyle("-fx-border-color: #f00020");
                prenom_profile.setText("-Prenom invalide !");
            }else {
                prenom_profile.setStyle("-fx-border-color: #22780F");
            }
            if (!validateCin()){
                cin_profile.setStyle("-fx-border-color: #f00020");
                cin_profile.setText("-Cin invalide !");
            }else {
                cin_profile.setStyle("-fx-border-color: #22780F");
            }
            if (!validateTelephone()){
                telephone_profile.setStyle("-fx-border-color: #f00020");
                telephone_profile.setText("-Numéro télephone invalide !");
            }else {
                telephone_profile.setStyle("-fx-border-color: #22780F");
            }
        }


    }

    @FXML
    public void redirectCartesFront(ActionEvent actionEvent) {
        try {
//            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Calendrier");
            Parent root = FXMLLoader.load(getClass().getResource("../../HomeFront/HomeFront.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void redirectLogoutFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Autentification");
            Parent root = FXMLLoader.load(getClass().getResource("Autentification.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void redirectCreditFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les Crédits");
            Parent root = FXMLLoader.load(getClass().getResource("../credits/Frontusercredit.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void RedirectAccueilFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Acceuil");
            Parent root = FXMLLoader.load(getClass().getResource("../../HomeFront/HomeFront.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void redirectTransactionsFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Effectuer une Transaction Bancaire");
            Parent root = FXMLLoader.load(getClass().getResource("../Transactions/Front/TransactionsFront.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }



    @FXML
    public void redirectChequesFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Mes cheques & chequiers");
            Parent root = FXMLLoader.load(getClass().getResource("../ChequesChequiers/FrontuserchequeController.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void redirectReclamationFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Passer Reclamations");
            Parent root = FXMLLoader.load(getClass().getResource("../Reclamations/AjouterRecFront.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void redirectCompteFront(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Mon Compte Bancaire");
            Parent root = FXMLLoader.load(getClass().getResource("../../HomeFront/HomeFront.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void supprimerReclamation(ActionEvent actionEvent) {
        selectionedReclamation = TableViewReclamtionProfile.getSelectionModel().getSelectedItem();
        java.util.Date current_date = new java.util.Date();
        java.util.Date creation_date = selectionedReclamation.getDate_rec();
        System.out.println(creation_date);
        long diffInMillies = Math.abs(current_date.getTime() - creation_date.getTime());
        long diff = TimeUnit.HOURS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        System.out.println(diff);


        if (selectionedReclamation.getEtat_rec().equals("Traité")) {
            Notifications n = Notifications.create()
                    .title("Erreur")
                    .text("Suppression impossible: Reclamation en traité")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            n.showWarning();
        }else if (diff >= 24) {
            Notifications b = Notifications.create()
                    .title("Erreur")
                    .text("Suppression impossible: Reclamation a depasée 24h depuis sa creation")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(5));
            b.showWarning();
        }else {
            List<Reclamation> listReclamation = TableViewReclamtionProfile.getSelectionModel().getSelectedItems();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Vous voulez vraiment supprimer cette réclamation ?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                new ReclamationCRUD().supprimerReclamation(listReclamation.get(0).getId());
                initialize();
            }
        }


    }


    @FXML
    public void qrcodegenerate(ActionEvent actionEvent) {
        UtilisateurCRUD pcd = new UtilisateurCRUD();
        Utilisateur u = pcd.afficherProfile2(Session.getId());

        String dataToEncode = String.format(
                "Nom: %s\nPrenom: %s\nCIN: %d\nTelephone: %d\nEmail: %s",
                u.getNom_u(), u.getPrenom_u(), u.getCin_u(), u.getNum_tel(), u.getEmail_u());
        int width = 250;
        int height = 250;

        try {
            ImageView qrCodeImageView = MyQRcode.generateQRCode(dataToEncode, width, height);
            qrCodeContainer.getChildren().add(qrCodeImageView);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }




}
