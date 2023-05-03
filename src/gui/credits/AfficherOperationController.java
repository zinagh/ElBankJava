package gui.credits;

import entities.OperationCredit;
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
import services.ServiceOperationCredit;
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

public class AfficherOperationController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private Button Bupdate;

    @FXML
    private Button ComptesBack;

    @FXML
    private Button TransactionsBack;

    @FXML
    private Button TransactionsBack1;

    @FXML
    private Button btajout;

    @FXML
    private Button btnAccueil;

    @FXML
    private Button btnCartesBack;

    @FXML
    private Button btnChequiersBack;

    @FXML
    private Button btnCreditsBack;

    @FXML
    private Button btnLogoutBack;

    @FXML
    private Button btnReclamationsBack;

    @FXML
    private Button btnRetour;

    @FXML
    private Button btnUtilisateursBack;

    @FXML
    private Button btsuprimer;

    @FXML
    private TableColumn<OperationCredit, String> col_creditid;

    @FXML
    private TableColumn<OperationCredit, String> col_dateop;

    @FXML
    private TableColumn<OperationCredit, String> col_echance;

    @FXML
    private TableColumn<OperationCredit, String> col_id;

    @FXML
    private TableColumn<OperationCredit, String> col_montpayer;

    @FXML
    private TableColumn<OperationCredit, String> col_solvabilite;

    @FXML
    private TableColumn<OperationCredit, String> col_tauxinteret;

    @FXML
    private TableColumn<OperationCredit, String> col_typeoperation;

    @FXML
    private AnchorPane contenuBack;

    @FXML
    private TableView<OperationCredit> table_stade;



    private Connection cnx = null;
    private PreparedStatement pst = null;


    private ObservableList<OperationCredit> listM;
    private ObservableList<OperationCredit> listS = FXCollections.observableArrayList();
    ArrayList name = new ArrayList();

    private ObservableList<OperationCredit> listT = FXCollections.observableArrayList();

    ObservableList<OperationCredit> list = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection con = MyConnection.getInstance().getCnx();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM operation_credit");

            while (rs.next()) {
                OperationCredit operationCredit = new OperationCredit(
                        rs.getInt("id"),
                        rs.getInt("credit_id"),
                        rs.getDate("date_op"),
                        rs.getInt("mont_payer"),
                        rs.getDate("echeance"),
                        rs.getInt("taux_interet"),
                        rs.getInt("solvabilite"),
                        rs.getString("type_operation")

                );
                list.add(operationCredit);
            }

            table_stade.setItems(list);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherOperationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            AfficherStade();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public void AfficherStade() throws SQLException {
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_dateop.setCellValueFactory(new PropertyValueFactory<>("dateOp"));
        col_montpayer.setCellValueFactory(new PropertyValueFactory<>("montPayer"));
        col_echance.setCellValueFactory(new PropertyValueFactory<>("echeance"));
        col_tauxinteret.setCellValueFactory(new PropertyValueFactory<>("tauxInteret"));
        col_solvabilite.setCellValueFactory(new PropertyValueFactory<>("solvabilite"));
        col_typeoperation.setCellValueFactory(new PropertyValueFactory<>("typeOperation"));
        col_creditid.setCellValueFactory(new PropertyValueFactory<>("creditid"));


        //UpdateTable();
        table_stade.setItems(list);

    }

    public OperationCredit gettempOperationCredit(TableColumn.CellEditEvent edittedCell) {
        OperationCredit test = table_stade.getSelectionModel().getSelectedItem();
        return test;
    }
    @FXML
    void OnRetour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Credit.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void ajouteropp(ActionEvent event) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Ajouter");
        dialog.setHeaderText("Ajouter une operation ");

        ButtonType saveButtonType = new ButtonType("Enregistrer", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);

        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, cancelButtonType);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField tf = new TextField();
        tf.setPromptText("id");
        TextField tf1 = new TextField();
        tf1.setPromptText("montPayer");
        TextField tf2 = new TextField();
        tf2.setPromptText("tauxInteret");
        TextField tf3 = new TextField();
        tf3.setPromptText("solvabilite");
        TextField tf4 = new TextField();
        tf4.setPromptText("typeOperation");
        DatePicker tf5 = new DatePicker();
        tf5.setPromptText("DateOp");
        DatePicker tf6 = new DatePicker();
        tf6.setPromptText("Echeance");
        TextField tf7 = new TextField();
        tf.setPromptText("idcredit");

        grid.add(new Label("id de l'opération:"), 0, 0);
        grid.add(tf, 1, 0);
        grid.add(new Label("montant à payer:"), 0, 1);
        grid.add(tf1, 1, 1);
        grid.add(new Label("taux d'intérêt:"), 0, 2);
        grid.add(tf2, 1, 2);
        grid.add(new Label("solvabilité:"), 0, 3);
        grid.add(tf3, 1, 3);
        grid.add(new Label("type d'opération:"), 0, 4);
        grid.add(tf4, 1, 4);
        grid.add(new Label("Date de l'opération:"), 0, 5);
        grid.add(tf5, 1, 5);
        grid.add(new Label("Échéance:"), 0, 6);
        grid.add(tf6, 1, 6);
        grid.add(new Label("id de crédit:"), 0, 7);
        grid.add(tf7, 1, 7);

        dialog.getDialogPane().setContent(grid);
        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == saveButtonType) {
            int id = Integer.parseInt(tf.getText());
            int montPayer = Integer.parseInt(tf1.getText());
            int tauxInteret = Integer.parseInt(tf2.getText());
            int solvabilite = Integer.parseInt(tf3.getText());
            String typeOperation = tf4.getText();
            Date sqldateop = Date.valueOf(tf5.getValue());
            Date sqldateech = Date.valueOf(tf6.getValue());
            int idc = Integer.parseInt(tf7.getText());


            OperationCredit newMat = new OperationCredit(id, idc ,sqldateop, montPayer, sqldateech, tauxInteret, solvabilite, typeOperation);
            ServiceOperationCredit ms = new ServiceOperationCredit();
            ms.ajouter2(newMat);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherOperation.fxml"));
                Parent root = loader.load();
                table_stade.getScene().setRoot(root);
            } catch (IOException ex) {
                ex.printStackTrace();
            }


            initialize(null, null);
        }
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
    private void suprimerop(ActionEvent event) {
        TableColumn.CellEditEvent edittedcell = null;
        OperationCredit x = gettempOperationCredit(edittedcell);
        int i = x.getId();
        ServiceOperationCredit cat = new ServiceOperationCredit();
        cat.supprimer(i);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherOperation.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        initialize(null, null);
    }

    @FXML
    private void updateoperation(ActionEvent event) {
        OperationCredit matiere = table_stade.getSelectionModel().getSelectedItem();
        if (matiere != null) {
            // Create the custom dialog
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Mise à jour Box");
            dialog.setHeaderText("Mise à jour de la Operation " + matiere.getId());

            // Set the icon///////////////////////arj3ileha
            // File fileRefresh = new File("images/refresh_green.png");
            ////Image refresh = new Image(fileRefresh.toURI().toString());
            //dialog.setGraphic(new ImageView(refresh));
            // Set the button types
            ButtonType saveButtonType = new ButtonType("Enregistrer", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButtonType = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);

            dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, cancelButtonType);

            // Create the nom and niveau labels and fields
            //grid setting
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setHgap(10);
            grid.setVgap(10);
            //grid.setPadding(new Insets(ajout, 150, 10, 10));
            grid.setVgap(10);
            grid.setVgap(10);
            //nom tf init
            TextField tf = new TextField();
            tf.setPromptText("id");
            tf.setText(String.valueOf(matiere.getId()));
            ///montPayer
            TextField tf1 = new TextField();
            tf1.setPromptText("montPayer");
            tf1.setText(String.valueOf(matiere.getMontPayer()));

            ///

            TextField tf2 = new TextField();
            tf2.setPromptText("tauxInteret");
            tf2.setText(String.valueOf(matiere.getTauxInteret()));

            //solvabilite
            TextField tf3 = new TextField();
            tf3.setPromptText("solvabilite");
            tf3.setText(String.valueOf(matiere.getSolvabilite()));

            //typeOperation
            TextField tf4 = new TextField();
            tf4.setPromptText("typeOperation");
            tf4.setText(String.valueOf(matiere.getTypeOperation()));


            //int dureecredit = Integer.parseInt(tfdureecredit.getText());
            //niveau combobox init
          /*  ComboBox<String> cb = new ComboBox<>();
            cb.setItems(niveauxList());
            cb.setValue(matiere.getNiveau());*/

            //add tf and cb to the grid +lables
            grid.add(new Label("id de l operatioon:"), 0, 0);
            grid.add(tf, 1, 0);
            grid.add(new Label("montpayer:"), 0, 1);
            grid.add(tf1, 1, 1);
            ////////////
            grid.add(new Label("tauxInteret:"), 0, 2);
            grid.add(tf2, 1, 2);
            grid.add(new Label("solvabilite:"), 0, 3);
            grid.add(tf3, 1, 3);
            grid.add(new Label("typeOperation:"), 0, 4);
            grid.add(tf4, 1, 4);
            dialog.getDialogPane().setContent(grid);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.get() == saveButtonType) {
                //controle de saisie

                int id = Integer.parseInt(tf.getText());
                int montcredit = Integer.parseInt(tf1.getText());
                int tauxInteret = Integer.parseInt(tf2.getText());
                int solvabilite = Integer.parseInt(tf3.getText());

                OperationCredit newMat = new OperationCredit(id, matiere.getDateOp(), montcredit, matiere.getEcheance(), tauxInteret, solvabilite, tf4.getText());
                ServiceOperationCredit ms = new ServiceOperationCredit();
                ms.modifier(newMat);
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherOperation.fxml"));
                    Parent root = loader.load();
                    table_stade.getScene().setRoot(root);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        } else { //si aucune matière sélectionnée à partir du tableau
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);//before(date2)
            alert1.setTitle("Aucune Operation n'a été sélectionnée");
            alert1.setHeaderText("Veuillez sélectionner une ligne depuis la table des matières");
            alert1.setContentText("Veuillez sélectionner une ligne depuis la table des matières");
            alert1.showAndWait();
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
    void versCreditsBack(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Credit.fxml"));
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
