package server;

import java.rmi.RemoteException;

import applicationTest.AccesClientInterface;

public class ThreadRappelerClient extends Thread{
    
    AccesClientInterface client;
    int temps;
    
    public ThreadRappelerClient(AccesClientInterface client, int temps) {
        this.client = client;
        this.temps = temps;
    }

    @Override
    public void run(){
        try {
            Thread.sleep(temps);
            System.out.println("On rappele le client");
            client.appeler();
            
        } catch (InterruptedException | RemoteException e) {
            System.out.println("Probleme ThreadRappelerClient → "+e);
            e.printStackTrace();
        }
    }
}
