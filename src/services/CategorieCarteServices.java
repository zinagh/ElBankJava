package services;

import entities.CategorieCarte;
import tools.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategorieCarteServices {

    private  final Connection conn;
    private PreparedStatement pst;
    private Statement ste;

    public CategorieCarteServices() {
        conn = MyConnection.getInstance().getCnx();
    }
    public void ajouter(CategorieCarte p) {
        String req = "INSERT INTO `categoriecarte` (`type`,`description`,`prix`,`montant_max`) VALUES ('" + p.getType() + "', '" + p.getDescription() + "', '" + p.getPrix() + "', '" + p.getMontant_max() +  "')";

        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("categorie created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void ajouter2(CategorieCarte p) {
        try {
            String req = "INSERT INTO categoriecarte  (type,description,prix,montant_max,date_categorie) VALUES (?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(req);
            ps.setString(1, p.getType());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getPrix());
            ps.setString(4, p.getMontant_max());
            ps.setTimestamp(5, p.getDate_categorie());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public int supprimer(int id)throws SQLException {
        int i = 0;
        try {
            Statement ste = conn.createStatement();
            String sql = "delete from categoriecarte where id=" + id;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(CategorieCarteServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return i;
    }
    public void modifier(CategorieCarte p) {
        try {
            String req = "UPDATE categoriecarte SET type=?, description=?, prix=?, montant_max=?, date_categorie=? WHERE id=?";
            PreparedStatement pstmt = conn.prepareStatement(req);
            pstmt.setString(1, p.getType());
            pstmt.setString(2, p.getDescription());
            pstmt.setDouble(3, Double.parseDouble(p.getPrix()));
            pstmt.setDouble(4, Double.parseDouble(p.getMontant_max()));
            pstmt.setTimestamp(5, p.getDate_categorie());
            pstmt.setInt(6, p.getId());

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("categorie updated !");
            } else {
                System.out.println("categorie not found !");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public List<CategorieCarte> getAll() {
        List<CategorieCarte> list = new ArrayList<>();
        String req = "Select * from categoriecarte";
        try {
            pst = conn.prepareStatement(req);
            ResultSet rs= pst.executeQuery();

            while(rs.next()){
                CategorieCarte p = new CategorieCarte();
                p.setId( rs.getInt("id") );
                p.setType(rs.getString(3));
                p.setDescription(rs.getString(4));
                p.setPrix(rs.getString(5));
                p.setMontant_max( rs.getString(6));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
}
