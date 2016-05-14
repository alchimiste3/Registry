/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botany;

import java.rmi.RemoteException;
import java.util.HashMap;

/**
 *
 * @author user
 */
public class CommandeLister {
    private BotanyDBInterface service;
    
    public CommandeLister(BotanyDBInterface service) {
        super();
        this.service = service;
    }

    public void execute() throws RemoteException{
        HashMap plantes = service.getPlantes();
        for (Object p : plantes.keySet()){
            
            String plante = plantes.get(p).toString();  
            System.out.println(plante);
            } 
    }
}
