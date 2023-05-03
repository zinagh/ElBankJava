package gui.utilisateur;

import com.itextpdf.text.DocumentException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import entities.Utilisateur;
import services.UtilisateurCRUD;
import tools.MyPdf;

import javax.swing.*;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class UtilisateurBackController {
    UtilisateurCRUD pcd = new UtilisateurCRUD();
    @javafx.fxml.FXML
    private TableColumn email_B;
    @javafx.fxml.FXML
    private TableColumn telephone_B;
    @javafx.fxml.FXML
    private TableView<Utilisateur> TableViewBackUser;
    @javafx.fxml.FXML
    private TableColumn prenom_B;

    @javafx.fxml.FXML
    private TableColumn date_naissance_B;
    @javafx.fxml.FXML
    private TableColumn nom_B;
    @javafx.fxml.FXML
    private TableColumn role_B;
    @javafx.fxml.FXML
    private TableColumn cin_B;
    @javafx.fxml.FXML
    private TextField cin_Ajout_B;
    @javafx.fxml.FXML
    private TextField prenom_Ajout_B;
    @javafx.fxml.FXML
    private TextField role_Ajout_B;
    @javafx.fxml.FXML
    private TextField telephone_Ajout_B;
    @javafx.fxml.FXML
    private TextField nom_Ajout_B;
    @javafx.fxml.FXML
    private TextField email_Ajout_B;
    @javafx.fxml.FXML
    private PasswordField mdp_Ajout_B;
    @javafx.fxml.FXML
    private DatePicker date_Ajout_B;
    @javafx.fxml.FXML
    private Button Ajouter_utilisateur_B;
    @javafx.fxml.FXML
    private Button ModifierUtilisateur;
    @javafx.fxml.FXML
    private Button Imprimer_utilisateur;
    @javafx.fxml.FXML
    private Button SupprimerUtilisateur1;
    int idModifier;
    String emailModif;
    Utilisateur U = new Utilisateur();
    @javafx.fxml.FXML
    private Button btnAcceuilBack;
    @javafx.fxml.FXML
    private Button btnResetUtilisateurBack;
    @javafx.fxml.FXML
    private Button btnChequesBack;
    @javafx.fxml.FXML
    private Button btnCreditsBack;
    @javafx.fxml.FXML
    private AnchorPane ContainerContenuBack;
    @javafx.fxml.FXML
    private Button ComptesBack;
    @javafx.fxml.FXML
    private Button pubBack;
    @javafx.fxml.FXML
    private Button TransactionsBack;
    @javafx.fxml.FXML
    private Button btnCartesBack;
    @javafx.fxml.FXML
    private Button btnReclamationsBack;
    @javafx.fxml.FXML
    private Button btnChequiersBack;
    @javafx.fxml.FXML
    private Button btnLogoutBack;
    @javafx.fxml.FXML
    private Label age;
    @javafx.fxml.FXML
    private Label controleMdpBack;
    @javafx.fxml.FXML
    private TextField rechercheAvance;
    ObservableList<Utilisateur> data=FXCollections.observableArrayList();
    @javafx.fxml.FXML
    private Button btnCommentaires;

    public void setAge(String age) {
        this.age.setText(String.valueOf(age));
    }

    public void initialize() {
        date_Ajout_B.valueProperty().addListener((ov, oldValue, newValue) -> {
            UtilisateurCRUD pcd = new UtilisateurCRUD();
            int a = pcd.calculateAge(Date.valueOf(newValue));
            age.setText(String.valueOf(a));
            setAge("votre age est :"+String.valueOf(a)+" Ans");
        });
        afficherUtilisateurBack();
//        recherche_avance();
    }
    public void afficherUtilisateurBack(){
        ObservableList<Utilisateur> data= FXCollections.observableArrayList();

        data.clear();
        for (Utilisateur utilisateur : data = FXCollections.observableArrayList(pcd.afficherUtilisateur())) {
            cin_B.setCellValueFactory(new PropertyValueFactory<>("cin_u"));
            nom_B.setCellValueFactory(new PropertyValueFactory<>("nom_u"));
            prenom_B.setCellValueFactory(new PropertyValueFactory<>("prenom_u"));
            date_naissance_B.setCellValueFactory(new PropertyValueFactory<>("date_naissance"));
            telephone_B.setCellValueFactory(new PropertyValueFactory<>("num_tel"));
            email_B.setCellValueFactory(new PropertyValueFactory<>("email_u"));
            role_B.setCellValueFactory(new PropertyValueFactory<>("role"));
            TableViewBackUser.setItems((ObservableList) data);
        }
        addEtatColumn();

        FilteredList<Utilisateur> filtereddata=new FilteredList<>(data, b->true);
        rechercheAvance.textProperty().addListener((observable,oldvalue,newValue) -> {
            System.out.println("anavt filter  data");
            filtereddata.setPredicate(user->{
                System.out.println("apresfilterdatea");
                if(newValue==null||newValue.isEmpty()){
                    return true;
                }
                String lowercasefilter=newValue.toLowerCase();
                if(user.getNom_u().toLowerCase().indexOf(lowercasefilter)!=-1){
                    System.out.println("getnomtrue");
                    return true;
                }
                else if(user.getPrenom_u().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(user.getEmail_u().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(String.valueOf(user.getNum_tel()).indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(user.getRole().toString().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(user.getDate_naissance().toString().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else{
                    System.out.println("false");
                    return false;
                }
            });
            System.out.println("avnat recherche");
        });
        System.out.println("apres recherche");
        TableViewBackUser.setItems(filtereddata);
    }

    public void refresh(){
        cin_Ajout_B.setText("");
        nom_Ajout_B.setText("");
        prenom_Ajout_B.setText("");
        date_Ajout_B.setValue(null);
        email_Ajout_B.setText("");
        telephone_Ajout_B.setText("");
        mdp_Ajout_B.setText("");
        role_Ajout_B.setText("");
        age.setText("");

        cin_Ajout_B.setStyle("-fx-border-color: #B0C4DE ; -fx-border-width: 1.5");
        nom_Ajout_B.setStyle("-fx-border-color: #B0C4DE ; -fx-border-width: 1.5");
        prenom_Ajout_B.setStyle("-fx-border-color: #B0C4DE ; -fx-border-width: 1.5");
        date_Ajout_B.setStyle("-fx-border-color: #B0C4DE ; -fx-border-width: 1.5");
        email_Ajout_B.setStyle("-fx-border-color: #B0C4DE ; -fx-border-width: 1.5");
        telephone_Ajout_B.setStyle("-fx-border-color: #B0C4DE ; -fx-border-width: 1.5");
        mdp_Ajout_B.setStyle("-fx-border-color: #B0C4DE ; -fx-border-width: 1.5");
        role_Ajout_B.setStyle("-fx-border-color: #B0C4DE ; -fx-border-width: 1.5");
        age.setStyle("-fx-border-color: #B0C4DE ; -fx-border-width: 1.5");

        afficherUtilisateurBack();

    }
    //    ************************* CONTROLE DE SAISIE **************************

    private boolean validateEmailExiste(){
        if (pcd.verifierEmailBd(email_Ajout_B.getText()) == true){
            return false;
        }else {
            return true;
        }
    }
    private boolean validateEmailForm(){
        if(pcd.email_Validation(email_Ajout_B.getText())){
            return true;
        }else {
            return false;
        }
    }
    private boolean validateNom() {
        if (!nom_Ajout_B.getText().isEmpty()) {
            if (nom_Ajout_B.getText().matches("(.*[a-z].*)")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    private boolean validatePrenom() {
        if (!prenom_Ajout_B.getText().isEmpty()) {
            if (prenom_Ajout_B.getText().matches("(.*[a-z].*)")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    private boolean validateCin() {
        if (!cin_Ajout_B.getText().isEmpty()) {
            if (cin_Ajout_B.getText().matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    private boolean validateTelephone() {
        if (!telephone_Ajout_B.getText().isEmpty()) {
            if (telephone_Ajout_B.getText().matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    private boolean validateAge() {
        if (pcd.calculateAge(Date.valueOf(date_Ajout_B.getValue())) > 18) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validateLongeurPwd() {//String faible moyenne excellent
        if (mdp_Ajout_B.getText().matches("^(?=.*\\\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidPassword(){
        boolean isValid = true;
        if (mdp_Ajout_B.getText().trim().length() > 15 || mdp_Ajout_B.getText().trim().length() < 10 ){
            isValid=false;
        }
        String upperCaseChars = "(.*[A-Z].*)";
        if (!mdp_Ajout_B.getText().matches(upperCaseChars )){
            isValid = false;
        }
        String lowerCaseChars = "(.*[a-z].*)";
        if (!mdp_Ajout_B.getText().matches(lowerCaseChars ))
        {
            isValid = false;
        }
        String numbers = "(.*[0-9].*)";
        if (!mdp_Ajout_B.getText().matches(numbers ))
        {
            isValid = false;
        }
        String specialChars = "(.*[@,#,$,%].*$)";
        if (!mdp_Ajout_B.getText().matches(specialChars )){
            isValid = false;
        }
        return isValid;
    }


    @javafx.fxml.FXML
    public void On_Click_AjouterUtilisateur(ActionEvent actionEvent) {
        if (validateEmailForm() && validateEmailExiste() && validateNom() && validatePrenom() && validateCin() && validateTelephone() && validateAge() && isValidPassword()) {
            Utilisateur U = new Utilisateur();
            U.setCin_u(Integer.parseInt(cin_Ajout_B.getText()));
            U.setNom_u(nom_Ajout_B.getText());
            U.setPrenom_u(prenom_Ajout_B.getText());
            U.setDate_naissance(Date.valueOf(date_Ajout_B.getValue()));
            U.setEmail_u(email_Ajout_B.getText());
            U.setNum_tel(Integer.parseInt(telephone_Ajout_B.getText()));
            //U.setRole("ROLE_CLIENT");
            U.setRole(role_Ajout_B.getText());
            U.setMot_de_passe(mdp_Ajout_B.getText());
            pcd.ajouterUtilisateur(U);
            JOptionPane.showMessageDialog(null, "vous ajouter un utilisateur avec success");
            refresh();
        }else {
            if (!validateEmailForm()){
                email_Ajout_B.setText("-From Email Incorrecte !");
                email_Ajout_B.setStyle("-fx-border-color: #f00020");
            }else {
                if (!validateEmailExiste()){
                    email_Ajout_B.setStyle("-fx-border-color: #f00020");
                    email_Ajout_B.setText("-Email déja utilisé !");
                }else {
                    email_Ajout_B.setStyle("-fx-border-color: #22780F");
                }
            }
            if (!validateNom()){
                nom_Ajout_B.setStyle("-fx-border-color: #f00020");
                nom_Ajout_B.setText("-Nom invalide !");
            }else {
                nom_Ajout_B.setStyle("-fx-border-color: #22780F");
            }
            if (!validatePrenom()){
                prenom_Ajout_B.setStyle("-fx-border-color: #f00020");
                prenom_Ajout_B.setText("-Prenom invalide !");
            }else {
                prenom_Ajout_B.setStyle("-fx-border-color: #22780F");
            }
            if (!validateCin()){
                cin_Ajout_B.setStyle("-fx-border-color: #f00020");
                cin_Ajout_B.setText("-Cin invalide !");
            }else {
                cin_Ajout_B.setStyle("-fx-border-color: #22780F");
            }
            if (!validateTelephone()){
                telephone_Ajout_B.setStyle("-fx-border-color: #f00020");
                telephone_Ajout_B.setText("-Numéro télephone invalide !");
            }else {
                telephone_Ajout_B.setStyle("-fx-border-color: #22780F");
            }
            if (!validateAge()){
                date_Ajout_B.setStyle("-fx-border-color: #f00020");
                age.setStyle("-fx-border-color: #f00020");
                age.setText("L'age minimum est 18 ans");
            }else {
                date_Ajout_B.setStyle("-fx-border-color: #22780F");
                age.setStyle("-fx-border-color: #22780F");
            }
            if (!isValidPassword()){
                mdp_Ajout_B.setStyle("-fx-border-color: #f00020");
                if (mdp_Ajout_B.getText().trim().length() > 15 || mdp_Ajout_B.getText().trim().length() < 10 ){
                    controleMdpBack.setText("Password must be less than 15 and more than 10");
                }
                String upperCaseChars = "(.*[A-Z].*)";
                if (!mdp_Ajout_B.getText().matches(upperCaseChars )){
                    controleMdpBack.setText("Password must have atleast one uppercase character");
                }
                String lowerCaseChars = "(.*[a-z].*)";
                if (!mdp_Ajout_B.getText().matches(lowerCaseChars )){
                    controleMdpBack.setText("Password must have atleast one lowercase character");
                }
                String numbers = "(.*[0-9].*)";
                if (!mdp_Ajout_B.getText().matches(numbers ))
                {
                    controleMdpBack.setText("Password must have atleast one number");
                }
                String specialChars = "(.*[@,#,$,%].*$)";
                if (!mdp_Ajout_B.getText().matches(specialChars ))
                {
                    controleMdpBack.setText("Password must have atleast one special character among @#$%");
                }
            }else {
                mdp_Ajout_B.setStyle("-fx-border-color: #22780F");
                controleMdpBack.setVisible(false);
            }
        }

    }

    private void addEtatColumn() {
        TableColumn<Utilisateur, Void> colBtn = new TableColumn("Etat");

        Callback<TableColumn<Utilisateur, Void>, TableCell<Utilisateur, Void>> cellFactory = new Callback<TableColumn<Utilisateur, Void>, TableCell<Utilisateur, Void>>() {

            public TableCell<Utilisateur, Void> call(final TableColumn<Utilisateur, Void> param) {
                final TableCell<Utilisateur, Void> cell = new TableCell<Utilisateur, Void>() {
                    ComboBox combEtat = new ComboBox();

                    {
                        combEtat.getItems().add("Bloquer");
                        combEtat.getItems().add("Debloquer");
                        combEtat.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                            Utilisateur data = getTableView().getItems().get(getIndex());
                            data.setEtat((String) newValue);
                            if((String) newValue == "Bloquer"){
                                data.setBloquer( (String) newValue);
                            }else{
                                data.setBloquer(null);
                            }
                            System.out.println(data.getBloquer());
                            UtilisateurCRUD.UpdatePersonne(data);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Utilisateur data = getTableView().getItems().get(getIndex());
                            combEtat.setValue(data.getEtat());
                            setGraphic(combEtat);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        TableViewBackUser.getColumns().add(colBtn);

    }

    @javafx.fxml.FXML
    public void On_Click_ModifierU(ActionEvent actionEvent) {
        if (validateEmailForm() && validateNom() && validatePrenom() && validateCin() && validateTelephone() && validateAge() && isValidPassword()) {
            U.setCin_u(Integer.parseInt(cin_Ajout_B.getText()));
            U.setNom_u(nom_Ajout_B.getText());
            U.setPrenom_u(prenom_Ajout_B.getText());
            U.setDate_naissance(Date.valueOf(date_Ajout_B.getValue()));
            U.setEmail_u(email_Ajout_B.getText());
            U.setNum_tel(Integer.parseInt(telephone_Ajout_B.getText()));
            //U.setRole("ROLE_CLIENT");
            U.setRole(role_Ajout_B.getText());
            U.setMot_de_passe(mdp_Ajout_B.getText());
            UtilisateurCRUD pcd =  new UtilisateurCRUD();
            pcd.modifierUtlisateur(U,idModifier);
            JOptionPane.showMessageDialog(null, "Modification effectué avec success");
            initialize();
            refresh();
        }else {
            if (!validateEmailForm()){
                email_Ajout_B.setText("-From Email Incorrecte !");
                email_Ajout_B.setStyle("-fx-border-color: #f00020");
            }else {
                email_Ajout_B.setStyle("-fx-border-color: #22780F");
            }
            if (!validateNom()){
                nom_Ajout_B.setStyle("-fx-border-color: #f00020");
                nom_Ajout_B.setText("-Nom invalide !");
            }else {
                nom_Ajout_B.setStyle("-fx-border-color: #22780F");
            }
            if (!validatePrenom()){
                prenom_Ajout_B.setStyle("-fx-border-color: #f00020");
                prenom_Ajout_B.setText("-Prenom invalide !");
            }else {
                prenom_Ajout_B.setStyle("-fx-border-color: #22780F");
            }
            if (!validateCin()){
                cin_Ajout_B.setStyle("-fx-border-color: #f00020");
                cin_Ajout_B.setText("-Cin invalide !");
            }else {
                cin_Ajout_B.setStyle("-fx-border-color: #22780F");
            }
            if (!validateTelephone()){
                telephone_Ajout_B.setStyle("-fx-border-color: #f00020");
                telephone_Ajout_B.setText("-Numéro télephone invalide !");
            }else {
                telephone_Ajout_B.setStyle("-fx-border-color: #22780F");
            }
            if (!validateAge()){
                date_Ajout_B.setStyle("-fx-border-color: #f00020");
                age.setStyle("-fx-border-color: #f00020");
                age.setText("L'age minimum est 18 ans");
            }else {
                date_Ajout_B.setStyle("-fx-border-color: #22780F");
                age.setStyle("-fx-border-color: #22780F");
            }
            if (!isValidPassword()){
                mdp_Ajout_B.setStyle("-fx-border-color: #f00020");
                if (mdp_Ajout_B.getText().trim().length() > 15 || mdp_Ajout_B.getText().trim().length() < 10 ){
                    controleMdpBack.setText("Password must be less than 15 and more than 10");
                }
                String upperCaseChars = "(.*[A-Z].*)";
                if (!mdp_Ajout_B.getText().matches(upperCaseChars )){
                    controleMdpBack.setText("Password must have atleast one uppercase character");
                }
                String lowerCaseChars = "(.*[a-z].*)";
                if (!mdp_Ajout_B.getText().matches(lowerCaseChars )){
                    controleMdpBack.setText("Password must have atleast one lowercase character");
                }
                String numbers = "(.*[0-9].*)";
                if (!mdp_Ajout_B.getText().matches(numbers ))
                {
                    controleMdpBack.setText("Password must have atleast one number");
                }
                String specialChars = "(.*[@,#,$,%].*$)";
                if (!mdp_Ajout_B.getText().matches(specialChars ))
                {
                    controleMdpBack.setText("Password must have atleast one special character among @#$%");
                }
            }else {
                mdp_Ajout_B.setStyle("-fx-border-color: #22780F");
                controleMdpBack.setVisible(false);
            }
        }
    }

    @javafx.fxml.FXML
    public void On_Click_SupprimerU(ActionEvent actionEvent) {
        pcd.supprimerUtilisateur(idModifier);
        refresh();
    }
    @javafx.fxml.FXML
    public void On_Click_Imprimer(ActionEvent actionEvent) {
        List<Utilisateur> userList = pcd.afficherUtilisateur();
        try {
            MyPdf.generate(userList , "utilisateurs.pdf");
            System.out.println("PDF file created successfully.");
        } catch (IOException | DocumentException e) {
            System.out.println("Failed to generate PDF file: " + e.getMessage());
        }
    }
    @javafx.fxml.FXML
    public void On_Click_TableViewUser(Event event) {
        String email =TableViewBackUser.getSelectionModel().getSelectedItem().getEmail_u();
        Utilisateur user= pcd.findByMail(email);
        idModifier=user.getId();

        cin_Ajout_B.setText(String.valueOf(user.getCin_u()));
        nom_Ajout_B.setText(user.getNom_u());
        prenom_Ajout_B.setText(user.getPrenom_u());
        date_Ajout_B.setValue(LocalDate.parse(String.valueOf(user.getDate_naissance())));
        email_Ajout_B.setText(user.getEmail_u());
        telephone_Ajout_B.setText(String.valueOf(user.getNum_tel()));
        role_Ajout_B.setText(user.getRole());
        //mdp_Ajout_B.setText(user.getMot_de_passe());
        emailModif= user.getEmail_u();
    }

    @javafx.fxml.FXML
    public void versChequiersBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les chequiers ");
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
    public void resetUtilisateurBack(ActionEvent actionEvent) {
        refresh();
    }

    @javafx.fxml.FXML
    public void versComptesBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Transaction");
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
    public void versChequesBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les chèques ");
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
            stage.setTitle("Les cartes ");
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
    public void versLogoutBack(ActionEvent actionEvent) {
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
    public void versReclamationsBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les Reclamations");
            Parent root = FXMLLoader.load(getClass().getResource("../Reclamations/ReclamationBack.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void recherche_avance(){
        FilteredList<Utilisateur> filtereddata=new FilteredList<>(data, b->true);
        rechercheAvance.textProperty().addListener((observable,oldvalue,newValue) -> {
            System.out.println("anavt filter  data");
            filtereddata.setPredicate(user->{
                System.out.println("apresfilterdatea");
                if(newValue==null||newValue.isEmpty()){
                    return true;
                }
                String lowercasefilter=newValue.toLowerCase();
                if(user.getNom_u().toLowerCase().indexOf(lowercasefilter)!=-1){
                    System.out.println("getnomtrue");
                    return true;
                }
                else if(user.getPrenom_u().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(user.getEmail_u().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(String.valueOf(user.getNum_tel()).indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(user.getRole().toString().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else if(user.getDate_naissance().toString().toLowerCase().indexOf(lowercasefilter)!=-1){
                    return true;
                }
                else{
                    System.out.println("false");
                    return false;
                }
            });
            System.out.println("avnat recherche");
        });
        System.out.println("apres recherche");
        TableViewBackUser.setItems(filtereddata);
    }




    @javafx.fxml.FXML
    public void versTransactionsBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Transaction");
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










































}
