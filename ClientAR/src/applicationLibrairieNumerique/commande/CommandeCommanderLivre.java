package applicationLibrairieNumerique.commande;

import java.rmi.RemoteException;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

import applicationLibrairieNumerique.LibrairieInterface;

public class CommandeCommanderLivre {

    private String nomUtilisateur;
    private String nomLivre;
    private LibrairieInterface librairie;
    private List<Message> listeMessage;
    
    public CommandeCommanderLivre(LibrairieInterface librairie, String nomUtilisateur, String nomLivre, List<Message> listeMessage) {
        this.nomUtilisateur = nomUtilisateur;
        this.nomLivre = nomLivre;
        this.librairie = librairie;
        this.listeMessage = listeMessage;
    }

    public void execute() throws RemoteException, JMSException{
        int sizelisteMessage = listeMessage.size();

        boolean rep = librairie.rappeleCommandeLivre(nomUtilisateur, nomLivre);
        
        if(rep) System.out.println("Votre commande est comfirmer");
        else System.out.println("commande impossible");
        
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
