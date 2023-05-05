package entities;
/**
 *
 * @author Zina Ghribi
 */
public class Carte {
        private int id;
        private String idclient;
        private String date_ex;
        private String mp ;
        private String login;
        private String num_carte;

    public Carte( String idclient, String date_ex, String mp, String login, String num_carte) {
       // this.id = id;
        this.idclient = idclient;
        this.date_ex = date_ex;
        this.mp = mp;
        this.login = login;
        this.num_carte = num_carte;
    }
     public Carte() {
       }


    public Carte(int id,String idclient, String date_ex, String mp, String login, String num_carte) {
        this.id = id;
        this.idclient = idclient;
        this.date_ex = date_ex;
        this.mp = mp;
        this.login = login;
        this.num_carte = num_carte;
    }
      public Carte(  String mp, String login, String num_carte) {
       
        
    
        this.mp = mp;
        this.login = login;
        this.num_carte = num_carte;
    }
        public Carte(  String mp,String date_ex, String login, String num_carte) {
       
        
        this.date_ex = date_ex;
        this.mp = mp;
        this.login = login;
        this.num_carte = num_carte;
    }



    

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdclient() {
        return idclient;
    }

    public void setIdclient(String idclient) {
        this.idclient = idclient;
    }

    public String getDate_ex() {
        return date_ex;
    }

    public void setDate_ex(String date_ex) {
        this.date_ex = date_ex;
    }

    public String getMp() {
        return mp;
    }

    public void setMp(String mp) {
        this.mp = mp;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNum_carte() {
        return num_carte;
    }

    public void setNum_carte(String num_carte) {
        this.num_carte = num_carte;
    }

   
       @Override
    public String toString() {
        return "Carte{" + "idclient=" + idclient + ", mp=" + mp + ", num_carte=" + num_carte + ", date_ex=" + date_ex + ", login=" + login + '}';
    }

  

  

    
}
