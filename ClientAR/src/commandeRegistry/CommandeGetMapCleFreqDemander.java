package commandeRegistry;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;

import objetRMICommun.MaRegistryInterface;

public class CommandeGetMapCleFreqDemander {

    private MaRegistryInterface maRMI;
    private int nb;

    public CommandeGetMapCleFreqDemander(MaRegistryInterface maRMI, int nb){
        this.maRMI = maRMI;
        this.nb = nb;

    }

    public void execute() throws RemoteException{
        HashMap<String, Integer> map = maRMI.getMapCleFreqDemander(nb);
        
        Iterator<String> it = map.keySet().iterator();
        
        System.out.println("Voici la liste des clés demander au moins "+ nb + " fois");
        for(;it.hasNext();){         
            String keyComp = it.next();
            int value = map.get(keyComp);
            System.out.println("   →cle = "+keyComp+" - nombre de fois = "+value);
        }
    }
  
    
}
