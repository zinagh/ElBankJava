package gui.carte;

import entities.CategorieCarte;
import entities.OperationCredit;
import entities.Utilisateur;
import gui.credits.AfficherOperationController;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import services.CategorieCarteServices;
import services.ServiceOperationCredit;
import tools.MyConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AfficherCategorieController implements Initializable {

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
    private TableColumn<?, ?> col_date;

    @FXML
    private TableColumn<CategorieCarte, String> col_ds;

    @FXML
    private TableColumn<CategorieCarte, String> col_id;

    @FXML
    private TableColumn<CategorieCarte, String> col_montant;

    @FXML
    private TableColumn<CategorieCarte, String> col_prix;

    @FXML
    private TableColumn<CategorieCarte, String> col_ty;

    @FXML
    private AnchorPane contenuBack;

    @FXML
    private TableColumn<CategorieCarte, String> idcarte;

    @FXML
    private TableView<CategorieCarte> table_stade;
    private Connection cnx = null;
    private PreparedStatement pst = null;


    private ObservableList<CategorieCarte> listM;
    private ObservableList<CategorieCarte> listS = FXCollections.observableArrayList();
    ArrayList name = new ArrayList();

    private ObservableList<CategorieCarte> listT = FXCollections.observableArrayList();

    ObservableList<CategorieCarte> list = FXCollections.observableArrayList();



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection con = MyConnection.getInstance().getCnx();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM categoriecarte");

            while (rs.next()) {
                CategorieCarte categorieCarte = new CategorieCarte(
                        rs.getInt("id"),
                        rs.getString("type"),
                        rs.getString("description"),
                        rs.getString("prix"),
                        rs.getString("montant_max"),
                        rs.getTimestamp("date_categorie")

                );
                    list.add(categorieCarte);
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
        col_ty.setCellValueFactory(new PropertyValueFactory<>("type"));
        col_ds.setCellValueFactory(new PropertyValueFactory<>("description"));
        col_prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        col_montant.setCellValueFactory(new PropertyValueFactory<>("montant_max"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date_categorie"));



        table_stade.setItems(list);

    }





    @FXML
    void OnRetour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutCarte.fxml"));
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
        dialog.setHeaderText("Ajouter une Categorie ");

        ButtonType saveButtonType = new ButtonType("Enregistrer", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);

        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, cancelButtonType);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);


        TextField tf1 = new TextField();
        tf1.setPromptText("type");
        TextField tf2 = new TextField();
        tf2.setPromptText("description");
        TextField tf3 = new TextField();
        tf3.setPromptText("prix");
        TextField tf4 = new TextField();
        tf4.setPromptText("montant_max");
        DatePicker tf5 = new DatePicker();
        tf5.setPromptText("date_categorie");



        grid.add(new Label("Type:"), 0, 0);
        grid.add(tf1, 1, 0);
        grid.add(new Label("description:"), 0, 1);
        grid.add(tf2, 1, 1);
        grid.add(new Label("prix:"), 0, 2);
        grid.add(tf3, 1, 2);
        grid.add(new Label("Montant Max:"), 0, 3);
        grid.add(tf4, 1, 3);
        grid.add(new Label("Date de categorie:"), 0, 4);
        grid.add(tf5, 1, 4);


        dialog.getDialogPane().setContent(grid);
        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == saveButtonType) {
            String type = tf1.getText();
            String description = tf2.getText();
            String prix = tf3.getText();
            String montant = tf4.getText();
            LocalDate localDate = tf5.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            Timestamp datecat = Timestamp.from(instant);


            CategorieCarte newMat = new CategorieCarte(type ,description, prix, montant, datecat);
            CategorieCarteServices ms = new CategorieCarteServices();
            ms.ajouter2(newMat);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutCategorie.fxml"));
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

    }
    public CategorieCarte gettempCateg(TableColumn.CellEditEvent edittedCell) {
        CategorieCarte test = table_stade.getSelectionModel().getSelectedItem();
        return test;
    }
    @FXML
    void suprimerop(ActionEvent event) throws SQLException {
        TableColumn.CellEditEvent edittedcell = null;
        CategorieCarte x = gettempCateg(edittedcell);
        int i = x.getId();
        CategorieCarteServices cat = new CategorieCarteServices();
        cat.supprimer(i);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutCategorie.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        initialize(null, null);
    }
    @FXML
    void updateoperation(ActionEvent event) {
        CategorieCarte matiere = table_stade.getSelectionModel().getSelectedItem();

        if (matiere != null) {

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Mise à jour Box");
            dialog.setHeaderText("Mise à jour de la Categorie " + matiere.getId());
            ButtonType saveButtonType = new ButtonType("Enregistrer", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButtonType = new ButtonType("Annuler", ButtonBar.ButtonData.CANCEL_CLOSE);

            dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, cancelButtonType);
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setHgap(10);
            grid.setVgap(10);
            //grid.setPadding(new Insets(ajout, 150, 10, 10));
            grid.setVgap(10);
            grid.setVgap(10);

            TextField tf1 = new TextField();
            tf1.setPromptText("type");
            tf1.setText(matiere.getType());

            TextField tf2 = new TextField();
            tf2.setPromptText("description");
            tf2.setText(matiere.getDescription());

            TextField tf3 = new TextField();
            tf3.setPromptText("prix");
            tf3.setText(String.valueOf(matiere.getPrix()));

            TextField tf4 = new TextField();
            tf4.setPromptText("montant_max");
            tf4.setText(String.valueOf(matiere.getMontant_max()));

            DatePicker tf5 = new DatePicker();
            tf5.setPromptText("date_categorie");
            tf5.setValue(matiere.getDate_categorie().toLocalDateTime().toLocalDate());



            grid.add(new Label("Type:"), 0, 0);
            grid.add(tf1, 1, 0);
            grid.add(new Label("description:"), 0, 1);
            grid.add(tf2, 1, 1);
            grid.add(new Label("prix:"), 0, 2);
            grid.add(tf3, 1, 2);
            grid.add(new Label("Montant Max:"), 0, 3);
            grid.add(tf4, 1, 3);
            grid.add(new Label("Date de categorie:"), 0, 4);
            grid.add(tf5, 1, 4);


            dialog.getDialogPane().setContent(grid);
            Optional<ButtonType> result = dialog.showAndWait();

            if (result.isPresent() && result.get() == saveButtonType) {
                String type = tf1.getText();
                String description = tf2.getText();
                String prix = tf3.getText();
                String montant = tf4.getText();
                LocalDate localDate = tf5.getValue();
                Timestamp timestamp = Timestamp.valueOf(localDate.atStartOfDay());


                CategorieCarte newMat = new CategorieCarte(type, description, prix, montant, timestamp);
                newMat.setId(matiere.getId()); // set the ID of the updated item

                CategorieCarteServices ms = new CategorieCarteServices();
                ms.modifier(newMat);
                table_stade.getItems().set(table_stade.getSelectionModel().getSelectedIndex(), newMat);

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutCategorie.fxml"));
                    Parent root = loader.load();
                    table_stade.getScene().setRoot(root);
                } catch (IOException ex) {
                    ex.printStackTrace();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutCarte.fxml"));
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
    void versCreditsBack(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../credits/Credit.fxml"));
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
