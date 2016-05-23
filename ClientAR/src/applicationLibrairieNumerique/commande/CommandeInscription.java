package applicationLibrairieNumerique.commande;

import java.rmi.RemoteException;

import applicationLibrairieNumerique.serviceRMI.LibrairieInterface;

/**
 * CommandeInscription
 * @author Quentin Laborde
 *
 */
public class CommandeInscription {

    private String nomUtilisateur;
    private String numCarteCredit;
    private LibrairieInterface librairie;
    private String nomQueue;

    
    public CommandeInscription(LibrairieInterface librairie, String nomUtilisateur, String numCarteCredit, String nomQueue) {
        super();
        this.nomUtilisateur = nomUtilisateur;
        this.numCarteCredit = numCarteCredit;
        this.librairie = librairie;
        this.nomQueue = nomQueue;
    }

    public void execute() throws RemoteException{
        System.out.println("→librairie.inscription("+nomUtilisateur+", "+numCarteCredit+","+ nomQueue +")");
        boolean rep = librairie.inscription(nomUtilisateur, numCarteCredit, nomQueue);
        
        if(rep) System.out.println("Votre inscription a bien été prise en compte");
        else System.out.println("L'inscription a échoué");
    }
}
