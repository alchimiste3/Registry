package client;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

import com.sun.org.apache.xml.internal.security.Init;

import applicationLibrairieNumerique.LibrairieInterface;
import applicationLibrairieNumerique.Livre;
import applicationLibrairieNumerique.commande.CommandeAcheterLivre;
import applicationLibrairieNumerique.commande.CommandeCommanderLivre;
import applicationLibrairieNumerique.commande.CommandeInfo;
import applicationLibrairieNumerique.commande.CommandeInscription;
import applicationLibrairieNumerique.commande.CommandeListerLivre;
import objetRMI.InfoConnectionJMS;
import objetRMI.MaRegistryInterface;


/**
 * Il faut demarer le registry avant de run :
 * dans le dossier des point class de interfaceDistante pour lancer la rmi avec "rmiregistry 1098"
 * 
 * 
 * -Djava.rmi.server.hostname="10.212.115.127"
 * 
 * 
 * -Djava.rmi.server.codebase=http://localhost:2000/
 * 
 * 
 * @author user
 *
 */
public class ClientApplicationLibrairie {
    
    private List<String> listeCommande = new ArrayList<>();
    private ArrayList<Message> listeMessage = new ArrayList<>();
    
    private LibrairieInterface librairie;
    
    private ClientJMS jms;
    
    public ClientApplicationLibrairie() {
        listeCommande.add("inscription nomUtilisateur numCarteCredit → permet a un acheteur de s'inscrire a la librairie");
        listeCommande.add("info → permet de récupérer les  info sur la librairie");
        listeCommande.add("acheterLivre → permet d'acheter un livre");
        listeCommande.add("listerLivre → permet d'affciher la liste des livres");
        listeCommande.add("commanderLivre → permet d'envoyer un demande de rappele pour la sortie d'un livre");

    }

    public void runClientLibrairie(MaRegistryInterface maRMI){
        try{
                
            librairie = (LibrairieInterface) maRMI.lookup("Librairie");        
    
            InfoConnectionJMS infoJMS = librairie.abonnement();
            jms = new ClientJMS();
            jms.connection(infoJMS.getUrl(), infoJMS.getLogin(), infoJMS.getPassword());
            jms.init(infoJMS.getNom(), listeMessage);
            
            Scanner scanner = new Scanner(System.in);        
            
            System.out.println("Vous pouvez utiliser plusieur commande (entrer \"?\" pour avoir la liste");
    
            String rep;
            String nomUtilisateur;
            String numCarteCredit;
            String nomLivre;


            while(true){
                
                System.out.println("Entrer une commande !");
                rep = scanner.nextLine();

                switch (rep) {
                    case "?":
                        help();
                    break;
    
                    case "inscription":
                        System.out.println("Nom de l'utilisateur ?");
                        nomUtilisateur = scanner.nextLine();
                        
                        System.out.println("Numero de carte de credit ?");
                        numCarteCredit = scanner.nextLine();
                        
                        //inscription(nomUtilisateur, numCarteCredit);
                        new CommandeInscription(librairie, nomUtilisateur, numCarteCredit).execute();
                    break;
                    
                    case "info":
                        //getInformation();
                        new CommandeInfo(librairie).execute();
                    break;
                    
                    case "acheterLivre":
                        System.out.println("Nom de l'utilisateur ?");
                        nomUtilisateur = scanner.nextLine();

                        System.out.println("nom du livre ?");
                        nomLivre = scanner.nextLine();

                        //acheterLivre(nomUtilisateur, nomLivre);
                        new CommandeAcheterLivre(librairie, nomUtilisateur, nomLivre, listeMessage).execute();
                    break;
                    
                    case "listerLivre":
                        //ListeLivre();
                        new CommandeListerLivre(librairie).execute();
                    break;
                    
                    case "commanderLivre":
                        System.out.println("Nom de l'utilisateur ?");
                        nomUtilisateur = scanner.nextLine();

                        System.out.println("nom du livre ?");
                        nomLivre = scanner.nextLine();

                        //rappeleCommandeLivre(nomUtilisateur, nomLivre);
                        new CommandeCommanderLivre(librairie, nomUtilisateur, nomLivre, listeMessage).execute();
                    break;
                    default:
                    break;
                }
                
                System.out.println("");

            } 
            
        }
        catch(RemoteException e){
            System.out.println("Probleme runClientLibrairie → "+e);
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }
    
    private void getInformation() throws RemoteException{
        System.out.println(librairie.getInformation());
    }
    
    private void inscription(String nomUtilisateur, String numCarteCredit) throws RemoteException{
        boolean rep = librairie.inscription(nomUtilisateur, numCarteCredit);
        
        if(rep) System.out.println("Votre inscription a bien été prise en compte");
        else System.out.println("L'inscription a échoué");
        
    }
    
    private void acheterLivre(String nomUtilisateur, String nomLivre) throws RemoteException, JMSException{
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
    
    private void ListeLivre() throws RemoteException{
        List<String> liste = librairie.ListeLivre();
        System.out.println("Voici la liste de livre disponible");
        for(String l : liste){
            System.out.println(l);
        }
    }
    
    private void rappeleCommandeLivre(String nomUtilisateur, String nomLivre) throws RemoteException, JMSException{
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
  
    private void help(){
        for(String s : listeCommande){
            System.out.println(s);
        }
    }
  

}
