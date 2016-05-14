package applicationLibrairieNumerique.commande;

import java.rmi.RemoteException;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

import applicationLibrairieNumerique.serviceRMI.LibrairieInterface;

public class CommandeCommanderLivre {

    private String nomUtilisateur;
    private String nomLivre;
    private LibrairieInterface librairie;
    private List<Message> listeMessage;
    
    public CommandeCommanderLivre(LibrairieInterface librairie, String nomUtilisateur, String nomLivre, List<Message> listeMessage) {
        this.nomUtilisateur = nomUtilisateur;
        this.nomLivre = nomLivre;
        this.librairie = librairie;
        this.listeMessage = listeMessage;
    }

    public void execute() throws RemoteException, JMSException{
        int sizelisteMessage = listeMessage.size();

        System.out.println("→librairie.rappeleCommandeLivre("+nomUtilisateur+", "+nomLivre+")");
        boolean rep = librairie.rappeleCommandeLivre(nomUtilisateur, nomLivre);
        
        if(rep) System.out.println("Votre commande est comfirmer");
        else System.out.println("commande impossible");
        
    }
}
