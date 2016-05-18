package commandeRegistry;

import java.rmi.RemoteException;
import objetRMICommun.MaRegistryInterface;

/**
 * CommandeClePlusDemander
 * @author Quentin Laborde
 *
 */
public class CommandeClePlusDemander {

    private MaRegistryInterface maRMI;
    
    public CommandeClePlusDemander(MaRegistryInterface maRMI) {
        this.maRMI = maRMI;
    }

    public void execute() throws RemoteException{
        String cle = maRMI.getClePlusDemander();
        
        System.out.println("La cle la plus demander est : "+ cle);

    }
}
