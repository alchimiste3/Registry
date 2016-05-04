package objetRMI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;


public class DataBase { 
    
    /**
     * La table qui contient les objet selon leur cle
     */
    public static HashMap<String,Serializable> tableObjet = new HashMap<>();
    
    /**
     * liste des demandes des utilisateur (log)
     */
    public static ArrayList<String> listeDemandeCle = new ArrayList<>();

    /**
     * Table des cle et leurs nombre d'acces utilisateur
     */
    public static HashMap<String,Integer> tableCleFreqDemander = new HashMap<>();

    
    public void add(String key, Serializable obj){
        tableObjet.put(key, obj);
    }
    
    public Serializable get(String key){
        listeDemandeCle.add(key);
        tableCleFreqDemander.put(key, tableCleFreqDemander.get(key));
        return tableObjet.get(key);
        
    }
    
    public List<Serializable> dernierInfo(int x){
        List<Serializable> liste = new ArrayList<>();
        
        int size = listeDemandeCle.size();
        
        for(int i = size - 1 ; i > size - x - 1; i--){
            liste.add(listeDemandeCle.get(i));
        }
        
        return liste;
    }

    public static ArrayList<String> getListeDemandeCle() {
        return listeDemandeCle;
    }

    public static HashMap<String, Integer> getTableCleFreqDemander() {
        return tableCleFreqDemander;
    }

    
    
}
