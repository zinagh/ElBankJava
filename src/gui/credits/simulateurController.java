package gui.credits;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class simulateurController {

    @FXML
    private Button colsim;
    @FXML
    private Button retour;
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
    private Label txt_rs;

    @FXML
    private TextField txt_salaire;

    @FXML
    void simuler_credit(ActionEvent event) {
        String mtCreditText = txt_mt_credit.getText();
        int mtCredit = Integer.parseInt(mtCreditText);

        String mtdureetxt = txt_duree.getText();
        int mtduree = Integer.parseInt(mtdureetxt);


        String mtsalaireText = txt_salaire.getText();
        int mtsalaire = Integer.parseInt(mtsalaireText);

        // Calculate the totalsalary in the credit delais as a float
        float totalsalary = mtsalaire * 12.0f * mtduree;

        // Calculate the totalAmount as a float
        float totalAmount = mtCredit + ((mtCredit * 2) / 100.0f);

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

    @FXML
    public void retour(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les Crédits");
            Parent root = FXMLLoader.load(getClass().getResource("Frontusercredit.fxml"));

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
