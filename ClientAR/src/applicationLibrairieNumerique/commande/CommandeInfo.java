package applicationLibrairieNumerique.commande;

import java.rmi.RemoteException;

import applicationLibrairieNumerique.serviceRMI.LibrairieInterface;

public class CommandeInfo {

    private LibrairieInterface librairie;
    
    
    public CommandeInfo(LibrairieInterface librairie) {
        this.librairie = librairie;
    }

    public void execute() throws RemoteException{
        System.out.println("→librairie.getInformation()");
        System.out.println(librairie.getInformation());
    }
}
