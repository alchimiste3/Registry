package objetRMI;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MaRegistryInterface extends Remote{
    
    public void salut() throws RemoteException;

    public void rebind(String key, Serializable obj) throws RemoteException;

    public Serializable lookup(String key) throws RemoteException;
}