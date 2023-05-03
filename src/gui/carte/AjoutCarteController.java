package gui.carte;

import entities.Carte;
import entities.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.CarteServices;
import tools.MyConnection;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class AjoutCarteController implements Initializable {

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
    private Button btnCreditsBack;

    @FXML
    private Button btnLogoutBack;

    @FXML
    private Button btnReclamationsBack;

    @FXML
    private Button btnUtilisateursBack;

    @FXML
    private Button btncategorie;

    @FXML
    private TableColumn<Carte, String> col_date;

    @FXML
    private TableColumn<Carte, String> col_id;

    @FXML
    private TableColumn<Carte, String> col_idclient;

    @FXML
    private TableColumn<Carte, String> col_login;

    @FXML
    private TableColumn<Carte, String> col_mp;

    @FXML
    private TableColumn<Carte, String> col_num;

    @FXML
    private AnchorPane contenuBack;

    @FXML
    private TextField date;

    @FXML
    private TextField fid;


    @FXML
    private TextField idclient;

    @FXML
    private TextField login;

    @FXML
    private TextField mp;

    @FXML
    private TextField num_carte;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableView<Carte> table_stade;


    private Connection cnx = null;
    private PreparedStatement pst = null;


    private ObservableList<Carte> listM;
    private ObservableList<Carte> listS = FXCollections.observableArrayList();
    ArrayList name = new ArrayList();

    private ObservableList<Carte> listT = FXCollections.observableArrayList();

    ObservableList<Carte> list = FXCollections.observableArrayList();




    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection cnx = MyConnection.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM carte");
            while (rs.next()) {
                list.add(new Carte(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));

                table_stade.setItems(list);
                table_stade.refresh();

            }
        } catch (SQLException ex) {
            Logger.getLogger(AjoutCarteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            AfficherStade();
        } catch (SQLException ex) {
            Logger.getLogger(AjoutCarteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection cnx = MyConnection.getInstance().getCnx();
    }

    public void AfficherStade() throws SQLException {
        col_idclient.setCellValueFactory(new PropertyValueFactory<>("idclient"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date_ex"));
        col_mp.setCellValueFactory(new PropertyValueFactory<>("mp"));
        col_login.setCellValueFactory(new PropertyValueFactory<>("login"));
        col_num.setCellValueFactory(new PropertyValueFactory<>("num_carte"));


        table_stade.setItems(list);

    }
    @FXML
    void AjouterCarte(ActionEvent event) throws SQLException {
        Carte c = new Carte();
        c.setIdclient(idclient.getText());
        c.setDate_ex(date.getText());
        c.setMp(mp.getText());
        c.setLogin(login.getText());
        c.setNum_carte(num_carte.getText());

        CarteServices sp = new CarteServices();
            sp.ajouter(new Carte(c.getIdclient(), c.getDate_ex(), c.getNum_carte(), c.getLogin(), c.getNum_carte()));

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutCarte.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        initialize(null, null);

    }
    int idM;

    @FXML
    void Edit(ActionEvent event) {
        Carte c = new Carte();
        c.setIdclient(idclient.getText());
        c.setDate_ex(date.getText());
        c.setMp(mp.getText());
        c.setLogin(login.getText());
        c.setNum_carte(num_carte.getText());

        CarteServices sp = new CarteServices();
        sp.modifier(c, idM);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutCarte.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        initialize(null, null);
    }

    @FXML
    void getCategoriesView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutCategorie.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void getSelected(MouseEvent event) {
            int idc =table_stade.getSelectionModel().getSelectedItem().getId();
            CarteServices sp = new CarteServices();

            Carte c= sp.findId(idc);
            idM = idc;

            idclient.setText(c.getIdclient());
            date.setText(c.getDate_ex());
            mp.setText(c.getMp());
            login.setText(c.getLogin());
            num_carte.setText(c.getNum_carte());

    }

    @FXML
    void openReclamationBack(ActionEvent event) {
        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();

            Stage stage = new Stage();
            stage.setTitle("Les Reclamations");
            Parent root = FXMLLoader.load(getClass().getResource("../Reclamations/ReclamationBack.fxml"));

            Scene scene = new Scene(root);
            javafx.scene.image.Image icon = new Image(getClass().getResourceAsStream("../../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public Carte gettempCarte(TableColumn.CellEditEvent edittedCell) {
        Carte test = table_stade.getSelectionModel().getSelectedItem();
        return test;
    }
    @FXML
    void supprimer(ActionEvent event) throws SQLException {
        TableColumn.CellEditEvent edittedcell = null;
        Carte x = gettempCarte(edittedcell);
        // UpdateTable();

        if (x != null) {

            int i = x.getId();
            CarteServices cat = new CarteServices();

            int s = cat.supprimer(i);
            //UpdateTable();


            if (s == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("carte deleted");

                alert.showAndWait();

                listM = MyConnection.getDataCarte();

                table_stade.setItems(listM);
//                list.clear();
//        AfficherStade();
//                table_stade.setItems(list);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../credits/Credit.fxml"));
            Parent root = loader.load();
            table_stade.getScene().setRoot(root);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void versLogoutBack(ActionEvent event) {
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
