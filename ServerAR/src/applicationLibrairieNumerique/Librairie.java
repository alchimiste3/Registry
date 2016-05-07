package applicationLibrairieNumerique;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import objetRMI.InfoConnectionJMS;
import objetRMI.MaRegistryInterface;
import server.ServerJMS;
import server.ThreadRappelerClientLibrairie;

@SuppressWarnings("serial")
public class Librairie extends UnicastRemoteObject implements LibrairieInterface{
    
    InfoConnectionJMS infoJMSLibrairy;
    private ServerJMS jms;
    private MaRegistryInterface maRMI;
    
    private List<String> listeLivre = new ArrayList<>();


    public Librairie(MaRegistryInterface maRMI, InfoConnectionJMS infoJMSLibrairy, ServerJMS jms) throws RemoteException {
        super();
        this.jms = jms;
        this.maRMI = maRMI;
        this.infoJMSLibrairy = infoJMSLibrairy;
        /**
         * On creer plusieur libre pour remplire la librairie
         */
        
        Livre livre1 = new Livre("Millénium - Tome 1 : Les hommes qui n'aimaient pas les femmes",
                "Stieg Larsson",
                12,
                2011,
                "Polar",
                "contenue du livre");

        Livre livre2 = new Livre("Millénium - Tome 2 : La fille qui rêvait d'un bidon d'essence et d'une allumette",
                "Stieg Larsson",
                1,
                2012,
                "Polar",
                "contenue du livre");
        
        Livre livre3 = new Livre("Millénium - Tome 3 : La reine dans le palais des courants d'air",
                "Stieg Larsson",
                1,
                2013,
                "Polar",
                "contenue du livre");
        
        listeLivre.add("Millénium - Tome 1");
        maRMI.rebind("Millénium - Tome 1", livre1);

        listeLivre.add("Millénium - Tome 2");
        maRMI.rebind("Millénium - Tome 2", livre2);

        listeLivre.add("Millénium - Tome 3");
        maRMI.rebind("Millénium - Tome 3", livre3);
        
    }

    @Override
    public String getInformation() throws RemoteException{
        System.out.println("\n getInformation \n");

        return "Librairie";
    }
    
    @Override
    public boolean inscription(String nomUtilisateur, String numCarteCredit) throws RemoteException {
        System.out.println("\n inscription \n");

        Acheteur acheteur = new Acheteur(nomUtilisateur,numCarteCredit);
        maRMI.rebind(nomUtilisateur, acheteur);
        // TODO faire un bind
        return true;
    }

    @Override
    public Livre acheterLivre(String nomUtilisateur, String nomLivre) throws RemoteException {
        System.out.println("\n acheterLivre \n");
        
        Acheteur acheteur = (Acheteur)maRMI.lookup(nomUtilisateur);
        
        HashMap<String, String> map = new HashMap<>();
        
        
        if(acheteur == null){
            System.out.println(infoJMSLibrairy.getNom());
            System.out.println("acheteur null");
            map = new HashMap<>();
            map.put("utilisateur", nomUtilisateur);
            map.put("message", "acheterLivre : L'utilisateur n'est pas enregistrer");
            
            jms.envoyerMessage("Librairie", map);
            return null;
        }
        
        Livre livre = (Livre)maRMI.lookup(nomLivre);
        
        if(livre == null){
            
            System.out.println("livre null");

            map = new HashMap<>();
            map.put("utilisateur", nomUtilisateur);
            map.put("message", "acheterLivre : Ce livre n'existe pas");
          
            jms.envoyerMessage("Librairie", map);
            return null;
        }
        
        map = new HashMap<>();
        map.put("utilisateur", nomUtilisateur);
        map.put("message", "acheterLivre : Paiement enregistrer de l'utilisateur "+nomUtilisateur+" du livre "+nomLivre);  
        jms.envoyerMessage("Librairie", map);

        acheteur.getLivreAcheter().add(nomLivre);
        
        maRMI.rebind(nomUtilisateur, acheteur);
        
        return livre;
    }

    @Override
    public List<String> ListeLivre() throws RemoteException {
        System.out.println("\n ListeLivre \n");

        return listeLivre;
    }

  
    @Override
    public boolean rappeleCommandeLivre(String nomUtilisateur, String nomLivre) throws RemoteException {
        System.out.println("\n rappeleSortieLivre \n");

        HashMap<String, String> map = new HashMap<>();

        Acheteur acheteur = (Acheteur)maRMI.lookup(nomUtilisateur);
        
        if(acheteur == null){
            System.out.println(infoJMSLibrairy.getNom());
            System.out.println("acheteur null");
            map = new HashMap<>();
            map.put("utilisateur", nomUtilisateur);
            map.put("message", "rappeleSortieLivre : L'utilisateur n'est pas enregistrer");
            
            jms.envoyerMessage("Librairie", map);
            return false;
        }
        
        Livre livre = (Livre)maRMI.lookup(nomLivre);

        
        if(livre != null){
            
            System.out.println("livre null");

            map = new HashMap<>();
            map.put("utilisateur", nomUtilisateur);
            map.put("message", "rappeleSortieLivre : Ce livre existe déjà");
          
            jms.envoyerMessage("Librairie", map);
            return false;
        }
        
        map = new HashMap<>();
        map.put("utilisateur", nomUtilisateur);
        map.put("message", "rappeleSortieLivre : le livre sera ajouter dans 50 seconde");  
        jms.envoyerMessage("Librairie", map);

        
        new ThreadRappelerClientLibrairie(acheteur.getClient(), nomLivre, this, 50000);
        return true;
    }

    @Override
    public InfoConnectionJMS abonnement() throws RemoteException {
        System.out.println("\n abonnement \n");

        return infoJMSLibrairy;
    }

    public MaRegistryInterface getMaRMI() {
        return maRMI;
    }

    public void setMaRMI(MaRegistryInterface maRMI) {
        this.maRMI = maRMI;
    }

    public List<String> getListeLivre() {
        return listeLivre;
    }

    public void setListeLivre(List<String> listeLivre) {
        this.listeLivre = listeLivre;
    }


    
    

    
    

}
