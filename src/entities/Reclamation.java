package entities;

import java.sql.Date;
/**
 *
 * @author Zina Ghribi
 */
public class Reclamation {
    private int id;
    private int nom_u_id;
    private String type_rec;
    private Date date_rec;
    private String etat_rec;
    private String desc_rec;

    public Reclamation() {
    }

    public Reclamation(int nom_u_id, String type_rec, Date date_rec, String etat_rec, String desc_rec) {
        this.nom_u_id = nom_u_id;
        this.type_rec = type_rec;
        this.date_rec = date_rec;
        this.etat_rec = etat_rec;
        this.desc_rec = desc_rec;
    }

    public Reclamation(int id, int nom_u_id, String type_rec, Date date_rec, String etat_rec, String desc_rec) {
        this.id = id;
        this.nom_u_id = nom_u_id;
        this.type_rec = type_rec;
        this.date_rec = date_rec;
        this.etat_rec = etat_rec;
        this.desc_rec = desc_rec;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNom_u_id() {
        return nom_u_id;
    }

    public void setNom_u_id(int nom_u_id) {
        this.nom_u_id = nom_u_id;
    }

    public String getType_rec() {
        return type_rec;
    }

    public void setType_rec(String type_rec) {
        this.type_rec = type_rec;
    }

    public Date getDate_rec() {
        return date_rec;
    }

    public void setDate_rec(Date date_rec) {
        this.date_rec = date_rec;
    }

    public String getEtat_rec() {
        return etat_rec;
    }

    public void setEtat_rec(String etat_rec) {
        this.etat_rec = etat_rec;
    }

    public String getDesc_rec() {
        return desc_rec;
    }

    public void setDesc_rec(String desc_rec) {
        this.desc_rec = desc_rec;
    }

    @Override
    public String toString() {
        return "Reclamation{" +
                "id=" + id +
                ", nom_u_id=" + nom_u_id +
                ", type_rec='" + type_rec + '\'' +
                ", date_rec=" + date_rec +
                ", etat_rec='" + etat_rec + '\'' +
                ", desc_rec='" + desc_rec + '\'' +
                '}';
    }

}
