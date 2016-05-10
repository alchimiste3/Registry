package serverBotany;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class BotanyDB  extends UnicastRemoteObject implements BotanyDBInterface {
        HashMap<String,Plant> plantes;

    public BotanyDB() throws RemoteException {
        super();
        this.plantes = new HashMap<>();
    }
    
    public HashMap getPlantes() {
        return plantes;
    }

    @Override
    public Plant getPlant(String nom) throws RemoteException {
        return plantes.get(nom);
    }

    @Override
    public void addPlant(Plant p) throws RemoteException {
        plantes.put(p.getNom(), p);
    }
    
}
