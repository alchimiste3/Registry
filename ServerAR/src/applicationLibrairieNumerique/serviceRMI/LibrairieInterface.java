package applicationLibrairieNumerique.serviceRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import applicationLibrairieNumerique.donneeRMI.Livre;
import objetRMICommun.InfoConnectionJMS;

/**
 * Interface du service Librairie qui fournie des service a un client
 * @author Quentin Laborde
 *
 */
public interface LibrairieInterface extends Remote {

    /**
     * Permet de recuperer les informations sur la librairie
     * @return
     */
    public String getInformation() throws RemoteException;
    
    /**
     * Permet d'inscrire un acheteur a la librairie
     * @param nomAcheteur
     * @param numCarteCredit
     * @return
     * @throws RemoteException
     */
    public boolean inscription(String nomAcheteur, String numCarteCredit, String nomQueue) throws RemoteException;

    /**
     * Permet d'acheter un livre a un acheteur
     * @param nomAcheteur
     * @param nomLivre
     * @return le livre acheter
     * @throws RemoteException
     */
    public Livre acheterLivre(String nomAcheteur, String nomLivre, String nomQueue) throws RemoteException;
    
    /**
     * Permet de recuperer les liste des livre disponnible a la vente
     * @return la liste des livre
     * @throws RemoteException
     */
    public List<String> ListeLivre() throws RemoteException;
   
    /**
     * Permet de commander un livre avec un rappel quant le livre est arrive
     * @param client
     * @param nomAcheteur
     * @param nomLivre
     * @return 
     * @throws RemoteException
     */
    public boolean rappeleCommandeLivre(AccesClientLibrairieInterface client, String nomAcheteur, String nomLivre, String nomQueue) throws RemoteException;
    
    /**
     * Permet au client de demander la creation d'une queue pour communiqué avec le serveur
     * @param idClient
     * @return Les information sur la queue
     * @throws RemoteException
     */
    public InfoConnectionJMS abonnement(String idClient) throws RemoteException; 
    
}
