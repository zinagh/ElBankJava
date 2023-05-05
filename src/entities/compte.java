package entities;
import java.util.Date;
/**
 *
 * @author Zina Ghribi
 */
public class Compte {
    private int id;
    private long num_compte;
    private String RIB_Compte;
    private float solde_compte;
    private String date_creation;
    private String type_compte;
    private float seuil_compte;
    private float taux_interet;
    private int fullname_client_id;
    private int etat_compte;
    private String fullname;

    public Compte() {
    }

    public Compte(int id, long num_compte, String RIB_Compte, float solde_compte, String date_creation, String type_compte, float seuil_compte, float taux_interet, int fullname_client_id, int etat_compte) {
        this.id = id;
        this.num_compte = num_compte;
        this.RIB_Compte = RIB_Compte;
        this.solde_compte = solde_compte;
        this.date_creation = date_creation;
        this.type_compte = type_compte;
        this.seuil_compte = seuil_compte;
        this.taux_interet = taux_interet;
        this.fullname_client_id = fullname_client_id;
        this.etat_compte = etat_compte;
    }

    public Compte(long num_compte, String RIB_Compte, float solde_compte, String date_creation, String type_compte, float seuil_compte, float taux_interet, int fullname_client_id, int etat_compte) {
        this.num_compte = num_compte;
        this.RIB_Compte = RIB_Compte;
        this.solde_compte = solde_compte;
        this.date_creation = date_creation;
        this.type_compte = type_compte;
        this.seuil_compte = seuil_compte;
        this.taux_interet = taux_interet;
        this.fullname_client_id = fullname_client_id;
        this.etat_compte = etat_compte;
    }

    public Compte(long num_compte, String RIB_Compte, float solde_compte, String date_creation, String type_compte, float seuil_compte, float taux_interet, int fullname_client_id, int etat_compte, String fullname) {
        this.num_compte = num_compte;
        this.RIB_Compte = RIB_Compte;
        this.solde_compte = solde_compte;
        this.date_creation = date_creation;
        this.type_compte = type_compte;
        this.seuil_compte = seuil_compte;
        this.taux_interet = taux_interet;
        this.fullname_client_id = fullname_client_id;
        this.etat_compte = etat_compte;
        this.fullname = fullname;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getNum_compte() {
        return num_compte;
    }

    public void setNum_compte(long num_compte) {
        this.num_compte = num_compte;
    }

    public String getRIB_Compte() {
        return RIB_Compte;
    }

    public void setRIB_Compte(String RIB_Compte) {
        this.RIB_Compte = RIB_Compte;
    }

    public float getSolde_compte() {
        return solde_compte;
    }

    public void setSolde_compte(float solde_compte) {
        this.solde_compte = solde_compte;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public String getType_compte() {
        return type_compte;
    }

    public void setType_compte(String type_compte) {
        this.type_compte = type_compte;
    }

    public float getSeuil_compte() {
        return seuil_compte;
    }

    public void setSeuil_compte(float seuil_compte) {
        this.seuil_compte = seuil_compte;
    }

    public float getTaux_interet() {
        return taux_interet;
    }

    public void setTaux_interet(float taux_interet) {
        this.taux_interet = taux_interet;
    }

    public int getFullname_client_id() {
        return fullname_client_id;
    }

    public void setFullname_client_id(int fullname_client_id) {
        this.fullname_client_id = fullname_client_id;
    }

    public int getEtat_compte() {
        return etat_compte;
    }

    public void setEtat_compte(int etat_compte) {
        this.etat_compte = etat_compte;
    }

    @Override
    public String toString() {
        return "Compte " + id + " : {" + '\n' +
                "num_compte=" + num_compte + '\n' +
                ", RIB_Compte='" + RIB_Compte + '\n' +
                ", solde_compte=" + solde_compte + '\n' +
                ", date_creation=" + date_creation + '\n' +
                ", type_compte='" + type_compte + '\n' +
                ", seuil_compte=" + seuil_compte + '\n' +
                ", taux_interet=" + taux_interet + '\n' +
                ", fullname_client_id=" + fullname_client_id + '\n' +
                ", etat_compte=" + etat_compte + '\n' +
                '}' + '\n' + "---------------------------------------------- \n";
    }
}

