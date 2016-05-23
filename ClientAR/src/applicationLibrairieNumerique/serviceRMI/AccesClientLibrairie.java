package applicationLibrairieNumerique.serviceRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
/**
 * Class qui implement le service fournie par le client au serveur
 * @author Quentin Laborde
 *
 */
public class AccesClientLibrairie extends UnicastRemoteObject implements AccesClientLibrairieInterface{

    public AccesClientLibrairie() throws RemoteException {
        super();
    }

    @Override
    public boolean appeler(String message) throws RemoteException {
        System.out.println("la librairie vous a contacté : "+message);
        System.out.println("\n>");

        return true;
    }
    
    
}
