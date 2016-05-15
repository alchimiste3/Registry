package applicationLibrairieNumerique.commande;

import java.rmi.RemoteException;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

import applicationLibrairieNumerique.serviceRMI.AccesClientLibrairieInterface;
import applicationLibrairieNumerique.serviceRMI.LibrairieInterface;

public class CommandeCommanderLivre {

    private String nomUtilisateur;
    private String nomLivre;
    private LibrairieInterface librairie;
    private AccesClientLibrairieInterface client;

    
    public CommandeCommanderLivre(LibrairieInterface librairie, AccesClientLibrairieInterface client, String nomUtilisateur, String nomLivre) {
        this.nomUtilisateur = nomUtilisateur;
        this.nomLivre = nomLivre;
        this.librairie = librairie;
        this.client = client;
    }

    public void execute() throws RemoteException, JMSException{

        System.out.println("→librairie.rappeleCommandeLivre("+ client +", "+nomUtilisateur+", "+nomLivre+")");
        boolean rep = librairie.rappeleCommandeLivre(client, nomUtilisateur, nomLivre);
        
        if(rep) System.out.println("Votre commande est comfirmer");
        else System.out.println("commande impossible");
        
    }
}
