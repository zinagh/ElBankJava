package gui.utilisateur;

import entities.Session;
import entities.Utilisateur;
import javafx.stage.Stage;
import services.UtilisateurCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class PopUpProfileController  implements Initializable {
    @javafx.fxml.FXML
    private Label DateNaissanceInfo;
    @javafx.fxml.FXML
    private Label NomInfo;
    @javafx.fxml.FXML
    private Label telInfo;
    @javafx.fxml.FXML
    private Label CinInfo;
    @javafx.fxml.FXML
    private Label PrenomInfo;
    @javafx.fxml.FXML
    private Label EmailInfo;
    @javafx.fxml.FXML
    private Label EtatInfo;
    @javafx.fxml.FXML
    private Button btnclosePopup;


    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        afficherInfos();
    }


    private void afficherInfos() {
        UtilisateurCRUD pcd = new UtilisateurCRUD();
        Utilisateur u = pcd.afficherProfile2(Session.getId());
        System.out.println(u);

        DateNaissanceInfo.setText(u.getDate_naissance().toString());
        NomInfo.setText(u.getNom_u());
        telInfo.setText(String.valueOf(u.getNum_tel()));
        CinInfo.setText(String.valueOf(u.getCin_u()));
        PrenomInfo.setText(u.getPrenom_u());
        EmailInfo.setText(u.getEmail_u());
        EtatInfo.setText(u.getEtat());
    }
    @FXML
    private void closePopup(ActionEvent event) {
        Stage stage = (Stage) btnclosePopup.getScene().getWindow();
        stage.close();

    }
    }
