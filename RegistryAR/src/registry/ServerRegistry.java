package registry;

import java.rmi.Naming;

import objetRMICommun.MaRegistry;

/**
 * Class qui permet de creer un serveur Registry qui fournie les services d'une Registry
 * @author Quentin Laborde
 *
 */
public class ServerRegistry {

    public static void main(String[] args) {
        ServerRegistry server = new ServerRegistry();
        server.init();
    }
    
    
    public void init(){
        try {
            System.out.println("Creation de l'objet serveur...");
            
            if(System.getSecurityManager() == null){
                System.out.println("init SecurityManager");
                System.setSecurityManager(new SecurityManager());
            }
            
            //La registry qui permet 
            MaRegistry obj = new MaRegistry();

           
            
            Naming.rebind("rmi://localhost:1100/MaRegistry",obj);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 
}
