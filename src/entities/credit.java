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
public class credit {
       private int id;
    private compte numeroCompteId;
    private int montCredit;
    private Date datePE;
    private Date dateDE;
    private int dureeC;
    private Date echeance;
    private int tauxInteret;
    private boolean decision;
    private String etatCredit;
    private String typeCredit;

    public credit(compte numeroCompteId, int montCredit, Date datePE, Date dateDE, int dureeC, Date echeance, int tauxInteret, boolean decision, String etatCredit, String typeCredit) {
        this.numeroCompteId = numeroCompteId;
        this.montCredit = montCredit;
        this.datePE = datePE;
        this.dateDE = dateDE;
        this.dureeC = dureeC;
        this.echeance = echeance;
        this.tauxInteret = tauxInteret;
        this.decision = decision;
        this.etatCredit = etatCredit;
        this.typeCredit = typeCredit;
    }

    public credit() {
    }

    public credit(int id) {
        this.id = id;
    }

    public credit(int id, compte numeroCompteId, int montCredit, Date datePE, Date dateDE, int dureeC, Date echeance, int tauxInteret, boolean decision, String etatCredit, String typeCredit) {
        this.id = id;
        this.numeroCompteId = numeroCompteId;
        this.montCredit = montCredit;
        this.datePE = datePE;
        this.dateDE = dateDE;
        this.dureeC = dureeC;
        this.echeance = echeance;
        this.tauxInteret = tauxInteret;
        this.decision = decision;
        this.etatCredit = etatCredit;
        this.typeCredit = typeCredit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public compte getNumeroCompteId() {
        return numeroCompteId;
    }

    public void setNumeroCompteId(compte numeroCompteId) {
        this.numeroCompteId = numeroCompteId;
    }

    public int getMontCredit() {
        return montCredit;
    }

    public void setMontCredit(int montCredit) {
        this.montCredit = montCredit;
    }

    public Date getDatePE() {
        return datePE;
    }

    public void setDatePE(Date datePE) {
        this.datePE = datePE;
    }

    public Date getDateDE() {
        return dateDE;
    }

    public void setDateDE(Date dateDE) {
        this.dateDE = dateDE;
    }

    public int getDureeC() {
        return dureeC;
    }

    public void setDureeC(int dureeC) {
        this.dureeC = dureeC;
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

    public boolean isDecision() {
        return decision;
    }

    public void setDecision(boolean decision) {
        this.decision = decision;
    }

    public String getEtatCredit() {
        return etatCredit;
    }

    public void setEtatCredit(String etatCredit) {
        this.etatCredit = etatCredit;
    }

    public String getTypeCredit() {
        return typeCredit;
    }

    public void setTypeCredit(String typeCredit) {
        this.typeCredit = typeCredit;
    }

    @Override
    public String toString() {
        return "credit{" + "id=" + id + ", numeroCompteId=" + numeroCompteId + ", montCredit=" + montCredit + ", datePE=" + datePE + ", dateDE=" + dateDE + ", dureeC=" + dureeC + ", echeance=" + echeance + ", tauxInteret=" + tauxInteret + ", decision=" + decision + ", etatCredit=" + etatCredit + ", typeCredit=" + typeCredit + '}';
    }
    
    
    
    
}
