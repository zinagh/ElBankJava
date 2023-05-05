package services;

import entities.Compte;
import entities.Transaction;
import tools.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionController {
    public void ajouterTransaction(Transaction trans) {
        try {
            String requete = "INSERT INTO transaction (rib_emetteur_id,montant_transaction,description_transaction,date_transaction,fullname_emetteur_id,fullname_recepteur,type_transaction,etat_transaction,rib_recepteur) VALUES "
                    + "('" + trans.getRIB_emetteur() + "','" + trans.getMontant_transaction() + "','" + trans.getDescription_transaction() + "','" + trans.getDate_transaction() + "','" + trans.getFullname_emetteur() + "','"
                    + trans.getFullname_recepteur() + "','" + trans.getType_transaction() + "','" + trans.getEtat_transaction() + "','" + trans.getRIB_recepteur() + "')";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.out.println("Transaction effectuée avec succès");
        } catch (SQLException ex) {
            Logger.getLogger(TransactionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ajouterTransaction2(Transaction trans) {
        try {
            String requete = "INSERT INTO transaction (rib_emetteur_id,montant_transaction,description_transaction,date_transaction,fullname_emetteur_id,fullname_recepteur,type_transaction,etat_transaction,rib_recepteur) VALUES  "
                    + "(?,?,?,?,?,?,?,?,?)";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, trans.getRIB_emetteur());
            pst.setFloat(2, trans.getMontant_transaction());
            pst.setString(3, trans.getDescription_transaction());
            pst.setString(4, trans.getDate_transaction());
            pst.setInt(5, trans.getFullname_emetteur());
            pst.setString(6, trans.getFullname_recepteur());
            pst.setString(7, trans.getType_transaction());
            pst.setInt(8, trans.getEtat_transaction());
            pst.setString(9, trans.getRIB_recepteur());
            pst.executeUpdate();
            System.out.println("Transaction effectuée avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Transaction> afficherTransactions() {
        List<Transaction> myList = new ArrayList();
        try {
            String requete = "SELECT * from transaction INNER JOIN compte ON (transaction .rib_emetteur_id = compte.id) INNER JOIN utilisateur ON (compte.fullname_client_id = utilisateur.id)";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Transaction trans = new Transaction();
                trans.setId(rs.getInt(1));
                trans.setRIB_emetteur(rs.getInt("RIB_emetteur_id"));
                trans.setRIB_recepteur(rs.getString("RIB_recepteur"));
                trans.setMontant_transaction(rs.getFloat("montant_transaction"));
                trans.setDescription_transaction(rs.getString("description_transaction"));
                trans.setFullname_recepteur(rs.getString("fullname_recepteur"));
                trans.setType_transaction(rs.getString("type_transaction"));
                trans.setEtat_transaction(rs.getInt("etat_transaction"));
                trans.setDate_transaction(rs.getString("date_transaction"));
                trans.setFullname_emetteur(rs.getInt("fullname_emetteur_id"));
                trans.setRib_fullname(rs.getString("RIB_Compte") + " - " + rs.getString("nom_u") + " " + rs.getString("prenom_u"));
                trans.setRibemet(rs.getString("RIB_Compte"));
                trans.setNomemet(rs.getString("nom_u") + " " + rs.getString("prenom_u"));
                myList.add(trans);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public List<Transaction> afficherMesTransactions(int id) {
        List<Transaction> myList = new ArrayList();
        try {
            String requete = "SELECT * from transaction INNER JOIN compte ON (transaction.rib_emetteur_id = compte.id) INNER JOIN utilisateur ON (compte.fullname_client_id = utilisateur.id) where transaction.rib_emetteur_id = " + id + " order by date_transaction limit 5";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Transaction trans = new Transaction();
                trans.setId(rs.getInt(1));
                trans.setRIB_emetteur(rs.getInt("RIB_emetteur_id"));
                trans.setRIB_recepteur(rs.getString("RIB_recepteur"));
                trans.setMontant_transaction(rs.getFloat("montant_transaction"));
                trans.setDescription_transaction(rs.getString("description_transaction"));
                trans.setFullname_recepteur(rs.getString("fullname_recepteur"));
                trans.setType_transaction(rs.getString("type_transaction"));
                trans.setEtat_transaction(rs.getInt("etat_transaction"));
                trans.setDate_transaction(rs.getString("date_transaction"));
                trans.setFullname_emetteur(rs.getInt("fullname_emetteur_id"));
                trans.setRib_fullname(rs.getString("RIB_Compte") + " - " + rs.getString("nom_u") + " " + rs.getString("prenom_u"));
                trans.setRibemet(rs.getString("RIB_Compte"));
                trans.setNomemet(rs.getString("nom_u") + " " + rs.getString("prenom_u"));
                myList.add(trans);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public List<Transaction> afficherMesTransactionsReleve(int id) {
        CompteController cmpt = new CompteController();
        Compte c = cmpt.rechercherCompte(id);
        String r = c.getRIB_Compte().replaceAll("\\s+", "");
        List<Transaction> myList = new ArrayList();
        try {
            String requete = "SELECT * from transaction INNER JOIN compte ON (transaction.rib_emetteur_id = compte.id) INNER JOIN utilisateur ON (compte.fullname_client_id = utilisateur.id) where (transaction.rib_emetteur_id = " + id + " or replace(transaction.RIB_recepteur, ' ', '') = " + r + ") order by date_transaction limit 40";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Transaction trans = new Transaction();
                trans.setId(rs.getInt(1));
                trans.setRIB_emetteur(rs.getInt("RIB_emetteur_id"));
                trans.setRIB_recepteur(rs.getString("RIB_recepteur"));
                trans.setMontant_transaction(rs.getFloat("montant_transaction"));
                trans.setDescription_transaction(rs.getString("description_transaction"));
                trans.setFullname_recepteur(rs.getString("fullname_recepteur"));
                trans.setType_transaction(rs.getString("type_transaction"));
                trans.setEtat_transaction(rs.getInt("etat_transaction"));
                trans.setDate_transaction(rs.getString("date_transaction"));
                trans.setFullname_emetteur(rs.getInt("fullname_emetteur_id"));
                trans.setRib_fullname(rs.getString("RIB_Compte") + " - " + rs.getString("nom_u") + " " + rs.getString("prenom_u"));
                trans.setRibemet(rs.getString("RIB_Compte"));
                trans.setNomemet(rs.getString("nom_u") + " " + rs.getString("prenom_u"));
                myList.add(trans);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public List<Transaction> afficherMesTransactionsRecepteur(String rib) {
        List<Transaction> myList = new ArrayList();
        String r = rib.replaceAll("\\s+", "");
        try {
            String requete = "SELECT * from transaction INNER JOIN compte ON (transaction .rib_emetteur_id = compte.id) INNER JOIN utilisateur ON (compte.fullname_client_id = utilisateur.id) where replace(transaction.RIB_recepteur, ' ', '') = " + r + " order by date_transaction limit 5";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Transaction trans = new Transaction();
                trans.setId(rs.getInt(1));
                trans.setRIB_emetteur(rs.getInt("RIB_emetteur_id"));
                trans.setRIB_recepteur(rs.getString("RIB_recepteur"));
                trans.setMontant_transaction(rs.getFloat("montant_transaction"));
                trans.setDescription_transaction(rs.getString("description_transaction"));
                trans.setFullname_recepteur(rs.getString("fullname_recepteur"));
                trans.setType_transaction(rs.getString("type_transaction"));
                trans.setEtat_transaction(rs.getInt("etat_transaction"));
                trans.setDate_transaction(rs.getString("date_transaction"));
                trans.setFullname_emetteur(rs.getInt("fullname_emetteur_id"));
                trans.setRib_fullname(rs.getString("RIB_Compte") + " - " + rs.getString("nom_u") + " " + rs.getString("prenom_u"));
                trans.setRibemet(rs.getString("RIB_Compte"));
                trans.setNomemet(rs.getString("nom_u") + " " + rs.getString("prenom_u"));
                myList.add(trans);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public Transaction rechercherTransaction(int id) {
        Transaction trans = new Transaction();
        try {
            String requete = "SELECT * from transaction INNER JOIN compte ON (transaction.rib_emetteur_id = compte.id) INNER JOIN utilisateur ON (compte.fullname_client_id = utilisateur.id) where transaction.id=" + id + "";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                trans.setId(rs.getInt(1));
                trans.setRIB_emetteur(rs.getInt("RIB_emetteur_id"));
                trans.setRIB_recepteur(rs.getString("RIB_recepteur"));
                trans.setMontant_transaction(rs.getFloat("montant_transaction"));
                trans.setDescription_transaction(rs.getString("description_transaction"));
                trans.setFullname_recepteur(rs.getString("fullname_recepteur"));
                trans.setType_transaction(rs.getString("type_transaction"));
                trans.setEtat_transaction(rs.getInt("etat_transaction"));
                trans.setDate_transaction(rs.getString("date_transaction"));
                trans.setFullname_emetteur(rs.getInt("fullname_emetteur_id"));
                trans.setRib_fullname(rs.getString("RIB_Compte") + " - " + rs.getString("nom_u") + " " + rs.getString("prenom_u"));
                trans.setRibemet(rs.getString("RIB_Compte"));
                trans.setNomemet(rs.getString("nom_u") + " " + rs.getString("prenom_u"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return trans;
    }

    public void modifierTransaction(Transaction trans, int id) {
        try {
            String requete = "UPDATE transaction SET rib_emetteur_id=?,montant_transaction=?,description_transaction=?,date_transaction=?,fullname_emetteur_id=?,fullname_recepteur=?,type_transaction=?,etat_transaction=?,rib_recepteur=? WHERE id=? ";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, trans.getRIB_emetteur());
            pst.setFloat(2, trans.getMontant_transaction());
            pst.setString(3, trans.getDescription_transaction());
            pst.setString(4, trans.getDate_transaction());
            pst.setInt(5, trans.getFullname_emetteur());
            pst.setString(6, trans.getFullname_recepteur());
            pst.setString(7, trans.getType_transaction());
            pst.setInt(8, trans.getEtat_transaction());
            pst.setString(9, trans.getRIB_recepteur());
            pst.setInt(10, id);
            pst.executeUpdate();
            System.out.println("Transaction modifiée avec succès");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerTransaction(int id) {
        try {
            String requete = "DELETE FROM transaction WHERE id='" + id + "'";
            Statement pst = MyConnection.getInstance().getCnx().createStatement();
            pst.executeUpdate(requete);
            System.out.println("Transaction supprimée avec succès");
        } catch (SQLException ex) {
            Logger.getLogger(CompteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String afficherMonNomRib(int idtrans) {
        String nom = "";
        try {
            String requete = "SELECT rib_compte, nom_u, prenom_u from transaction INNER JOIN compte ON (transaction .rib_emetteur_id = compte.id) INNER JOIN utilisateur ON (compte.fullname_client_id = utilisateur.id) where transaction.id = " + idtrans + "";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                nom = rs.getString("rib_compte") + " - " + rs.getString("nom_u") + " " + rs.getString("prenom_u");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nom;
    }
    public List<Transaction> afficherTransactionsTriRecepteur() {
        List<Transaction> myList = new ArrayList();
        try {
            String requete = "SELECT * from transaction INNER JOIN compte ON (transaction .rib_emetteur_id = compte.id) INNER JOIN utilisateur ON (compte.fullname_client_id = utilisateur.id) order by transaction.fullname_recepteur ASC";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Transaction trans = new Transaction();
                trans.setId(rs.getInt(1));
                trans.setRIB_emetteur(rs.getInt("RIB_emetteur_id"));
                trans.setRIB_recepteur(rs.getString("RIB_recepteur"));
                trans.setMontant_transaction(rs.getFloat("montant_transaction"));
                trans.setDescription_transaction(rs.getString("description_transaction"));
                trans.setFullname_recepteur(rs.getString("fullname_recepteur"));
                trans.setType_transaction(rs.getString("type_transaction"));
                trans.setEtat_transaction(rs.getInt("etat_transaction"));
                trans.setDate_transaction(rs.getString("date_transaction"));
                trans.setFullname_emetteur(rs.getInt("fullname_emetteur_id"));
                trans.setRib_fullname(rs.getString("RIB_Compte") + " - " + rs.getString("nom_u") + " " + rs.getString("prenom_u"));
                trans.setRibemet(rs.getString("RIB_Compte"));
                trans.setNomemet(rs.getString("nom_u") + " " + rs.getString("prenom_u"));
                myList.add(trans);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public List<Transaction> afficherTransactionsTriDate() {
        List<Transaction> myList = new ArrayList();
        try {
            String requete = "SELECT * from transaction INNER JOIN compte ON (transaction .rib_emetteur_id = compte.id) INNER JOIN utilisateur ON (compte.fullname_client_id = utilisateur.id) order by transaction.date_transaction DESC";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Transaction trans = new Transaction();
                trans.setId(rs.getInt(1));
                trans.setRIB_emetteur(rs.getInt("RIB_emetteur_id"));
                trans.setRIB_recepteur(rs.getString("RIB_recepteur"));
                trans.setMontant_transaction(rs.getFloat("montant_transaction"));
                trans.setDescription_transaction(rs.getString("description_transaction"));
                trans.setFullname_recepteur(rs.getString("fullname_recepteur"));
                trans.setType_transaction(rs.getString("type_transaction"));
                trans.setEtat_transaction(rs.getInt("etat_transaction"));
                trans.setDate_transaction(rs.getString("date_transaction"));
                trans.setFullname_emetteur(rs.getInt("fullname_emetteur_id"));
                trans.setRib_fullname(rs.getString("RIB_Compte") + " - " + rs.getString("nom_u") + " " + rs.getString("prenom_u"));
                trans.setRibemet(rs.getString("RIB_Compte"));
                trans.setNomemet(rs.getString("nom_u") + " " + rs.getString("prenom_u"));
                myList.add(trans);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }
    public List<Transaction> SuppressionCompteTransaction(int id) {
        CompteController cmpt = new CompteController();
        Compte c = cmpt.rechercherCompte(id);
        String r = c.getRIB_Compte().replaceAll("\\s+", "");
        List<Transaction> myList = new ArrayList();
        try {
            String requete = "SELECT * from transaction INNER JOIN compte ON (transaction.rib_emetteur_id = compte.id) INNER JOIN utilisateur ON (compte.fullname_client_id = utilisateur.id) where (transaction.rib_emetteur_id = " + id + " or replace(transaction.RIB_recepteur, ' ', '') = " + r + ")";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Transaction trans = new Transaction();
                trans.setId(rs.getInt(1));
                trans.setRIB_emetteur(rs.getInt("RIB_emetteur_id"));
                trans.setRIB_recepteur(rs.getString("RIB_recepteur"));
                trans.setMontant_transaction(rs.getFloat("montant_transaction"));
                trans.setDescription_transaction(rs.getString("description_transaction"));
                trans.setFullname_recepteur(rs.getString("fullname_recepteur"));
                trans.setType_transaction(rs.getString("type_transaction"));
                trans.setEtat_transaction(rs.getInt("etat_transaction"));
                trans.setDate_transaction(rs.getString("date_transaction"));
                trans.setFullname_emetteur(rs.getInt("fullname_emetteur_id"));
                trans.setRib_fullname(rs.getString("RIB_Compte") + " - " + rs.getString("nom_u") + " " + rs.getString("prenom_u"));
                trans.setRibemet(rs.getString("RIB_Compte"));
                trans.setNomemet(rs.getString("nom_u") + " " + rs.getString("prenom_u"));
                myList.add(trans);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

}
