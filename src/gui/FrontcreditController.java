/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.credit;
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
public class FrontcreditController implements Initializable {

    @FXML
    private Text aff_numaa;
    @FXML
    private Text aff_mon;
    @FXML
    private Text aff_dp;
    @FXML
    private Text aff_duree;
    @FXML
    private Text aff_de;
    @FXML
    private Text aff_echan;
    @FXML
    private Text aff_taux;
    @FXML
    private Text aff_decision;
    @FXML
    private Text aff_type;
    @FXML
    private Text aff_etat;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    public void setProduit (credit c){
    
        
      aff_numaa.setText(Integer.toString(c.getNumeroCompteId().getId()));
    
      aff_mon.setText(Integer.toString(c.getMontCredit()));
    

   aff_dp.setText(Integer.toString(c.getDureeC()));
             SimpleDateFormat ddateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String fformattedDate = ddateFormat.format(c.getDureeC());
    
      aff_duree.setText(fformattedDate);
                   SimpleDateFormat dddateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String ffformattedDate = dddateFormat.format(c.getDatePE());
   
      aff_de.setText(ffformattedDate);
                         SimpleDateFormat ddddateFormat = new SimpleDateFormat("dd/MM/yyyy");
    String fffformattedDate = ddddateFormat.format(c.getEcheance());
   
      aff_echan.setText(fffformattedDate);
    
      aff_taux.setText(Integer.toString(c.getTauxInteret()));
      boolean decision = c.isDecision();
    
      aff_decision.setText(decision ? "Décision prise" : "Pas de décision");
    
      aff_type.setText(c.getTypeCredit());
    
      aff_etat.setText(c.getEtatCredit());
    
    
    
    }
    
}
