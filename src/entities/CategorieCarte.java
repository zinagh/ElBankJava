package entities;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Zina Ghribi
 */
public class CategorieCarte {
        private int id;
        private String type;
        private String description;
        private String prix ;
        private String montant_max;
        private Date date_creation_categorie;
        private Timestamp  date_categorie;

    public CategorieCarte(int id, String type, String description, String prix, String montant_max, Timestamp date_categorie) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.prix = prix;
        this.montant_max = montant_max;
        this.date_categorie = date_categorie;
    }
        private String image;
   
        
        

   
     public CategorieCarte() {
       }

    public CategorieCarte (String type, String description, String prix, String montant_max, Timestamp date_categorie) {

        this.type = type;
        this.description = description;
        this.prix = prix;
        this.montant_max = montant_max;
        this.date_categorie = date_categorie;
    }

 

  
  
   
    public CategorieCarte(String type, String description, String prix, String montant_max, Timestamp date_categorie, String image) {
        this.type = type;
        this.description = description;
        this.prix = prix;
        this.montant_max = montant_max;
        this.date_categorie = date_categorie;
        this.image = image;
    }
       



    

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getMontant_max() {
        return montant_max;
    }

    public void setMontant_max(String montant_max) {
        this.montant_max = montant_max;
    }

 

   

  

    public Date getDate_creation_categorie() {
        return date_creation_categorie;
    }

    public Timestamp getDate_categorie() {
        return date_categorie;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDate_creation_categorie(Date date_creation_categorie) {
        this.date_creation_categorie = date_creation_categorie;
    }

    public void setDate_categorie(Timestamp date_categorie) {
        this.date_categorie = date_categorie;
    }



  
   
       @Override
    public String toString() {
        return "CategorieCarte{id="+ id+ " ,type=" + type + ", description=" + description + ", prix=" + prix + ", montant_max=" + montant_max + ", date_creation_categorie=" + date_creation_categorie +", date_categorie=" + date_categorie + ", image=" + image +'}';
    }

  

  

    
}
