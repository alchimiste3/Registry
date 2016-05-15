package applicationLibrairieNumerique.donneeRMI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import applicationLibrairieNumerique.serviceRMI.AccesClientLibrairieInterface;

@SuppressWarnings("serial")
public class Acheteur implements Serializable{

    private String nom;
    private String numCarteCredit;
    
    
    public Acheteur(String nom, String numCarteCredit) {
        super();
        this.nom = nom;
        this.numCarteCredit = numCarteCredit;
    }

    


    List<String> livreAcheter = new ArrayList<>();



    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumCarteCredit() {
        return numCarteCredit;
    }

    public void setNumCarteCredit(String numCarteCredit) {
        this.numCarteCredit = numCarteCredit;
    }



    public List<String> getLivreAcheter() {
        return livreAcheter;
    }

    public void setLivreAcheter(List<String> livreAcheter) {
        this.livreAcheter = livreAcheter;
    }
    
    
    
    
    
    
}
