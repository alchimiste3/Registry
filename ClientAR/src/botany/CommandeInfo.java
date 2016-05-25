/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botany;

import java.rmi.RemoteException;

public class CommandeInfo {
    private String nomPlante;
    private BotanyDBInterface service;
    
    public CommandeInfo(BotanyDBInterface service, String nomPlante) {
        super();
        this.service = service;
        this.nomPlante = nomPlante;
    }

    public void execute() throws RemoteException{
        Plant p = service.getPlant(nomPlante);
        if (p != null)
            System.out.println(service.getPlant(nomPlante).toString());
        else
            System.out.println("Il n'y a pas de plante avec ce nom");
    }
}
