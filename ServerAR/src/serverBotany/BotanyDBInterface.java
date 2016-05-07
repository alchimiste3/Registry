package serverBotany;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BotanyDBInterface extends Remote {
    Plant getPlant(String nom) throws RemoteException;
    void addPlant(Plant p) throws RemoteException;    
}
