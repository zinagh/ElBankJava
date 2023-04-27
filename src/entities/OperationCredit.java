/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author MSI
 */
public class OperationCredit {
        private int id;
    private credit credit;
    private Date dateOp;
    private int montPayer;
    private Date echeance;
    private int tauxInteret;
    private int solvabilite;
    private String typeOperation;

    public OperationCredit() {
    }

    public OperationCredit(int id, credit credit, Date dateOp, int montPayer, Date echeance, int tauxInteret, int solvabilite, String typeOperation) {
        this.id = id;
        this.credit = credit;
        this.dateOp = dateOp;
        this.montPayer = montPayer;
        this.echeance = echeance;
        this.tauxInteret = tauxInteret;
        this.solvabilite = solvabilite;
        this.typeOperation = typeOperation;
    }

    public OperationCredit(credit credit, Date dateOp, int montPayer, Date echeance, int tauxInteret, int solvabilite, String typeOperation) {
        this.credit = credit;
        this.dateOp = dateOp;
        this.montPayer = montPayer;
        this.echeance = echeance;
        this.tauxInteret = tauxInteret;
        this.solvabilite = solvabilite;
        this.typeOperation = typeOperation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public credit getCredit() {
        return credit;
    }

    public void setCredit(credit credit) {
        this.credit = credit;
    }

    public Date getDateOp() {
        return dateOp;
    }

    public void setDateOp(Date dateOp) {
        this.dateOp = dateOp;
    }

    public int getMontPayer() {
        return montPayer;
    }

    public void setMontPayer(int montPayer) {
        this.montPayer = montPayer;
    }

    public Date getEcheance() {
        return echeance;
    }

    public void setEcheance(Date echeance) {
        this.echeance = echeance;
    }

    public int getTauxInteret() {
        return tauxInteret;
    }

    public void setTauxInteret(int tauxInteret) {
        this.tauxInteret = tauxInteret;
    }

    public int getSolvabilite() {
        return solvabilite;
    }

    public void setSolvabilite(int solvabilite) {
        this.solvabilite = solvabilite;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    @Override
    public String toString() {
        return "OperationCredit{" + "id=" + id + ", credit=" + credit + ", dateOp=" + dateOp + ", montPayer=" + montPayer + ", echeance=" + echeance + ", tauxInteret=" + tauxInteret + ", solvabilite=" + solvabilite + ", typeOperation=" + typeOperation + '}';
    }

    public void setDPO(Date valueOf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setmontantPO(int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setecheanceo(Date valueOf) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setintereto(int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setsolv(int parseInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void settype(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
