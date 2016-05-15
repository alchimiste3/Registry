package applicationLibrairieNumerique.serviceRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import applicationLibrairieNumerique.donneeRMI.Livre;
import objetRMICommun.InfoConnectionJMS;

public interface LibrairieInterface extends Remote {

    /**
     * Permet de recuperer les information sur la librairie
     * @return
     */
    public String getInformation() throws RemoteException;
    
    public boolean inscription(String nomAcheteur, String numCarteCredit) throws RemoteException;

    public Livre acheterLivre(String nomAcheteur, String nomLivre) throws RemoteException;
    
    public List<String> ListeLivre() throws RemoteException;
   
    public boolean rappeleCommandeLivre(AccesClientLibrairieInterface client, String nomAcheteur, String nomLivre) throws RemoteException;
    

    public InfoConnectionJMS abonnement(String idClient) throws RemoteException; 
    
}
