package applicationLibrairieNumerique.commande;

import java.rmi.RemoteException;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

import applicationLibrairieNumerique.LibrairieInterface;
import applicationLibrairieNumerique.Livre;

public class CommandeAcheterLivre {

    private String nomUtilisateur;
    private String nomLivre;
    private LibrairieInterface librairie;
    private List<Message> listeMessage;
    
    public CommandeAcheterLivre(LibrairieInterface librairie, String nomUtilisateur, String nomLivre, List<Message> listeMessage) {
        super();
        this.nomUtilisateur = nomUtilisateur;
        this.nomLivre = nomLivre;
        this.librairie = librairie;
        this.listeMessage = listeMessage;
    }

    public void execute() throws RemoteException, JMSException{
        int sizelisteMessage = listeMessage.size();
        
        Livre livre = librairie.acheterLivre(nomUtilisateur, nomLivre);
        
        if(livre == null){
            System.out.println("Vous ne pouvez pas acheter ce livre");
        }
        else{
            System.out.println("Voici votre nouveau livre ");
            System.out.println(livre.toString());
        }
        
        //On attend que le message arrive
        while(sizelisteMessage == listeMessage.size()){}
        
        Message message = listeMessage.get(listeMessage.size() - 1);
        
        String service = ((MapMessage)message).getString("service");

        if(service.equals("Librairie")){
            String utilisateur = ((MapMessage)message).getString("utilisateur");
            if(utilisateur.equals(nomUtilisateur)){
                System.out.println(((MapMessage)message).getString("message"));
            }
        }
        
    }
}
