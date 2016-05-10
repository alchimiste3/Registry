package applicationLibrairieNumerique.commande;

import java.rmi.RemoteException;

import applicationLibrairieNumerique.serviceRMI.LibrairieInterface;

public class CommandeInscription {

    private String nomUtilisateur;
    private String numCarteCredit;
    private LibrairieInterface librairie;
    
    
    public CommandeInscription(LibrairieInterface librairie, String nomUtilisateur, String numCarteCredit) {
        super();
        this.nomUtilisateur = nomUtilisateur;
        this.numCarteCredit = numCarteCredit;
        this.librairie = librairie;
    }

    public void execute() throws RemoteException{
        System.out.println("→librairie.inscription("+nomUtilisateur+", "+numCarteCredit+")");
        boolean rep = librairie.inscription(nomUtilisateur, numCarteCredit);
        
        if(rep) System.out.println("Votre inscription a bien été prise en compte");
        else System.out.println("L'inscription a échoué");
    }
}
