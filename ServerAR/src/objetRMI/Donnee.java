package objetRMI;

import java.io.Serializable;

public class Donnee implements Serializable{
    private String donnee = "ServerAR";

    public String getDonnee() {
        return donnee;
    }

    public void setDonnee(String donnee) {
        this.donnee = donnee;
    }
    
    
}
