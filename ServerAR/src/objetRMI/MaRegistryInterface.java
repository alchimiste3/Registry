package objetRMI;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MaRegistryInterface extends Remote{
    
    public void rebind(String key, Serializable obj) throws RemoteException;

    public Serializable lookup(String key) throws RemoteException;

    /**
     * Les x dernier objets demander par les utilisateurs
     * @param x
     * @return Liste d'objet serialisable
     */
    public List<Serializable> dernierInfo(int x);
    
    /**
     * Les x dernier cle demander par les utilisateurs
     * @param x
     * @return Liste de cle
     */
    public List<String> dernierCle(int x);

    /**
     * Les x cle les plus demander par les utilisateurs
     * @param x
     * @return Liste de cle
     */
    public List<String> ClePlusDemander(int x);

}