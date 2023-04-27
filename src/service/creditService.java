/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.OperationCredit;
import entities.compte;
import entities.credit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author MSI
 */
public class creditService implements Icredit{

   public credit findById(int id) throws SQLException {
    String request = "SELECT * FROM credit WHERE id = ?";
    PreparedStatement pst = cnx.prepareStatement(request);
    pst.setInt(1, id);
    ResultSet rs = pst.executeQuery();
    if (rs.next()) {
        credit c = new credit();
        c.setId(rs.getInt("id"));
        compte numeroCompte = new compte();
        numeroCompte.setId(rs.getInt("numero_compte_id"));
        c.setNumeroCompteId(numeroCompte);
        c.setMontCredit(rs.getInt("mont_credit"));
        c.setDatePE(rs.getDate("datepe"));
        c.setDateDE(rs.getDate("datede"));
        c.setDureeC(rs.getInt("duree_c"));
        c.setEcheance(rs.getDate("echeance"));
        c.setTauxInteret(rs.getInt("taux_interet"));
        c.setDecision(rs.getBoolean("decision"));
        c.setEtatCredit(rs.getString("etat_credit"));
        c.setTypeCredit(rs.getString("type_credit"));
        return c;
    }
    return null; // if no matching record is found
}

    Connection cnx;

    public creditService() {
        cnx =MyDB.getInstance().getCnx();
    }

    @Override
    public boolean ajoutcredit(credit c) throws SQLException {
                String request = "INSERT INTO credit (mont_credit,datepe,datede,duree_c, echeance, taux_interet, decision, etat_credit, type_credit)"
                + " VALUES (?,?,?,?,?,?,?,?,?)";

        PreparedStatement pst = cnx.prepareStatement(request);

       
        pst.setInt(1, c.getMontCredit());
        pst.setDate(2, new java.sql.Date(c.getDatePE().getTime()));
        pst.setDate(3, new java.sql.Date(c.getDateDE().getTime()));
        pst.setInt(4, c.getDureeC());
        pst.setDate(5, new java.sql.Date(c.getEcheance().getTime()));
        pst.setInt(6, c.getTauxInteret());
        pst.setBoolean(7, c.isDecision());
        pst.setString(8, c.getEtatCredit());
        pst.setString(9, c.getTypeCredit());
        pst.executeUpdate();
        System.out.println("Credit Added");
        return true;
    }

    public boolean updatecredit(credit c, int id) throws SQLException {
        String request = "UPDATE credit SET mont_credit=?, datepe=?, datede=?, duree_c=?, echeance=?, taux_interet=?, decision=?, etat_credit=?, type_credit=? WHERE id=?";
        PreparedStatement pst = cnx.prepareStatement(request);
        pst.setInt(1, c.getMontCredit());
        pst.setDate(2, new java.sql.Date(c.getDatePE().getTime()));
        pst.setDate(3, new java.sql.Date(c.getDateDE().getTime()));
        pst.setInt(4, c.getDureeC());
        pst.setDate(5, new java.sql.Date(c.getEcheance().getTime()));
        pst.setInt(6, c.getTauxInteret());
        pst.setBoolean(7, c.isDecision());
        pst.setString(8, c.getEtatCredit());
        pst.setString(9, c.getTypeCredit());
        pst.setInt(10, c.getId());
        pst.executeUpdate();
        System.out.println("Credit Updated");
        return true;
    }

    @Override
    public boolean suprimecredit(credit c) throws SQLException {
                String req = "DELETE FROM credit WHERE id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, c.getId());
        ps.executeUpdate();
        System.out.println("Credit deleted");
        return true;
    }

    @Override
    public List<credit> getAll() throws SQLException {
            List<credit> credits = new ArrayList<>();
    String request = "SELECT * FROM credit";
    PreparedStatement pst = cnx.prepareStatement(request);
    ResultSet rs = pst.executeQuery();
    while (rs.next()) {
        credit c = new credit();
        c.setId(rs.getInt("id"));
        compte numeroCompte = new compte();
        numeroCompte.setId(rs.getInt("numero_compte_id"));
        c.setNumeroCompteId(numeroCompte);
        c.setMontCredit(rs.getInt("mont_credit"));
        c.setDatePE(rs.getDate("datepe"));
        c.setDateDE(rs.getDate("datede"));
        c.setDureeC(rs.getInt("duree_c"));
        c.setEcheance(rs.getDate("echeance"));
        c.setTauxInteret(rs.getInt("taux_interet"));
        c.setDecision(rs.getBoolean("decision"));
        c.setEtatCredit(rs.getString("etat_credit"));
        c.setTypeCredit(rs.getString("type_credit"));
        credits.add(c);
    }
    return credits;
    }
   
public List<credit> rechercheCredit(int id) throws SQLException {
    List<credit> credits = new ArrayList<>();
    String request = "SELECT * FROM credit WHERE id = ?";
    PreparedStatement pst = cnx.prepareStatement(request);
    pst.setInt(1, id);
    ResultSet rs = pst.executeQuery();
    while (rs.next()) {
        credit c = new credit();
        c.setId(rs.getInt("id"));
        compte numeroCompte = new compte();
        numeroCompte.setId(rs.getInt("numero_compte_id"));
        c.setNumeroCompteId(numeroCompte);
        c.setMontCredit(rs.getInt("mont_credit"));
        c.setDatePE(rs.getDate("datepe"));
        c.setDateDE(rs.getDate("datede"));
        c.setDureeC(rs.getInt("duree_c"));
        c.setEcheance(rs.getDate("echeance"));
        c.setTauxInteret(rs.getInt("taux_interet"));
        c.setDecision(rs.getBoolean("decision"));
        c.setEtatCredit(rs.getString("etat_credit"));
        c.setTypeCredit(rs.getString("type_credit"));
        credits.add(c);
    }
    return credits;
}
 
   
}


    
    
    

