package objetRMI;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;



public class MaRegistry extends UnicastRemoteObject implements MaRegistryInterface {
    
    DataBase database;
    
    public MaRegistry() throws RemoteException {
        super();
        database = new DataBase();
    }

    public void rebind(String key, Serializable obj) throws RemoteException{
        
        database.add(key, obj);  
        System.out.println(obj.getClass());

    }
    
    public Serializable lookup(String key) throws RemoteException{
        return database.get(key);
    }

    @Override
    public List<Serializable> dernierInfo(int x) {
        return database.dernierInfo(x);
    }

    @Override
    public ArrayList<String> dernierCle(int x) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<String> ClePlusDemander(int x) {
        // TODO Auto-generated method stub
        return null;
    }
    




}