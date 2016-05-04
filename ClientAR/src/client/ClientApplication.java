package client;

import java.rmi.Naming;
import java.rmi.registry.*;

import objetRMI.MaRegistryInterface;
import objetRMI.TestInterface;


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
public class ClientApplication {


    public static void main(String[] args) {
        try {
            System.out.println("Creation de l'objet serveur de l'application");

            if(System.getSecurityManager() == null){
                System.out.println("init SecurityManager");
                System.setSecurityManager(new SecurityManager());
            }
            
            Registry reg = LocateRegistry.getRegistry("localhost",1098);

           
            MaRegistryInterface maRMI = (MaRegistryInterface) reg.lookup("MaRegistry");
            

            //TestInterface test = (TestInterface) maRMI.lookup("test1");
            
            test.salut();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
