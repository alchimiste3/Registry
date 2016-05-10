package applicationLibrairieNumerique.commande;

import java.rmi.RemoteException;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

import applicationLibrairieNumerique.donneeRMI.Livre;
import applicationLibrairieNumerique.serviceRMI.LibrairieInterface;

public class CommandeAcheterLivre {

    private String nomUtilisateur;
    private String nomLivre;
    private LibrairieInterface librairie;
    private List<Message> listeMessage;
    
    public CommandeAcheterLivre(LibrairieInterface librairie, String nomUtilisateur, String nomLivre, List<Message> listeMessage) {
        super();
        this.nomUtilisateur = nomUtilisateur;
        this.nomLivre = nomLivre;
        this.librairie = librairie;
        this.listeMessage = listeMessage;
    }

    public void execute() throws RemoteException, JMSException{
        int sizelisteMessage = listeMessage.size();
        
        System.out.println("→librairie.acheterLivre("+nomUtilisateur+", "+nomLivre+")");
        Livre livre = librairie.acheterLivre(nomUtilisateur, nomLivre);
        
        if(livre == null){
            System.out.println("Vous ne pouvez pas acheter ce livre");
        }
        else{
            System.out.println("Voici votre nouveau livre ");
            System.out.println(livre.toString());
        }
        
       
        
    }
}
