//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package gui.ChequesChequiers;

import entities.Cheque;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.ChequeCrud;
import tools.MyConnection;

public class ChequeBackController implements Initializable {
    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;
    @FXML
    private Button ComptesBack;
    @FXML
    private Button TransactionsBack;
    @FXML
    private Button TransactionsBack1;
    @FXML
    private Button btnAccueilBack;
    @FXML
    private Button btnCartesBack;
    @FXML
    private Button btnChequiersBack;
    @FXML
    private Button btnCreditsBack;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnReclamationsBack;
    @FXML
    private Button btnUtilisateursBack;
    @FXML
    private TableColumn<Cheque, Integer> client_tel;
    @FXML
    private TableColumn<Cheque, Date> date_cheq;
    @FXML
    private ImageView datec;
    @FXML
    private TableColumn<Cheque, Date> enddate;
    @FXML
    private TableColumn<Cheque, Integer> colid;
    @FXML
    private TableColumn<Cheque, Integer> id_cheq;
    @FXML
    private TableColumn<Cheque, Integer> Destinataire;
    @FXML
    private TextField id_delete;
    @FXML
    private TableColumn<Cheque, String> lieu;
    @FXML
    private TableColumn<Cheque, Integer> montant;
    @FXML
    private Pane pnlOverview;
    @FXML
    private TableColumn<Cheque, Integer> prop;
    @FXML
    private TableColumn<Cheque, String> rib_receiver;
    @FXML
    private TableColumn<Cheque, String> rib_sender;
    @FXML
    private AnchorPane root;
    @FXML
    private TableColumn<Cheque, Integer> signature;
    @FXML
    private Button supprimer;
    @FXML
    private TableView<Cheque> table;
    int idModifier;
    Connection connection = null;
    ObservableList<Cheque> n = FXCollections.observableArrayList();
    ChequeCrud cc = new ChequeCrud();
    private String Motif;

    public ChequeBackController() {
    }

    public void initialize(URL location, ResourceBundle resources) {
        this.connection = MyConnection.getInstance().getCnx();
        this.refresh();
    }

    public void refresh() {
        this.n.clear();
        this.supprimer.setDisable(true);
        this.n.addAll(this.cc.Chequelister());
        this.colid.setCellValueFactory(new PropertyValueFactory("id"));
        this.prop.setCellValueFactory(new PropertyValueFactory("proprietaire_id"));
        this.Destinataire.setCellValueFactory(new PropertyValueFactory("destinataire_id"));
        this.id_cheq.setCellValueFactory(new PropertyValueFactory("idchequiers_id"));
        this.montant.setCellValueFactory(new PropertyValueFactory("montant"));
        this.date_cheq.setCellValueFactory(new PropertyValueFactory("date_cheque"));
        this.signature.setCellValueFactory(new PropertyValueFactory("signature"));
        this.lieu.setCellValueFactory(new PropertyValueFactory("lieu"));
        this.client_tel.setCellValueFactory(new PropertyValueFactory("client_tel"));
        this.rib_sender.setCellValueFactory(new PropertyValueFactory("rib_sender"));
        this.rib_receiver.setCellValueFactory(new PropertyValueFactory("rib_reciever"));
        this.table.setItems(this.n);
        this.table.setRowFactory((tv) -> {
            TableRow<Cheque> myRow = new TableRow();
            myRow.setOnMouseClicked((ev) -> {
                if (ev.getClickCount() == 1 && !myRow.isEmpty()) {
                    this.myIndex = this.table.getSelectionModel().getSelectedIndex();
                    this.id = ((Cheque)this.table.getItems().get(this.myIndex)).getId();
                    id_delete.setText(String.valueOf(this.id));
                    this.supprimer.setDisable(false);
                    System.out.println(String.valueOf(((Cheque)this.table.getItems().get(this.myIndex)).getId()));
                }

            });
            return myRow;
        });
    }

    @FXML
    void said(ActionEvent event) {
    }

    @FXML
    void say(ActionEvent event) {
    }

    public Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/elbank", "root", "");
            return conn;
        } catch (Exception var3) {
            System.out.println("ERROR" + var3.getMessage());
            return null;
        }
    }

    void executeQuery(String query) {
        Connection conn = this.getConnection();

        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception var5) {
            System.out.println("ERROR" + var5.getMessage());
        }

    }

    @FXML
    private void deletebtn() {
        String Query = "DELETE FROM cheques WHERE id =" + this.id_delete.getText() + "";
        this.executeQuery(Query);
        this.refresh();
    }

    @FXML
    void versAccueilBakc(ActionEvent actionEvent) {
        try {
            ((Node)((Node)actionEvent.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            stage.setTitle("Acceuil Admin");
            Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("../../Home/Home.fxml"));
            Scene scene = new Scene(root);
            Image icon = new Image(this.getClass().getResourceAsStream("../../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException var6) {
            System.out.println(var6.getMessage());
        }

    }

    @FXML
    void versCartesBack(ActionEvent actionEvent) {
        try {
            ((Node)((Node)actionEvent.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            stage.setTitle("Acceuil Admin");
            Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("../../carte/AjoutCarte.fxml"));
            Scene scene = new Scene(root);
            Image icon = new Image(this.getClass().getResourceAsStream("../../../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException var6) {
            System.out.println(var6.getMessage());
        }

    }

    @FXML
    void versChequesBack(ActionEvent actionEvent) {
        try {
            ((Node)((Node)actionEvent.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            stage.setTitle("Les chèques ");
            Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("../ChequeBack.fxml"));
            Scene scene = new Scene(root);
            Image icon = new Image(this.getClass().getResourceAsStream("../../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException var6) {
            System.out.println(var6.getMessage());
        }

    }

    @FXML
    void versComptesBack(ActionEvent actionEvent) {
        try {
            ((Node)((Node)actionEvent.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            stage.setTitle("Transaction");
            Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("../Comptes/Back/ComptesBack.fxml"));
            Scene scene = new Scene(root);
            Image icon = new Image(this.getClass().getResourceAsStream("../../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException var6) {
            System.out.println(var6.getMessage());
        }

    }

    @FXML
    void versCreditsBack(ActionEvent actionEvent) {
        try {
            ((Node)((Node)actionEvent.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            stage.setTitle("Les Crédits");
            Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("../credits/Credit.fxml"));
            Scene scene = new Scene(root);
            Image icon = new Image(this.getClass().getResourceAsStream("../../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException var6) {
            System.out.println(var6.getMessage());
        }

    }

    @FXML
    void versLogoutBack(ActionEvent actionEvent) {
        try {
            ((Node)((Node)actionEvent.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            stage.setTitle("Autentification");
            Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("Autentification.fxml"));
            Scene scene = new Scene(root);
            Image icon = new Image(this.getClass().getResourceAsStream("../../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException var6) {
            System.out.println(var6.getMessage());
        }

    }

    @FXML
    void versReclamationsBack(ActionEvent actionEvent) {
        try {
            ((Node)((Node)actionEvent.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            stage.setTitle("Les Reclamations");
            Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("../Reclamations/ReclamationBack.fxml"));
            Scene scene = new Scene(root);
            Image icon = new Image(this.getClass().getResourceAsStream("../../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException var6) {
            System.out.println(var6.getMessage());
        }

    }

    @FXML
    void versTransactionsBack(ActionEvent actionEvent) {
        try {
            ((Node)((Node)actionEvent.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            stage.setTitle("Transaction");
            Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("../Transactions/Back/TransactionsBack.fxml"));
            Scene scene = new Scene(root);
            Image icon = new Image(this.getClass().getResourceAsStream("../../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException var6) {
            System.out.println(var6.getMessage());
        }

    }

    @FXML
    void versUtilisateursBack(ActionEvent actionEvent) {
        try {
            ((Node)((Node)actionEvent.getSource())).getScene().getWindow().hide();
            Stage stage = new Stage();
            stage.setTitle("Autentification");
            Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("../../utilisateur/UtilisateurBack.fxml"));
            Scene scene = new Scene(root);
            Image icon = new Image(this.getClass().getResourceAsStream("../../../assets/Images/logo-Final.png"));
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException var6) {
            System.out.println(var6.getMessage());
        }

    }
}
