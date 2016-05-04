package objetRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AccesClient extends UnicastRemoteObject implements AccesClientInterface{

    public AccesClient() throws RemoteException {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean appeler() throws RemoteException {
        // TODO Auto-generated method stub
        return false;
    }
    
    
}
