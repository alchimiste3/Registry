package server;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.util.HashMap;
import java.util.Scanner;

import applicationLibrairieNumerique.Librairie;
import applicationLibrairieNumerique.LibrairieInterface;
import applicationLibrairieNumerique.Livre;
import applicationTest.Donnee;
import applicationTest.Service;
import applicationTest.ServiceInterface;
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
public class ServerApplication {

    private InfoConnectionJMS infoJMS = new InfoConnectionJMS();
    private InfoConnectionJMS infoJMSLibrairy = new InfoConnectionJMS();

    
    public static void main(String[] args) {
        ServerApplication server = new ServerApplication();

        System.out.println("Creation de l'objet serveur de l'application");

        
        server.initLibrairie();

        
        server.initParDefaut();
    }
    
    public void initParDefaut(){
        try {
    
            // On init les info du JMS
            infoJMS.setUrl("tcp://localhost:61616");
            infoJMS.setLogin("user");
            infoJMS.setPassword("user");
            infoJMS.setNom("queueTest");
                        
            // On creer un service et une donnee
            Service service = new Service(infoJMS);
            Donnee donnee = new Donnee();

            Naming.rebind("rmi://localhost:1101/Service",(ServiceInterface)service);
            
            System.out.println(donnee.getDonnee());            
            System.out.println(service.getInfo());

            
            MaRegistryInterface maRMI = (MaRegistryInterface) Naming.lookup("rmi://localhost:1100/MaRegistry");
            
            maRMI.rebind("Service", service);
            maRMI.rebind("Donnee", donnee);
            runPublicationInfo();  
            
        } catch (RemoteException | MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
            ServerJMS jms = initLibrairieQueue();

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
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public ServerJMS initLibrairieQueue(){
        ServerJMS jms = new ServerJMS();
        jms.connection(infoJMSLibrairy.getUrl(), infoJMSLibrairy.getLogin(), infoJMSLibrairy.getPassword());
        jms.init(infoJMSLibrairy.getNom());
        return jms;
    }
    
    public void runPublicationInfo(){
        try {
            
            ServerJMS jms = new ServerJMS();
            jms.connection(infoJMS.getUrl(), infoJMS.getLogin(), infoJMS.getPassword());
            jms.init(infoJMS.getNom());
            
            int i = 0;
            
            while(true){
                HashMap<String, String> map = new HashMap<>();
                map.put("message", "info" + i++ );
                jms.envoyerMessage("defaut", map);
                Thread.sleep(100000);

            }

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace(); 
        }
    }

}
