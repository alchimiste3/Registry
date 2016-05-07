package applicationLibrairieNumerique;

import java.io.Serializable;

public class Livre implements Serializable{


    private String titre;
    private String auteur;
    private int moisParution;
    private int anneeParution;

    private String date;
    private String genre;
    private String contenue;
    

    
    
    public Livre(String titre, String auteur, int moisParution, int anneeParution, String genre, String contenue) {
        this.titre = titre;
        this.auteur = auteur;
        this.moisParution = moisParution;
        this.anneeParution = anneeParution;
        this.genre = genre;
        this.contenue = contenue;
    }
    
    
    public String getTitre() {
        return titre;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getAuteur() {
        return auteur;
    }
    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getContenue() {
        return contenue;
    }
    public void setContenue(String contenue) {
        this.contenue = contenue;
    }
    
    
    
    
}
