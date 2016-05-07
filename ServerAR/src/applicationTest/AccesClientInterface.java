package applicationTest;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AccesClientInterface  extends Remote{

    public boolean appeler() throws RemoteException;

}
