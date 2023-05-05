package gui.Reclamations;


import entities.Reclamation;
import entities.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.ReclamationCRUD;
import services.UtilisateurCRUD;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ReclamationBackController {
    @javafx.fxml.FXML
    private Button ComptesBack;
    @javafx.fxml.FXML
    private Button pubBack;
    @javafx.fxml.FXML
    private Button TransactionsBack;
    @javafx.fxml.FXML
    private Button TransactionsBack1;
    @javafx.fxml.FXML
    private Button btnAcceuilBack;
    @javafx.fxml.FXML
    private Button btnCartesBack;
    @javafx.fxml.FXML
    private Button btnReclamationsBack;
    @javafx.fxml.FXML
    private Button btnCreditsBack;
    @javafx.fxml.FXML
    private AnchorPane contenuBack;
    @javafx.fxml.FXML
    private Button btnChequiersBack;
    @javafx.fxml.FXML
    private Button btnUtilisateursBack;
    @javafx.fxml.FXML
    private ImageView btnLogoutBack;
    @javafx.fxml.FXML
    private AnchorPane root;
    @javafx.fxml.FXML
    private TableColumn desc_back_col;
    @javafx.fxml.FXML
    private TableColumn etat_back_col;
    @javafx.fxml.FXML
    private TableColumn date_back_col;
    @javafx.fxml.FXML
    private TableView<Reclamation> TableViewBackReclamation;
    @javafx.fxml.FXML
    private TableColumn Type_back_col;


    static Reclamation selectionedReclamation;

    ReclamationCRUD rec = new ReclamationCRUD();
    Reclamation r = new Reclamation();
    UtilisateurCRUD pcd = new UtilisateurCRUD();
    Utilisateur u = new Utilisateur();
    @javafx.fxml.FXML
    private Button btnRecTraite;
    @javafx.fxml.FXML
    private Button btnCommentaires;

    public void initialize(){
        afficherToutReclamation();
    }

    public void afficherToutReclamation(){
        ObservableList<Reclamation> donne= FXCollections.observableArrayList();

        donne.clear();
        for (Reclamation Reclamation : donne = FXCollections.observableArrayList(rec.afficherReclamation())) {
            /*u = pcd.findByID(r.getNom_u_id());
            String var = u.getNom_u();*/
            Type_back_col.setCellValueFactory(new PropertyValueFactory<>("type_rec"));
            date_back_col.setCellValueFactory(new PropertyValueFactory<>("date_rec"));
            etat_back_col.setCellValueFactory(new PropertyValueFactory<>("etat_rec"));
            desc_back_col.setCellValueFactory(new PropertyValueFactory<>("desc_rec"));
            //utilisateur_back_col.setText(var);

            TableViewBackReclamation.setItems((ObservableList) donne);
        }
    }

    @javafx.fxml.FXML
    public void versCreditsBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les Crédits");
            Parent root = FXMLLoader.load(getClass().getResource("../credits/Credit.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void versCartesBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Transactions ");
            Parent root = FXMLLoader.load(getClass().getResource("../carte/AjoutCarte.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }



    @javafx.fxml.FXML
    public void versChequiersBack(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void versComptesBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Transactions ");
            Parent root = FXMLLoader.load(getClass().getResource("../Comptes/Back/ComptesBack.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void versTransactionsBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Transactions ");
            Parent root = FXMLLoader.load(getClass().getResource("../Transactions/Back/TransactionsBack.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void versUtilisateursBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            stage.setTitle("Liste Utilisateurs");
            Parent root = FXMLLoader.load(getClass().getResource("../utilisateur/UtilisateurBack.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void versChequesBack(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void logoutBack(Event event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Autentification");
            Parent root = FXMLLoader.load(getClass().getResource("../utilisateur/Autentification.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void versAccueilBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Acceuil Admin");
            Parent root = FXMLLoader.load(getClass().getResource("../../Home/Home.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void On_Click8traiter8reclamation(ActionEvent actionEvent) {
        selectionedReclamation = TableViewBackReclamation.getSelectionModel().getSelectedItem();
        List<Reclamation> listReclamation = TableViewBackReclamation.getSelectionModel().getSelectedItems();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Vous voulez vraiment marquer cette réclamation comme traité ?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            new ReclamationCRUD().traiterReclamation(listReclamation.get(0).getId());
            afficherToutReclamation();
        }

    }


}
