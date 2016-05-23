package mainServer;

import botany.BotanyDB;
import botany.BotanyDBInterface;
import botany.BotanyNotification;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Timer;
import objetRMICommun.InfoConnectionJMS;
import objetRMICommun.MaRegistryInterface;

public class ServerBotanique {
    private InfoConnectionJMS infoJMSBotany = new InfoConnectionJMS();
    
    
        public static void main(String[] args) {
        ServerBotanique server = new ServerBotanique();
        server.init();
    }
        public void init(){
        try {
            
            
            System.out.println("Creation de l'objet serveur botanique qui permet à des clients de récupérer des information sur certaines espèces");
    
            MaRegistryInterface maRMI = (MaRegistryInterface) Naming.lookup("rmi://localhost:1100/MaRegistry");
                        
            BotanyDB service = new BotanyDB(maRMI);

            Naming.rebind("rmi://localhost:1101/Service",(BotanyDBInterface)service);
            maRMI.rebind("BotanyDB", service);
            
            infoJMSBotany.setUrl("tcp://localhost:61616");
            infoJMSBotany.setLogin("user");
            infoJMSBotany.setPassword("user");
            infoJMSBotany.setNom("queueBotany");  
            BotanyNotification jms = new BotanyNotification();
            jms.connection(infoJMSBotany.getUrl(), infoJMSBotany.getLogin(), infoJMSBotany.getPassword());
            jms.init(infoJMSBotany.getNom(), service.getPlantes());
            Thread t = new Thread(jms);
            t.start();
                       
            
        } catch (RemoteException | MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }      
    
}
