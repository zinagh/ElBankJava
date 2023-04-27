package gui;

import entities.OperationCredit;
import entities.credit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.OperationCreditService;
import service.creditService;

public class FrontController implements Initializable{

     @FXML
    private TableColumn<credit, String> DDP_Credit;

    @FXML
    private TableColumn<credit, String> DPP_Credit;

    @FXML
    private TableColumn<credit, Integer> Duree_Credit;

    @FXML
    private TableColumn<credit, String> Echeance;

    @FXML
    private TableColumn<credit, Integer> Interet;

    @FXML
    private TableColumn<credit, Integer> Montant_credit;

    @FXML
    private TableColumn<credit, Integer> Numero_Compte;

    @FXML
    private TableColumn<credit, String> Type_credit;
    
    
    @FXML
    private TableColumn<OperationCredit, String> echeanceo;
      @FXML
    private TableColumn<OperationCredit, String> DO;

    @FXML
    private TableColumn<OperationCredit, Integer> intereto;

    @FXML
    private TableColumn<OperationCredit, Integer> montantPO;

    @FXML
    private TableColumn<OperationCredit, Integer> opcredit_id;

    @FXML
    private TableColumn<OperationCredit, Integer> solv;

    @FXML
    private TableColumn<OperationCredit, String> typeo;
    
    
    
   
    
    
    
    
    
    
    
    
    

    
    
    
    
        @FXML
    private Button Simuler;
    
    
    
    
    @FXML
    private TextField cher_cridit;

    @FXML
    private TextField cher_op;

    @FXML
    private TextField col_cridtid;

    @FXML
    private DatePicker col_dated;

    @FXML
    private DatePicker col_dateop;

    @FXML
    private DatePicker col_datep;

    @FXML
    private TextField col_dur;

    @FXML
    private DatePicker col_echance;

    @FXML
    private DatePicker col_echnaaceop;

    @FXML
    private TextField col_inter;

    @FXML
    private TextField col_interetop;

    @FXML
    private TextField col_mantanpayer;

    @FXML
    private TextField col_montan;

    @FXML
    private TextField col_num;

    @FXML
    private TextField col_solva;

    @FXML
    private TextField col_type;

    @FXML
    private TextField col_typeop;

    
    @FXML
    private TableView<credit> tablecredit;
    
    @FXML
    private TableView<OperationCredit> tableoperation;
    
private creditService creditService = new creditService();
private OperationCreditService opcredit = new OperationCreditService();
    @FXML
    private Text ttnumcompte;
    @FXML
    private Text ttmontant;
    @FXML
    private Text ttdatepp;
    @FXML
    private Text ttdatedp;
    @FXML
    private Text ttduree;
    @FXML
    private Text ttecheance;
    @FXML
    private Text ttinteret;
    @FXML
    private Text tttypec;
    @FXML
    private Text ttdatep;
    @FXML
    private Text ttm;
    @FXML
    private Text ttech;
    @FXML
    private Text ttit;
    @FXML
    private Text ttsb;
    @FXML
    private Text ttop;
    
  public void initialize(URL url, ResourceBundle rb) {
        List<credit> produits;
        try {
            produits = creditService.getAll();
                        ObservableList<credit> olp = FXCollections.observableArrayList(produits);
            tablecredit.setItems(olp);
            Numero_Compte.setCellValueFactory(new PropertyValueFactory("numeroCompteId"));
            Montant_credit.setCellValueFactory(new PropertyValueFactory("montCredit"));
            DPP_Credit.setCellValueFactory(new PropertyValueFactory("datePE"));
            DDP_Credit.setCellValueFactory(new PropertyValueFactory("dateDE"));
            Duree_Credit.setCellValueFactory(new PropertyValueFactory("dureeC"));
            Echeance.setCellValueFactory(new PropertyValueFactory("echeance"));
            Interet.setCellValueFactory(new PropertyValueFactory("tauxInteret"));
            Type_credit.setCellValueFactory(new PropertyValueFactory("typeCredit"));           
        } catch (SQLException ex) {
            Logger.getLogger(BacckController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
           List<OperationCredit> operations;
        try {
            operations = opcredit.getAll();
            ObservableList<OperationCredit> olp = FXCollections.observableArrayList(operations);
            tableoperation.setItems(olp);
            opcredit_id.setCellValueFactory(new PropertyValueFactory("credit"));
            DO.setCellValueFactory(new PropertyValueFactory("dateOp"));
            montantPO.setCellValueFactory(new PropertyValueFactory("montPayer"));
            echeanceo.setCellValueFactory(new PropertyValueFactory("echeance"));
            intereto.setCellValueFactory(new PropertyValueFactory("tauxInteret"));
            solv.setCellValueFactory(new PropertyValueFactory("solvabilite"));
            typeo.setCellValueFactory(new PropertyValueFactory("typeOperation")); 
        }
    catch(SQLException e){
    e.printStackTrace();

    }

        
    }    

    
    
    @FXML
    void AJOUTER_cridt(ActionEvent event) throws SQLException {

    credit cf = new credit();

    boolean isFormValid = true; // assume the form is valid

    if (col_montan.getText().isEmpty()) {
        ttmontant.setText("Veuillez saisir le montant désiré");
        isFormValid = false;
    }

    if (col_datep.getValue() == null) {
        ttdatepp.setText("Veuillez saisir la date de première échéance");
        isFormValid = false;
    }

    if (col_dated.getValue() == null) {
        ttdatedp.setText("Veuillez saisir la date de déblocage des fonds");
        isFormValid = false;
    }

    if (col_dur.getText().isEmpty()) {
        ttduree.setText("Veuillez saisir la durée du crédit");
        isFormValid = false;
    }

    if (col_echance.getValue() == null) {
        ttecheance.setText("Veuillez saisir la date d'échéance finale");
        isFormValid = false;
    }

    if (col_inter.getText().isEmpty()) {
        tttypec.setText("Veuillez saisir le taux d'intérêt");
        isFormValid = false;
    }

    if (col_type.getText().isEmpty()) {
        tttypec.setText("Veuillez saisir le type de crédit");
        isFormValid = false;
    }

    if (isFormValid) {
        cf.setMontCredit(Integer.parseInt(col_montan.getText()));
        cf.setDatePE(java.sql.Date.valueOf((col_datep.getValue())));
        cf.setDateDE(java.sql.Date.valueOf((col_dated.getValue())));
        cf.setDureeC(Integer.parseInt(col_dur.getText()));
        cf.setEcheance(java.sql.Date.valueOf((col_echance.getValue())));
        cf.setTauxInteret(Integer.parseInt(col_inter.getText()));
        cf.setDecision(false);
        cf.setEtatCredit("Encours");
        cf.setTypeCredit(col_type.getText());
        creditService.ajoutcredit(cf);

        // update the credit table view
        List<credit> credits;
        try {
            credits = creditService.getAll();
            ObservableList<credit> olp = FXCollections.observableArrayList(credits);
            tablecredit.setItems(olp);
            Numero_Compte.setCellValueFactory(new PropertyValueFactory("numeroCompteId"));
            Montant_credit.setCellValueFactory(new PropertyValueFactory("montCredit"));
            DPP_Credit.setCellValueFactory(new PropertyValueFactory("datePE"));
            DDP_Credit.setCellValueFactory(new PropertyValueFactory("dateDE"));
            Duree_Credit.setCellValueFactory(new PropertyValueFactory("dureeC"));
            Echeance.setCellValueFactory(new PropertyValueFactory("echeance"));
            Interet.setCellValueFactory(new PropertyValueFactory("tauxInteret"));
            Type_credit.setCellValueFactory(new PropertyValueFactory("typeCredit"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

    
    
    
    @FXML
    void ajouter_op(ActionEvent event) throws SQLException {
    OperationCredit cf = new OperationCredit();
    boolean isFormValid = true; // assume the form is valid

//    if (col_cridtid.getText().isEmpty()) {
//        ttcredit.setText("Veuillez saisir l'identifiant du crédit");
//        isFormValid = false;
//    }

    if (col_dateop.getValue() == null) {
        ttdatep.setText("Veuillez saisir la date de l'opération");
        isFormValid = false;
    }

    if (col_mantanpayer.getText().isEmpty()) {
        ttm.setText("Veuillez saisir le montant payé");
        isFormValid = false;
    }

    if (col_echnaaceop.getValue() == null) {
        ttech.setText("Veuillez saisir la date d'échéance de l'opération");
        isFormValid = false;
    }

    if (col_interetop.getText().isEmpty()) {
        ttit.setText("Veuillez saisir le taux d'intérêt de l'opération");
        isFormValid = false;
    }

    if (col_solva.getText().isEmpty()) {
        ttsb.setText("Veuillez saisir la solvabilité de l'emprunteur");
        isFormValid = false;
    }

    if (col_typeop.getText().isEmpty()) {
        ttop.setText("Veuillez saisir le type d'opération");
        isFormValid = false;
    }

    if (isFormValid) {
        credit ts = creditService.findById(Integer.parseInt(col_cridtid.getText()));
        cf.setCredit(ts);
        cf.setDateOp(java.sql.Date.valueOf((col_dateop.getValue())));
        cf.setMontPayer(Integer.parseInt(col_mantanpayer.getText()));
        cf.setEcheance(java.sql.Date.valueOf((col_echnaaceop.getValue())));
        cf.setTauxInteret(Integer.parseInt(col_interetop.getText()));
        cf.setSolvabilite(Integer.parseInt(col_solva.getText()));
        cf.setTypeOperation(col_typeop.getText());
        opcredit.ajoutOperationCredit(cf);

        // update the operation credit table view
        List<OperationCredit> operations;
        try {
            operations = opcredit.getAll();
            ObservableList<OperationCredit> olp = FXCollections.observableArrayList(operations);
            tableoperation.setItems(olp);
            opcredit_id.setCellValueFactory(new PropertyValueFactory("credit"));
            DO.setCellValueFactory(new PropertyValueFactory("dateOp"));
            montantPO.setCellValueFactory(new PropertyValueFactory("montPayer"));
            echeanceo.setCellValueFactory(new PropertyValueFactory("echeance"));
            intereto.setCellValueFactory(new PropertyValueFactory("tauxInteret"));
            solv.setCellValueFactory(new PropertyValueFactory("solvabilite"));
            typeo.setCellValueFactory(new PropertyValueFactory("typeOperation"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    
    
    
    
    
    
    
    
    
    
    
   
   
    

    
    
    @FXML
    void simulation(ActionEvent event) throws IOException {

        try {    
        Parent root =FXMLLoader.load(getClass().getResource("simulateur.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
        
        } catch (Exception e) {
        e.printStackTrace();
        
        }
        
        
        
        
        
    }
    
    
    
    
     void simuler_credit(ActionEvent event) {

    }
    
    
    
    
    
    

    void chercher(ActionEvent event) {

    }

    void chercher_op(ActionEvent event) {

    }

    @FXML
    private void chercher_operation(InputMethodEvent event) {
       
      
                      List<OperationCredit> operations;
        try {
            operations = opcredit.getAllByMontPayer(Integer.parseInt(cher_op.getText()));
            ObservableList<OperationCredit> olp = FXCollections.observableArrayList(operations);
            tableoperation.setItems(olp);
            opcredit_id.setCellValueFactory(new PropertyValueFactory("credit"));
            DO.setCellValueFactory(new PropertyValueFactory("dateOp"));
            montantPO.setCellValueFactory(new PropertyValueFactory("montPayer"));
            echeanceo.setCellValueFactory(new PropertyValueFactory("echeance"));
            intereto.setCellValueFactory(new PropertyValueFactory("tauxInteret"));
            solv.setCellValueFactory(new PropertyValueFactory("solvabilite"));
            typeo.setCellValueFactory(new PropertyValueFactory("typeOperation")); 
        }
    catch(SQLException e){
    e.printStackTrace();

    }
    }

    @FXML
    private void chercher_operation(KeyEvent event) {
                           List<OperationCredit> operations;
        try {
            operations = opcredit.getAllByMontPayer(Integer.parseInt(cher_op.getText()));
            ObservableList<OperationCredit> olp = FXCollections.observableArrayList(operations);
            tableoperation.setItems(olp);
            opcredit_id.setCellValueFactory(new PropertyValueFactory("credit"));
            DO.setCellValueFactory(new PropertyValueFactory("dateOp"));
            montantPO.setCellValueFactory(new PropertyValueFactory("montPayer"));
            echeanceo.setCellValueFactory(new PropertyValueFactory("echeance"));
            intereto.setCellValueFactory(new PropertyValueFactory("tauxInteret"));
            solv.setCellValueFactory(new PropertyValueFactory("solvabilite"));
            typeo.setCellValueFactory(new PropertyValueFactory("typeOperation")); 
        }
    catch(SQLException e){
    e.printStackTrace();

    }
        
    }

    @FXML
    private void chercher_cridt(KeyEvent event) {
        
                
                        List<credit> produits;
        try {
            produits = creditService.rechercheCredit(Integer.parseInt(cher_cridit.getText()));
                        ObservableList<credit> olp = FXCollections.observableArrayList(produits);
            tablecredit.setItems(olp);
            Numero_Compte.setCellValueFactory(new PropertyValueFactory("numeroCompteId"));
            Montant_credit.setCellValueFactory(new PropertyValueFactory("montCredit"));
            DPP_Credit.setCellValueFactory(new PropertyValueFactory("datePE"));
            DDP_Credit.setCellValueFactory(new PropertyValueFactory("dateDE"));
            Duree_Credit.setCellValueFactory(new PropertyValueFactory("dureeC"));
            Echeance.setCellValueFactory(new PropertyValueFactory("echeance"));
            Interet.setCellValueFactory(new PropertyValueFactory("tauxInteret"));
            Type_credit.setCellValueFactory(new PropertyValueFactory("typeCredit"));           
        } catch (SQLException ex) {
            Logger.getLogger(BacckController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}