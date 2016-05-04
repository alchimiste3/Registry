package objetRMI;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Service extends UnicastRemoteObject implements ServiceInterface , Serializable {

    public Service() throws RemoteException {
        super();
    }

    @Override
    public String getInfo() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ReponseService accesService() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean etreRappelé(AccesClientInterface client) {
        // TODO Auto-generated method stub
        return false;
    }


    
    

}
