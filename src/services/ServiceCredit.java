/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Credit;
import tools.MyConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author user
 */
public class ServiceCredit {
    Connection cnx = MyConnection.getInstance().getCnx();

    public void ajouter2(Credit c) {
        try {
            String req = "INSERT INTO credit (mont_credit,datepe,datede,duree_C,echeance,taux_Interet,decision,etat_Credit,type_Credit,numero_compte_id) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, c.getMontCredit());
            // Date date = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(c.getDatepe());
            ps.setDate(2, (Date) c.getDatepe());
            ps.setDate(3, (Date) c.getDatede());
            ps.setInt(4, c.getDureeC());
            ps.setDate(5, (Date) c.getEcheance());
            ps.setInt(6, c.getTauxInteret());
            ps.setBoolean(7, c.isDecision());
            ps.setString(8, c.getEtatCredit());
            ps.setString(9, c.getTypeCredit());
            ps.setInt(10, c.getNumero_compte());


            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /*
     public List<Credit> listeCredit(){
         List<Credit> list=new ArrayList<>();

         try {
             String req = "select * from credi";
             Statement st = Connection.getInstance().getCnx().createStatement();
             Credit rs = st.executeQuery(req);
             ServiceCredit ns=new ServiceCredit();
             while (rs.next()) {
                 Credit c=new Credit();
                 c.setId(rs.getInt("id"));
                 c.setClasse(rs.getString("classe"));
                 c.setNiveau(ns.getOneById(rs.getString("niveau_id")));
                 list.add(c);
             }
         }catch (SQLException ex) {
             System.out.println(ex.getMessage());
         }

         return list;
     } */
    public List<Credit> getAll() {
        List<Credit> list = new ArrayList<>();
        String req = "Select * from credit";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Credit p = new Credit();
                p.setId(rs.getInt("id"));
                p.setNumero_compte(rs.getInt(2));
                //p.setMontCredit(rs.getInt(3));
                p.setDatepe(rs.getDate(4));
                p.setDatede(rs.getDate(5));
                p.setDureeC(rs.getInt(6));
                p.setEcheance(rs.getDate(7));
                p.setTauxInteret(rs.getInt(8));
                p.setDecision(rs.getBoolean(9));
                p.setEtatCredit(rs.getString(10));
                p.setTypeCredit(rs.getString(11));
                //p.setNumero_compte( rs.getInt(12));


                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public void modifier(Credit p) {
        try {
            // boolean decision =(boolean) p.isDecision();
            String req = "UPDATE credit SET mont_Credit = '" + p.getMontCredit() + "', datepe = '" + p.getDatepe() + "', duree_c = '" + p.getDureeC() + "', echeance = '" + p.getEcheance() + "', taux_interet = '" + p.getTauxInteret() + "', decision = '" + 1 + "', etat_credit = '" + p.getEtatCredit() + "', type_credit = '" + p.getTypeCredit() + "', numero_compte_id = '" + p.getNumero_compte() + "' WHERE credit.`id` = " + p.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Carte updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    public void supprimer(int id) {
        try {
            String req = "DELETE FROM credit WHERE id = " + id;
            Statement ste = cnx.createStatement();
            ste.executeUpdate(req);
            System.out.println("Credit deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String simulation(int montCredit, int dureec, int salaire, String typeCredit) {
        int tauxInteret;
        String r = "";
        switch (typeCredit) {
            case "voiture":
                tauxInteret = 20;
                if (((montCredit) * (1 + (tauxInteret / 100)) / (12 * dureec)) < ((salaire) / 2))
                    r = (" tu peux avoir un credit voiture avec une écheance de " + ((montCredit) * (1 + (tauxInteret / 100)) / (12 * dureec)) + " par mois sur " + dureec + "années");
                else
                    r = ("votre salaire de" + salaire + "vous ne permet pas d avoire cette credit esaiyer une autre foit");
                break;
            case "maison":
                tauxInteret = 25;
                if (((montCredit) * (1 + (tauxInteret / 100)) / (12 * dureec)) < ((salaire) / 2))
                    r = (" tu peux avoir un credit voiture avec une écheance de " + ((montCredit) * (1 + (tauxInteret / 100)) / (12 * dureec)) + " sur " + dureec + " années ");
                else
                    r = ("votre salaire de " + salaire + " vous ne permet pas d avoire cette credit esaiyer une autre foit");
                break;
            case "etude":
                tauxInteret = 15;
                if (((montCredit) * (1 + (tauxInteret / 100)) / (12 * dureec)) < ((salaire) / 2))
                    r = (" tu peux avoir un credit voiture avec une écheance de " + ((montCredit) * (1 + (tauxInteret / 100)) / (12 * dureec)) + " sur " + dureec + " années");
                else
                    r = ("votre salaire de " + salaire + " vous ne permet pas d avoire cette credit esaiyer une autre foit");
                break;
            case "voiyage":
                tauxInteret = 10;
                if (((montCredit) * (1 + (tauxInteret / 100)) / (12 * dureec)) < ((salaire) / 2))
                    r = (" tu peux avoir un credit voiture avec une écheance de " + ((montCredit) * (1 + (tauxInteret / 100)) / (12 * dureec)) + " sur" + dureec + " années");
                else
                    r = ("votre salaire de " + salaire + " vous ne permet pas d avoire cette credit esaiyer une autre foit");
                break;
            case "afaire":
                tauxInteret = 25;
                if (((montCredit) * (1 + (tauxInteret / 100)) / (12 * dureec)) < ((salaire) / 2))
                    r = (" tu peux avoir un credit voiture avec une écheance de " + ((montCredit) * (1 + (tauxInteret / 100)) / (12 * dureec)) + " sur " + dureec + " années");
                else
                    r = ("votre salaire de " + salaire + " vous ne permet pas d avoire cette credit esaiyer une autre foit");
                break;

        }
        System.out.println(r);
        return r;
    }
    ///////////////fctlistcredit

    /**
     * @return
     * @throws SQLException
     */
    public List<Integer> listecredit() throws SQLException {
        List<Integer> list = new ArrayList<Integer>();
        Connection con = MyConnection.getInstance().getCnx();
        ResultSet rs = con.createStatement().executeQuery("SELECT * FROM credit");
        while (rs.next()) {
            list.add(rs.getInt(1));

            //table_stade.setItems(list);
        }
        return list;


    }

    public List<Credit> recherche(int x) {
        List<Credit> list = new ArrayList<>();//WHERE credit_id="+credit;
        String req = "Select * from credit WHERE id=" + x;
        try {
            PreparedStatement pst = cnx.prepareStatement(req);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Credit p = new Credit();
                p.setId(rs.getInt("id"));
                p.setNumero_compte(rs.getInt(2));
                //p.setMontCredit(rs.getInt(3));
                p.setDatepe(rs.getDate(4));
                p.setDatede(rs.getDate(5));
                p.setDureeC(rs.getInt(6));
                p.setEcheance(rs.getDate(7));
                p.setTauxInteret(rs.getInt(8));
                p.setDecision(rs.getBoolean(9));
                p.setEtatCredit(rs.getString(10));
                p.setTypeCredit(rs.getString(11));
                //p.setNumero_compte( rs.getInt(12));


                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public float getAllech(int credit) {
        List<Credit> list = new ArrayList<>();

        String req = "Select *"
                + " from credit WHERE id=" + credit;
        float nberchance = 0;
        int nbp = 0;
        int nbt = 0;
        try {
            PreparedStatement pst = cnx.prepareStatement(req);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Credit p = new Credit();
                p.setId(rs.getInt("id"));
                p.setNumero_compte(rs.getInt(2));
                p.setMontCredit(rs.getInt(3));
                p.setDatepe(rs.getDate(4));
                p.setDatede(rs.getDate(5));
                p.setDureeC(rs.getInt(6));
                p.setEcheance(rs.getDate(7));
                p.setTauxInteret(rs.getInt(8));
                p.setDecision(rs.getBoolean(9));
                p.setEtatCredit(rs.getString(10));
                p.setTypeCredit(rs.getString(11));
                /////////testamdouniyet///////////
                ServiceOperationCredit c = new ServiceOperationCredit();
                nbp = c.getAll10(rs.getInt("id"));
                nbt = (rs.getInt(6)) * 12;
                nberchance = ((float) nbp / nbt);


//nberchance=( (rs.getInt(3))*(1+(( rs.getInt(8))/100)) )/( rs.getInt(6));
                //p.setNumero_compte( rs.getInt(12));


                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return nberchance;
    }
}

