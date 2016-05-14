package mainClient;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.jms.JMSException;
import javax.jms.Message;

import applicationLibrairieNumerique.commande.CommandeAcheterLivre;
import applicationLibrairieNumerique.commande.CommandeCommanderLivre;
import applicationLibrairieNumerique.commande.CommandeInfo;
import applicationLibrairieNumerique.commande.CommandeInscription;
import applicationLibrairieNumerique.commande.CommandeListerLivre;
import applicationLibrairieNumerique.serviceRMI.LibrairieInterface;
import objetRMICommun.InfoConnectionJMS;
import objetRMICommun.MaRegistryInterface;


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
        listeCommande.add("inscription → permet a un acheteur de s'inscrire a la librairie");
        listeCommande.add("info → permet de récupérer les  info sur la librairie");
        listeCommande.add("acheterLivre → permet d'acheter un livre");
        listeCommande.add("listerLivre → permet d'affciher la liste des livres");
        listeCommande.add("commanderLivre → permet d'envoyer un demande de rappele pour la sortie d'un livre");

    }
    
    public static void main(String[] args) {
        try {
            
            ClientApplicationLibrairie clientLibrairie = new ClientApplicationLibrairie();
            
            MaRegistryInterface maRMI = clientLibrairie.init();
     
            clientLibrairie.runClientLibrairie(maRMI);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public MaRegistryInterface init(){
        try {
            System.out.println("Creation de l'objet serveur de l'application");
    
            if(System.getSecurityManager() == null){
                System.out.println("init SecurityManager");
                System.setSecurityManager(new SecurityManager());
            }
            
            Registry reg = LocateRegistry.getRegistry("localhost",1100);
            
            return (MaRegistryInterface) reg.lookup("MaRegistry");

        } catch (RemoteException e) {
            System.out.println("Probleme init → "+e);
        } catch (NotBoundException e) {
            System.out.println("Probleme init → "+e);
        }
        
        return null;
       
    }
    

    public void runClientLibrairie(MaRegistryInterface maRMI){
        try{
                
            librairie = (LibrairieInterface) maRMI.lookup("Librairie");     
            
            
            @SuppressWarnings("resource")
            Scanner scanner = new Scanner(System.in);  
            
            /**
             * Pour pouvoir creer un Queue personnalisé pour chaque client
             */
            System.out.println("Vous devez choisir un identifiant pour votre client :");

            
            String idClient = scanner.nextLine();
            

            InfoConnectionJMS infoJMS = librairie.abonnement(idClient);
            jms = new ClientJMS();
            jms.connection(infoJMS.getUrl(), infoJMS.getLogin(), infoJMS.getPassword());
            jms.init(infoJMS.getNom(), listeMessage);
      
            
            System.out.println("Vous pouvez utiliser plusieurs commandes (entrer \"?\" pour avoir la liste");
    
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
                        
                        new CommandeInscription(librairie, nomUtilisateur, numCarteCredit).execute();
                    break;
                    
                    case "info":
                        new CommandeInfo(librairie).execute();
                    break;
                    
                    case "acheterLivre":
                        System.out.println("Nom de l'utilisateur ?");
                        nomUtilisateur = scanner.nextLine();

                        System.out.println("nom du livre ?");
                        nomLivre = scanner.nextLine();

                        new CommandeAcheterLivre(librairie, nomUtilisateur, nomLivre, listeMessage).execute();
                    break;
                    
                    case "listerLivre":
                        new CommandeListerLivre(librairie).execute();
                    break;
                    
                    case "commanderLivre":
                        System.out.println("Nom de l'utilisateur ?");
                        nomUtilisateur = scanner.nextLine();

                        System.out.println("nom du livre ?");
                        nomLivre = scanner.nextLine();

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
            System.out.println("Probleme runClientLibrairie → "+e);
        }
        

    }
    
   
  
    private void help(){
        for(String s : listeCommande){
            System.out.println(s);
        }
    }
  

}
