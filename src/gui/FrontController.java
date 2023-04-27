/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.OperationCredit;
import entities.compte;
import entities.credit;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import service.OperationCreditService;
import service.creditService;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class FrontController implements Initializable {

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
    private GridPane gridProduit;
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
    private GridPane gridopreration;
    creditService ps = new creditService();
    OperationCreditService ops = new OperationCreditService();
    credit c = new credit(1);
    @FXML
    private TextField cher_cridit;
    @FXML
    private TextField cher_op;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gridProduit.getChildren().clear();
        gridopreration.getChildren().clear();
                                try {
            List<credit> evenements = ps.getAll();
            int row = 0;
            int column = 0;
            for (int i = 0; i < evenements.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("frontcredit.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                FrontcreditController controller = loader.getController();
                controller.setProduit(evenements.get(i));
              //  controller.setIdevent(evenements.get(i).getId_event());
                gridProduit.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
            }
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
                                
                                                        try {
            List<OperationCredit> evenementss = ops.getAll();
            int row = 0;
            int column = 0;
            for (int i = 0; i < evenementss.size(); i++) {
                //chargement dynamique d'usne interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ffrontoperation.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                FfrontoperationController controller = loader.getController();
                controller.setoperation(evenementss.get(i));
              //  controller.setIdevent(evenements.get(i).getId_event());
                gridopreration.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
            }
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        
        
    }    

    @FXML
    private void AJOUTER_cridt(ActionEvent event) throws SQLException {
        gridProduit.getChildren().clear();
        
             
                  

                compte cc = new compte(1);
                 
                  
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
       
                                       try {
            List<credit> evenements = ps.getAll();
            int row = 0;
            int column = 0;
            for (int i = 0; i < evenements.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("frontcredit.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                FrontcreditController controller = loader.getController();
                controller.setProduit(evenements.get(i));
              //  controller.setIdevent(evenements.get(i).getId_event());
                gridProduit.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
            }
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
             
            
           

        

                        
    }

    @FXML
    private void chercher(ActionEvent event) {
        int ca =Integer.parseInt(cher_cridit.getText());
        gridProduit.getChildren().clear();
        try {
            List<credit> evenements = ps.rechercheCredit(ca);
            int row = 0;
            int column = 0;
            for (int i = 0; i < evenements.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("frontcredit.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                FrontcreditController controller = loader.getController();
                controller.setProduit(evenements.get(i));
              //  controller.setIdevent(evenements.get(i).getId_event());
                gridProduit.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
            }
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ajouter_op(ActionEvent event) throws SQLException {
        
        gridopreration.getChildren().clear();
        OperationCredit op = new OperationCredit();
        credit ccc= new credit(Integer.parseInt(col_cridtid.getText()));
        op.setCredit(ccc);
             op.setDateOp(java.sql.Date.valueOf((col_dateop.getValue())));
    
   op.setMontPayer(Integer.parseInt(col_mantanpayer.getText()));
   
  op.setEcheance(java.sql.Date.valueOf(col_echnaaceop.getValue()));
    
     op.setTauxInteret(Integer.parseInt(col_interetop.getText()));
    
    op.setSolvabilite(Integer.parseInt(col_solva.getText()));
   
    op.setTypeOperation(col_typeop.getText());
    ops.ajoutOperationCredit(op);
    try {
            List<OperationCredit> evenementss = ops.getAll();
            int row = 0;
            int column = 0;
            for (int i = 0; i < evenementss.size(); i++) {
                //chargement dynamique d'usne interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ffrontoperation.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                FfrontoperationController controller = loader.getController();
                controller.setoperation(evenementss.get(i));
              //  controller.setIdevent(evenements.get(i).getId_event());
                gridopreration.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
            }
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
   
     
        
        
    }

    @FXML
    private void chercher_op(ActionEvent event) {
        int af=Integer.parseInt(cher_op.getText());
        gridopreration.getChildren().clear();
            try {
            List<OperationCredit> evenementss = ops.getAllByCreditId(af);
            int row = 0;
            int column = 0;
            for (int i = 0; i < evenementss.size(); i++) {
                //chargement dynamique d'usne interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ffrontoperation.fxml"));
                AnchorPane pane = loader.load();
               
                //passage de parametres
                FfrontoperationController controller = loader.getController();
                controller.setoperation(evenementss.get(i));
              //  controller.setIdevent(evenements.get(i).getId_event());
                gridopreration.add(pane, column, row);
                column++;
                if (column > 1) {
                    column = 0;
                    row++;
                }
            }
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
