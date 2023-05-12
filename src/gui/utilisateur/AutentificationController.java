package gui.utilisateur;

import entities.Session;
import entities.Utilisateur;
import services.UtilisateurCRUD;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutentificationController {
    @javafx.fxml.FXML
    private TextField cin;
    @javafx.fxml.FXML
    private TextField telephone;
    @javafx.fxml.FXML
    private TextField email_login;
    @javafx.fxml.FXML
    private TextField nom;
    @javafx.fxml.FXML
    private Button mdp_oubliee;
    @javafx.fxml.FXML
    private PasswordField mdp_login;
    @javafx.fxml.FXML
    private Button inscription;
    @javafx.fxml.FXML
    private PasswordField mot_de_passe;
    @javafx.fxml.FXML
    private TextField prenom;
    @javafx.fxml.FXML
    private Label age;
    @javafx.fxml.FXML
    private DatePicker date_naissance;
    @javafx.fxml.FXML
    private TextField email;
    @javafx.fxml.FXML
    private Button connexion;
    UtilisateurCRUD pcd = new UtilisateurCRUD();
    Utilisateur u = new Utilisateur();
    private Stage stage;
    private Scene scene;
    private Parent root;
    @javafx.fxml.FXML
    private Label labelMdp;

    public void refresh(){
        cin.setText("");
        nom.setText("");
        prenom.setText("");
        date_naissance.setValue(null);
        email.setText("");
        telephone.setText("");
        mot_de_passe.setText("");
        age.setText("");
    }
    public void initialize() {
        date_naissance.valueProperty().addListener((ov, oldValue, newValue) -> {
            UtilisateurCRUD pcd = new UtilisateurCRUD();
            int a = pcd.calculateAge(Date.valueOf(newValue));
            age.setText(String.valueOf(a));
            setAge("votre age est :"+String.valueOf(a)+" Ans");
        });
    }
    public void setAge(String age) {
        this.age.setText(String.valueOf(age));
    }

    @javafx.fxml.FXML
    public void On_Click_mdp_Oubliee(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Reset mot de passe");
            Parent root = FXMLLoader.load(getClass().getResource("MdpOubliee.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @javafx.fxml.FXML
    public void On_Click_Connexion(ActionEvent actionEvent) {
        if (pcd.Check_login(email_login.getText(), mdp_login.getText())) {

            if (pcd.login(email_login.getText(), mdp_login.getText())) {
                Utilisateur u = pcd.findByMail(email_login.getText());


                System.out.println(Session.getEtat());

                switch (u.getRole()) {
                    case "ROLE_USER":
                        try {
                            if (Session.getEtat().equals("Debloquer")) {
                                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                                Parent root = FXMLLoader.load(getClass().getResource("../../HomeFront/HomeFront.fxml"));
                                Scene scene = new Scene(root, 1000, 680);
                                scene.setFill(Color.TRANSPARENT);
                                Stage stage = new Stage();
                                stage.setTitle("EBank - EL Bank");
                                stage.setScene(scene);
                                System.out.println(Session.getId());
                                stage.show();
                            } else {
                                JOptionPane.showMessageDialog(null, "bloquer", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "ROLE_ADMIN":
                        try {
                            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                            Parent root = FXMLLoader.load(getClass().getResource("../../Home/Home.fxml"));
                            Scene scene = new Scene(root, 1203, 671);
                            scene.setFill(Color.TRANSPARENT);
                            Stage stage = new Stage();
                            stage.setTitle("Dashboard EL Bank");
                            stage.setScene(scene);
                            stage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    default:
                        break;
                }


            }
        } else {
            JOptionPane.showMessageDialog(null, "Email or password invalid!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //    ************************* CONTROLE DE SAISIE **************************

    private boolean validateEmailExiste(){
        if (pcd.verifierEmailBd(email.getText()) == true){
            return false;
        }else {
            return true;
        }
    }
    private boolean validateEmailForm(){
        if(pcd.email_Validation(email.getText())){
            return true;
        }else {
            return false;
        }
    }
    private boolean validateNom() {
        if (!nom.getText().isEmpty()) {
            if (nom.getText().matches("(.*[a-z].*)")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    private boolean validatePrenom() {
        if (!prenom.getText().isEmpty()) {
            if (prenom.getText().matches("(.*[a-z].*)")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    private boolean validateCin() {
        if (!cin.getText().isEmpty()) {
            if (cin.getText().matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    private boolean validateTelephone() {
        if (!telephone.getText().isEmpty()) {
            if (telephone.getText().matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    private boolean validateAge() {
        if (pcd.calculateAge(Date.valueOf(date_naissance.getValue())) > 18) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validateLongeurPwd() {//String faible moyenne excellent
        if (mot_de_passe.getText().matches("^(?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidPassword(){
        boolean isValid = true;
        if (mot_de_passe.getText().trim().length() > 15 || mot_de_passe.getText().trim().length() < 10 ){
            isValid=false;
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if (!mot_de_passe.getText().matches(upperCaseChars )){
            isValid = false;
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!mot_de_passe.getText().matches(lowerCaseChars ))
        {
            isValid = false;
        }
        String numbers = "(.*[0-9].*)";
        if (!mot_de_passe.getText().matches(numbers ))
        {
            isValid = false;
        }
        String specialChars = "(.*[@,#,$,%].*$)";
        if (!mot_de_passe.getText().matches(specialChars )){
            isValid = false;
        }
        return isValid;
    }

    @javafx.fxml.FXML
    public void On_Click_Inscription(ActionEvent actionEvent) {
        if (validateEmailForm() && validateEmailExiste() && validateNom() && validatePrenom() && validateCin() && validateTelephone() && validateAge() && isValidPassword()){
            Utilisateur U = new Utilisateur();
            U.setCin_u(Integer.parseInt(cin.getText()));
            U.setNom_u(nom.getText());
            U.setPrenom_u(prenom.getText());
            U.setDate_naissance(Date.valueOf(date_naissance.getValue()));
            U.setEmail_u(email.getText());
            U.setNum_tel(Integer.parseInt(telephone.getText()));
            U.setRole("ROLE_USER");
            U.setMot_de_passe(mot_de_passe.getText());

            pcd.ajouterUtilisateur(U);

            refresh();
            JOptionPane.showMessageDialog(null, "vous avez inscrit avec success");

        }else {
            if (!validateEmailForm()){
                email.setText("-From Email Incorrecte !");
                email.setStyle("-fx-border-color: #f00020");
            }else {
                if (!validateEmailExiste()){
                    email.setStyle("-fx-border-color: #f00020");
                    email.setText("-Email déja utilisé !");
                }else {
                    email.setStyle("-fx-border-color: #22780F");
                }
            }
            if (!validateNom()){
                nom.setStyle("-fx-border-color: #f00020");
                nom.setText("-Nom invalide !");
            }else {
                nom.setStyle("-fx-border-color: #22780F");
            }
            if (!validatePrenom()){
                prenom.setStyle("-fx-border-color: #f00020");
                prenom.setText("-Prenom invalide !");
            }else {
                prenom.setStyle("-fx-border-color: #22780F");
            }
            if (!validateCin()){
                cin.setStyle("-fx-border-color: #f00020");
                cin.setText("-Cin invalide !");
            }else {
                cin.setStyle("-fx-border-color: #22780F");
            }
            if (!validateTelephone()){
                telephone.setStyle("-fx-border-color: #f00020");
                telephone.setText("-Numéro télephone invalide !");
            }else {
                telephone.setStyle("-fx-border-color: #22780F");
            }
            if (!validateAge()){
                date_naissance.setStyle("-fx-border-color: #f00020");
                age.setStyle("-fx-border-color: #f00020");
                age.setText("L'age minimum est 18 ans");
            }else {
                date_naissance.setStyle("-fx-border-color: #22780F");
                age.setStyle("-fx-border-color: #22780F");
            }
            if (!isValidPassword()){
                mot_de_passe.setStyle("-fx-border-color: #f00020");
                if (mot_de_passe.getText().trim().length() > 15 || mot_de_passe.getText().trim().length() < 10 ){
                    labelMdp.setText("Password must be less than 15 and more than 10");
                }
                String upperCaseChars = "(.*[A-Z].*)";
                if (!mot_de_passe.getText().matches(upperCaseChars )){
                    labelMdp.setText("Password must have atleast one uppercase character");
                }
                String lowerCaseChars = "(.*[a-z].*)";
                if (!mot_de_passe.getText().matches(lowerCaseChars )){
                    labelMdp.setText("Password must have atleast one lowercase character");
                }
                String numbers = "(.*[0-9].*)";
                if (!mot_de_passe.getText().matches(numbers ))
                {
                    labelMdp.setText("Password must have atleast one number");
                }
                String specialChars = "(.*[@,#,$,%].*$)";
                if (!mot_de_passe.getText().matches(specialChars ))
                {
                    labelMdp.setText("Password must have atleast one special character among @#$%");
                }
            }else {
                mot_de_passe.setStyle("-fx-border-color: #22780F");
                labelMdp.setVisible(false);
            }

        }


    }
}
