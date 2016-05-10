/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverBotany;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author user
 */
public class BotanyNotification implements Runnable {
    
    private javax.jms.Connection connect = null;
    private javax.jms.Session session = null;
    private javax.jms.MessageProducer producteur = null;
    InitialContext context = null;
    HashMap<String, Plant> hashPlantes;
    
    
    public void connection(String url, String login, String password){
        try {

            Hashtable<String, String> properties = new Hashtable<String, String>();
            
            properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.put(Context.PROVIDER_URL, url);
    
            context = new InitialContext(properties);
    
    
            javax.jms.ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
            
            connect = factory.createConnection();
        
            connect.start();
        } catch (NamingException e) {
            System.out.println("Probleme connection JMS → "+e);
            e.printStackTrace();
        } catch (JMSException e) {
            System.out.println("Probleme connection JMS → "+e);
            e.printStackTrace();
        }
    }
    
    public void init(String nom, HashMap<String,Plant> hash){    
        try{
            this.hashPlantes = hash;
            session = connect.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
            Queue queue = (Queue) context.lookup("dynamicQueues/"+nom);
            producteur = session.createProducer(queue);
            producteur.setTimeToLive(20000);
        }
        catch(JMSException e){
            System.out.println("Probleme initialisation queue JMS → "+e);
            e.printStackTrace();
        } catch (NamingException e) {
            System.out.println("Probleme initialisation queue JMS → "+e);
            e.printStackTrace();
        }
        
    }
    
    public void notifier(String message) {
                try {
            MapMessage m = session.createMapMessage();
            m.setString("message", message);
            m.setStringProperty("typeMess","important");

            producteur.send(m);
            
        } catch (JMSException e) {
            System.out.println("Probleme envoyerMessage → "+e);
        }
 
    }

    @Override
    public void run() {
            try {
                while (true) {
                    Iterator it = hashPlantes.entrySet().iterator();
                    String mes = "";
                    while (it.hasNext()) {
                        HashMap.Entry pair = (HashMap.Entry)it.next();
                        mes = mes + pair.getKey() + " = " + pair.getValue() + "\n";
                        it.remove();
                    }
                    notifier(mes);
                Thread.sleep(10000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(BotanyNotification.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
