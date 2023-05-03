package gui.ChequesChequiers;

import entities.Chequier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import services.ChequierCrud;
import tools.MyConnection;

import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

public class ChequierBackController implements Initializable {

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
    private TableColumn<Chequier,String> datecc;

    @FXML
    private TableColumn<Chequier,String> etat;

    @FXML
    private TableColumn<Chequier,String> motif;

    @FXML
    private TableColumn<Chequier,String> nomc;

    @FXML
    private TableColumn<Chequier,String> num;

    @FXML
    private TableColumn<Chequier,String> numeroc;

    @FXML
    private Pane pnlOverview;

    @FXML
    private AnchorPane root;

    @FXML
    private Button supprimer;

    @FXML
    private TableView<Chequier> table;

    Connection connection = null;
    ObservableList<Chequier> n = FXCollections.observableArrayList();
    ChequierCrud cc=new ChequierCrud();
    int id;
    int myIndex;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        connection = MyConnection.getInstance().getCnx();
        refresh();

    }

    public void refresh()
    {
        n.clear();
        confirmer.setDisable(true);
        supprimer.setDisable(true);

        //n.clear();
        n.addAll(cc.listerChequier());
        numeroc.setCellValueFactory(new PropertyValueFactory<Chequier,String>("num_compte_id"));
        datecc.setCellValueFactory(new PropertyValueFactory<Chequier,String>("date_creation"));
        motif.setCellValueFactory(new PropertyValueFactory<Chequier,String>("motif_chequier"));
        nomc.setCellValueFactory(new PropertyValueFactory<Chequier,String>("nom_client_id"));
        num.setCellValueFactory(new PropertyValueFactory<Chequier,String>("client_tel"));
        etat.setCellValueFactory(new PropertyValueFactory<Chequier,String>("etat_chequier"));
        table.setItems(n);
        table.setRowFactory(tv ->{
                    TableRow<Chequier> myRow=new TableRow<>();
                    myRow.setOnMouseClicked(ev ->{
                        if(ev.getClickCount()==1 && (!myRow.isEmpty())){
                            myIndex=table.getSelectionModel().getSelectedIndex();
                            id=table.getItems().get(myIndex).getId();
                            confirmer.setDisable(false);
                            supprimer.setDisable(false);
                            System.out.println(String.valueOf(table.getItems().get(myIndex).getId()));
                        }
                    });
                    return myRow;
                }
        );

    }
    @FXML
    void said(ActionEvent event) {

    }

    @FXML
    void say(ActionEvent event) {

    }

    @FXML
    void versAccueilBakc(ActionEvent event) {

    }

    @FXML
    void versCartesBack(ActionEvent event) {

    }

    @FXML
    void versChequesBack(ActionEvent event) {

    }

    @FXML
    void versComptesBack(ActionEvent event) {

    }

    @FXML
    void versCreditsBack(ActionEvent event) {

    }

    @FXML
    void versLogoutBack(ActionEvent event) {

    }

    @FXML
    void versReclamationsBack(ActionEvent event) {

    }

    @FXML
    void versTransactionsBack(ActionEvent event) {

    }

    @FXML
    void versUtilisateursBack(ActionEvent event) {

    }

}
