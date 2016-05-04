package server;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.registry.*;

import objetRMI.Donnee;
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


    public static void main(String[] args) {
        try {
            System.out.println("Creation de l'objet serveur de l'application");

            ServiceInterface service = new Service();
            Donnee donnee = new Donnee();

            Naming.rebind("rmi://localhost:1101/Service",service);

            
            MaRegistryInterface maRMI = (MaRegistryInterface) Naming.lookup("rmi://localhost:1098/MaRegistry");
            
            maRMI.rebind("Service", (Serializable) service);
            maRMI.rebind("Service", donnee);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
