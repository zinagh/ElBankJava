package gui.Transactions.Back;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.CompteController;
import services.TransactionController;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class TransactionsBackController implements Initializable {

    @FXML
    private ComboBox ComboboxRibRecepteurBack;

    @FXML
    private Button ComptesBack;

    @FXML
    private TextField DescriptionTransBack;

    @FXML
    private TextField MontantTransBack;


    @FXML
    private ComboBox RibEmetteurBack;

    @FXML
    private TextField RibRecepteurBack;

    @FXML
    private Button TransactionsBack;

    @FXML
    private ComboBox TypeTransBack;

    @FXML
    private Button btnAcceuilBack;

    @FXML
    private Button btnAjouterTransBack;

    @FXML
    private Button btnAnnulerAutreTransBack;

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
    private Button btnModiferTransBack;

    @FXML
    private Button btnReclamationsBack;

    @FXML
    private Button btnResetTransBack;

    @FXML
    private Button btnSupprimerTransBack;

    @FXML
    private Button btnUtilisateursBack;

    @FXML
    private AnchorPane contenuBack;

    @FXML
    private Label controleDescriptionBack;

    @FXML
    private Label controleEmetteurBack;

    @FXML
    private Label controleMontantTransBack;

    @FXML
    private Label controleNomRecepteurBack;

    @FXML
    private Label controleRibRecepteurBack;

    @FXML
    private Label controleTypeTransBack;

    @FXML
    private AnchorPane root;

    @FXML
    private TableColumn tabDateBack;

    @FXML
    private TableColumn tabDescriptionBack;

    @FXML
    private TableColumn tabEtatBack;

    @FXML
    private TableColumn tabMontantBack;

    @FXML
    private TableColumn tabNomEmBack;

    @FXML
    private TableColumn tabNomRecBanck;

    @FXML
    private TableColumn tabRibEmBack;

    @FXML
    private TableColumn tabRibRecBack;

    @FXML
    private TableColumn tabTypeBack;

    @FXML
    private TableView transactions_Back;


    PreparedStatement pst = null;
    ResultSet rs = null;
    Connection cnx2;
    public ObservableList<Transaction> listTrans = FXCollections.observableArrayList();
    public ObservableList<String> comboboxEmetteur = FXCollections.observableArrayList();

    @FXML
    public void afficherTransactions() {
        TransactionController transCont = new TransactionController();
        List<Transaction> trans = transCont.afficherTransactions();
        if (!trans.isEmpty()) {
            for (int i = 0; i < trans.size(); i++) {
                listTrans.add(trans.get(i));
            }
        }
        addEtatColumn();

        tabRibRecBack.setCellValueFactory(new PropertyValueFactory<Transaction, Integer>("RIB_recepteur"));
        tabNomEmBack.setCellValueFactory(new PropertyValueFactory<Transaction, String>("nomemet"));
        tabDescriptionBack.setCellValueFactory(new PropertyValueFactory<Transaction, String>("description_transaction"));
        tabNomRecBanck.setCellValueFactory(new PropertyValueFactory<Transaction, String>("fullname_recepteur"));
        tabEtatBack.setCellValueFactory(new PropertyValueFactory<Transaction, String>("etat_transaction"));
        tabRibEmBack.setCellValueFactory(new PropertyValueFactory<Transaction, String>("ribemet"));
        tabDateBack.setCellValueFactory(new PropertyValueFactory<Transaction, String>("date_transaction"));
        tabTypeBack.setCellValueFactory(new PropertyValueFactory<Transaction, String>("type_transaction"));
        tabMontantBack.setCellValueFactory(new PropertyValueFactory<Transaction, String>("montant_transaction"));
        transactions_Back.setItems(listTrans);
    }

    private void addEtatColumn() {
        TableColumn<Transaction, Void> colBtn = new TableColumn("Etat Transaction");

        Callback<TableColumn<Transaction, Void>, TableCell<Transaction, Void>> cellFactory = new Callback<TableColumn<Transaction, Void>, TableCell<Transaction, Void>>() {

            public TableCell<Transaction, Void> call(final TableColumn<Transaction, Void> param) {
                final TableCell<Transaction, Void> cell = new TableCell<Transaction, Void>() {
                    ComboBox combEtat = new ComboBox();

                    {
                        combEtat.getItems().add("Aboutie");
                        combEtat.getItems().add("Annulée");
                        combEtat.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
                            Transaction data = getTableView().getItems().get(getIndex());
                            if (newValue == "Aboutie") {
                                data.setEtat_transaction(1);
                            } else {
                                data.setEtat_transaction(2);
                            }
                            TransactionController transss = new TransactionController();
                            transss.modifierTransaction(data, data.getId());

                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            Transaction data = getTableView().getItems().get(getIndex());
                            if (data.getEtat_transaction() == 1) {
                                combEtat.setValue("Aboutie");
                            } else if (data.getEtat_transaction() == 2) {
                                combEtat.setValue("Annulée");
                            } else if (data.getEtat_transaction() == 0) {
                                combEtat.getItems().add("En cours");
                                combEtat.setValue("En cours");
                            }
                            setGraphic(combEtat);

                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        transactions_Back.getColumns().add(colBtn);
    }

    @FXML
    public void fillComboboxTrans() {
        CompteController comptCont = new CompteController();
        List<String> rib_noms = comptCont.afficherNomRib();
        if (!rib_noms.isEmpty()) {
            for (int i = 0; i < rib_noms.size(); i++) {
                comboboxEmetteur.add(rib_noms.get(i));
            }
        }
        RibEmetteurBack.setItems(comboboxEmetteur);
        RibEmetteurBack.getSelectionModel().selectFirst();

        ComboboxRibRecepteurBack.setItems(comboboxEmetteur);
        ComboboxRibRecepteurBack.getSelectionModel().selectFirst();

        TypeTransBack.getSelectionModel().selectFirst();

        controleRibRecepteurBack.setText("* Veuillez choisir un recepteur *");
        controleRibRecepteurBack.setVisible(false);
    }

    @FXML
    void clearFieldsTrans() {
        DescriptionTransBack.clear();
        RibRecepteurBack.clear();
        MontantTransBack.clear();
        RibEmetteurBack.getSelectionModel().selectFirst();
        ComboboxRibRecepteurBack.getSelectionModel().selectFirst();
        TypeTransBack.getSelectionModel().selectFirst();
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        afficherTransactions();
        fillComboboxTrans();
        clearFieldsTrans();

        controleNomRecepteurBack.setVisible(false);
        RibRecepteurBack.setStyle(null);
        controleRibRecepteurBack.setVisible(false);
        TypeTransBack.setStyle(null);
        controleTypeTransBack.setVisible(false);
        RibEmetteurBack.setStyle(null);
        ComboboxRibRecepteurBack.setStyle(null);
        controleEmetteurBack.setVisible(false);
        MontantTransBack.setStyle(null);
        controleMontantTransBack.setVisible(false);
        DescriptionTransBack.setStyle(null);
        controleDescriptionBack.setVisible(false);


//        TimeNow();
    }

    @FXML
    void AnnulerAutreRecepteurTransBack(ActionEvent event) {

    }

    @FXML
    void OnComboboxRibEmet(ActionEvent event) {

    }

    @FXML
    void OnComboboxRibRecep(ActionEvent event) {

    }

    @FXML
    void ajouterTransactionBack(ActionEvent event) {
        TransactionController transCont = new TransactionController();
        CompteController comptCont = new CompteController();

        String emetteur = RibEmetteurBack.getSelectionModel().getSelectedItem().toString();
        String[] arrOfStr = emetteur.split("-");
        System.out.println(arrOfStr[0]);
        System.out.println(arrOfStr[1]);
        Compte cmptEmetteur = comptCont.rechercherCompteRib(arrOfStr[0]);
//        System.out.println(cmptEmetteur.getId());

        String description = DescriptionTransBack.getText();
        String nomRecepteur = "";
        String ribRecepteur = "";
        if (ComboboxRibRecepteurBack.isVisible()) {
            String recep = ComboboxRibRecepteurBack.getSelectionModel().getSelectedItem().toString();
            String[] arrOfStr1 = recep.split("-");
            System.out.println(arrOfStr1[0]);
            System.out.println(arrOfStr1[1]);
            Compte cmptRecep = comptCont.rechercherCompteRib(arrOfStr1[0]);

            nomRecepteur = cmptRecep.getFullname();
            ribRecepteur = cmptRecep.getRIB_Compte();
        } else {
            ribRecepteur = RibRecepteurBack.getText();
        }
        Float montant = Float.valueOf(MontantTransBack.getText());
        String type = TypeTransBack.getSelectionModel().getSelectedItem().toString();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Gestion des transactions bancaires");
        alert.setHeaderText(null);
        alert.setContentText("Transaction Bancaire effectuée avec succès !");
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        alert.showAndWait();

        Transaction trans = new Transaction(cmptEmetteur.getId(), ribRecepteur, montant, dtf.format(now), description, cmptEmetteur.getId(), nomRecepteur, type, 1);
        transCont.ajouterTransaction2(trans);
        comptCont.modificationTransaction(trans.getRIB_emetteur(), trans.getRIB_recepteur(), trans.getMontant_transaction());

        transactions_Back.getItems().clear();
        afficherTransactions();
        clearFieldsTrans();

        controleNomRecepteurBack.setVisible(false);
        RibRecepteurBack.setStyle(null);
        controleRibRecepteurBack.setVisible(false);
        TypeTransBack.setStyle(null);
        controleTypeTransBack.setVisible(false);
        RibEmetteurBack.setStyle(null);
        ComboboxRibRecepteurBack.setStyle(null);
        ComboboxRibRecepteurBack.setVisible(true);
        controleEmetteurBack.setVisible(false);
        MontantTransBack.setStyle(null);
        controleMontantTransBack.setVisible(false);
        DescriptionTransBack.setStyle(null);
        controleDescriptionBack.setVisible(false);


    }

    @FXML
    void doubleClickTransBack(MouseEvent event) {

    }

    @FXML
    void modifierTransactionBack(ActionEvent event) {
        if (!transactions_Back.getSelectionModel().getSelectedCells().isEmpty()) {

            CompteController comptCont = new CompteController();
            TransactionController transCont = new TransactionController();

            Transaction trans = (Transaction) transactions_Back.getSelectionModel().getSelectedItem();

            String emetteur = RibEmetteurBack.getSelectionModel().getSelectedItem().toString();
            System.out.println(emetteur);
            String[] arrOfStr = emetteur.split("-");
            System.out.println(arrOfStr[0]);
            System.out.println(arrOfStr[1]);
            Compte cmptEmetteur = comptCont.rechercherCompteRib(arrOfStr[0]);

            String description = DescriptionTransBack.getText();
            String ribRecepteur = RibRecepteurBack.getText();
            Float montant = Float.valueOf(MontantTransBack.getText());
            String type = TypeTransBack.getSelectionModel().getSelectedItem().toString();

            float montantancien = trans.getMontant_transaction();

            trans.setDescription_transaction(description);
            trans.setRIB_recepteur(ribRecepteur);
            trans.setMontant_transaction(montant);
            trans.setType_transaction(type);

            transCont.modifierTransaction(trans, trans.getId());
            float diff = montant - montantancien;
            comptCont.modificationTransaction(trans.getRIB_emetteur(), trans.getRIB_recepteur(), diff);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Gestion des transactions bancaires");
            alert.setHeaderText(null);
            alert.setContentText("Transaction Bancaire modifiée avec succès !");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            alert.showAndWait();

            transactions_Back.getItems().clear();

            afficherTransactions();
            RibEmetteurBack.setDisable(false);
            ComboboxRibRecepteurBack.setDisable(false);
            btnAnnulerAutreTransBack.setDisable(false);
            RibRecepteurBack.setDisable(false);
            clearFieldsTrans();

            controleNomRecepteurBack.setVisible(false);
            RibRecepteurBack.setStyle(null);
            controleRibRecepteurBack.setVisible(false);
            TypeTransBack.setStyle(null);
            controleTypeTransBack.setVisible(false);
            RibEmetteurBack.setStyle(null);
            ComboboxRibRecepteurBack.setStyle(null);
            ComboboxRibRecepteurBack.setVisible(true);
            controleEmetteurBack.setVisible(false);
            MontantTransBack.setStyle(null);
            controleMontantTransBack.setVisible(false);
            DescriptionTransBack.setStyle(null);
            controleDescriptionBack.setVisible(false);


        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Gestion des transactions bancaires");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner la transaction à modifier !");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            alert.showAndWait();
        }
    }


    @FXML
    void resetTransactionBack(ActionEvent event) {

    }

    @FXML
    void supprimerTransactionBack(ActionEvent event) {
        if (!transactions_Back.getSelectionModel().getSelectedCells().isEmpty()) {
            TransactionController trans = new TransactionController();


            TablePosition pos = (TablePosition) transactions_Back.getSelectionModel().getSelectedCells().get(0);
            int index = pos.getRow();
            String selected = transactions_Back.getItems().get(index).toString();
            selected = selected.substring(12, selected.indexOf(" : "));
            Transaction tr = trans.rechercherTransaction(Integer.parseInt(selected));

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Gestion des transactions bancaires");
            alert.setHeaderText(null);
            alert.setContentText("Voulez-vous supprimer la transaction effectuée par " + tr.getNomemet() + " pour " + tr.getFullname_recepteur() + " ?");
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            ButtonType okButton = new ButtonType("Oui", ButtonBar.ButtonData.YES);
            ButtonType cancelButton = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(okButton, cancelButton);
            String finalSelected = selected;
            alert.showAndWait().ifPresent(type -> {
                if (type == okButton) {
                    trans.supprimerTransaction(Integer.parseInt(finalSelected));
                    transactions_Back.getItems().removeAll(transactions_Back.getSelectionModel().getSelectedItem());

                    RibEmetteurBack.setDisable(false);
                    ComboboxRibRecepteurBack.setDisable(false);
                    btnAnnulerAutreTransBack.setDisable(false);
                    RibRecepteurBack.setDisable(false);
                    ComboboxRibRecepteurBack.setVisible(true);

                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Gestion des transactions bancaires");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez selectionner la transaction à supprimer !");
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
    void versComptesBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Acceuil Admin");
            Parent root = FXMLLoader.load(getClass().getResource("../../Comptes/Back/ComptesBack.fxml"));

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
    void versLogoutBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

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
    void versReclamationsBack(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();

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


