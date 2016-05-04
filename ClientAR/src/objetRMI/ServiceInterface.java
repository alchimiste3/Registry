package objetRMI;

import java.rmi.Remote;

public interface ServiceInterface extends Remote{

    /**
     * Permet de recuperer les information sur le service
     * @return
     */
    public String getInfo();
    
    /**
     * Permet de recuperer un objet Resultat qui contient ce qu'on veut
     * @return
     */
    public ReponseService accesService();
    
    
    
    public boolean etreRappelé(AccesClient); 
    
    
    //s'abonner aux dernières informations
}
