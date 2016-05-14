/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botany;

import java.rmi.RemoteException;


public class CommandeAjouter {
    private String nomPlante;
    private String description;
    private String famille;
    private BotanyDBInterface service;
    
    public CommandeAjouter(BotanyDBInterface service, String nomPlante, String description, String famille) {
        super();
        this.service = service;
        this.nomPlante = nomPlante;
        this.description = description;
        this.famille = famille;
    }

    public void execute() throws RemoteException{
        service.addPlant(new Plant(nomPlante, famille, description));
    }
}
