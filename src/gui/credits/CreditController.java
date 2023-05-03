package gui.credits;

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

public class CreditController implements Initializable {

    @FXML
    private Button Bupdate;

    @FXML
    private Button ComptesBack;

    @FXML
    private Button TransactionsBack;

    @FXML
    private Button TransactionsBack1;

    @FXML
    private Button btnAccueil;

    @FXML
    private Button btnCartesBack;

    @FXML
    private Button btnChequiersBack;

    @FXML
    private Button btnLogoutBack;

    @FXML
    private Button btnReclamationsBack;

    @FXML
    private Button btnUtilisateursBack;

    @FXML
    private Button btoperation;

    @FXML
    private Button btsuprimer;

    @FXML
    private TableColumn<Credit, String >  col_datede;

    @FXML
    private TableColumn<Credit, String >  col_datepe;

    @FXML
    private TableColumn<Credit, String >  col_decision;

    @FXML
    private TableColumn<Credit, String >  col_dureec;

    @FXML
    private TableColumn<Credit, String >  col_echeance;

    @FXML
    private TableColumn<Credit, String >  col_etatc;

    @FXML
    private TableColumn<?, ?> col_id;

    @FXML
    private TableColumn<Credit, String> col_montcredit;

    @FXML
    private TableColumn<Credit, String > col_numeroC;

    @FXML
    private TableColumn<Credit, String >  col_tauxint;

    @FXML
    private TableColumn<Credit, String >  col_typec;

    @FXML
    private AnchorPane contenuBack;

    @FXML
    private Button imp;

    @FXML
    private Label label;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Credit> table_stade;
    private Connection cnx = null;
    private PreparedStatement pst = null;
    private ObservableList<Credit> listM;
    private ObservableList<Credit> listS = FXCollections.observableArrayList();

    ArrayList name = new ArrayList();

    private ObservableList<Credit> listT = FXCollections.observableArrayList();

    ObservableList<Credit> list = FXCollections.observableArrayList();


    @FXML
    void demander(ActionEvent event) {

        // Create the custom dialog
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Ajout Box");
        dialog.setHeaderText("Ajouter un Credit ");
        ButtonType saveButtonType = new ButtonType("Ajouter", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);

        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, cancelButtonType);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setVgap(10);
        grid.setVgap(10);

        TextField tf1 = new TextField();
        tf1.setPromptText("numero_compte");
        TextField tf2 = new TextField();
        tf2.setPromptText("montCredit");
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        DatePicker tf3 = new DatePicker();
        tf3.setPromptText("datepe");
        DatePicker tf4 = new DatePicker();
        tf4.setPromptText("datede");
        TextField tf5 = new TextField();
        tf5.setPromptText("dureeC");
        DatePicker tf6 = new DatePicker();
        tf6.setPromptText("echeance");

        TextField tf7 = new TextField();
        tf7.setPromptText("tauxInteret");
        CheckBox tf8 = new CheckBox();
        TextField tf9 = new TextField();
        tf9.setPromptText("etatCredit");
        TextField tf10 = new TextField();
        tf10.setPromptText("typeCredit");




        //add tf and cb to the grid +lables

        grid.add(new Label("numero_compte:"), 0, 0);
        grid.add(tf1, 1, 0);
        ////////////
        grid.add(new Label("montCredit:"), 0, 1);
        grid.add(tf2, 1, 1);
        grid.add(new Label("datepe:"), 0, 2);
        grid.add(tf3, 1, 2);
        grid.add(new Label("datede:"), 0, 3);
        grid.add(tf4, 1, 3);
        ////
        grid.add(new Label("dureeC:"), 0, 4);
        grid.add(tf5, 1, 4);
        ///
        grid.add(new Label("echeance:"), 0, 5);
        grid.add(tf6, 1, 5);
        ///
        grid.add(new Label("tauxInteret:"), 0, 6);
        grid.add(tf7, 1, 6);
        ///
        grid.add(new Label("decision:"), 0, 7);
        grid.add(tf8, 1, 7);
        ///
        grid.add(new Label("etatCredit:"), 0, 8);
        grid.add(tf9, 1, 8);
        //
        grid.add(new Label("typeCredit:"), 0, 9);
        grid.add(tf10, 1, 9);
        //
        dialog.getDialogPane().setContent(grid);
        Optional<ButtonType> result = dialog.showAndWait();

        int numero_compte = Integer.parseInt(tf1.getText());
        int montCredit = Integer.parseInt(tf2.getText());//
        Date sqldatepe = Date.valueOf(tf3.getValue());
        Date sqldatede = Date.valueOf(tf4.getValue());
        int dureeC = Integer.parseInt(tf5.getText());
        Date sqlecheance = Date.valueOf(tf6.getValue());
        int tauxInteret = Integer.parseInt(tf7.getText());
        //
        boolean decision = (boolean) tf8.isSelected();
        if (decision) {
            tf8.setSelected(false);
        } else {
            tf8.setSelected(true);
        }
        String etatCredit = tf9.getText();
        String typeCredit = tf10.getText();
        Credit newMat = new Credit(montCredit, sqldatepe, sqldatede, dureeC, sqlecheance, tauxInteret, (boolean) decision, etatCredit, typeCredit, numero_compte);
        ServiceCredit ms = new ServiceCredit();
        ms.ajouter2(newMat);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Credit.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        initialize(null, null);


    }

    @FXML
    void goperation(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherOperation.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection con = MyConnection.getInstance().getCnx();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM credit");
            while (rs.next()) {
                list.add(new Credit(rs.getInt(1), rs.getInt(3), rs.getDate(4), rs.getDate(5), rs.getInt(6), rs.getDate(7), rs.getInt(8), rs.getBoolean(9), rs.getString(10), rs.getString(11), rs.getInt(2)));

                table_stade.setItems(list);
                table_stade.refresh();


            }
        } catch (SQLException ex) {
            Logger.getLogger(CreditController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            AfficherStade();
        } catch (SQLException ex) {
            Logger.getLogger(CreditController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
    public void AfficherStade() throws SQLException {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_montcredit.setCellValueFactory(new PropertyValueFactory<>("montCredit"));
        col_datepe.setCellValueFactory(new PropertyValueFactory<>("datepe"));
        col_datede.setCellValueFactory(new PropertyValueFactory<>("datede"));
        col_dureec.setCellValueFactory(new PropertyValueFactory<>("dureeC"));
        col_tauxint.setCellValueFactory(new PropertyValueFactory<>("tauxInteret"));
        col_echeance.setCellValueFactory(new PropertyValueFactory<>("echeance"));
        col_decision.setCellValueFactory(new PropertyValueFactory<>("decision"));
        col_etatc.setCellValueFactory(new PropertyValueFactory<>("etatCredit"));
        col_typec.setCellValueFactory(new PropertyValueFactory<>("typeCredit"));
        col_numeroC.setCellValueFactory(new PropertyValueFactory<>("numero_compte"));


        //UpdateTable();
        table_stade.setItems(list);

    }

    public Credit gettempCredit(TableColumn.CellEditEvent edittedCell) {
        Credit test = table_stade.getSelectionModel().getSelectedItem();
        return test;
    }
    @FXML
    void suprimerC(ActionEvent event) {
        TableColumn.CellEditEvent edittedcell = null;
        Credit x = gettempCredit(edittedcell);
        int i = x.getId();
        ServiceCredit cat = new ServiceCredit();
        cat.supprimer(i);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Credit.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        initialize(null, null);

    }

    @FXML
    void logoutBack(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Autentification");
            Parent root = FXMLLoader.load(getClass().getResource("../../gui/utilisateur/Autentification.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    void openReclamationBack(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les Reclamations");
            Parent root = FXMLLoader.load(getClass().getResource("../Reclamations/ReclamationBack.fxml"));

            Scene scene = new Scene(root);
            Image icon = new Image(getClass().getResourceAsStream("../../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }



    @FXML
    void updatecredit(ActionEvent event) {

        Credit matiere = table_stade.getSelectionModel().getSelectedItem();
        if (matiere != null) {
            // Create the custom dialog
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Mise à jour Box");
            dialog.setHeaderText("Mise à jour de la Credit " + matiere.getId());
            ButtonType saveButtonType = new ButtonType("Enregistrer", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButtonType = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);

            dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, cancelButtonType);
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setVgap(10);
            grid.setVgap(10);
            TextField tf = new TextField();
            tf.setPromptText("id");
            tf.setText(String.valueOf(matiere.getId()));
            TextField tf1 = new TextField();
            tf1.setPromptText("numero_compte");
            tf1.setText(String.valueOf(matiere.getNumero_compte()));
            TextField tf2 = new TextField();
            tf2.setPromptText("montCredit");
            tf2.setText(String.valueOf(matiere.getMontCredit()));
            Date date = (Date) matiere.getDatepe();
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
            String strDate = formatter.format(date);
            DatePicker tf3 = new DatePicker();
            tf3.setPromptText("datepe");
            DatePicker tf4 = new DatePicker();
            tf4.setPromptText("datede");
            TextField tf5 = new TextField();
            tf5.setPromptText("dureeC");
            tf5.setText(String.valueOf(matiere.getDureeC()));
            DatePicker tf6 = new DatePicker();
            tf6.setPromptText("echeance");

            TextField tf7 = new TextField();
            tf7.setPromptText("tauxInteret");
            tf7.setText(String.valueOf(matiere.getDureeC()));
            CheckBox tf8 = new CheckBox();
            tf8.setText(String.valueOf(matiere.isDecision()));
            TextField tf9 = new TextField();
            tf9.setPromptText("etatCredit");
            tf9.setText(String.valueOf(matiere.getEtatCredit()));
            TextField tf10 = new TextField();
            tf10.setPromptText("typeCredit");
            tf10.setText(String.valueOf(matiere.getTypeCredit()));




            //add tf and cb to the grid +lables
            grid.add(new Label("id de la credit:"), 0, 0);
            grid.add(tf, 1, 0);
            grid.add(new Label("numero_compte:"), 0, 1);
            grid.add(tf1, 1, 1);
            ////////////
            grid.add(new Label("montCredit:"), 0, 2);
            grid.add(tf2, 1, 2);
            grid.add(new Label("datepe:"), 0, 3);
            grid.add(tf3, 1, 3);
            grid.add(new Label("datede:"), 0, 4);
            grid.add(tf4, 1, 4);
            ////
            grid.add(new Label("dureeC:"), 0, 5);
            grid.add(tf5, 1, 5);
            ///
            grid.add(new Label("echeance:"), 0, 6);
            grid.add(tf6, 1, 6);
            ///
            grid.add(new Label("tauxInteret:"), 0, 7);
            grid.add(tf7, 1, 7);
            ///
            grid.add(new Label("decision:"), 0, 8);
            grid.add(tf8, 1, 8);
            ///
            grid.add(new Label("etatCredit:"), 0, 9);
            grid.add(tf9, 1, 9);
            //
            grid.add(new Label("typeCredit:"), 0, 10);
            grid.add(tf10, 1, 10);
            //
            dialog.getDialogPane().setContent(grid);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.get() == saveButtonType) {
                int id = Integer.parseInt(tf.getText());
                int numero_compte = Integer.parseInt(tf1.getText());
                int montCredit = Integer.parseInt(tf2.getText());//
                Date sqldatepe = Date.valueOf(tf3.getValue());
                Date sqldatede = Date.valueOf(tf4.getValue());
                int dureeC = Integer.parseInt(tf5.getText());
                Date sqlecheance = Date.valueOf(tf6.getValue());
                int tauxInteret = Integer.parseInt(tf7.getText());
                //
                boolean decision = (boolean) tf8.isSelected();
                if (decision) {
                    tf8.setSelected(false);
                } else {
                    tf8.setSelected(true);
                }
                String etatCredit = tf9.getText();
                String typeCredit = tf10.getText();


                Credit newMat = new Credit(id, montCredit, sqldatepe, sqldatede, dureeC, sqlecheance, tauxInteret, (boolean) decision, etatCredit, typeCredit, numero_compte);
                ServiceCredit ms = new ServiceCredit();
                ms.modifier(newMat);
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Credit.fxml"));
                    Parent root = loader.load();
                    table_stade.getScene().setRoot(root);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Aucune Operation n'a été sélectionnée");
                alert1.setHeaderText("Veuillez sélectionner une ligne depuis la table des matières");
                alert1.setContentText("Veuillez sélectionner une ligne depuis la table des matières");
                alert1.showAndWait();
            }


        }
        initialize(null, null);

    }

    @FXML
    void versAccueilBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../Home/Home.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    void versCartesBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../carte/AjoutCarte.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void versChequesBack(ActionEvent event) {

    }

    @FXML
    void versChequiersBack(ActionEvent event) {

    }

    @FXML
    void versComptesBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Comptes/Back/ComptesBack.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void versTransactionsBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Transactions/Back/TransactionsBack.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void versUtilisateursBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../utilisateur/UtilisateurBack.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
