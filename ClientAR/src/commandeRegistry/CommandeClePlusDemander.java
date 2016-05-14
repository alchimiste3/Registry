package commandeRegistry;

import java.rmi.RemoteException;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

import applicationLibrairieNumerique.donneeRMI.Livre;
import applicationLibrairieNumerique.serviceRMI.LibrairieInterface;
import objetRMICommun.MaRegistryInterface;

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
