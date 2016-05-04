package objetRMI;

import java.io.Serializable;
import java.util.HashMap;


public class DataBase { 
    
    public static HashMap<String,Serializable> table = new HashMap<>();
    
    public void add(String key, Serializable obj){
        table.put(key, obj);
    }
    
    public Serializable get(String key){
        return table.get(key);
    }
}
