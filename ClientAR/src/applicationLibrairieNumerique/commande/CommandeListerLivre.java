package applicationLibrairieNumerique.commande;

import java.rmi.RemoteException;
import java.util.List;

import applicationLibrairieNumerique.LibrairieInterface;

public class CommandeListerLivre {

    private LibrairieInterface librairie;
    
    
    public CommandeListerLivre(LibrairieInterface librairie) {
        this.librairie = librairie;
    }


    
    public void execute() throws RemoteException{
        List<String> liste = librairie.ListeLivre();
        System.out.println("Voici la liste de livre disponible");
        for(String l : liste){
            System.out.println(l);
        }
    }
}
