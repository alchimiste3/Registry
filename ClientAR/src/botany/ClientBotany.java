package botany;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import objetRMICommun.MaRegistryInterface;

public class ClientBotany {
    
        private List<String> listeCommande = new ArrayList<>();

    public ClientBotany() {
        listeCommande.add("ajouter nomPlante famille description -> Permet d'ajouter une plante à la base de données");
        listeCommande.add("info nomPlante -> Permet de consulter les informations sur une plante");
        listeCommande.add("liste -> Permet de récupérer la liste des plantes contenues dans la base de données");
        
    }
         
        public static void main(String[] args) {
        try {
            ClientBotany client = new ClientBotany();

            MaRegistryInterface maRMI = client.init();
            client.run(maRMI);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public MaRegistryInterface init(){
        try {
            System.out.println("Initialisation du client Botany");
    
            if(System.getSecurityManager() == null){
                System.out.println("init SecurityManager");
                System.setSecurityManager(new SecurityManager());
            }
            
            Registry reg = LocateRegistry.getRegistry("localhost",1100);
            
            return (MaRegistryInterface) reg.lookup("MaRegistry");

        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
       
    }
    
        public void run(MaRegistryInterface maRMI){
        try{
            BotanyDBInterface service = (BotanyDBInterface) maRMI.lookup("BotanyDB");           
            Scanner scanner = new Scanner(System.in);        
            
            System.out.println("Vous pouvez utiliser plusieurs commandes (entrer \"?\" pour avoir la liste");
            String rep;
            String nomPlante, famille, description;
            
            while(true){
                
                System.out.println("Entrer une commande !");
                rep = scanner.nextLine();

                switch (rep) {
                    case "?":
                        help();
                    break;
    
                    case "ajouter":
                        System.out.println("Nom de la plante ?");
                        nomPlante = scanner.nextLine();
                        
                        System.out.println("Famille ?");
                        famille = scanner.nextLine();
                        
                        System.out.println("Description ?");
                        description = scanner.nextLine();
                        
                        new CommandeAjouter(service, nomPlante, description, famille).execute();
                    break;
                        
                    case "info":
                        System.out.println("Nom de la plante ?");
                        nomPlante = scanner.nextLine();
                        new CommandeInfo(service, nomPlante).execute();
                    break;
                    
                    case "lister":
                        new CommandeLister(service).execute();
                    break;
                    default:
                    break;
                }
                
                System.out.println("");

            } 
            
        }
        catch(RemoteException e){
            
        }

        
    }
        
   private void help(){
        for(String s : listeCommande){
            System.out.println(s);
        }
    }
    
}
