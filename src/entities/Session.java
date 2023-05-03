package entities;
import java.util.Date;
/**
 *
 * @author Zina Ghribi
 */

public class Session {
    private static int id = 0;
    private static int cin;
    private static String username;
    private static String prenom;
    private static Date date_naissance;
    private static String email;
    private static int numero_tel;
    private static String roles;
    private static String etat;

    public static int getId() {
        return id;
    }

    public static int getCin() {
        return cin;
    }

    public static void setCin(int cin) {
        Session.cin = cin;
    }

    public static void setId(int id) {
        Session.id = id;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Session.username = username;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Session.email = email;
    }

    public static String getRoles() {
        return roles;
    }

    public static void setRoles(String roles) {
        Session.roles = roles;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        Session.prenom = prenom;
    }

    public static Date getDate_naissance() {
        return date_naissance;
    }

    public static void setDate_naissance(Date date_naissance) {
        Session.date_naissance = date_naissance;
    }

    public static int getNumero_tel() {
        return numero_tel;
    }

    public static void setNumero_tel(int numero_tel) {
        Session.numero_tel = numero_tel;
    }

    public static String getEtat() {
        return etat;
    }

    public static void setEtat(String etat) {
        Session.etat = etat;
    }
}
