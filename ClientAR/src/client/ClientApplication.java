package client;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.util.ArrayList;

import com.sun.org.apache.xml.internal.security.Init;

import objetRMI.Donnee;
import objetRMI.MaRegistryInterface;
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
public class ClientApplication {
    
    private ArrayList<String> listeMessage = new ArrayList<>();


    public static void main(String[] args) {
        try {
            ClientApplication client = new ClientApplication();

            MaRegistryInterface maRMI = client.init();
            client.run(maRMI);
            

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
            
            Donnee donnee = (Donnee) maRMI.lookup("Donnee");
            System.out.println(donnee.getDonnee());
            ServiceInterface service = (ServiceInterface) maRMI.lookup("Service");        
            System.out.println(service.getInfo());
            
            System.out.println(maRMI.dernierObjetAjouter(2));
            
            ClientJMS jms = new ClientJMS();
            jms.connection("tcp://localhost:61616", "user", "user");
            jms.init("queueTest", listeMessage);
            
        }
        catch(RemoteException e){
            
        }
        

    }

}
