package commandeRegistry;

import java.rmi.RemoteException;
import java.util.List;

import objetRMICommun.MaRegistryInterface;

/**
 * CommandeDerniereCleDemander
 * @author Quentin Laborde
 *
 */
public class CommandeDerniereCleDemander {

    private MaRegistryInterface maRMI;
    private int nb;

    public CommandeDerniereCleDemander(MaRegistryInterface maRMI, int nb){
        this.maRMI = maRMI;
        this.nb = nb;
    }

    public void execute() throws RemoteException{
        List<String> liste = maRMI.derniereCleDemander(nb);
        
        System.out.println("Voici la liste des "+ nb + " dernieres clés demander (lookup)");
        for(String s : liste){
            System.out.println("   →"+s);
        }
    }
  
    
}
