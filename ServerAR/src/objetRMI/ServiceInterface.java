package objetRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceInterface extends Remote {

    /**
     * Permet de recuperer les information sur le service
     * @return
     */
    public String getInfo() throws RemoteException;
    
    /**
     * Permet de recuperer un objet Resultat qui contient ce qu'on veut
     * @return
     */
    public ReponseService accesService() throws RemoteException;
    
    
    /**
     * Permet de recuperer une interface du client pour l'informé continuelement
     * @param client
     * @return
     * @throws RemoteException
     */
    public boolean etreRappelé(AccesClientInterface client, int temps) throws RemoteException; 
    
    
    /**
     * Permet de recuperer les informations de connection sur le server JMS et lire la queue
     * 
     * @return InfoConnectionJMS
     * @throws RemoteException
     */
    public InfoConnectionJMS abonnement() throws RemoteException; 
    
}
