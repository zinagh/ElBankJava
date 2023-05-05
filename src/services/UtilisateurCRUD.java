package services;


import entities.Session;
import entities.Utilisateur;
import tools.MyConnection;

import javax.lang.model.type.NullType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
/**
 *
 * @author Zina Ghribi
 */
public class UtilisateurCRUD {

    public void ajouterUtilisateur(Utilisateur u) {
        String requete = "INSERT INTO utilisateur (cin_u,nom_u,prenom_u,date_naissance,email_u,num_tel,role,mot_de_passe, bloquer_token, etat) VALUES"
                + "(?,?,?,?,?,?,?,MD5(?),?, ?)";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, u.getCin_u());
            pst.setString(2, u.getNom_u());
            pst.setString(3, u.getPrenom_u());
            pst.setDate(4, u.getDate_naissance());
            pst.setString(5, u.getEmail_u());
            pst.setInt(6, u.getNum_tel());
            pst.setString(7, u.getRole());
            pst.setString(8, u.getMot_de_passe());
            pst.setString(9, null);
            pst.setString(10, "Debloquer");
            pst.executeUpdate();
            System.out.println("utilisateur ajoutee");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Utilisateur> afficherUtilisateur() {
        List<Utilisateur> myListUtilisateur = new ArrayList<>();
        try {
            String requete = "SELECT * FROM utilisateur";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Utilisateur u = new Utilisateur();
                u.setId(rs.getInt(1));
                u.setCin_u(rs.getInt(2));
                u.setNom_u(rs.getString("nom_u"));
                u.setPrenom_u(rs.getString("prenom_u"));
                u.setDate_naissance(rs.getDate("date_naissance"));
                u.setEmail_u(rs.getString("email_u"));
                u.setNum_tel(rs.getInt("num_tel"));
                u.setRole(rs.getString("role"));
                u.setMot_de_passe(rs.getString("mot_de_passe"));
                u.setEtat((rs.getString("etat")));
                myListUtilisateur.add(u);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return myListUtilisateur;
    }
    public void modifierUtlisateur(Utilisateur u,int idModifier){
        String requete = "UPDATE utilisateur SET `cin_u`=?,`nom_u`=?,`prenom_u`=?,`date_naissance`=?, `email_u`=?,`num_tel`=?, `role`=?, `mot_de_passe`= MD5(?) WHERE id=" + idModifier;
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, u.getCin_u());
            pst.setString(2, u.getNom_u());
            pst.setString(3, u.getPrenom_u());
            pst.setDate(4, u.getDate_naissance());
            pst.setString(5, u.getEmail_u());
            pst.setInt(6, u.getNum_tel());
            pst.setString(7, u.getRole());
            pst.setString(8, u.getMot_de_passe());
            pst.executeUpdate();
            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification de l'utilisateur : " + u.getNom_u() + " a été éffectuée avec succès ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierProfile(Utilisateur u,int idModifier){
        String requete = "UPDATE utilisateur SET `cin_u`=?,`nom_u`=?,`prenom_u`=?,`date_naissance`=?, `email_u`=?,`num_tel`=? WHERE id=" + idModifier;
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, u.getCin_u());
            pst.setString(2, u.getNom_u());
            pst.setString(3, u.getPrenom_u());
            pst.setDate(4, u.getDate_naissance());
            pst.setString(5, u.getEmail_u());
            pst.setInt(6, u.getNum_tel());
            pst.executeUpdate();
            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification de l'utilisateur : " + u.getNom_u() + " a été éffectuée avec succès ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void UpdatePersonne(Utilisateur u) {
        try {

            String requette ="UPDATE utilisateur SET `cin_u`=?,`nom_u`=?,`prenom_u`=?,`date_naissance`=?, `email_u`=?,`num_tel`=?, `role`=?, `etat`=? ,  `mot_de_passe`= ? , `bloquer_token` = ? WHERE id= ? " ;  //prepared statement asra3 et securise mn statment khter mara bark tet3ada b 4 phase w lbe3i phase execution bark
            //drop matekhdemch fl prepared , prepred statment drequette dynamique
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requette);


            pst.setInt(1, u.getCin_u());
            pst.setString(2, u.getNom_u());
            pst.setString(3, u.getPrenom_u());
            pst.setDate(4, u.getDate_naissance());
            pst.setString(5, u.getEmail_u());
            pst.setInt(6, u.getNum_tel());
            pst.setString(7, u.getRole());
            pst.setString(8, u.getEtat());
            pst.setString(9, u.getMot_de_passe());
            pst.setString(10,u.getBloquer());
            pst.setInt(11,u.getId());
//            if(u.getEtat().equals("Bloquer")){
//                pst.setString(11,u.getEtat());
//            }else{
//                pst.setString(11, null);
//            }
            pst.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void supprimerUtilisateur(int id){
        String requete = "DELETE FROM `utilisateur` WHERE id=" +id ;
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.executeUpdate();
            System.out.println("Utilisateur supprimé");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Utilisateur> triUtilisateurParNom(){
        List<Utilisateur> myListUtilisateur = new ArrayList<>();
        try {
            String requete = "SELECT * FROM utilisateur ORDER BY nom_u ASC";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                Utilisateur u = new Utilisateur();
                u.setId(rs.getInt(1));
                u.setCin_u(rs.getInt(2));
                u.setNom_u(rs.getString("nom_u"));
                u.setPrenom_u(rs.getString("prenom_u"));
                u.setDate_naissance(rs.getDate("date_naissance"));
                u.setEmail_u(rs.getString("email_u"));
                u.setNum_tel(rs.getInt("num_tel"));
                u.setRole(rs.getString("role"));
                u.setMot_de_passe(rs.getString("mot_de_passe"));
                myListUtilisateur.add(u);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return myListUtilisateur;
    }

    public boolean Check_login(String mail,String pwd){
//        String requete = "SELECT * FROM `utilisateur` WHERE `email_u`=? AND `mot_de_passe`= MD5(?) ";
        String requete = "SELECT * FROM `utilisateur` WHERE `email_u`=? AND `mot_de_passe`= MD5(?) ";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, mail);
            pst.setString(2, pwd);
//            pst.setString(2, pwd);
            ResultSet rs = pst.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean login(String mail,String pwd) {
        List<Utilisateur> myListUtilisateur = new ArrayList<>();
//        String requete = "SELECT * FROM `utilisateur` WHERE `email_u`=? AND `mot_de_passe`= MD5(?) ";
        String requete = "SELECT * FROM `utilisateur` WHERE `email_u`=?  ";
        Utilisateur u = null;
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, mail);
//            pst.setString(2, pwd);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                u = new Utilisateur();
                Session.setId(rs.getInt("id"));
                Session.setCin(rs.getInt("cin_u"));
                Session.setUsername(rs.getString("nom_u"));
                Session.setPrenom(rs.getString("prenom_u"));
                Session.setDate_naissance(rs.getDate("date_naissance"));
                Session.setEmail(rs.getString("email_u"));
                Session.setNumero_tel(rs.getInt("num_tel"));
                Session.setRoles(rs.getString("role"));
                Session.setEtat(rs.getString("etat"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // System.out.println(myListUtilisateur);
        System.out.println("welcome : " + Session.getUsername()+ ":votre login a été éffectuée avec succès ");
        //MailApi.send("testutilisateurs1@gmail.com", "espritBankiz",Session.getEmail(), "Notification de connexion", " Nous avons remarqué une nouvelle connexion depuis votre compte  ");

        return true;
    }
    public Utilisateur afficherProfile2(int idd) {

        Utilisateur u = null;

        PreparedStatement pst = null;
        try {
            String requete = "SELECT * FROM `utilisateur` WHERE `id`= "+idd+"";
            pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                u = new Utilisateur();
                Session.setId(rs.getInt("id"));
                Session.setCin(rs.getInt("cin_u"));
                Session.setUsername(rs.getString("nom_u"));
                Session.setPrenom(rs.getString("prenom_u"));
                Session.setDate_naissance(rs.getDate("date_naissance"));
                Session.setEmail(rs.getString("email_u"));
                Session.setNumero_tel(rs.getInt("num_tel"));
                Session.setRoles(rs.getString("role"));
                Session.setEtat(rs.getString("etat"));

                u.setId(Session.getId());
                u.setCin_u(Session.getCin());
                u.setNom_u(Session.getUsername());
                u.setPrenom_u(Session.getPrenom());
                u.setDate_naissance((java.sql.Date) Session.getDate_naissance());
                u.setEmail_u(Session.getEmail());
                u.setNum_tel(Session.getNumero_tel());
                u.setRole(Session.getRoles());
                u.setEtat(rs.getString("etat"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return u;
    }
    public List<Utilisateur> afficherProfile() {
        List<Utilisateur> myProfile = new ArrayList<>();


        Utilisateur u = new Utilisateur();
        u.setId(Session.getId());
        u.setCin_u(Session.getCin());
        u.setNom_u(Session.getUsername());
        u.setPrenom_u(Session.getPrenom());
        u.setDate_naissance((java.sql.Date) Session.getDate_naissance());
        u.setEmail_u(Session.getEmail());
        u.setNum_tel(Session.getNumero_tel());
        u.setRole(Session.getRoles());

        myProfile.add(u);


        return myProfile;
    }
    public  boolean email_Validation(String email){
        boolean status = false;

        String email_Pattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(email_Pattern);
        Matcher matcher = pattern.matcher(email);
        status = matcher.matches();
        return status;
    }
    public boolean verifierPwd(String password) {
        String requete = "SELECT mot_de_passe FROM utilisateur WHERE mot_de_passe=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1,password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void modifierPassword(String mail, String password) {
        PreparedStatement stmt;
        try {
            String sql = "UPDATE utilisateur SET mot_de_passe= MD5(?) WHERE email_u=?";
            stmt = MyConnection.getInstance().getCnx().prepareStatement(sql);
            stmt.setString(1, password);
            stmt.setString(2, mail);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    /*public void modifierPassword(String mail, String password) {
        String requete = "UPDATE utilisateur SET mot_de_passe=? WHERE email_u=?";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, password);
            pst.setString(2, mail);
            pst.executeUpdate();

            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("le mot de passe de  : " + mail + " a été éffectuée avec succès ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    public int nbAdmins() {
        List<Utilisateur> admin = new ArrayList<>();
        String requete = "SELECT email_u FROM `utilisateur` WHERE role='ROLE_ADMIN'";
        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Utilisateur c = new Utilisateur();
                c.setEmail_u(rs.getString("email_u"));
                admin.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin.size();
    }

    public int calculateAge(Date birthDate) {
        LocalDate currentDate = LocalDate.now();
        int age = currentDate.getYear() - birthDate.getYear();
        int month1 = currentDate.getMonthValue();
        int month2 = birthDate.getMonth();
        if (month2 > month1) {
            age--;
        } else if (month1 == month2) {
            int day1 = currentDate.getDayOfMonth();
            int day2 = birthDate.getDay();
            if (day2 > day1) {
                age--;
            }
        }
        return age-1900;
    }

    public List<Utilisateur> findByPrenom(String prenom){
        List<Utilisateur> u = afficherUtilisateur();
        List<Utilisateur> resultat=u.stream().filter(Utilisateur->prenom.equals(Utilisateur.getPrenom_u())).collect(Collectors.toList());
        if(resultat.isEmpty()){
            System.out.println("l utilisateur n existe pas");
        }else{
            System.out.println("l utilisateur existe");
        }
        return resultat;
    }


    public Utilisateur findByMail(String Mail){
        Utilisateur u=new Utilisateur();
        String requete = "SELECT * FROM `utilisateur` WHERE email_u = ?";

        PreparedStatement pst = null;
        try {
            pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, Mail);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                u.setId(rs.getInt(1));
                u.setCin_u(rs.getInt(2));
                u.setNom_u(rs.getString("nom_u"));
                u.setPrenom_u(rs.getString("prenom_u"));
                u.setDate_naissance(rs.getDate("date_naissance"));
                u.setEmail_u(rs.getString("email_u"));
                u.setNum_tel(rs.getInt("num_tel"));
                u.setRole(rs.getString("role"));
                u.setMot_de_passe(rs.getString("mot_de_passe"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }


    public boolean verifierEmailBd(String email) { //Controle De Saisie si mail existe
        PreparedStatement stmt = null;
        ResultSet rst = null;
        try {
            String sql = "SELECT * FROM utilisateur WHERE email_u=?";
            stmt = MyConnection.getInstance().getCnx().prepareStatement(sql);
            stmt.setString(1, email);
            rst = stmt.executeQuery();
            if (rst.next()) {
                return true;

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public List<Utilisateur> getIdFromFullname(String name) {
        String[] arrOfStr = name.split(" ");
        List<Utilisateur> u = afficherUtilisateur();
        List<Utilisateur> resultat = u.stream().filter(Utilisateur -> arrOfStr[0].toLowerCase().equals(Utilisateur.getNom_u().toLowerCase()) && arrOfStr[1].toLowerCase().equals(Utilisateur.getPrenom_u().toLowerCase())).collect(Collectors.toList());
        System.out.println(resultat.get(0).getId());
        return resultat;
    }

    public List<String> afficherFullnameUtilisateur() {
        List<String> myListUtilisateur = new ArrayList<>();
        try {
            String requete = "SELECT nom_u, prenom_u FROM utilisateur";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {
                myListUtilisateur.add(rs.getString("nom_u") + ' ' + rs.getString("prenom_u"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return myListUtilisateur;
    }

}