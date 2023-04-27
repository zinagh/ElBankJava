/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.OperationCredit;
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
public class OperationCreditService implements IOperationCredit{
    Connection cnx;

    public OperationCreditService() {
        cnx =MyDB.getInstance().getCnx();
    }
@Override
    public boolean ajoutOperationCredit(OperationCredit O) throws SQLException {
                String request = "INSERT INTO operation_credit (credit_id ,date_op,mont_payer, echeance, taux_interet,solvabilite,type_operation)"
                + " VALUES (?,?,?,?,?,?,?)";
        
            PreparedStatement pst = cnx.prepareStatement(request);
            
            pst.setInt(1, O.getCredit().getId());
            pst.setDate(2, new java.sql.Date(O.getDateOp().getTime()));
            pst.setInt(3, O.getMontPayer());
            pst.setDate(4, new java.sql.Date(O.getEcheance().getTime()));
            pst.setInt(5, O.getTauxInteret());
            pst.setInt(6, O.getSolvabilite());
            pst.setString(7, O.getTypeOperation());
            pst.executeUpdate();
            System.out.println("OperationCredi Added");
            return true;   
    }

    @Override
    public boolean updateOperationCredit(OperationCredit O) throws SQLException {
               String request = "UPDATE operation_credit SET credit_id=?, date_op=?, mont_payer=?, echeance=?, taux_interet=?, solvabilite=?, type_operation=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(request);
            pst.setInt(1, O.getCredit().getId());
            pst.setDate(2, new java.sql.Date(O.getDateOp().getTime()));
            pst.setInt(3, O.getMontPayer());
            pst.setDate(4, new java.sql.Date(O.getEcheance().getTime()));
            pst.setInt(5, O.getTauxInteret());
            pst.setInt(6, O.getSolvabilite());
            pst.setString(7, O.getTypeOperation());
            pst.setInt(8, O.getId());
            pst.executeUpdate();
            System.out.println("OperationCredi Updated");
            return true;
    }

    @Override
    public boolean suprimeOperationCredit(OperationCredit O) throws SQLException {
            String req = "DELETE FROM operation_credit WHERE id = ?";
    PreparedStatement ps = cnx.prepareStatement(req);
    ps.setInt(1, O.getId());
    ps.executeUpdate();
                System.out.println("OperationCredit deletd");
            return true;
    }

    @Override
    public List<OperationCredit> getAll() throws SQLException {
            List<OperationCredit> list = new ArrayList<>();
    String request = "SELECT * FROM operation_credit";
    PreparedStatement pst = cnx.prepareStatement(request);
    ResultSet rs = pst.executeQuery();

    while (rs.next()) {
        OperationCredit O = new OperationCredit();
        O.setId(rs.getInt("id"));
        O.setDateOp(rs.getDate("date_op"));
        O.setMontPayer(rs.getInt("mont_payer"));
        O.setEcheance(rs.getDate("echeance"));
        O.setTauxInteret(rs.getInt("taux_interet"));
        O.setSolvabilite(rs.getInt("solvabilite"));
        O.setTypeOperation(rs.getString("type_operation"));

        credit C = new credit();
        C.setId(rs.getInt("credit_id"));
        O.setCredit(C);

        list.add(O);
    }
    return list;
    }
    public List<OperationCredit> getAllByCreditId(int creditId) throws SQLException {
    List<OperationCredit> list = new ArrayList<>();
    String request = "SELECT * FROM operation_credit WHERE credit_id = ?";
    PreparedStatement pst = cnx.prepareStatement(request);
    pst.setInt(1, creditId);
    ResultSet rs = pst.executeQuery();

    while (rs.next()) {
        OperationCredit O = new OperationCredit();
        O.setId(rs.getInt("id"));
        O.setDateOp(rs.getDate("date_op"));
        O.setMontPayer(rs.getInt("mont_payer"));
        O.setEcheance(rs.getDate("echeance"));
        O.setTauxInteret(rs.getInt("taux_interet"));
        O.setSolvabilite(rs.getInt("solvabilite"));
        O.setTypeOperation(rs.getString("type_operation"));

        credit C = new credit();
        C.setId(rs.getInt("credit_id"));
        O.setCredit(C);

        list.add(O);
    }
    return list;
}

    
    
    
}
