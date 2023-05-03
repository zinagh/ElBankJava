/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;
import java.util.Date;

/**
 *
 * @author Zina Ghribi
 */
public class OperationCredit {
     private int id;
   
    private Date dateOp;
   
    private int montPayer;
    private Date echeance;
    private int tauxInteret;
   private int solvabilite;
    private String typeOperation;
    private int creditid;

    public OperationCredit() {
    }

    public OperationCredit(int id, Date dateOp, int montPayer, Date echeance, int tauxInteret, int solvabilite, String typeOperation) {
        this.id = id;
        this.dateOp = dateOp;
        this.montPayer = montPayer;
        this.echeance = echeance;
        this.tauxInteret = tauxInteret;
        this.solvabilite = solvabilite;
        this.typeOperation = typeOperation;
    }

    public OperationCredit(Date dateOp, int montPayer, Date echeance, int tauxInteret, int solvabilite, String typeOperation) {
        this.dateOp = dateOp;
        this.montPayer = montPayer;
        this.echeance = echeance;
        this.tauxInteret = tauxInteret;
        this.solvabilite = solvabilite;
        this.typeOperation = typeOperation;
    }

    public OperationCredit(Date dateOp, int montPayer, Date echeance, int tauxInteret, int solvabilite, String typeOperation, int creditid) {
        this.dateOp = dateOp;
        this.montPayer = montPayer;
        this.echeance = echeance;
        this.tauxInteret = tauxInteret;
        this.solvabilite = solvabilite;
        this.typeOperation = typeOperation;
        this.creditid = creditid;
    }





    public OperationCredit(int id, int creditid ,Date dateOp, int montPayer, Date echeance, int tauxInteret, int solvabilite, String typeOperation) {
        this.id = id;
        this.creditid = creditid;
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

    public int getCreditid() {
        return creditid;
    }

    public void setCreditid(int creditid) {
        this.creditid = creditid;
    }

   
    
    
}
