package objetRMI;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface MaRegistryInterface extends Remote{
    
    public void rebind(String key, Serializable obj) throws RemoteException;

    public Serializable lookup(String key) throws RemoteException;

    /**
     * Les x dernier cle demander par les utilisateurs
     * @param x
     * @return Liste de cle
     */
    public List<String> dernierCleAjouter(int x) throws RemoteException;
    
    /**
     * Les x dernier objets demander par les utilisateurs
     * @param x
     * @return Liste d'objet serialisable
     */
    public List<Serializable> dernierObjetAjouter(int x) throws RemoteException;

//    /**
//     * Les x cle les plus demander par les utilisateurs
//     * @param x
//     * @return Liste de cle
//     */
//    public HashMap<String, Integer> ClePlusDemander(int x) throws RemoteException;

}