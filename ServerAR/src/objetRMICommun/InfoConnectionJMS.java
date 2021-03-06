package objetRMICommun;

import java.io.Serializable;

@SuppressWarnings("serial")
/**
 * Class qui permet de creer les informations d'une queue
 * @author Quentin Laborde
 *
 */
public class InfoConnectionJMS implements Serializable{

    private String url;
    private String login;
    private String password;
    private String nom;
    
    
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    
    
}
