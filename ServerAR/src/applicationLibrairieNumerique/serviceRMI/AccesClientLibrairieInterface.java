package applicationLibrairieNumerique.serviceRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface du service AccesClientLibrairie qui fournie un service specifique au serveur 
 * @author Quentin Laborde
 *
 */
public interface AccesClientLibrairieInterface  extends Remote{

    /**
     * Permet de contacter les le client depuis le serveur
     * @param message
     * @return
     * @throws RemoteException
     */
    public boolean appeler(String message) throws RemoteException;

}
