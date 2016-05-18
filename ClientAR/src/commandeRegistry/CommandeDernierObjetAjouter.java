package commandeRegistry;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

import objetRMICommun.MaRegistryInterface;

/**
 * CommandeDernierObjetAjouter
 * @author Quentin Laborde
 *
 */
public class CommandeDernierObjetAjouter {

    private MaRegistryInterface maRMI;
    private int nb;

    public CommandeDernierObjetAjouter(MaRegistryInterface maRMI, int nb){
        this.maRMI = maRMI;
        this.nb = nb;

    }

    public void execute() throws RemoteException{
        List<Serializable> liste = maRMI.dernierObjetAjouter(nb);
        
        System.out.println("Voici la liste des "+ nb + " derniers objets ajoutés (rebind)");
        for(Serializable o : liste){
            System.out.println("   →"+o);
        }
    }
  
    
}