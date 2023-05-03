package services;

import tools.MyConnection;
import entities.Cheque;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChequeCrud {

    public void ajouterCheque(Cheque c) {
        try {
            String requete = "INSERT INTO cheques (proprietaire_id,montant,lieu,signature,client_tel,date_cheque,destinataire_id,idchequiers_id,rib_sender,rib_reciever) VALUES "
                    + "(?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, c.getProprietaire_id());
            pst.setDouble(2, c.getMontant());
            pst.setString(3, c.getLieu());
            pst.setInt(4, c.getSignature());
            pst.setInt(5, c.getClient_tel());

            Date date = new Date(Calendar.getInstance().getTime().getTime());
            pst.setDate(6, date);

            pst.setInt(7, c.getDestinataire_id());
            pst.setInt(8, c.getIdchequiers_id());
            pst.setString(9, c.getRib_sender());
            pst.setString(10, c.getRib_reciever());

                pst.executeUpdate();
                System.out.println("Cheque ajouté");


            System.out.println("Cheque ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.err.println("probleme dans les l'odre des champs dans BD");


        }
    }

    public void UpdateCheque(Cheque c) {

        String requete = "UPDATE cheques SET montant=?,lieu=?,client_tel=?,idchequiers_id=?  WHERE id=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);

            pst.setDouble(1, c.getMontant());
            pst.setString(2, c.getLieu());
            pst.setInt(3, c.getClient_tel());
            pst.setInt(4, c.getIdchequiers_id());
            pst.setInt(5, c.getId());

            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public List<Cheque> Chequelister() {
        List<Cheque> myList = new ArrayList();

// (proprietaire_id,date_cheque,montant,lieu,signature,client_tel,destinataire_id,idchequiers_id,rib_sender,rib_reciever)
        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            String req = "select * from cheques";
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Cheque c = new Cheque();
                c.setId(rs.getInt("id"));
                c.setProprietaire_id(rs.getInt("proprietaire_id"));
                c.setDate_cheque(new Date(rs.getTimestamp("date_cheque").getTime()));
                c.setMontant(rs.getInt("montant"));
                c.setLieu(rs.getString("lieu"));
                c.setSignature(rs.getInt("signature"));
                c.setClient_tel(rs.getInt("client_tel"));
                c.setDestinataire_id(rs.getInt("destinataire_id"));
                c.setIdchequiers_id(rs.getInt("idchequiers_id"));
                c.setRib_sender(rs.getString("rib_sender"));
                c.setRib_reciever(rs.getString("rib_reciever"));
                myList.add(c);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public void supprimerCheque(int c) {

        try {

            String requete = "DELETE  FROM cheques WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, c);
            pst.executeUpdate();
            System.out.println("Chéque supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public List<Cheque> listerCheq(int id) {
        List<Cheque> myList = new ArrayList();
        try {


            String req = "select * from cheques where idchequiers_id=" + id;
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(req, Statement.RETURN_GENERATED_KEYS);


            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Cheque c = new Cheque();
                c.setId(rs.getInt("id"));
                c.setProprietaire_id(rs.getInt("proprietaire_id"));
                c.setDate_cheque(new Date(rs.getTimestamp("date_cheque").getTime()));
                c.setMontant(rs.getInt("montant"));
                c.setLieu(rs.getString("lieu"));
                c.setSignature(rs.getInt("signature"));
                c.setClient_tel(rs.getInt("client_tel"));
                c.setDestinataire_id(rs.getInt("destinataire_id"));
                c.setIdchequiers_id(rs.getInt("idchequiers_id"));
                c.setRib_sender(rs.getString("rib_sender"));
                c.setRib_reciever(rs.getString("rib_reciever"));
                myList.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myList;
    }

    public String proptoid(int id) {
        String r = "";
        try {
            String req = "select * from compte where id=?";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, id);


            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                r = rs.getString(2);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return r;


    }
    public String idtoname(String id) {
        String r = "";
        try {
            String req = "select * from utilisateur where id=?";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setString(1, id);


            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                r = rs.getString(4) + " " + rs.getString(3);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return r;
    }
    public String desttoid(int id) {
        String r = "";
        try {
            String req = "select * from compte where id=?";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, id);


            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                r = rs.getString(2);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return r;
    }

}
