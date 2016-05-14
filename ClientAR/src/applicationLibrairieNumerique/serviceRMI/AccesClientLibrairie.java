package applicationLibrairieNumerique.serviceRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
public class AccesClientLibrairie extends UnicastRemoteObject implements AccesClientLibrairieInterface{

    public AccesClientLibrairie() throws RemoteException {
        super();
    }

    @Override
    public boolean appeler(String message) throws RemoteException {
        System.out.println("la libraire vous a contacté : "+message);
        return true;
    }
    
    
}
