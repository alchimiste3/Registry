package client;

import java.util.ArrayList;
import java.util.Hashtable;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ClientJMS  implements javax.jms.MessageListener{

    private ArrayList<String> listeMessage = new ArrayList<>();

    
    private javax.jms.Connection connect = null;
    private javax.jms.Session session = null;
    private javax.jms.Queue queue = null;
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
    
    public void init(String nom, ArrayList<String> listeMessage){    
        try{            
            
            this.listeMessage = listeMessage;
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
        try {
            String m = ((MapMessage)message).getString("nom");
            System.out.print("Recu un message de la queue: "+ message);

            listeMessage.add(m);
            
            System.out.println(" Mess redelivre: "+message.getJMSRedelivered());
            
            
        } catch (JMSException e){ 
            System.out.println("Probleme onMessage JMS → "+e);
            e.printStackTrace();
        }        
    }
}