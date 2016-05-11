package serverBotany;

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
                        
            BotanyDB service = new BotanyDB();
            Plant p1 = new Plant("Coriandre", "La coriandre est une plante annuelle élancée, ramifiée, mesurant généralement en floraison de 30 à 60 cm mais pouvant atteindre 1,40 m11. Le feuillage et la tige sont verts ou vert clair tirant parfois sur le rouge ou le violet pendant la floraison, glabres, luisants (notamment les faces inférieures des feuilles). L'inflorescence, blanche ou rose-mauve très pâle, est typique des Apiacées (Ombellifères) : petites fleurs pentamères disposées en ombelles composées. L'odeur de la plante est souvent décrite comme fétide12,13, surtout en floraison ou début de fructification.", "Apiacées");
            service.addPlant(p1);
            maRMI.rebind(p1.getNom(), p1);
            Plant p2 = new Plant("Poivrier noir", "Le Poivrier noir ou Poivre noir (Piper nigrum) est une liane de la famille des Pipéracées originaire de la côte de Malabar. Cette plante est cultivée dans la zone tropicale pour ses baies qui donnent une épice recherchée : le poivre.", "Pipéracées");
            service.addPlant(p2);
            maRMI.rebind(p2.getNom(), p2);
            Plant p3 = new Plant("Aneth", "C'est une plante annuelle à tige lisse, de 80 à 150 cm avec un étalement d'une trentaine de centimètres. Les feuilles sont très découpées, fines, filiformes, de couleur vert bleuté.  La floraison produit des ombelles terminales à fleurs jaune verdâtre parfumées. Chaque fleur a 5 pétales jaunes et 5 étamines.  Les graines sont petites (2,5 mm), ovales, aplaties à côtes proéminentes, de couleur brune ; elles se scindent en deux au séchage et sont matures en août-septembre.", " Apiacées");
            service.addPlant(p3);
            maRMI.rebind(p3.getNom(), p3);
            Naming.rebind("rmi://localhost:1101/Service",(BotanyDBInterface)service);
            maRMI.rebind("BotanyDB", service);
            
            infoJMSBotany.setUrl("tcp://localhost:61616");
            infoJMSBotany.setLogin("user");
            infoJMSBotany.setPassword("user");
            infoJMSBotany.setNom("queueBotany");  
            BotanyNotification jms = new BotanyNotification();
            jms.connection(infoJMSBotany.getUrl(), infoJMSBotany.getLogin(), infoJMSBotany.getPassword());
            jms.init(infoJMSBotany.getNom(), service.getPlantes());
            jms.run();
            
        } catch (RemoteException | MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }
    
}
