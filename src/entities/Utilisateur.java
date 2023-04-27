package entities;

import com.itextpdf.text.pdf.PdfPCell;

import java.sql.Date;
/**
 *
 * @author Zina Ghribi
 */
public class Utilisateur {
    private int id;
    private int cin_u;
    private String nom_u;
    private String prenom_u;
    private Date date_naissance;
    private String email_u;
    private int num_tel;
    private String role;
    private String etat;
    private String mot_de_passe;
    private String bloquer;

    public Utilisateur(int id, int cin_u, String nom_u, String prenom_u, Date date_naissance, String email_u, int num_tel, String role, String mot_de_passe) {
        this.id = id;
        this.cin_u = cin_u;
        this.nom_u = nom_u;
        this.prenom_u = prenom_u;
        this.date_naissance = date_naissance;
        this.email_u = email_u;
        this.num_tel = num_tel;
        this.role = role;
        this.mot_de_passe = mot_de_passe;
    }

    public Utilisateur() {
    }

    public Utilisateur( int cin_u, String nom_u, String prenom_u, Date date_naissance, String email_u, int num_tel, String role,String etat, String mot_de_passe) {
        this.cin_u = cin_u;
        this.nom_u = nom_u;
        this.prenom_u = prenom_u;
        this.date_naissance = date_naissance;
        this.email_u = email_u;
        this.num_tel = num_tel;
        this.role = role;
        this.etat = etat;
        this.mot_de_passe = mot_de_passe;
    }

    public String getEtat() {
        return etat;
    }
    public Date getDate_naissance() {
        return date_naissance;
    }


    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public void setCin_u(int cin_u) {
        this.cin_u = cin_u;
    }

    public String getNom_u() {
        return nom_u;
    }
    public int getCin_u() {
        return cin_u;
    }

    public void setNom_u(String nom_u) {
        this.nom_u = nom_u;
    }

    public String getPrenom_u() {
        return prenom_u;
    }

    public void setPrenom_u(String prenom_u) {
        this.prenom_u = prenom_u;
    }


    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getEmail_u() {
        return email_u;
    }

    public void setEmail_u(String email_u) {
        this.email_u = email_u;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getBloquer() {
        return bloquer;
    }

    public void setBloquer(String bloquer) {
        this.bloquer = bloquer;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", cin_u=" + cin_u +
                ", nom_u='" + nom_u + '\'' +
                ", prenom_u='" + prenom_u + '\'' +
                ", date_naissance=" + date_naissance +
                ", email_u='" + email_u + '\'' +
                ", num_tel=" + num_tel +
                ", role='" + role + '\'' +
                ", mot_de_passe='" + mot_de_passe + '\'' +
                '}';
    }

}
