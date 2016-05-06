package server;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.*;

import objetRMI.Donnee;
import objetRMI.InfoConnectionJMS;
import objetRMI.MaRegistryInterface;
import objetRMI.Service;
import objetRMI.ServiceInterface;


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

    public static void main(String[] args) {
        ServerApplication server = new ServerApplication();
        server.init();
    }
    
    public void init(){
        try {
            System.out.println("Creation de l'objet serveur de l'application");
    
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
    
    public void runPublicationInfo(){
        try {
            
            ServerJMS jms = new ServerJMS();
            jms.connection(infoJMS.getUrl(), infoJMS.getLogin(), infoJMS.getPassword());
            jms.init(infoJMS.getNom());
            
            int i = 0;
            
            while(true){
                jms.envoyerMessage("info"+i++);
                Thread.sleep(20000);

            }

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace(); 
        }
    }

}
