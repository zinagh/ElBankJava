package gui;

import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class simulateurController {

    @FXML
    private Button colsim;

    @FXML
    private Label lbl_duree;

    @FXML
    private Label lbl_duree1;

    @FXML
    private Label lbl_montant;

    @FXML
    private Label lbl_salaire;

    @FXML
    private TextField txt_duree;

    @FXML
    private TextField txt_mt_credit;

    @FXML
    private TextField txt_ntr;

    @FXML
    private Label txt_rs;

    @FXML
    private TextField txt_salaire;

    @FXML
    void simuler_credit(ActionEvent event) {
    String mtCreditText = txt_mt_credit.getText();
    int mtCredit = Integer.parseInt(mtCreditText);
    
    String mtdureetxt = txt_duree.getText();
    int mtduree = Integer.parseInt(mtdureetxt);
    
    String mtntrtext = txt_ntr.getText();
    int mtntr = Integer.parseInt(mtntrtext);
    
    String mtsalaireText = txt_salaire.getText();
    int mtsalaire = Integer.parseInt(mtsalaireText);
 
    // Calculate the totalsalary in the credit delais as a float
    float totalsalary = mtsalaire * 12.0f * mtduree;
    
    // Calculate the totalAmount as a float
    float totalAmount = mtCredit + ((mtCredit * mtntr) / 100.0f);
    
    if ((totalsalary - (totalsalary * 0.6f)) - totalAmount > 0) {
        // If the condition is true, set the text of the txt_rs label to "Crédit sera accepté"
        txt_rs.setText("Crédit accepté");
        txt_rs.setTextFill(Color.GREEN);

        
    } else {
        // If the condition is false, set the text of the txt_rs label to "crédit refusé"
        txt_rs.setText("crédit refusé");
        txt_rs.setTextFill(Color.RED);

    }

    }

}
