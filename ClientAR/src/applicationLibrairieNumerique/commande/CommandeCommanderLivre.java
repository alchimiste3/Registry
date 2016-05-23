package applicationLibrairieNumerique.commande;

import java.rmi.RemoteException;
import javax.jms.JMSException;


import applicationLibrairieNumerique.serviceRMI.AccesClientLibrairieInterface;
import applicationLibrairieNumerique.serviceRMI.LibrairieInterface;

/**
 * CommandeCommanderLivre
 * @author Quentin Laborde
 *
 */
public class CommandeCommanderLivre {

    private String nomUtilisateur;
    private String nomLivre;
    private LibrairieInterface librairie;
    private AccesClientLibrairieInterface client;
    private String nomQueue;

    
    public CommandeCommanderLivre(LibrairieInterface librairie, AccesClientLibrairieInterface client, String nomUtilisateur, String nomLivre, String nomQueue) {
        this.nomUtilisateur = nomUtilisateur;
        this.nomLivre = nomLivre;
        this.librairie = librairie;
        this.client = client;
        this.nomQueue = nomQueue;
    }

    public void execute() throws RemoteException, JMSException{

        System.out.println("→librairie.rappeleCommandeLivre(client , "+nomUtilisateur+", "+nomLivre+","+ nomQueue +")");
        boolean rep = librairie.rappeleCommandeLivre(client, nomUtilisateur, nomLivre, nomQueue);
        
        if(rep) System.out.println("Votre commande est comfirmer");
        else System.out.println("commande impossible");
        
    }
}
