package applicationLibrairieNumerique.commande;

import java.rmi.RemoteException;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;

import applicationLibrairieNumerique.donneeRMI.Livre;
import applicationLibrairieNumerique.serviceRMI.LibrairieInterface;

/**
 * CommandeAcheterLivre
 * @author Quentin Laborde
 *
 */
public class CommandeAcheterLivre {

    private String nomUtilisateur;
    private String nomLivre;
    private LibrairieInterface librairie;
    private String nomQueue;
    
    public CommandeAcheterLivre(LibrairieInterface librairie, String nomUtilisateur, String nomLivre, String nomQueue) {
        super();
        this.nomUtilisateur = nomUtilisateur;
        this.nomLivre = nomLivre;
        this.librairie = librairie;
        this.nomQueue = nomQueue;
    }

    public void execute() throws RemoteException, JMSException{
        
        System.out.println("→librairie.acheterLivre("+nomUtilisateur+", "+nomLivre+","+ nomQueue +")");
        Livre livre = librairie.acheterLivre(nomUtilisateur, nomLivre, nomQueue);
        
        if(livre == null){
            System.out.println("Vous ne pouvez pas acheter ce livre");
        }
        else{
            System.out.println("Voici votre nouveau livre ");
            System.out.println(livre.toString());
        }
        
       
        
    }
}
