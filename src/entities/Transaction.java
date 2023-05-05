package entities;
/**
 *
 * @author Zina Ghribi
 */
public class Transaction {
    private int id;
    private int RIB_emetteur;
    private String RIB_recepteur;
    private float montant_transaction;
    private String date_transaction;
    private String description_transaction;
    private int fullname_emetteur;
    private String fullname_recepteur;
    private String type_transaction;
    private int etat_transaction;
    private String rib_fullname;
    private String ribemet;
    private String nomemet;

    public Transaction() {
    }

    public Transaction(int id, int RIB_emetteur, String RIB_recepteur, float montant_transaction, String date_transaction, String description_transaction, int fullname_emetteur, String fullname_recepteur, String type_transaction, int etat_transaction) {
        this.id = id;
        this.RIB_emetteur = RIB_emetteur;
        this.RIB_recepteur = RIB_recepteur;
        this.montant_transaction = montant_transaction;
        this.date_transaction = date_transaction;
        this.description_transaction = description_transaction;
        this.fullname_emetteur = fullname_emetteur;
        this.fullname_recepteur = fullname_recepteur;
        this.type_transaction = type_transaction;
        this.etat_transaction = etat_transaction;
    }

    public Transaction(int RIB_emetteur, String RIB_recepteur, float montant_transaction, String date_transaction, String description_transaction, int fullname_emetteur, String fullname_recepteur, String type_transaction, int etat_transaction) {
        this.RIB_emetteur = RIB_emetteur;
        this.RIB_recepteur = RIB_recepteur;
        this.montant_transaction = montant_transaction;
        this.date_transaction = date_transaction;
        this.description_transaction = description_transaction;
        this.fullname_emetteur = fullname_emetteur;
        this.fullname_recepteur = fullname_recepteur;
        this.type_transaction = type_transaction;
        this.etat_transaction = etat_transaction;
    }

    public Transaction(int RIB_emetteur, String RIB_recepteur, float montant_transaction, String date_transaction, String description_transaction, int fullname_emetteur, String fullname_recepteur, String type_transaction, int etat_transaction, String rib_fullname) {
        this.RIB_emetteur = RIB_emetteur;
        this.RIB_recepteur = RIB_recepteur;
        this.montant_transaction = montant_transaction;
        this.date_transaction = date_transaction;
        this.description_transaction = description_transaction;
        this.fullname_emetteur = fullname_emetteur;
        this.fullname_recepteur = fullname_recepteur;
        this.type_transaction = type_transaction;
        this.etat_transaction = etat_transaction;
        this.rib_fullname = rib_fullname;
    }

    public Transaction(int RIB_emetteur, String RIB_recepteur, float montant_transaction, String date_transaction, String description_transaction, int fullname_emetteur, String fullname_recepteur, String type_transaction, int etat_transaction, String rib_fullname, String ribemet, String nomemet) {
        this.RIB_emetteur = RIB_emetteur;
        this.RIB_recepteur = RIB_recepteur;
        this.montant_transaction = montant_transaction;
        this.date_transaction = date_transaction;
        this.description_transaction = description_transaction;
        this.fullname_emetteur = fullname_emetteur;
        this.fullname_recepteur = fullname_recepteur;
        this.type_transaction = type_transaction;
        this.etat_transaction = etat_transaction;
        this.rib_fullname = rib_fullname;
        this.ribemet = ribemet;
        this.nomemet = nomemet;
    }

    public String getRib_fullname() {
        return rib_fullname;
    }

    public void setRib_fullname(String rib_fullname) {
        this.rib_fullname = rib_fullname;
    }

    public String getRibemet() {
        return ribemet;
    }

    public void setRibemet(String ribemet) {
        this.ribemet = ribemet;
    }

    public String getNomemet() {
        return nomemet;
    }

    public void setNomemet(String nomemet) {
        this.nomemet = nomemet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRIB_emetteur() {
        return RIB_emetteur;
    }

    public void setRIB_emetteur(int RIB_emetteur) {
        this.RIB_emetteur = RIB_emetteur;
    }

    public String getRIB_recepteur() {
        return RIB_recepteur;
    }

    public void setRIB_recepteur(String RIB_recepteur) {
        this.RIB_recepteur = RIB_recepteur;
    }

    public float getMontant_transaction() {
        return montant_transaction;
    }

    public void setMontant_transaction(float montant_transaction) {
        this.montant_transaction = montant_transaction;
    }

    public String getDate_transaction() {
        return date_transaction;
    }

    public void setDate_transaction(String date_transaction) {
        this.date_transaction = date_transaction;
    }

    public String getDescription_transaction() {
        return description_transaction;
    }

    public void setDescription_transaction(String description_transaction) {
        this.description_transaction = description_transaction;
    }

    public int getFullname_emetteur() {
        return fullname_emetteur;
    }

    public void setFullname_emetteur(int fullname_emetteur) {
        this.fullname_emetteur = fullname_emetteur;
    }

    public String getFullname_recepteur() {
        return fullname_recepteur;
    }

    public void setFullname_recepteur(String fullname_recepteur) {
        this.fullname_recepteur = fullname_recepteur;
    }

    public String getType_transaction() {
        return type_transaction;
    }

    public void setType_transaction(String type_transaction) {
        this.type_transaction = type_transaction;
    }

    public int getEtat_transaction() {
        return etat_transaction;
    }

    public void setEtat_transaction(int etat_transaction) {
        this.etat_transaction = etat_transaction;
    }

    @Override
    public String toString() {
        return "Transaction " + id + " : {" + '\n' +
                "RIB_emetteur='" + RIB_emetteur + '\n' +
                ", RIB_recepteur='" + RIB_recepteur + '\n' +
                ", montant_transaction=" + montant_transaction + '\n' +
                ", date_transaction=" + date_transaction + '\n' +
                ", description_transaction='" + description_transaction + '\n' +
                ", fullname_emetteur=" + fullname_emetteur + '\n' +
                ", fullname_recepteur='" + fullname_recepteur + '\n' +
                ", type_transaction='" + type_transaction + '\n' +
                ", etat_transaction=" + etat_transaction + '\n' +
                '}' + '\n' + "---------------------------------------------- \n";
    }
}
