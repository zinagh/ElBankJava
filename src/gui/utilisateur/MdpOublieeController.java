package gui.utilisateur;

import tools.MailApi;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import entities.Utilisateur;
import services.UtilisateurCRUD;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MdpOublieeController {
    String emailPW = null;
    @javafx.fxml.FXML
    private Button search;
    @javafx.fxml.FXML
    private TextField code;
    @javafx.fxml.FXML
    private TextField email_search;

    UtilisateurCRUD pcd = new UtilisateurCRUD();
    Utilisateur u = new Utilisateur();
    int n;
    @javafx.fxml.FXML
    private Button btn_modifier;
    @javafx.fxml.FXML
    private Button btnRetourLogin;

    private Stage stage;
    private Scene scene;
    private Parent root;
    @javafx.fxml.FXML
    private PasswordField new_pwd;
    @javafx.fxml.FXML
    private PasswordField confirme_pwd;
    @javafx.fxml.FXML
    private Label cntrlConfirmeMdp;
    @javafx.fxml.FXML
    private Label cntrlNewMdp;
    @javafx.fxml.FXML
    private Label cntrlCode;

    public void initialize() {
        // TODO
        btn_modifier.setVisible(false);
        code.setVisible(false);
        new_pwd.setVisible(false);
        confirme_pwd.setVisible(false);
        Random rand =new Random();
        n=rand.nextInt(99999);
    }
    public boolean isValidPassword(){
        boolean isValid = true;
        if (new_pwd.getText().trim().length() > 15 || new_pwd.getText().trim().length() < 10 ){
            isValid=false;
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if (!new_pwd.getText().matches(upperCaseChars )){
            isValid = false;
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!new_pwd.getText().matches(lowerCaseChars ))
        {
            isValid = false;
        }
        String numbers = "(.*[0-9].*)";
        if (!new_pwd.getText().matches(numbers ))
        {
            isValid = false;
        }
        String specialChars = "(.*[@,#,$,%].*$)";
        if (!new_pwd.getText().matches(specialChars )){
            isValid = false;
        }
        return isValid;
    }

    @javafx.fxml.FXML
    public void On_Click_Chercher(ActionEvent actionEvent) {
        if(pcd.verifierEmailBd(email_search.getText())){
            String mail = email_search.getText();
            MailApi.send("elbankservices@gmail.com", "loojpwkbsfeesgyi", mail, "Mot de passe oublié", "Cher client, \nVoici votre code pour la mise à jour de votre mot de passe: \n"+n);
            email_search.setVisible(false);
            search.setVisible(false);
            btn_modifier.setVisible(true);
            code.setVisible(true);
            new_pwd.setVisible(true);
            confirme_pwd.setVisible(true);
        }
        else{
            email_search.setStyle("-fx-border-color: #f00020");
            email_search.setText("-Email n'existe pas");
        }
    }

    @javafx.fxml.FXML
    public void On_Click_ModifierMDP(ActionEvent actionEvent) {
        System.out.println(u);
        if (code.getText().equals(String.valueOf(n)) && new_pwd.getText().equals(confirme_pwd.getText()) && isValidPassword()){
            String mail = email_search.getText();
            String pwd = new_pwd.getText();
            pcd.modifierPassword(mail,pwd);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("success");
            alert.setContentText("Votre mot de passe a été changé avec succès");
            alert.showAndWait();
            try {
                root = FXMLLoader.load(getClass().getResource("Autentification.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(AutentificationController.class.getName()).log(Level.SEVERE, null, ex);
            }

            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else {
            if (!isValidPassword()){
                new_pwd.setStyle("-fx-border-color: #f00020");
                if (new_pwd.getText().trim().length() > 15 || new_pwd.getText().trim().length() < 10 ){
                    cntrlNewMdp.setText("Password must be less than 15 and more than 10");
                }
                String upperCaseChars = "(.*[A-Z].*)";
                if (!new_pwd.getText().matches(upperCaseChars )){
                    cntrlNewMdp.setText("Password must have atleast one uppercase character");
                }
                String lowerCaseChars = "(.*[a-z].*)";
                if (!new_pwd.getText().matches(lowerCaseChars )){
                    cntrlNewMdp.setText("Password must have atleast one lowercase character");
                }
                String numbers = "(.*[0-9].*)";
                if (!new_pwd.getText().matches(numbers ))
                {
                    cntrlNewMdp.setText("Password must have atleast one number");
                }
                String specialChars = "(.*[@,#,$,%].*$)";
                if (!new_pwd.getText().matches(specialChars ))
                {
                    cntrlNewMdp.setText("Password must have atleast one special character among @#$%");
                }
            }else {
                new_pwd.setStyle("-fx-border-color: #22780F");
                cntrlNewMdp.setVisible(false);
            }
            if (!code.getText().equals(String.valueOf(n))){
                code.setStyle("-fx-border-color: #f00020");
                cntrlCode.setText("- Code Invalide");
            }else {
                code.setStyle("-fx-border-color: #22780F");
                cntrlCode.setVisible(false);
            }
            if (!new_pwd.getText().equals(confirme_pwd.getText())){
                confirme_pwd.setStyle("-fx-border-color: #f00020");
                cntrlConfirmeMdp.setText("Les mot de passe ne sont pas identiques ");
            }else {
                confirme_pwd.setStyle("-fx-border-color: #22780F");
                cntrlConfirmeMdp.setVisible(false);
            }
        }

    }


    @javafx.fxml.FXML
    public void VersAutentification(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Autentification");
            Parent root = FXMLLoader.load(getClass().getResource("Autentification.fxml"));

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
    public void On_Click_RetourLogin(Event event) {
    }
}
