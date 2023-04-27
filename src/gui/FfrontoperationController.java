/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.OperationCredit;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class FfrontoperationController implements Initializable {

    @FXML
    private Text aff_dop;
    @FXML
    private Text aff_mont;
    @FXML
    private Text aff_taux;
    @FXML
    private Text aff_echna;
    @FXML
    private Text aff_solva;
    @FXML
    private Text aff_type;
    @FXML
    private Text aff_op;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public void setoperation(OperationCredit o) 
    {
                

    

   
            SimpleDateFormat ddateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String fformattedDate = ddateFormat.format(o.getDateOp());
    
      aff_dop.setText(fformattedDate);
       aff_mont.setText(Integer.toString(o.getMontPayer()));
    aff_taux.setText(Integer.toString(o.getTauxInteret()));
    
              SimpleDateFormat dddateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String ffformattedDate = dddateFormat.format(o.getEcheance());
    
      aff_echna.setText(ffformattedDate);
    
aff_solva.setText(Integer.toString(o.getSolvabilite()));
  aff_type.setText(o.getTypeOperation());
  
aff_op.setText(Integer.toString(o.getCredit().getId()));
  
    
    
    
    
    } 
    
}
