package clientBotany;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import objetRMICommun.MaRegistryInterface;

public class ClientBotany {
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
            
            System.out.println("On affiche les informations d'une espèce de plante");
            service.addPlant(new Plant("Lavande ", "La Lavande officinale, Lavande vraie ou Lavande à feuilles étroites (Lavandula angustifolia Mill.) est une espèce de sous-arbrisseaux de la famille des Lamiaceae. C'est une plante qui est appréciée pour son odeur. C'est la plus appréciée des lavandes pour la qualité olfactive de son huile essentielle. À l'état sauvage, elle pousse surtout en Provence mais elle peut être cultivée dans des régions plus septentrionales, d'autant qu'il en existe de nombreux cultivars.", "Lamiaceae"));

            System.out.println(service.getPlant("Coriandre"));
            System.out.println(service.getPlant("Lavande"));
            
        }
        catch(RemoteException e){
            
        }

        
    }
    
}
