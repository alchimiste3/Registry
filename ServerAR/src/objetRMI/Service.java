package objetRMI;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Service extends UnicastRemoteObject implements ServiceInterface{

    public Service() throws RemoteException {
        super();
    }

    @Override
    public String getInfo() throws RemoteException{
        return "Service";
    }

    @Override
    public ReponseService accesService() throws RemoteException{
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean etreRappelé(AccesClientInterface client) throws RemoteException{
        // TODO Auto-generated method stub
        return false;
    }


    
    

}
