package botany;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Queue;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class BotanyJMSClient implements javax.jms.MessageListener {
    
    private ArrayList<Message> listeMessage = new ArrayList<>();

    
    private javax.jms.Connection connect = null;
    private javax.jms.Session session = null;
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
            session = connect.createSession(false,javax.jms.Session.AUTO_ACKNOWLEDGE);  
            Queue queue = (Queue) context.lookup("dynamicQueues/"+nom);
            javax.jms.MessageConsumer qReceiver = session.createConsumer(queue,"typeMess = 'important'");
            qReceiver.setMessageListener(this);
            
        }
        catch(JMSException e){
            System.out.println("Probleme initialisation queue JMS → "+e);
            e.printStackTrace();
        } catch (NamingException e) {
            System.out.println("Probleme initialisation queue JMS → "+e);
            e.printStackTrace();
        }
        
    }

    @Override
    public void onMessage(Message message) {
        listeMessage.add(message);
        System.out.println("=============NOTIFICATION=============");
        try {
            System.out.println(((MapMessage)message).getString("message"));
        } catch (JMSException ex) {
            Logger.getLogger(BotanyJMSClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
