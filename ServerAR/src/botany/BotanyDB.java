package botany;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import objetRMICommun.MaRegistryInterface;

public class BotanyDB  extends UnicastRemoteObject implements BotanyDBInterface {
        HashMap<String,Plant> plantes;

    public BotanyDB(MaRegistryInterface maRMI) throws RemoteException {
        super();
        this.plantes = new HashMap<>();
            Plant p1 = new Plant("Coriandre", "La coriandre est une plante annuelle élancée, ramifiée, mesurant généralement en floraison de 30 à 60 cm mais pouvant atteindre 1,40 m11. Le feuillage et la tige sont verts ou vert clair tirant parfois sur le rouge ou le violet pendant la floraison, glabres, luisants (notamment les faces inférieures des feuilles). L'inflorescence, blanche ou rose-mauve très pâle, est typique des Apiacées (Ombellifères) : petites fleurs pentamères disposées en ombelles composées. L'odeur de la plante est souvent décrite comme fétide12,13, surtout en floraison ou début de fructification.", "Apiacées");
            addPlant(p1);
            maRMI.rebind(p1.getNom(), p1);
            Plant p2 = new Plant("Poivrier noir", "Le Poivrier noir ou Poivre noir (Piper nigrum) est une liane de la famille des Pipéracées originaire de la côte de Malabar. Cette plante est cultivée dans la zone tropicale pour ses baies qui donnent une épice recherchée : le poivre.", "Pipéracées");
            addPlant(p2);
            maRMI.rebind(p2.getNom(), p2);
            Plant p3 = new Plant("Aneth", "C'est une plante annuelle à tige lisse, de 80 à 150 cm avec un étalement d'une trentaine de centimètres. Les feuilles sont très découpées, fines, filiformes, de couleur vert bleuté.  La floraison produit des ombelles terminales à fleurs jaune verdâtre parfumées. Chaque fleur a 5 pétales jaunes et 5 étamines.  Les graines sont petites (2,5 mm), ovales, aplaties à côtes proéminentes, de couleur brune ; elles se scindent en deux au séchage et sont matures en août-septembre.", " Apiacées");
            addPlant(p3);
            addPlant(new Plant("Lavande", "La Lavande officinale, Lavande vraie ou Lavande à feuilles étroites (Lavandula angustifolia Mill.) est une espèce de sous-arbrisseaux de la famille des Lamiaceae. C'est une plante qui est appréciée pour son odeur. C'est la plus appréciée des lavandes pour la qualité olfactive de son huile essentielle. À l'état sauvage, elle pousse surtout en Provence mais elle peut être cultivée dans des régions plus septentrionales, d'autant qu'il en existe de nombreux cultivars.", "Lamiaceae"));
            maRMI.rebind(p3.getNom(), p3);
    }
    
    public HashMap getPlantes() throws RemoteException{
        return plantes;
    }

    @Override
    public Plant getPlant(String nom) throws RemoteException {
        return plantes.get(nom);
    }

    @Override
    public void addPlant(Plant p) throws RemoteException {
        plantes.put(p.getNom(), p);
    }
    
}
