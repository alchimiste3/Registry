package mainServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


import applicationLibrairieNumerique.serviceRMI.Librairie;
import applicationLibrairieNumerique.serviceRMI.LibrairieInterface;
import objetRMICommun.InfoConnectionJMS;
import objetRMICommun.MaRegistryInterface;


/**
 * Class qui permet de creer un serveur simulant un libraire en ligne avec des service RMI
 * @author Quentin Laborde
 *
 */
public class ServerApplicationLibrairie {

    private InfoConnectionJMS infoJMSLibrairy = new InfoConnectionJMS();
        
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
