package applicationTest;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import objetRMI.InfoConnectionJMS;
import server.ThreadRappelerClient;

public class Service extends UnicastRemoteObject implements ServiceInterface{
    
    private InfoConnectionJMS infoJMS;

    public Service(InfoConnectionJMS info) throws RemoteException {
        super();
        this.infoJMS = info;
        
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
    public boolean etreRappelé(AccesClientInterface client, int temps) throws RemoteException{
        new ThreadRappelerClient(client, temps).start();
        return true;
    }

    @Override
    public InfoConnectionJMS abonnement() throws RemoteException {
        return infoJMS;
    }


    
    

}
