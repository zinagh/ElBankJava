package entities;

import services.ChequeCrud;

import java.util.Date;

public class Cheque {
    private int id ;
    private int proprietaire_id;
    private Date date_cheque;
    private String nom_client_id;
    private double montant;
    private String lieu;
    private int signature;
    private int client_tel;
    private int idchequiers_id;
    private int destinataire_id;
    private String rib_sender;
    private String rib_reciever;
    private String full_name_id;
    private String full_name_id2;
    private String full_name;
    private String full_named;

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getFull_name_id() {
        return full_name_id;
    }

    public void setFull_name_id(String full_name_id) {
        this.full_name_id = full_name_id;
    }

    public int getId() {
        return id;
    }

    public String getFull_name_id2() {
        return full_name_id2;
    }

    public void setFull_name_id2(String full_name_id2) {
        this.full_name_id2 = full_name_id2;
    }

    public String getFull_named() {
        return full_named;
    }

    public void setFull_named(String full_named) {
        this.full_named = full_named;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProprietaire_id() {
        return proprietaire_id;
    }

    public void setProprietaire_id(int proprietaire_id) {
        this.proprietaire_id = proprietaire_id;
        ChequeCrud ch = new ChequeCrud();
        this.full_name_id=ch.proptoid(proprietaire_id);
        full_name= ch.idtoname(full_name_id);


    }

    public Date getDate_cheque() {
        return date_cheque;
    }

    public void setDate_cheque(Date date_cheque) {
        this.date_cheque = date_cheque;
    }

    public String getNom_client_id() {
        return nom_client_id;
    }

    public void setNom_client_id(String nom_client_id) {
        this.nom_client_id = nom_client_id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getSignature() {
        return signature;
    }

    public void setSignature(int t) {

        this.signature = t ;

    }
    public void setSignature() {
        int i = (int) (client_tel);
        this.signature = id + i ;
    }

    public int getClient_tel() {
        return client_tel;
    }

    public void setClient_tel(int client_tel) {
        this.client_tel = client_tel;
    }

    public int getDestinataire_id() {
        return destinataire_id;
    }

    public void setDestinataire_id(int destinataire_id) {
        this.destinataire_id = destinataire_id;
        ChequeCrud ch = new ChequeCrud();
        this.full_name_id2=ch.desttoid(destinataire_id);
        full_named= ch.idtoname(full_name_id2);
    }

    public String getRib_sender() {
        return rib_sender;
    }

    public void setRib_sender(String rib_sender) {
        this.rib_sender = rib_sender;
    }

    public String getRib_reciever() {
        return rib_reciever;
    }

    public void setRib_reciever(String rib_reciever) {
        this.rib_reciever = rib_reciever;
    }

    public Cheque() {
    }

    public Cheque(int id, int proprietaire_id, Date date_cheque, String nom_client_id, double montant, String lieu, int signature, int client_tel, int idchequiers_id, int destinataire_id, String rib_sender, String rib_reciever) {
        this.id = id;
        this.proprietaire_id = proprietaire_id;
        this.date_cheque = date_cheque;
        this.nom_client_id = nom_client_id;
        this.montant = montant;
        this.lieu = lieu;
        this.signature = signature;
        this.client_tel = client_tel;
        this.idchequiers_id = idchequiers_id;
        this.destinataire_id = destinataire_id;
        this.rib_sender = rib_sender;
        this.rib_reciever = rib_reciever;
    }

    public int getIdchequiers_id() {
        return idchequiers_id;
    }

    public void setIdchequiers_id(int idchequiers_id) {
        this.idchequiers_id = idchequiers_id;
    }

    public Cheque(int proprietaire_id, Date date_cheque, String nom_client_id, double montant, String lieu, int signature, int client_tel, int idchequiers_id, int destinataire_id, String rib_sender, String rib_reciever) {
        this.proprietaire_id = proprietaire_id;
        this.date_cheque = date_cheque;
        this.nom_client_id = nom_client_id;
        this.montant = montant;
        this.lieu = lieu;
        this.signature = signature;
        this.client_tel = client_tel;
        this.idchequiers_id = idchequiers_id;
        this.destinataire_id = destinataire_id;
        this.rib_sender = rib_sender;
        this.rib_reciever = rib_reciever;
    }

    @Override
    public String toString() {
        return "Cheque{" +
                "id=" + id +
                ", proprietaire_id=" + proprietaire_id +
                ", date_cheque=" + date_cheque +
                ", nom_client_id='" + nom_client_id + '\'' +
                ", montant=" + montant +
                ", lieu='" + lieu + '\'' +
                ", signature=" + signature +
                ", client_tel=" + client_tel +
                ", destinataire_id=" + destinataire_id +
                ", rib_sender='" + rib_sender + '\'' +
                ", rib_reciever='" + rib_reciever + '\'' +
                '}';    }


}
