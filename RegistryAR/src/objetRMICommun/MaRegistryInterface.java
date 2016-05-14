package objetRMICommun;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

public interface MaRegistryInterface extends Remote{
    
    /**
     * Permet de stocker un objet serialisable avec un cle
     * @param key
     * @param obj
     * @throws RemoteException
     */
    public void rebind(String key, Serializable obj) throws RemoteException;

    /**
     * Permet de recupere un objet serialisable a partire de sa cle
     * @param key
     * @return
     * @throws RemoteException
     */
    public Serializable lookup(String key) throws RemoteException;

    /**
     * Permet de recupere les x dernier cle ajouter par les utilisateurs
     * @param x
     * @return Liste de cle
     */
    public List<String> dernierCleAjouter(int x) throws RemoteException;
    
    /**
     * Permet de recupere les x dernier cle demander par les utilisateurs
     * @param x
     * @return Liste de cle
     */
    public List<String> dernierCleDemander(int x) throws RemoteException;
    
    /**
     * Permet de recupere les x dernier objets demander par les utilisateurs
     * @param x
     * @return Liste d'objet serialisable
     */
    public List<Serializable> dernierObjetAjouter(int x) throws RemoteException;

    /**
     * Permet de recupere toute les cle qui on ete demande au moins x fois
     * @param x
     * @return Liste de cle
     */
    public HashMap<String, Integer> getMapCleFreqDemand(int x) throws RemoteException;
    
    /**
     * Permet de récupérer la clé la plus demandée
     * @return clé
     * @throws RemoteException 
     */
    public String getClePlusDemande() throws RemoteException;

}