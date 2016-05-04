package objetRMI;

import java.io.IOException;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;



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
    
    public void salut() throws RemoteException{
        System.out.println("salut");
    }





}