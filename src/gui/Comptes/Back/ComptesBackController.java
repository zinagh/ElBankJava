package gui.Comptes.Back;

import entities.Compte;
import entities.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.CompteController;
import services.TransactionController;
import services.UtilisateurCRUD;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class ComptesBackController implements Initializable {
    public ObservableList<Compte> listCompte = FXCollections.observableArrayList();
    public ObservableList<String> rechercheCompte = FXCollections.observableArrayList();
    public ObservableList<String> namesAjout = FXCollections.observableArrayList();
    @FXML
    private TableColumn AffichedateCompteBack;
    @FXML
    private TableColumn AfficheRIBCompteBack;
    @FXML
    private TableColumn AffichenumCompteBack;
    @FXML
    private TableColumn AfficheEtatCompteBack;
    @FXML
    private TableColumn AffichetypeCompteBack;
    @FXML
    private TableColumn AffichetauxCompteBack;
    @FXML
    private TableColumn AffichenomClientCompteBack;
    @FXML
    private TableColumn AffichesoldeCompteBack;
    @FXML
    private TableColumn AfficheseuilCompteBack;
    @FXML
    private TableView TableauCompteBack;

    @FXML
    private Button ComptesBack;

    @FXML
    private AnchorPane ContainerContenuBack;


    @FXML
    private Button TransactionsBack;

    @FXML
    private Button btnAcceuilBack;

    @FXML
    private Button btnAjouterCompteBack;

    @FXML
    private Button btnCartesBack;

    @FXML
    private Button btnChequesBack;

    @FXML
    private Button btnChequiersBack;

    @FXML
    private Button btnCreditsBack;

    @FXML
    private Button btnLogoutBack;

    @FXML
    private Button btnModifierCompteBack;

    @FXML
    private Button btnRechercheBack;

    @FXML
    private Button btnReclamationsBack;

    @FXML
    private Button btnResetCompteBack;

    @FXML
    private Button btnSupprimerCompteBack;

    @FXML
    private Button btnUtilisateursBack;

    @FXML
    private Label controleClientCompteBack;

    @FXML
    private Label controleInteretCompteBack;

    @FXML
    private Label controleSeuilCompteBack;

    @FXML
    private Label controleSoldeCompteBack;

    @FXML
    private Label controleTypeCompteBack;

    @FXML
    private TextField interetBack;

    @FXML
    private Label labelInteretCompteBack;

    @FXML
    private Label labelNomClientCompteBack;

    @FXML
    private Label labelSeuilCompteBack;

    @FXML
    private Label labelSoldeCompteBack;

    @FXML
    private Label labelTypeCompteBack;

    @FXML
    private ComboBox nomClientBack;

    @FXML
    private ComboBox rechercheCompteBack;

    @FXML
    private TextField seuilBack;

    @FXML
    private TextField soldeBack;

    @FXML
    private ComboBox typeCompteBack;




    @FXML
    public void afficherComptes() {
        CompteController comptCont = new CompteController();
        List<Compte> comptes = comptCont.afficherComptes();
        if (!comptes.isEmpty()) {
            for (int i = 0; i < comptes.size(); i++) {
                listCompte.add(comptes.get(i));
            }
        }

        addEtatColumn();

        AffichedateCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, Integer>("date_creation"));
        AfficheRIBCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("RIB_Compte"));
        AffichenumCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("num_compte"));
        AfficheEtatCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("etat_compte"));
        AffichetypeCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("type_compte"));
        AffichetauxCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("taux_interet"));
        AffichenomClientCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("fullname"));
        AffichesoldeCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("solde_compte"));
        AfficheseuilCompteBack.setCellValueFactory(new PropertyValueFactory<Compte, String>("seuil_compte"));
        TableauCompteBack.setItems(listCompte);
    }

    private void addEtatColumn() {
        TableColumn<Compte, Void> colBtn = new TableColumn("Etat Compte");

        Callback<TableColumn<Compte, Void>, TableCell<Compte, Void>> cellFactory = new Callback<TableColumn<Compte, Void>, TableCell<Compte, Void>>() {

            public TableCell<Compte, Void> call(final TableColumn<Compte, Void> param) {
                final TableCell<Compte, Void> cell = new TableCell<Compte, Void>() {
                    ComboBox combEtat = new ComboBox();

                    {
                        combEtat.getItems().add("Activé");
                        combEtat.getItems().add("Désactivé");
                        combEtat.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                            Compte data = getTableView().getItems().get(getIndex());
                            if(newValue == "Activé"){
                                data.setEtat_compte(1);
                            }else{
                                data.setEtat_compte(2);
                            }
                            CompteController.modifierCompte(data, data.getId());
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Compte data = getTableView().getItems().get(getIndex());
                            if(data.getEtat_compte() == 1){
                                combEtat.setValue("Activé");
                            }else if(data.getEtat_compte() == 2){
                                combEtat.setValue("Désactivé");
                            }else if (data.getEtat_compte() == 0){
                                combEtat.getItems().add("En cours d'activation");
                                combEtat.setValue("En cours d'activation");
                            }
                            setGraphic(combEtat);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        TableauCompteBack.getColumns().add(colBtn);
    }
    @FXML
    public void fillCombobox() {
        CompteController comptCont = new CompteController();
//        Combobox Recherche
        List<String> noms = comptCont.afficherNomClientComptes();
        if (!noms.isEmpty()) {
            for (int i = 0; i < noms.size(); i++) {
                rechercheCompte.add(noms.get(i));
            }
        }
        rechercheCompteBack.setItems(rechercheCompte);
        rechercheCompteBack.getSelectionModel().selectFirst();

//        Combobox Ajout
        UtilisateurCRUD userCont = new UtilisateurCRUD();

        List<String> names = userCont.afficherFullnameUtilisateur();
        if (!names.isEmpty()) {
            for (int i = 0; i < names.size(); i++) {
                namesAjout.add(names.get(i));
            }
        }

        nomClientBack.setItems(namesAjout);

        nomClientBack.getSelectionModel().selectFirst();
        typeCompteBack.getSelectionModel().selectFirst();
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        afficherComptes();
        fillCombobox();
        clearFields();

        nomClientBack.setStyle(null);
        controleClientCompteBack.setVisible(false);
        typeCompteBack.setStyle(null);
        controleTypeCompteBack.setVisible(false);
        interetBack.setStyle(null);
        controleInteretCompteBack.setVisible(false);
        soldeBack.setStyle(null);
        controleSoldeCompteBack.setVisible(false);
        seuilBack.setStyle(null);
        controleSeuilCompteBack.setVisible(false);

    }

    @FXML
    void clearFields() {
        interetBack.clear();
        seuilBack.clear();
        soldeBack.clear();
        nomClientBack.getSelectionModel().selectFirst();
        typeCompteBack.getSelectionModel().selectFirst();
    }

    @FXML
    void ajouterCompteBack(ActionEvent event) {
        CompteController comptCont = new CompteController();
        UtilisateurCRUD user = new UtilisateurCRUD();

            String nom = nomClientBack.getSelectionModel().getSelectedItem().toString();

            int idClient = user.getIdFromFullname(nom).get(0).getId();

            Float solde = Float.valueOf(soldeBack.getText());
            Float seuil = Float.valueOf(seuilBack.getText());
            Float interet = Float.valueOf(interetBack.getText());
            String type = typeCompteBack.getSelectionModel().getSelectedItem().toString();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            long numCompte = comptCont.GenererNumCompte();
            Compte cmpt = new Compte(numCompte, "123451234" + numCompte + "12", solde, dtf.format(now), type, seuil, interet, idClient, 0);
            comptCont.ajouterCompte2(cmpt);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Gestion des comptes bancaires");
            alert.setHeaderText(null);
            alert.setContentText("Compte Bancaire ajouté avec succès !");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("../../../assets/Images/logo-Final.png"));
            alert.showAndWait();
            TableauCompteBack.getItems().clear();
            afficherComptes();
            clearFields();

            nomClientBack.setStyle(null);
            seuilBack.setStyle(null);
            soldeBack.setStyle(null);
            interetBack.setStyle(null);
            typeCompteBack.setStyle(null);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ComptesBack.fxml"));
            Parent root = loader.load();
            TableauCompteBack.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        initialize(null, null);


    }

    @FXML
    void calculSeuil(KeyEvent event) {

    }

    @FXML
    void doubleClickCompteBack(MouseEvent event) {

    }

    @FXML
    void modifierCompteBack(ActionEvent event) {
        if(!TableauCompteBack.getSelectionModel().getSelectedCells().isEmpty()) {

            CompteController comptCont = new CompteController();
            UtilisateurCRUD user = new UtilisateurCRUD();


                Compte cmpt = (Compte) TableauCompteBack.getSelectionModel().getSelectedItem();
            nomClientBack.setValue(cmpt.getFullname());
            soldeBack.setText(String.valueOf(cmpt.getSolde_compte()));
            seuilBack.setText(String.valueOf(cmpt.getSeuil_compte()));
            interetBack.setText(String.valueOf(cmpt.getTaux_interet()));
            typeCompteBack.setValue(cmpt.getType_compte());

                Float solde = Float.valueOf(soldeBack.getText());
                Float seuil = Float.valueOf(seuilBack.getText());
                Float interet = Float.valueOf(interetBack.getText());
                String type = typeCompteBack.getSelectionModel().getSelectedItem().toString();

                cmpt.setSolde_compte(solde);
                cmpt.setSeuil_compte(seuil);
                cmpt.setTaux_interet(interet);
                cmpt.setType_compte(type);

                comptCont.modifierCompte(cmpt, cmpt.getId());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Gestion des comptes bancaires");
                alert.setHeaderText(null);
                alert.setContentText("Compte Bancaire modifié avec succès !");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image("../../../assets/Images/logo-Final.png"));
                alert.showAndWait();

                TableauCompteBack.getItems().clear();

                afficherComptes();
                nomClientBack.setDisable(false);
                clearFields();

                nomClientBack.setStyle(null);
                seuilBack.setStyle(null);
                soldeBack.setStyle(null);
                interetBack.setStyle(null);
                typeCompteBack.setStyle(null);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Gestion des comptes bancaires");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner le compte bancaire à modifier !");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image("../../../assets/Images/logo-Final.png"));
            alert.showAndWait();
        }
    }

    @FXML
    void rechercherCompteBack(ActionEvent event) {

    }

    @FXML
    void resetCompteBack(ActionEvent event) {
        TableauCompteBack.getItems().clear();
        rechercheCompteBack.getItems().clear();
        nomClientBack.getItems().clear();

        nomClientBack.setDisable(false);

        clearFields();
        fillCombobox();
        afficherComptes();

        nomClientBack.setStyle(null);
        controleClientCompteBack.setVisible(false);
        typeCompteBack.setStyle(null);
        controleTypeCompteBack.setVisible(false);
        interetBack.setStyle(null);
        controleInteretCompteBack.setVisible(false);
        soldeBack.setStyle(null);
        controleSoldeCompteBack.setVisible(false);
        seuilBack.setStyle(null);
        controleSeuilCompteBack.setVisible(false);
    }

    @FXML
    void supprimerCompteBack(ActionEvent event) {
        if (!TableauCompteBack.getSelectionModel().getSelectedCells().isEmpty()) {

            CompteController cmpt = new CompteController();
            TransactionController transCont = new TransactionController();

            TablePosition pos = (TablePosition) TableauCompteBack.getSelectionModel().getSelectedCells().get(0);
            int index = pos.getRow();
            String selected = TableauCompteBack.getItems().get(index).toString();
            selected = selected.substring(7, selected.indexOf(" : "));
            Compte c = cmpt.rechercherCompte(Integer.parseInt(selected));

            List<Transaction> trans = transCont.SuppressionCompteTransaction(c.getId());
            System.out.println(trans);
            if (trans.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Gestion des comptes bancaires");
                alert.setHeaderText(null);
                alert.setContentText("Voulez-vous supprimer le compte de " + c.getFullname() + " ?");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                ButtonType okButton = new ButtonType("Oui", ButtonBar.ButtonData.YES);
                ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(okButton, cancelButton);
                String finalSelected = selected;
                alert.showAndWait().ifPresent(type -> {
                    if (type == okButton) {
                        cmpt.supprimerCompte(Integer.parseInt(finalSelected));

                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Gestion des comptes bancaires");
                        alert2.setHeaderText(null);
                        alert2.setContentText("Compte Bancaire supprimé avec succès !");
                        Stage stage2 = (Stage) alert2.getDialogPane().getScene().getWindow();
                        alert2.showAndWait();

                        TableauCompteBack.getItems().removeAll(TableauCompteBack.getSelectionModel().getSelectedItem());
                    }
                });
            } else {
                System.out.println("lkjhlkcdsjhmlsqkjhlkqjshdkjhdsqblksqdjhkvgqdxnmlkXLSJHVGDCKBLXNQMW");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Gestion des comptes bancaires");
                alert.setHeaderText(null);
                alert.setContentText("Ce compte a déjà effectué des transactions. Voulez vous supprimer le compte et ses transactions ?");
                Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
                ButtonType okButton = new ButtonType("Oui", ButtonBar.ButtonData.YES);
                ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(okButton, cancelButton);
                String finalSelected = selected;
                alert.showAndWait().ifPresent(type -> {
                    if (type == okButton) {
                        for (int i = 0; i < trans.size(); i++) {
                            transCont.supprimerTransaction(trans.get(i).getId());
                        }
                        cmpt.supprimerCompte(Integer.parseInt(finalSelected));

                        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                        alert2.setTitle("Gestion des comptes bancaires");
                        alert2.setHeaderText(null);
                        alert2.setContentText("Compte Bancaire et transactions supprimés avec succès !");
                        Stage stage2 = (Stage) alert2.getDialogPane().getScene().getWindow();
                        alert2.showAndWait();

                        TableauCompteBack.getItems().removeAll(TableauCompteBack.getSelectionModel().getSelectedItem());
                    }
                });
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Gestion des comptes bancaires");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner le compte bancaire à supprimer !");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            alert.showAndWait();

        }
    }

    @FXML
    void versAccueilBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Acceuil Admin");
            Parent root = FXMLLoader.load(getClass().getResource("../../../Home/Home.fxml"));

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
    void versCartesBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Acceuil Admin");
            Parent root = FXMLLoader.load(getClass().getResource("../../carte/AjoutCarte.fxml"));

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
    void versChequesBack(ActionEvent event) {

    }

    @FXML
    void versChequiersBack(ActionEvent event) {

    }

    @FXML
    void versCreditsBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Acceuil Admin");
            Parent root = FXMLLoader.load(getClass().getResource("../../credits/Credit.fxml"));

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
    void versLogoutBack(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Autentification");
            Parent root = FXMLLoader.load(getClass().getResource("../../utilisateur/Autentification.fxml"));

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
    void versReclamationsBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Autentification");
            Parent root = FXMLLoader.load(getClass().getResource("../../Reclamations/ReclamationBack.fxml"));

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
    void versTransactionsBack(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Transactions ");
            Parent root = FXMLLoader.load(getClass().getResource("../../Transactions/Back/TransactionsBack.fxml"));

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
    void versUtilisateursBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Autentification");
            Parent root = FXMLLoader.load(getClass().getResource("../../utilisateur/UtilisateurBack.fxml"));

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
