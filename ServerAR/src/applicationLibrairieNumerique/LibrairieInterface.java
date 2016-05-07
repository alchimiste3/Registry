package applicationLibrairieNumerique;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import objetRMI.InfoConnectionJMS;

public interface LibrairieInterface extends Remote {

    /**
     * Permet de recuperer les information sur la librairie
     * @return
     */
    public String getInformation() throws RemoteException;
    
    public boolean inscription(String nomUtilisateur, String numCarteCredit) throws RemoteException;

    public Livre acheterLivre(String nomUtilisateur, String nomLivre) throws RemoteException;
    
    public List<String> ListeLivre() throws RemoteException;
   
    public boolean rappeleCommandeLivre(String nomUtilisateur, String nomLivre) throws RemoteException;
    

    /**
     * Permet de recuperer les informations de connection sur le server JMS et lire la queue
     * 
     * @return InfoConnectionJMS
     * @throws RemoteException
     */
    public InfoConnectionJMS abonnement() throws RemoteException; 
    
}
