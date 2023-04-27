/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.rowset.internal.Row;
import entities.OperationCredit;
import entities.credit;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import service.OperationCreditService;
import service.creditService;
import utils.Mailing;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
/**
 * FXML Controller class
 *
 * @author MSI
 */
public class BacckController implements Initializable {

    @FXML
    private TextField col_num;
    @FXML
    private TextField col_montan;
    @FXML
    private DatePicker col_datep;
    @FXML
    private DatePicker col_dated;
    @FXML
    private TextField col_dur;
    @FXML
    private DatePicker col_echance;
    @FXML
    private TextField col_inter;
    @FXML
    private TextField col_etat;
    @FXML
    private TextField col_type;
    @FXML
    private TableView<credit> tab_credit;
    @FXML
    private TableColumn<credit, Integer> col_id;
    @FXML
    private TableColumn<credit, Integer> col_mont;
    @FXML
    private TableColumn<credit, String> col_datepe;
    @FXML
    private TableColumn<credit, String> col_datede;
    @FXML
    private TableColumn<credit, Integer> col_dure;
    @FXML
    private TableColumn<credit, String> col_echeancec;
    @FXML
    private TableColumn<credit, Integer> col_tauxc;
    @FXML
    private TableColumn<credit, Boolean> col_decisionc;
    @FXML
    private TableColumn<credit, String> col_etatc;
    @FXML
    private TableColumn<credit, Button> col_detalc;
    @FXML
    private TextField col_cridtid;
    @FXML
    private DatePicker col_dateop;
    @FXML
    private TextField col_mantanpayer;
    @FXML
    private DatePicker col_echnaaceop;
    @FXML
    private TextField col_interetop;
    @FXML
    private TextField col_solva;
    @FXML
    private TextField col_typeop;
    @FXML
    private TableColumn<OperationCredit, Integer> colop_id;
    @FXML
    private TableColumn<OperationCredit, Integer> colop_credit;
    @FXML
    private TableColumn<OperationCredit, String> colop_dateop;
    @FXML
    private TableColumn<OperationCredit, Integer> colop_man;
    @FXML
    private TableColumn<OperationCredit, String> colop_echeance;
    @FXML
    private TableColumn<OperationCredit, Integer> colop_taux;
    @FXML
    private TableColumn<OperationCredit, Integer> colop_solvabilite;
    @FXML
    private TableColumn<OperationCredit, String> colop_operation;
    @FXML
    private TableColumn<OperationCredit, Button> colop_dalate;
    creditService ps = new creditService();
    OperationCreditService ops = new OperationCreditService();
    @FXML
    private TableColumn<credit, Integer> collidcompt;
    @FXML
    private TableColumn<credit, String> colllllltype;
    @FXML
    private TableView<OperationCredit> tabop;

    @FXML
    private TextField col_sender;

    int idcredit;
    int opcredit;
    @FXML
    private Button modifiercredit;
    @FXML
    private Button sender;
    @FXML
    private Button modifieroperation;
    @FXML
    private Button excel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<credit> produits;
        try {
            produits = ps.getAll();
            ObservableList<credit> olp = FXCollections.observableArrayList(produits);
            tab_credit.setItems(olp);
            col_id.setCellValueFactory(new PropertyValueFactory("id"));
            collidcompt.setCellValueFactory(new PropertyValueFactory("numeroCompteId"));
            col_mont.setCellValueFactory(new PropertyValueFactory("montCredit"));
            col_datepe.setCellValueFactory(new PropertyValueFactory("datePE"));
            col_datede.setCellValueFactory(new PropertyValueFactory("dateDE"));
            col_dure.setCellValueFactory(new PropertyValueFactory("dureeC"));
            col_echeancec.setCellValueFactory(new PropertyValueFactory("echeance"));
            col_tauxc.setCellValueFactory(new PropertyValueFactory("tauxInteret"));
            col_decisionc.setCellValueFactory(new PropertyValueFactory("decision"));
            col_etatc.setCellValueFactory(new PropertyValueFactory("etatCredit"));
            colllllltype.setCellValueFactory(new PropertyValueFactory("typeCredit"));
            List<OperationCredit> OperationCredits = ops.getAll();
            ObservableList<OperationCredit> oolp = FXCollections.observableArrayList(OperationCredits);
            tabop.setItems(oolp);
            colop_id.setCellValueFactory(new PropertyValueFactory("id"));
            colop_credit.setCellValueFactory(new PropertyValueFactory("credit"));
            colop_dateop.setCellValueFactory(new PropertyValueFactory("dateOp"));
            colop_man.setCellValueFactory(new PropertyValueFactory("montPayer"));
            colop_echeance.setCellValueFactory(new PropertyValueFactory("echeance"));
            colop_taux.setCellValueFactory(new PropertyValueFactory("tauxInteret"));
            colop_solvabilite.setCellValueFactory(new PropertyValueFactory("solvabilite"));
            colop_operation.setCellValueFactory(new PropertyValueFactory("typeOperation"));
            this.deletee();
            this.delete();
        } catch (SQLException ex) {
            Logger.getLogger(BacckController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void AJOUTER_cridt(ActionEvent event) throws SQLException {
        credit cf = new credit();

        cf.setMontCredit(Integer.parseInt(col_montan.getText()));
        cf.setDatePE(java.sql.Date.valueOf((col_datep.getValue())));
        cf.setDateDE(java.sql.Date.valueOf((col_dated.getValue())));
        cf.setDureeC(Integer.parseInt(col_dur.getText()));
        cf.setEcheance(java.sql.Date.valueOf((col_echance.getValue())));

        cf.setTauxInteret(Integer.parseInt(col_inter.getText()));

        cf.setDecision(false);
        cf.setEtatCredit(col_etat.getText());

        cf.setTypeCredit(col_type.getText());;
        ps.ajoutcredit(cf);
        List<credit> produits;
        try {
            produits = ps.getAll();
            ObservableList<credit> olp = FXCollections.observableArrayList(produits);
            tab_credit.setItems(olp);
            col_id.setCellValueFactory(new PropertyValueFactory("id"));
            collidcompt.setCellValueFactory(new PropertyValueFactory("numeroCompteId"));
            col_mont.setCellValueFactory(new PropertyValueFactory("montCredit"));
            col_datepe.setCellValueFactory(new PropertyValueFactory("datePE"));
            col_datede.setCellValueFactory(new PropertyValueFactory("dateDE"));
            col_dure.setCellValueFactory(new PropertyValueFactory("dureeC"));
            col_echeancec.setCellValueFactory(new PropertyValueFactory("echeance"));
            col_tauxc.setCellValueFactory(new PropertyValueFactory("tauxInteret"));
            col_decisionc.setCellValueFactory(new PropertyValueFactory("decision"));
            col_etatc.setCellValueFactory(new PropertyValueFactory("etatCredit"));
            colllllltype.setCellValueFactory(new PropertyValueFactory("typeCredit"));

            this.delete();
        } catch (SQLException ex) {
            Logger.getLogger(BacckController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void modifiercredit(ActionEvent event) throws SQLException {
        credit cf = ps.findById(idcredit);
        cf.setMontCredit(Integer.parseInt(col_montan.getText()));
        cf.setDatePE(java.sql.Date.valueOf((col_datep.getValue())));
        cf.setDateDE(java.sql.Date.valueOf((col_dated.getValue())));
        cf.setDureeC(Integer.parseInt(col_dur.getText()));
        cf.setEcheance(java.sql.Date.valueOf((col_echance.getValue())));
        cf.setTauxInteret(Integer.parseInt(col_inter.getText()));
        cf.setDecision(false);
        cf.setEtatCredit(col_etat.getText());
        cf.setTypeCredit(col_type.getText());;
        ps.updatecredit(cf, idcredit);
        JOptionPane.showMessageDialog(null, "Modification effectué avec success");
        List<credit> produits;
        try {
            produits = ps.getAll();
            ObservableList<credit> olp = FXCollections.observableArrayList(produits);
            tab_credit.setItems(olp);
            col_id.setCellValueFactory(new PropertyValueFactory("id"));
            collidcompt.setCellValueFactory(new PropertyValueFactory("numeroCompteId"));
            col_mont.setCellValueFactory(new PropertyValueFactory("montCredit"));
            col_datepe.setCellValueFactory(new PropertyValueFactory("datePE"));
            col_datede.setCellValueFactory(new PropertyValueFactory("dateDE"));
            col_dure.setCellValueFactory(new PropertyValueFactory("dureeC"));
            col_echeancec.setCellValueFactory(new PropertyValueFactory("echeance"));
            col_tauxc.setCellValueFactory(new PropertyValueFactory("tauxInteret"));
            col_decisionc.setCellValueFactory(new PropertyValueFactory("decision"));
            col_etatc.setCellValueFactory(new PropertyValueFactory("etatCredit"));
            colllllltype.setCellValueFactory(new PropertyValueFactory("typeCredit"));

            this.delete();
        } catch (SQLException ex) {
            Logger.getLogger(BacckController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void clickingmodi(MouseEvent event) throws SQLException {
        int idc = tabop.getSelectionModel().getSelectedItem().getId();
        OperationCredit opc = tabop.getSelectionModel().getSelectedItem();
        opcredit = opc.getId();
        if (opc == null) {
            return;
        }

        int creditid = opc.getCredit().getId();
        col_cridtid.setText(String.valueOf(creditid));
        col_dateop.setValue(LocalDate.parse(String.valueOf(opc.getDateOp())));
        col_mantanpayer.setText(String.valueOf(opc.getMontPayer()));
        col_echnaaceop.setValue(LocalDate.parse(String.valueOf(opc.getEcheance())));
        col_interetop.setText(String.valueOf(opc.getTauxInteret()));
        col_solva.setText(String.valueOf(opc.getSolvabilite()));
        col_typeop.setText(opc.getTypeOperation());

    }

    @FXML
    public void ClickModif(MouseEvent event) throws SQLException {
        int id = tab_credit.getSelectionModel().getSelectedItem().getId();
        credit cred = ps.findById(id);
        idcredit = cred.getId();
        col_num.setText(String.valueOf(cred.getNumeroCompteId()));
        col_montan.setText(String.valueOf(cred.getMontCredit()));
        col_datep.setValue(LocalDate.parse(String.valueOf(cred.getDatePE())));
        col_dated.setValue(LocalDate.parse(String.valueOf(cred.getDateDE())));
        col_dur.setText(String.valueOf(cred.getDureeC()));
        col_echance.setValue(LocalDate.parse(String.valueOf(cred.getEcheance())));
        col_inter.setText(String.valueOf(cred.getTauxInteret()));
        col_etat.setText(cred.getEtatCredit());
        col_type.setText(cred.getTypeCredit());

    }

    @FXML
    private void modifieroperation(ActionEvent event) throws SQLException {
        if (ops == null) {
            System.out.println("ops is null");
            return;
        }
        OperationCredit cf = ops.findById(opcredit);
        credit ccc;
        ccc = new credit(Integer.parseInt(col_cridtid.getText()));
        cf.setCredit(ccc);
        cf.setDateOp(java.sql.Date.valueOf((col_dateop.getValue())));
        cf.setMontPayer(Integer.parseInt(col_mantanpayer.getText()));
        cf.setEcheance(java.sql.Date.valueOf(col_echnaaceop.getValue()));
        cf.setTauxInteret(Integer.parseInt(col_interetop.getText()));
        cf.setSolvabilite(Integer.parseInt(col_solva.getText()));
        cf.setTypeOperation(col_typeop.getText());
        ops.updateOperationCredit(cf, idcredit);
        JOptionPane.showMessageDialog(null, "Modification effectué avec success");
        try {

            // TODO
            List<OperationCredit> OperationCredits = ops.getAll();
            ObservableList<OperationCredit> olp = FXCollections.observableArrayList(OperationCredits);
            tabop.setItems(olp);
            colop_id.setCellValueFactory(new PropertyValueFactory("id"));
            colop_credit.setCellValueFactory(new PropertyValueFactory("credit"));
            colop_dateop.setCellValueFactory(new PropertyValueFactory("dateOp"));
            colop_man.setCellValueFactory(new PropertyValueFactory("montPayer"));
            colop_echeance.setCellValueFactory(new PropertyValueFactory("echeance"));
            colop_taux.setCellValueFactory(new PropertyValueFactory("tauxInteret"));
            colop_solvabilite.setCellValueFactory(new PropertyValueFactory("solvabilite"));
            colop_operation.setCellValueFactory(new PropertyValueFactory("typeOperation"));
            this.deletee();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void ajouter_op(ActionEvent event) throws SQLException {
        OperationCredit op = new OperationCredit();
        credit ccc = new credit(Integer.parseInt(col_cridtid.getText()));
        op.setCredit(ccc);
        op.setDateOp(java.sql.Date.valueOf((col_dateop.getValue())));

        op.setMontPayer(Integer.parseInt(col_mantanpayer.getText()));

        op.setEcheance(java.sql.Date.valueOf(col_echnaaceop.getValue()));

        op.setTauxInteret(Integer.parseInt(col_interetop.getText()));

        op.setSolvabilite(Integer.parseInt(col_solva.getText()));

        op.setTypeOperation(col_typeop.getText());
        ops.ajoutOperationCredit(op);
        try {

            // TODO
            List<OperationCredit> OperationCredits = ops.getAll();
            ObservableList<OperationCredit> olp = FXCollections.observableArrayList(OperationCredits);
            tabop.setItems(olp);
            colop_id.setCellValueFactory(new PropertyValueFactory("id"));
            colop_credit.setCellValueFactory(new PropertyValueFactory("credit"));
            colop_dateop.setCellValueFactory(new PropertyValueFactory("dateOp"));
            colop_man.setCellValueFactory(new PropertyValueFactory("montPayer"));
            colop_echeance.setCellValueFactory(new PropertyValueFactory("echeance"));
            colop_taux.setCellValueFactory(new PropertyValueFactory("tauxInteret"));
            colop_solvabilite.setCellValueFactory(new PropertyValueFactory("solvabilite"));
            colop_operation.setCellValueFactory(new PropertyValueFactory("typeOperation"));
            this.deletee();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    public void delete() {
        col_detalc.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("delete");
                        b.setOnAction((event) -> {
                            try {
                                if (ps.suprimecredit(tab_credit.getItems().get(getIndex()))) {
                                    tab_credit.getItems().remove(getIndex());
                                    tab_credit.refresh();

                                }
                            } catch (SQLException ex) {
                                System.out.println("erreor:" + ex.getMessage());

                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });

    }

    public void deletee() {
        colop_dalate.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("delete");
                        b.setOnAction((event) -> {
                            try {
                                if (ops.suprimeOperationCredit(tabop.getItems().get(getIndex()))) {
                                    tabop.getItems().remove(getIndex());
                                    tabop.refresh();

                                }
                            } catch (SQLException ex) {
                                System.out.println("erreor:" + ex.getMessage());

                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });

    }

    @FXML
    private void sendermail(ActionEvent event) throws SQLException {
        List<credit> credits = ps.getAll();
        String mail = col_sender.getText();

        // create text table from list of credits
        StringBuilder tableBuilder = new StringBuilder();
        tableBuilder.append("ID| Montant| D.Paiement| D.echeance| Durée| Tintérêt| Type\n");
        for (credit c : credits) {
            tableBuilder.append(c.getId()).append("|\t");
            tableBuilder.append(c.getMontCredit()).append("|\t");
            tableBuilder.append(c.getDatePE()).append("|\t");
            tableBuilder.append(c.getEcheance()).append("|\t");
            tableBuilder.append(c.getDureeC()).append("|\t");
            tableBuilder.append(c.getTauxInteret()).append("|\t");
            tableBuilder.append(c.getTypeCredit()).append("\n");
        }

        // send email with formatted message
        String message = "Cher client,\n\nVous trouverez ci-joint la liste des crédits :\n\n" + tableBuilder.toString();
        Mailing.envoyermail("elbankservices@gmail.com", "loojpwkbsfeesgyi", mail, "Liste des Credits", message);
    }

    @FXML
    private void excel_extraction(ActionEvent event) {
       
    }

}
