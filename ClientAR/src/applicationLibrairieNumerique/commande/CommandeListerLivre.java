package applicationLibrairieNumerique.commande;

import java.rmi.RemoteException;
import java.util.List;

import applicationLibrairieNumerique.serviceRMI.LibrairieInterface;

/**
 * CommandeListerLivre
 * @author Quentin Laborde
 *
 */
public class CommandeListerLivre {

    private LibrairieInterface librairie;
    
    
    public CommandeListerLivre(LibrairieInterface librairie) {
        this.librairie = librairie;
    }


    
    public void execute() throws RemoteException{
        System.out.println("→librairie.ListeLivre()");
        List<String> liste = librairie.ListeLivre();
        System.out.println("Voici la liste de livre disponible");
        for(String l : liste){
            System.out.println(l);
        }
    }
}
