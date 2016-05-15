package applicationLibrairieNumerique.serviceRMI;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.jms.MessageProducer;

import applicationLibrairieNumerique.donneeRMI.Acheteur;
import applicationLibrairieNumerique.donneeRMI.Livre;
import mainServer.ServerJMS;
import mainServer.ThreadCommandeClientLibrairie;
import objetRMICommun.InfoConnectionJMS;
import objetRMICommun.MaRegistryInterface;

@SuppressWarnings("serial")
public class Librairie extends UnicastRemoteObject implements LibrairieInterface{
    
    InfoConnectionJMS infoJMSLibrairy;
    private ServerJMS jms;
    private MaRegistryInterface maRMI;
    
    private List<String> listeLivre = new ArrayList<>();
    
    private MessageProducer prod = null;

    
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
        return "Librairie";
    }
    
    @Override
    public boolean inscription(String nomAcheteur, String numCarteCredit) throws RemoteException {
        Acheteur acheteur = (Acheteur)maRMI.lookup(nomAcheteur);
        
        System.out.println("prod = " + prod);
        if(acheteur != null && prod != null){
            HashMap<String, String> map = new HashMap<>();

            map = new HashMap<>();
            map.put("message", "inscription \""+ nomAcheteur + "\": cet acheteur existe déja !");
          
            jms.envoyerMessage(prod, "Librairie", map);
            return false;
        }
        
        acheteur = new Acheteur(nomAcheteur,numCarteCredit);
        maRMI.rebind(nomAcheteur, acheteur);
        return true;
    }

    @Override
    public Livre acheterLivre(String nomAcheteur, String nomLivre) throws RemoteException {        
        Acheteur acheteur = (Acheteur)maRMI.lookup(nomAcheteur);
        
        HashMap<String, String> map = new HashMap<>();
        
        
        if(acheteur == null && prod != null){
            System.out.println(infoJMSLibrairy.getNom());
            System.out.println("acheteur null");
            map = new HashMap<>();
            map.put("utilisateur", nomAcheteur);
            map.put("message", "acheterLivre : L'utilisateur n'est pas enregistrer");
            
            jms.envoyerMessage(prod, "Librairie", map);
            return null;
        }
        
        Livre livre = (Livre)maRMI.lookup(nomLivre);
        
        if(livre == null && prod != null){
            
            System.out.println("livre null");

            map = new HashMap<>();
            map.put("utilisateur", nomAcheteur);
            map.put("message", "acheterLivre : Ce livre n'existe pas");
          
            jms.envoyerMessage(prod, "Librairie", map);
            return null;
        }
        
        if(prod != null){
            map = new HashMap<>();
            map.put("utilisateur", nomAcheteur);
            map.put("message", "acheterLivre : Paiement enregistrer de l'utilisateur "+nomAcheteur+" pour le livre : "+nomLivre);  
            jms.envoyerMessage(prod, "Librairie", map);
        }
        


        acheteur.getLivreAcheter().add(nomLivre);
        
        maRMI.rebind(nomAcheteur, acheteur);
        
        return livre;
    }

    @Override
    public List<String> ListeLivre() throws RemoteException {
        return listeLivre;
    }

  
    @Override
    public boolean rappeleCommandeLivre(AccesClientLibrairieInterface client, String nomAcheteur, String nomLivre) throws RemoteException {
        HashMap<String, String> map = new HashMap<>();

        Acheteur acheteur = (Acheteur)maRMI.lookup(nomAcheteur);
        
        System.out.println(acheteur);
        System.out.println(prod);

        if(acheteur == null && prod != null){
            System.out.println(infoJMSLibrairy.getNom());
            System.out.println("acheteur null");
            map = new HashMap<>();
            map.put("utilisateur", nomAcheteur);
            map.put("message", "rappeleCommandeLivre : L'utilisateur n'est pas enregistrer");
            
            jms.envoyerMessage(prod, "Librairie", map);
            return false;
        }
        
        Livre livre = (Livre)maRMI.lookup(nomLivre);

        System.out.println(livre);

        if(livre != null && prod != null){
            
            System.out.println("livre null");

            map = new HashMap<>();
            map.put("utilisateur", nomAcheteur);
            map.put("message", "rappeleCommandeLivre : Ce livre existe déjà");
          
            jms.envoyerMessage(prod, "Librairie", map);
            return false;
        }
        
        if(prod != null){
            map = new HashMap<>();
            map.put("utilisateur", nomAcheteur);
            map.put("message", "rappeleCommandeLivre : le livre sera ajouter a la librairie dans 50 seconde");  
            jms.envoyerMessage(prod, "Librairie", map);
        }


        // Ici on attend toujous 50 second pour une commande, c'est une limitation du code
        new ThreadCommandeClientLibrairie(client , nomLivre, this, 50000).start();;
        return true;
    }

    @Override
    public InfoConnectionJMS abonnement(String idClient) throws RemoteException {
        String nomQueue = "Queue_"+idClient;
        prod = jms.initQueue(nomQueue);
        infoJMSLibrairy.setNom(nomQueue);
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
