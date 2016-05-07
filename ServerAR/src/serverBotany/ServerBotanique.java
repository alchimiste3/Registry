package serverBotany;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import objetRMI.MaRegistryInterface;

public class ServerBotanique {
    
    
        public static void main(String[] args) {
        ServerBotanique server = new ServerBotanique();
        server.init();
    }
        public void init(){
        try {
            System.out.println("Creation de l'objet serveur botanique qui permet à des clients de récupérer des information sur certaines espèces");
    
            BotanyDB service = new BotanyDB();
            service.addPlant(new Plant("Coriandre", "La coriandre est une plante annuelle élancée, ramifiée, mesurant généralement en floraison de 30 à 60 cm mais pouvant atteindre 1,40 m11. Le feuillage et la tige sont verts ou vert clair tirant parfois sur le rouge ou le violet pendant la floraison, glabres, luisants (notamment les faces inférieures des feuilles). L'inflorescence, blanche ou rose-mauve très pâle, est typique des Apiacées (Ombellifères) : petites fleurs pentamères disposées en ombelles composées. L'odeur de la plante est souvent décrite comme fétide12,13, surtout en floraison ou début de fructification.", "Apiacées"));
            service.addPlant(new Plant("Poivrier noir", "Le Poivrier noir ou Poivre noir (Piper nigrum) est une liane de la famille des Pipéracées originaire de la côte de Malabar. Cette plante est cultivée dans la zone tropicale pour ses baies qui donnent une épice recherchée : le poivre.", "Pipéracées"));
            service.addPlant(new Plant("Aneth", "C'est une plante annuelle à tige lisse, de 80 à 150 cm avec un étalement d'une trentaine de centimètres. Les feuilles sont très découpées, fines, filiformes, de couleur vert bleuté.  La floraison produit des ombelles terminales à fleurs jaune verdâtre parfumées. Chaque fleur a 5 pétales jaunes et 5 étamines.  Les graines sont petites (2,5 mm), ovales, aplaties à côtes proéminentes, de couleur brune ; elles se scindent en deux au séchage et sont matures en août-septembre.", " Apiacées"));

            Naming.rebind("rmi://localhost:1101/BotanyDB",(BotanyDBInterface)service);
            
            MaRegistryInterface maRMI = (MaRegistryInterface) Naming.lookup("rmi://localhost:1100/MaRegistry");
            
            maRMI.rebind("BotanyDB", service);
            
        } catch (RemoteException | MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }
    
}
