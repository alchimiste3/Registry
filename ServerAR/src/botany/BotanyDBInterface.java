package botany;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface BotanyDBInterface extends Remote {
    Plant getPlant(String nom) throws RemoteException;
    void addPlant(Plant p) throws RemoteException;
    HashMap getPlantes()  throws RemoteException;
}
