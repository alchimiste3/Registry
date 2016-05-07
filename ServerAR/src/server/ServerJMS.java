package server;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServerJMS {

    
    private javax.jms.Connection connect = null;
    private javax.jms.Session session = null;
    private javax.jms.MessageProducer producteur = null;
    InitialContext context = null;
    
    
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
    
    public void init(String nom){    
        try{
            session = connect.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
            Queue queue = (Queue) context.lookup("dynamicQueues/"+nom);
            producteur = session.createProducer(queue);
        }
        catch(JMSException e){
            System.out.println("Probleme initialisation queue JMS → "+e);
            e.printStackTrace();
        } catch (NamingException e) {
            System.out.println("Probleme initialisation queue JMS → "+e);
            e.printStackTrace();
        }
        
    }
    
    public void envoyerMessage(String service, HashMap<String, String> listeMessage){
        try {
            MapMessage m = session.createMapMessage();
            m.setString("service",service);

            Iterator<String> it = listeMessage.keySet().iterator();
            
            for(;it.hasNext();){
                String cle = it.next();
                m.setString(cle, listeMessage.get(cle));
            }
            
            m.setStringProperty("typeMess","important");

            producteur.send(m);
            
        } catch (JMSException e) {
            System.out.println("Probleme envoyerMessage → "+e);
        }
 
    }
}
