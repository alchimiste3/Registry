package applicationLibrairieNumerique.serviceRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AccesClientLibrairieInterface  extends Remote{

    public boolean appeler(String message) throws RemoteException;

}
