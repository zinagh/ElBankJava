package services;

import entities.Compte;
import entities.Reclamation;
import entities.Utilisateur;
import tools.MyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Zina Ghribi
 */
public class ReclamationCRUD {

    public void ajouterReclamation(Reclamation r) {
        String requete = "INSERT INTO reclamation (nom_u_id,type_rec,date_rec,etat_rec,desc_rec) VALUES"
                + "(?,?,?,?,?)";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, r.getNom_u_id());
            pst.setString(2, r.getType_rec());
            pst.setDate(3, r.getDate_rec());
            pst.setString(4, r.getEtat_rec());
            pst.setString(5, r.getDesc_rec());
            pst.executeUpdate();
            System.out.println("reclamation ajoutee");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int nbReclamation() {
        int nb = 0;
        String requete = "SELECT count(*) FROM `reclamation`";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            if (rs.first()) {
                nb = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nb;
    }

    public List<Reclamation> afficherReclamation() {
        List<Reclamation> myListReclamation = new ArrayList<>();
        try {
            String requete = "SELECT * FROM reclamation";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setId(rs.getInt(1));
                r.setNom_u_id(rs.getInt("nom_u_id"));
                r.setType_rec(rs.getString("type_rec"));
                r.setDate_rec(rs.getDate("date_rec"));
                r.setEtat_rec(rs.getString("etat_rec"));
                r.setDesc_rec(rs.getString("desc_rec"));
                myListReclamation.add(r);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return myListReclamation;
    }
    public void modifierReclamation(Reclamation r,int id){
        String requete = "UPDATE reclamation SET `nom_u_id`=?,`type_rec`=?,`date_rec`=?,`etat_rec`=?, `desc_rec`=? WHERE id=" + id;
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, r.getNom_u_id());
            pst.setString(2, r.getType_rec());
            pst.setString(3, String.valueOf(r.getDate_rec()));
            pst.setString(4, r.getEtat_rec());
            pst.setString(5, r.getDesc_rec());
            pst.executeUpdate();
            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification de la reclamation de l client avec id : " + r.getNom_u_id() + " a été éffectuée avec succès ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //    public void supprimerReclamation(int id){
//        String requete = "DELETE FROM `reclamation` WHERE id=" +id ;
//        try {
//            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
//            pst.executeUpdate();
//            System.out.println("reclamation supprimé");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    public void supprimerReclamation(int r) {
        try {
            String requete = "delete from `reclamation` where id=?";
            PreparedStatement ps;
            ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ps.setInt(1, r);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void traiterReclamation(int r) {
        try {
            String requete ="UPDATE reclamation SET `etat_rec`=? where id=?";
            PreparedStatement ps;
            ps = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ps.setString(1, "Traité");
            ps.setInt(2,r);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public List<Reclamation> afficherMesReclamations(int id) {
        List<Reclamation> myListReclamation = new ArrayList<>();
        try {
            String requete = "SELECT * from reclamation INNER JOIN utilisateur ON (reclamation.nom_u_id = utilisateur.id) where nom_u_id=" + id;
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setId(rs.getInt(1));
                r.setNom_u_id(rs.getInt("nom_u_id"));
                r.setType_rec(rs.getString("type_rec"));
                r.setDate_rec(rs.getDate("date_rec"));
                r.setEtat_rec(rs.getString("etat_rec"));
                r.setDesc_rec(rs.getString("desc_rec"));
                myListReclamation.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return myListReclamation;
    }

}
