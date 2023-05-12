package Home;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;

import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;
import javafx.scene.input.KeyEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.json.JSONObject;

public class HomeController implements Initializable {
    private Parent fxml;

    @javafx.fxml.FXML
    private Button pubBack;
    @FXML
    private AnchorPane root;
    @FXML
    private Button ComptesBack;
    @FXML
    private Button TransactionsBack;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button btnCreditsBack;
    @FXML
    private Button devise1;
    @FXML
    private AnchorPane contenuBack;
    @FXML
    private Button btnChequiersBack;

    @FXML
    private Button btnOpCredBack;
    @FXML
    private Button btnUtilisateursBack;
    @FXML
    private Button TransactionsBack1;
    @FXML
    private Button btnLogoutBack;
    @FXML
    private Button btnCartesBack;
    @FXML
    private Button btnReclamationsBack;
    @FXML
    private Button btnCommentaireBack;
    @FXML
    private TableView<CurrencyEntry> tableView;

    @FXML
    private TableColumn<CurrencyEntry, String> currencyColumn;

    @FXML
    private TableColumn<CurrencyEntry, String> valueColumn;

    @FXML
    private TextField amountTextField;

    @FXML
    private Label money;
    double montantd;
    private FilteredList<CurrencyEntry> filteredData;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        currencyColumn.setCellValueFactory(new PropertyValueFactory<>("currency"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        // Add listener to searchTextField
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData(newValue);
        });
    }
    private void filterData(String currency) {
        filteredData.setPredicate(entry -> {
            // If the currency matches the filter or the filter is empty, show the entry
            if (currency == null || currency.isEmpty()) {
                return true;
            }

            // Filter based on currency
            String lowerCaseCurrency = currency.toLowerCase();
            return entry.getCurrency().toLowerCase().contains(lowerCaseCurrency);
        });
    }




    @FXML
    public void versComptesBack(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Transactions ");
            Parent root = FXMLLoader.load(getClass().getResource("../gui/Comptes/Back/ComptesBack.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void versTransactionsBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Transactions ");
            Parent root = FXMLLoader.load(getClass().getResource("../gui/Transactions/Back/TransactionsBack.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }



    @FXML
    public void versCreditsBack(ActionEvent actionEvent) {
       try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les Crédits");
            Parent root = FXMLLoader.load(getClass().getResource("../gui/credits/Credit.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @FXML
    public void versOps(ActionEvent actionEvent) {
    }


    @FXML
    public void versCartesBack(ActionEvent actionEvent) {
       try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les cartes ");
            Parent root = FXMLLoader.load(getClass().getResource("../gui/carte/AjoutCarte.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void versChequiersBack(ActionEvent actionEvent) {
         try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les chequiers ");
            Parent root = FXMLLoader.load(getClass().getResource("../gui/ChequesChequiers/ChequierBack.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void versUtilisateursBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Liste Utilisateurs");
            Parent root = FXMLLoader.load(getClass().getResource("/gui/utilisateur/UtilisateurBack.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void versChequesBack(ActionEvent actionEvent) {
       try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les chèques ");
            Parent root = FXMLLoader.load(getClass().getResource("../gui/ChequesChequiers/ChequeBack.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void logoutBack(Event event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Autentification");
            Parent root = FXMLLoader.load(getClass().getResource("/gui/utilisateur/Autentification.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void openReclamationBack(ActionEvent actionEvent) {
        try {
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Reclamations ");
            Parent root = FXMLLoader.load(getClass().getResource("../gui/Reclamations/ReclamationBack.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    public void devise(ActionEvent actionEvent) {
        String amountString = amountTextField.getText();

        montantd = Double.parseDouble(amountString);
        double rs;

        rs = montantd/ Float.parseFloat(JsonReader.show("TND"));
        money.setText(""+rs);
        money.setVisible(true);

    }
    @FXML
    public void convert(ActionEvent actionEvent) throws IOException {
        tableView.getItems().clear(); // Clear previous entries

        String amountString = amountTextField.getText();
        double amount = Double.parseDouble(amountString);

        JSONObject json = JsonReader.readJsonFromUrl("http://api.exchangeratesapi.io/v1/latest?access_key=89bedf05075fc89f6b9b6e62c388548c");
        JSONObject rates = json.getJSONObject("rates");

        ObservableList<CurrencyEntry> currencyEntries = FXCollections.observableArrayList();


        for (String currency : rates.keySet()) {
            double rate = rates.getDouble(currency);
            double convertedAmount = amount * rate;
            String value = String.valueOf(convertedAmount);

            CurrencyEntry entry = new CurrencyEntry(currency, value);
            System.out.println(entry);
            currencyEntries.add(entry);
        }
        // Create a new FilteredList based on the currencyEntries list
        filteredData = new FilteredList<>(currencyEntries);

        // Set filteredData as the items of the tableView
        tableView.setItems(filteredData);

        // Clear the searchTextField
        searchTextField.clear();


    }




}
