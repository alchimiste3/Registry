package commandeRegistry;

import java.rmi.RemoteException;
import java.util.List;

import objetRMICommun.MaRegistryInterface;

public class CommandeDerniereCleAjouter {

    private MaRegistryInterface maRMI;
    private int nb;
    
    public CommandeDerniereCleAjouter(MaRegistryInterface maRMI, int nb){
        this.maRMI = maRMI;
        this.nb = nb;
    }

    public void execute() throws RemoteException{
        List<String> liste = maRMI.derniereCleAjouter(nb);
        
        System.out.println("Voici la liste des "+ nb + " clés ajouter");
        for(String s : liste){
            System.out.println("   →"+s);
        }
    }
  
    
}
