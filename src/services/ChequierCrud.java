//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package services;

import entities.Chequier;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import tools.MyConnection;

public class ChequierCrud {
    public ChequierCrud() {
    }

    public void ajouterChequier(Chequier c) {
        try {
            String requete = "INSERT INTO chequier (num_compte_id,date_creation,motif_chequier,etat_chequier,nom_client_id,client_tel) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, c.getNum_compte_id());
            Date date = new Date(Calendar.getInstance().getTime().getTime());
            pst.setDate(2, date);
            pst.setString(3, c.getMotif_chequier());
            pst.setInt(4, c.getEtat_chequier());
            pst.setInt(5, c.getNom_client_id());
            pst.setInt(6, c.getClient_tel());
            pst.executeUpdate();
            System.out.println("Chequier ajouté");
        } catch (SQLException var5) {
            System.out.println(var5.getMessage());
        }

    }

    public void UpdateChequier(Chequier c) {
        String requete = "UPDATE chequier SET num_compte_id=?,motif_chequier=?,etat_chequier=?,client_tel=? WHERE id=?";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, c.getNum_compte_id());
            pst.setString(2, c.getMotif_chequier());
            pst.setInt(3, c.getEtat_chequier());
            pst.setInt(4, c.getClient_tel());
            pst.setInt(5, c.getId());
            pst.executeUpdate();
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }

    }

    public List<Chequier> listerChequier() {
        List<Chequier> myList = new ArrayList();

        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            String req = "select * from chequier";
            ResultSet rs = st.executeQuery(req);

            while(rs.next()) {
                Chequier c = new Chequier();
                c.setId(rs.getInt("id"));
                c.setNum_compte_id(rs.getInt("num_compte_id"));
                c.setDate_creation(new Date(rs.getTimestamp("date_creation").getTime()));
                c.setMotif_chequier(rs.getString("motif_chequier"));
                c.setEtat_chequier(rs.getInt("etat_chequier"));
                c.setNom_client_id(rs.getInt("nom_client_id"));
                c.setClient_tel(rs.getInt("client_tel"));
                myList.add(c);
            }
        } catch (SQLException var6) {
            System.out.println(var6.getMessage());
        }

        return myList;
    }

    public void supprimerChequier(int id) {
        String requete = "DELETE FROM `utilisateur` WHERE id=" + id;

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Utilisateur supprimé");
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

    }

    public boolean updateEtat(int id) {
        ChequierCrud cs = new ChequierCrud();
        String requete = "update chequier set etat_chequier =1 where id=?";
        if (cs.VerifEtatChequier(id) == 0) {
            try {
                PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
                pst.setInt(1, id);
                pst.executeUpdate();
                System.out.println("Etat chequier updated");
                return true;
            } catch (SQLException var5) {
                System.out.println(var5.toString());
                return false;
            }
        } else {
            System.out.println("non");
            return false;
        }
    }

    public int VerifEtatChequier(int m) {
        int v = 0;
        String requete = "SELECT etat_chequier  from chequier  where id=?";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, m);

            for(ResultSet rs = pst.executeQuery(); rs.next(); v = rs.getInt(1)) {
            }
        } catch (SQLException var6) {
            System.err.println(var6.getMessage());
        }

        return v;
    }

    public List<Chequier> listerChequierEtat() {
        List<Chequier> myList = new ArrayList();

        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            String req = "select * from chequier";
            ResultSet rs = st.executeQuery(req);

            while(rs.next()) {
                Chequier c = new Chequier();
                c.setId(rs.getInt("id"));
                c.setNum_compte_id(rs.getInt("num_compte_id"));
                c.setDate_creation(new Date(rs.getTimestamp("date_creation").getTime()));
                c.setMotif_chequier(rs.getString("motif_chequier"));
                c.setEtat_chequier(rs.getInt("etat_chequier"));
                c.setNom_client_id(rs.getInt("nom_client_id"));
                c.setClient_tel(rs.getInt("client_tel"));
                if (c.getEtat_chequier() == 1) {
                    myList.add(c);
                }
            }
        } catch (SQLException var6) {
            System.out.println(var6.getMessage());
        }

        return myList;
    }

    public List<Chequier> listerChequierEtat0() {
        List<Chequier> myList = new ArrayList();

        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            String req = "select * from chequier";
            ResultSet rs = st.executeQuery(req);

            while(rs.next()) {
                Chequier c = new Chequier();
                c.setId(rs.getInt("id"));
                c.setNum_compte_id(rs.getInt("num_compte_id"));
                c.setDate_creation(new Date(rs.getTimestamp("date_creation").getTime()));
                c.setMotif_chequier(rs.getString("motif_chequier"));
                c.setEtat_chequier(rs.getInt("etat_chequier"));
                c.setNom_client_id(rs.getInt("nom_client_id"));
                c.setClient_tel(rs.getInt("client_tel"));
                if (c.getEtat_chequier() == 0) {
                    myList.add(c);
                }
            }
        } catch (SQLException var6) {
            System.out.println(var6.getMessage());
        }

        return myList;
    }

    public int motifToId(String motif) {
        int r = -1;

        try {
            String req = "select * from chequier where motif_chequier=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setString(1, motif);

            for(ResultSet rs = pst.executeQuery(); rs.next(); r = rs.getInt(1)) {
            }
        } catch (SQLException var6) {
            System.out.println(var6.getMessage());
        }

        return r;
    }

    public List<Chequier> listerChequiercmp(int ncmp) {
        List<Chequier> myList = new ArrayList();

        try {
            String req = "select * from chequier WHERE num_compte_id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, ncmp);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                Chequier c = new Chequier();
                c.setId(rs.getInt("id"));
                c.setNum_compte_id(rs.getInt("num_compte_id"));
                c.setDate_creation(new Date(rs.getTimestamp("date_creation").getTime()));
                c.setMotif_chequier(rs.getString("motif_chequier"));
                c.setEtat_chequier(rs.getInt("etat_chequier"));
                c.setNom_client_id(rs.getInt("nom_client_id"));
                c.setClient_tel(rs.getInt("client_tel"));
                myList.add(c);
            }
        } catch (SQLException var7) {
            System.out.println(var7.getMessage());
        }

        return myList;
    }

    public Chequier findByTel(String Motif) {
        Chequier u = new Chequier();
        String requete = "SELECT * FROM `utilisateur` WHERE motif_chequier = ?";
        PreparedStatement pst = null;

        try {
            pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, Motif);
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                u.setId(rs.getInt(1));
                u.setDate_creation(rs.getDate("datecc"));
                u.setNom_client_id(rs.getInt("nomc"));
                u.setMotif_chequier(rs.getString("motif"));
                u.setEtat_chequier(rs.getInt("etat"));
                u.setClient_tel(rs.getInt("num"));
            }
        } catch (SQLException var6) {
            var6.printStackTrace();
        }

        return u;
    }

    public static void modifierCompte(Chequier cmpt, int id) {
        try {
            String requete = "UPDATE chequier SET date_creation=?,motif_chequier=?,etat_chequier=?,client_tel=? WHERE id=? ";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(2, cmpt.getMotif_chequier());
            pst.setDate(1, (Date)cmpt.getDate_creation());
            pst.setInt(3, cmpt.getEtat_chequier());
            pst.setInt(4, cmpt.getEtat_chequier());
            pst.setInt(5, id);
            pst.executeUpdate();
            System.out.println("Compte modifié avec succès");
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }

    }

    public ResultSet nbr() {
        try {
            String req = "SELECT etat_chequier, COUNT(CASE WHEN etat_chequier = 0 THEN 1 ELSE NULL END) AS etat0, COUNT(CASE WHEN etat_chequier = 2 THEN 1 ELSE NULL END) AS etat2 FROM chequier GROUP BY etat_chequier";
            Statement st = MyConnection.getInstance().getCnx().prepareStatement(req);
            ResultSet RS = st.executeQuery(req);
            return RS;
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
            return null;
        }
    }
}
