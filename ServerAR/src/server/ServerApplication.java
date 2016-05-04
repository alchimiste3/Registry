package server;

import java.rmi.Naming;
import java.rmi.registry.*;

import objetRMI.MaRegistryInterface;
import objetRMI.Test;
import objetRMI.Test2;
import objetRMI.Test3;

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


            
            MaRegistryInterface maRMI = (MaRegistryInterface) Naming.lookup("rmi://localhost:1098/MaRegistry");
            
            Test t = new Test();
            
            maRMI.salut();
            
            maRMI.rebind("test1", new Test());
            maRMI.rebind("test2", new Test2());
            maRMI.rebind("test3", new Test3());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
