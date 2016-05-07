package clientBotany;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Plant implements Serializable {
    private String nom;
    private String description;
    private String famille;

    public Plant(String nom, String description, String famille) {
        this.nom = nom;
        this.description = description;
        this.famille = famille;
    }

    @Override
    public String toString() {
        return "Plant{" + "nom=" + nom + ", description=" + description + ", famille=" + famille + '}';
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFamille() {
        return famille;
    }

    public void setFamille(String famille) {
        this.famille = famille;
    }
    
    
}
