//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package gui.ChequesChequiers;

import entities.Chequier;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ResourceBundle;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
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
import javafx.util.Callback;
import services.ChequierCrud;
import services.ServiceOperationCredit;
import tools.MyConnection;

public class ChequierBackController implements Initializable {
    Connection con;
    PreparedStatement pst;
    int myIndex;
    int id;
    @FXML
    private Button stat;
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
    private Label chequiersAtt;
    @FXML
    private Button confirmer;
    @FXML
    private ImageView datec;
    @FXML
    private TableColumn<Chequier, String> datecc;
    @FXML
    private TableColumn<Chequier, String> etat;
    @FXML
    private TableColumn<Chequier, String> motif;
    @FXML
    private TableColumn<Chequier, String> nomc;
    @FXML
    private TableColumn<Chequier, String> num;
    @FXML
    private TableColumn<Chequier, String> numeroc;
    @FXML
    private Pane pnlOverview;
    @FXML
    private AnchorPane root;
    @FXML
    private Button supprimer;
    @FXML
    private TableView<Chequier> table;
    int idModifier;
    Connection connection = null;
    ObservableList<Chequier> n = FXCollections.observableArrayList();
    ChequierCrud cc = new ChequierCrud();
    private String Motif;

    public ChequierBackController() {
    }

    public void initialize(URL location, ResourceBundle resources) {
        this.connection = MyConnection.getInstance().getCnx();
        this.refresh();
    }

    public void refresh() {
        this.n.clear();
        this.confirmer.setDisable(true);
        this.supprimer.setDisable(true);
        this.n.addAll(this.cc.listerChequier());
        this.numeroc.setCellValueFactory(new PropertyValueFactory("num_compte_id"));
        this.datecc.setCellValueFactory(new PropertyValueFactory("date_creation"));
        this.motif.setCellValueFactory(new PropertyValueFactory("motif_chequier"));
        this.nomc.setCellValueFactory(new PropertyValueFactory("nom_client_id"));
        this.num.setCellValueFactory(new PropertyValueFactory("client_tel"));
        this.etat.setCellValueFactory(new PropertyValueFactory("etat_chequier"));
        this.table.setItems(this.n);
        this.table.setRowFactory((tv) -> {
            TableRow<Chequier> myRow = new TableRow();
            myRow.setOnMouseClicked((ev) -> {
                if (ev.getClickCount() == 1 && !myRow.isEmpty()) {
                    this.myIndex = this.table.getSelectionModel().getSelectedIndex();
                    this.id = ((Chequier)this.table.getItems().get(this.myIndex)).getId();
                    this.confirmer.setDisable(false);
                    this.supprimer.setDisable(false);
                    System.out.println(String.valueOf(((Chequier)this.table.getItems().get(this.myIndex)).getId()));
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

    public Chequier gettempchequier(TableColumn.CellEditEvent edittedCell) {
        Chequier test = table.getSelectionModel().getSelectedItem();
        return test;
    }

    @FXML
    private void deletebtn(ActionEvent event) {
        TableColumn.CellEditEvent edittedcell = null;
        Chequier x = gettempchequier(edittedcell);
        int i = x.getId();
        String Query = "DELETE FROM chequier WHERE id = " + i;
        this.executeQuery(Query);
        this.refresh();

    }




    public void addEtatColumn() {
        TableColumn<Chequier, Integer> colEtatChequier = new TableColumn<>("Etat");
        colEtatChequier.setCellValueFactory(new PropertyValueFactory<>("etat_chequier"));
        colEtatChequier.setCellFactory(column -> {
            TableCell<Chequier, Integer> cell = new TableCell<Chequier, Integer>() {
                @Override
                protected void updateItem(Integer item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText("");
                    } else {
                        setText(item == 1 ? "confirmé" : "non confirmé");
                    }
                }
            };
            cell.setOnMouseClicked(event -> {
                if (!cell.isEmpty()) {
                    Chequier chequier = cell.getTableView().getItems().get(cell.getIndex());
                    chequier.setEtat_chequier(1);
                    // call a method to update the chequier object in the database
                }
            });
            return cell;
        });
        this.table.getColumns().add(colEtatChequier);
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
            Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("ChequeBack.fxml"));
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
