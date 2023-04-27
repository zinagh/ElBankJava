package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import entities.Carte;
import entities.CategorieCarte;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
/**
 *
 * @author user
 */
public class MyConnection {
    private final String url ="jdbc:mysql://localhost:3306/elbank";
    private final String login="root";
    private final String pwd="";

    Connection cnx;
    public static MyConnection instance;
    private MyConnection() {
        try {
            cnx= DriverManager.getConnection(url,login,pwd);
            System.out.println("Connexion etablie");
        } catch (SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ex.getMessage()");
        }
    }

    public Connection getCnx() {
        return cnx;
    }
    public static MyConnection getInstance(){
        if(instance == null){
            instance = new MyConnection();
        }
        return instance;
    }

    Connection conn = null;
    public static Connection ConnectDb(){
        try {

            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/elbank","root","");
            // JOptionPane.showMessageDialog(null, "Connection Established");

            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }




}