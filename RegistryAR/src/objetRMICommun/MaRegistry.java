package objetRMICommun;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;



@SuppressWarnings("serial")
public class MaRegistry extends UnicastRemoteObject implements MaRegistryInterface {
    
    DataBase database;
    
    public MaRegistry() throws RemoteException {
        super();
        database = new DataBase();
    }

    public void rebind(String key, Serializable obj) throws RemoteException{
        System.out.println("rebind de "+ obj.getClass());
        database.add(key, obj);  
    }
    
    public Serializable lookup(String key) throws RemoteException{
        return database.get(key);
    }

    @Override
    public List<String> derniereCleAjouter(int x) throws RemoteException {
        return database.derniereCleAjouter(x);
    }
    
    @Override
    public List<String> derniereCleDemander(int x) throws RemoteException {
        return database.derniereCleDemander(x);
    }

    @Override
    public List<Serializable> dernierObjetAjouter(int x) throws RemoteException {
        return database.dernierObjetAjouter(x);
    }

    @Override
    public HashMap<String, Integer> getMapCleFreqDemander(int x) throws RemoteException {
        return database.getMapCleFreqDemander(x);
    }
    
    @Override
    public String getClePlusDemander() throws RemoteException {
        return database.getClePlusDemander();
    }



    

    




}