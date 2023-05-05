package gui.credits;

import entities.Credit;
import entities.OperationCredit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import services.ServiceCredit;

import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.ServiceOperationCredit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import entities.Credit;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import services.ServiceCredit;
import tools.MyConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FrontusercreditController implements Initializable {

    @FXML
    private TableView<Credit> Tcredit;

    @FXML
    private TableView<OperationCredit> Toperation;


    @FXML
    private AnchorPane anc1;

    @FXML
    private AnchorPane anc2;

    @FXML
    private AnchorPane anc3;

    @FXML
    private Button btnAccueilFront;

    @FXML
    private Button btnCartesFront;

    @FXML
    private Button btnChequesFront;

    @FXML
    private Button btnCompteFront;

    @FXML
    private Button btnCreditFront;

    @FXML
    private Button btnLogoutFront;

    @FXML
    private Button btnReclamationsFront;

    @FXML
    private Button btnTransFront;

    @FXML
    private Label chequiersAcc1;

    @FXML
    private TableColumn<OperationCredit, Integer> col_creditidop;

    @FXML
    private TableColumn<Credit, Integer> col_d;

    @FXML
    private TableColumn<Credit, Date> col_datede;

    @FXML
    private TableColumn<OperationCredit, Date> col_dateop;

    @FXML
    private TableColumn<Credit, Date> col_datepe;

    @FXML
    private TableColumn<Credit, Integer> col_duree;

    @FXML
    private TableColumn<Credit, Date> col_echeance;

    @FXML
    private TableColumn<OperationCredit, Date> col_echeanceop;

    @FXML
    private TableColumn<Credit, String> col_etat;

    @FXML
    private TableColumn<Credit, Integer> col_idcredit;

    @FXML
    private TableColumn<OperationCredit, Integer> col_idop;

    @FXML
    private TableColumn<Credit, Integer> col_montant;

    @FXML
    private TableColumn<OperationCredit, Integer> col_montantop;

    @FXML
    private TableColumn<Credit, Integer> col_numcompte;

    @FXML
    private TableColumn<OperationCredit, Integer> col_sol;

    @FXML
    private TableColumn<Credit, String> col_t;

    @FXML
    private TableColumn<OperationCredit, Integer> col_tauxop;

    @FXML
    private TableColumn<Credit, String> col_type;

    @FXML
    private TableColumn<OperationCredit, String> Type_op;

    @FXML
    private Label money;
    ObservableList<Credit> userData = FXCollections.observableArrayList();


    @FXML
    private TextField recherche;
    ServiceCredit creditService = new ServiceCredit();
    ServiceOperationCredit ServiceOperationCredit = new ServiceOperationCredit();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Credit> produits;


        produits = creditService.getAll();
        ObservableList<Credit> olp = FXCollections.observableArrayList(produits);
        Tcredit.setItems(olp);

        col_idcredit.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_numcompte.setCellValueFactory(new PropertyValueFactory<>("numero_compte"));
        col_montant.setCellValueFactory(new PropertyValueFactory<>("montCredit"));
        col_datepe.setCellValueFactory(new PropertyValueFactory<>("datepe"));
        col_datede.setCellValueFactory(new PropertyValueFactory<>("datede"));
        col_duree.setCellValueFactory(new PropertyValueFactory<>("dureeC"));
        col_echeance.setCellValueFactory(new PropertyValueFactory<>("echeance"));
        col_t.setCellValueFactory(new PropertyValueFactory<>("tauxInteret"));
        col_d.setCellValueFactory(new PropertyValueFactory<>("decision"));
        col_etat.setCellValueFactory(new PropertyValueFactory<>("etatCredit"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("typeCredit"));


        List<OperationCredit> operations;
        operations = ServiceOperationCredit.getAll();
        ObservableList<OperationCredit> oolp = FXCollections.observableArrayList(operations);
        Toperation.setItems(oolp);
        col_idop.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_creditidop.setCellValueFactory(new PropertyValueFactory<>("creditid"));
        col_dateop.setCellValueFactory(new PropertyValueFactory<>("dateOp"));
        col_montantop.setCellValueFactory(new PropertyValueFactory<>("montPayer"));
        col_echeanceop.setCellValueFactory(new PropertyValueFactory<>("echeance"));
        col_tauxop.setCellValueFactory(new PropertyValueFactory<>("tauxInteret"));
        col_sol.setCellValueFactory(new PropertyValueFactory<>("solvabilite"));
        Type_op.setCellValueFactory(new PropertyValueFactory<>("typeOperation"));
    }


    @FXML
    void ajouter_credit_front(ActionEvent event) {

    }


    private ObservableList<OperationCredit> operationCreditsList = FXCollections.observableArrayList();

    private void initOperationTable() {
        // Liaison des colonnes aux propriétés des objets OperationCredit


        Toperation.setItems(operationCreditsList);
    }


    @FXML
    void ajouter_operation(ActionEvent event) {

    }

    @FXML
    void redirectAccueilFront(ActionEvent event) {

    }

    @FXML
    void redirectCartesFront(ActionEvent event) {

    }

    @FXML
    void redirectCompteFront(ActionEvent event) {

    }

    @FXML
    void redirectCreditFront(ActionEvent event) {

    }

    @FXML
    void redirectLogoutFront(ActionEvent event) {

    }

    @FXML
    void redirectReclamationsFront(ActionEvent event) {

    }

    @FXML
    void redirectTransactionsFront(ActionEvent event) {

    }

    @FXML
    void simuler_credit(ActionEvent event) {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/gui/credits/simulateur.fxml"));
            Stage myWindow = (Stage) Tcredit.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("page name");
            //myWindow.setFullScreen(true);
            myWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(FrontusercreditController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    @FXML
    void recherche(ActionEvent event) {


        col_idcredit.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_numcompte.setCellValueFactory(new PropertyValueFactory<>("numero_compte"));
        col_montant.setCellValueFactory(new PropertyValueFactory<>("montCredit"));
        col_datepe.setCellValueFactory(new PropertyValueFactory<>("datepe"));
        col_datede.setCellValueFactory(new PropertyValueFactory<>("datede"));
        col_duree.setCellValueFactory(new PropertyValueFactory<>("dureeC"));
        col_echeance.setCellValueFactory(new PropertyValueFactory<>("echeance"));
        col_t.setCellValueFactory(new PropertyValueFactory<>("tauxInteret"));
        col_d.setCellValueFactory(new PropertyValueFactory<>("decision"));
        col_etat.setCellValueFactory(new PropertyValueFactory<>("etatCredit"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("typeCredit"));

        List<Credit> list = creditService.getAll();
        userData.clear();
        userData.addAll(list);

        //tableview.setItems(observablelist);

        FilteredList<Credit> filtredData = new FilteredList<>(userData, b -> true);
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            Predicate<? super Credit> Reservation;
            filtredData.setPredicate((Credit e) -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();
                if (String.valueOf(e.getDatede()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(e.getMontCredit()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else
                    return false;
            });
        });
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Credit> sortedData = new SortedList<>(filtredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(Tcredit.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        Tcredit.setItems(sortedData);


    }

}
