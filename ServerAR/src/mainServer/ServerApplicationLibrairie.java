package mainServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Scanner;

import applicationLibrairieNumerique.serviceRMI.Librairie;
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
public class ServerApplicationLibrairie {

    private InfoConnectionJMS infoJMSLibrairy = new InfoConnectionJMS();
    
    private int nombreClient = 0;;
    
    public static void main(String[] args) {
        ServerApplicationLibrairie server = new ServerApplicationLibrairie();

        System.out.println("Creation de l'objet serveur de l'application");

        
        server.initLibrairie();

        
    }

    public void initLibrairie(){
        try {
    
            /**
             * On init les info du JMS
             */
            infoJMSLibrairy.setUrl("tcp://localhost:61616");
            infoJMSLibrairy.setLogin("user");
            infoJMSLibrairy.setPassword("user");
            infoJMSLibrairy.setNom("queueLibrairie"); 
            
            ServerJMS jms = new ServerJMS();
            jms.connection(infoJMSLibrairy.getUrl(), infoJMSLibrairy.getLogin(), infoJMSLibrairy.getPassword());
            
            /**
             * On recupere le stubs de la registry
             */
            MaRegistryInterface maRMI = (MaRegistryInterface) Naming.lookup("rmi://localhost:1100/MaRegistry");

            /**
             * On creer un service et une donnee
             */
            Librairie librairie = new Librairie(maRMI, infoJMSLibrairy, jms);
            Naming.rebind("rmi://localhost:1101/Service",(LibrairieInterface)librairie);
            maRMI.rebind("Librairie", librairie);
            

        } catch (RemoteException | MalformedURLException e) {
            System.out.println("Probleme initLibrairie → "+e);
            e.printStackTrace();
        } catch (NotBoundException e) {
            System.out.println("Probleme initLibrairie → "+e);
            e.printStackTrace();
        }
    }


}
