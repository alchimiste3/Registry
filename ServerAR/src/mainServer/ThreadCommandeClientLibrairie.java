package mainServer;

import java.rmi.RemoteException;
import java.util.Date;

import applicationLibrairieNumerique.donneeRMI.Livre;
import applicationLibrairieNumerique.serviceRMI.AccesClientLibrairieInterface;
import applicationLibrairieNumerique.serviceRMI.Librairie;

public class ThreadCommandeClientLibrairie extends Thread{
    
    AccesClientLibrairieInterface client;
    int temps;
    String nomLivre;
    Librairie librairie;
    
    public ThreadCommandeClientLibrairie(AccesClientLibrairieInterface client, String nomLivre, Librairie librairie, int temps) {
        this.client = client;
        this.temps = temps;
        this.librairie = librairie;
        this.nomLivre = nomLivre;
    }

    @Override
    public void run(){
        try {
            Thread.sleep(temps);
            
            Date date = new Date();
            
            System.out.println("On creer le livre attendu par le client");

            @SuppressWarnings("deprecation")
            Livre livre = new Livre(nomLivre,
                    "moi",
                    date.getMonth(),
                    date.getYear(),
                    "un autre genre",
                    "contenue du livre");
            
            librairie.getListeLivre().add(nomLivre);
            librairie.getMaRMI().rebind(nomLivre, livre);
            
            

            System.out.println("On rappele le client");
            
            
            client.appeler("Le livre "+nomLivre+" est disponible");
            
        } catch (InterruptedException | RemoteException e) {
            System.out.println("Probleme ThreadRappelerClient → "+e);
            e.printStackTrace();
        }
    }
}
