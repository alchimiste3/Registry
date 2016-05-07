package applicationTest;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AccesClient extends UnicastRemoteObject implements AccesClientInterface{

    public AccesClient() throws RemoteException {
        super();
    }

    @Override
    public boolean appeler() throws RemoteException {
        System.out.println("le server a reppelé !!!");
        return true;
    }
    
    
}
