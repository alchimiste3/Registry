package commandeRegistry;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

import objetRMICommun.MaRegistryInterface;

public class CommandeDernierObjetAjouter {

    private MaRegistryInterface maRMI;
    private int nb;

    public CommandeDernierObjetAjouter(MaRegistryInterface maRMI, int nb){
        this.maRMI = maRMI;

    }

    public void execute() throws RemoteException{
        List<Serializable> liste = maRMI.dernierObjetAjouter(nb);
        
        System.out.println("Voici la liste des "+ nb + " objets ajoutés");
        for(Serializable o : liste){
            System.out.println("   →"+o);
        }
    }
  
    
}