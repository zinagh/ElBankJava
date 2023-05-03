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
public class Credit {
      private int id;
   private int montCredit;
    private Date datepe;
    private Date datede;
    private int dureeC;
    private Date echeance;
    private int tauxInteret;
    private boolean decision;
    private String etatCredit,typeCredit;
    private int numero_compte;
    private int OperationCredits;

    public Credit(int id, int montCredit, Date datepe, Date datede, int dureeC, Date echeance, int tauxInteret, boolean decision, String etatCredit, String typeCredit, int numero_compte) {
        this.id = id;
        this.montCredit = montCredit;
        this.datepe = datepe;
        this.datede = datede;
        this.dureeC = dureeC;
        this.echeance = echeance;
        this.tauxInteret = tauxInteret;
        this.decision = decision;
        this.etatCredit = etatCredit;
        this.typeCredit = typeCredit;
        this.numero_compte = numero_compte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMontCredit() {
        return montCredit;
    }

    public void setMontCredit(int montCredit) {
        this.montCredit = montCredit;
    }

    public Date getDatepe() {
        return datepe;
    }

    public void setDatepe(Date datepe) {
        this.datepe = datepe;
    }

    public Date getDatede() {
        return datede;
    }

    public void setDatede(Date datede) {
        this.datede = datede;
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

    public int getNumero_compte() {
        return numero_compte;
    }

    public void setNumero_compte(int numero_compte) {
        this.numero_compte = numero_compte;
    }

    public int getOperationCredits() {
        return OperationCredits;
    }

    public void setOperationCredits(int OperationCredits) {
        this.OperationCredits = OperationCredits;
    }

    public Credit(int montCredit, Date datepe, Date datede, int dureeC, Date echeance, int tauxInteret, boolean decision, String etatCredit, String typeCredit, int numero_compte) {
        this.montCredit = montCredit;
        this.datepe = datepe;
        this.datede = datede;
        this.dureeC = dureeC;
        this.echeance = echeance;
        this.tauxInteret = tauxInteret;
        this.decision = decision;
        this.etatCredit = etatCredit;
        this.typeCredit = typeCredit;
        this.numero_compte = numero_compte;
    }

    public Credit(int montCredit, Date datepe, Date datede, int dureeC, Date echeance, int tauxInteret, boolean decision, String etatCredit, String typeCredit) {
        this.montCredit = montCredit;
        this.datepe = datepe;
        this.datede = datede;
        this.dureeC = dureeC;
        this.echeance = echeance;
        this.tauxInteret = tauxInteret;
        this.decision = decision;
        this.etatCredit = etatCredit;
        this.typeCredit = typeCredit;
    }

    public Credit() {
    }

    public Credit(int montCredit, int dureeC, int tauxInteret, boolean decision, String etatCredit, String typeCredit) {
        this.montCredit = montCredit;
        this.dureeC = dureeC;
        this.tauxInteret = tauxInteret;
        this.decision = decision;
        this.etatCredit = etatCredit;
        this.typeCredit = typeCredit;
    }

    

    public Credit(int id, int montCredit, Date datepe, Date datede, int dureeC, Date echeance, int tauxInteret, boolean decision, String etatCredit, String typeCredit, int numero_compte, int OperationCredits) {
        this.id = id;
        this.montCredit = montCredit;
        this.datepe = datepe;
        this.datede = datede;
        this.dureeC = dureeC;
        this.echeance = echeance;
        this.tauxInteret = tauxInteret;
        this.decision = decision;
        this.etatCredit = etatCredit;
        this.typeCredit = typeCredit;
        this.numero_compte = numero_compte;
        this.OperationCredits = OperationCredits;
    }
    
    public Credit(int montCredit, Date datepe, Date datede, int dureeC, Date echeance, int tauxInteret, boolean decision, String etatCredit, String typeCredit, int numero_compte, int OperationCredits) {
        this.montCredit = montCredit;
        this.datepe = datepe;
        this.datede = datede;
        this.dureeC = dureeC;
        this.echeance = echeance;
        this.tauxInteret = tauxInteret;
        this.decision = decision;
        this.etatCredit = etatCredit;
        this.typeCredit = typeCredit;
        this.numero_compte = numero_compte;
        this.OperationCredits = OperationCredits;
    }

}