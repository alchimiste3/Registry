package objetRMICommun;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Class qui Fournie les methodes pour stocker et rechercher un objet serialisable
 * @author Quentin Laborde
 *
 */
public class DataBase { 
    
    /**
     * La table qui contient les objet serialisable selon leur cle
     */
    public static HashMap<String,Serializable> mapObjetSeri = new HashMap<>();
    
    /**
     * liste des cle ajouter par des utilisateur (log)
     */
    public static ArrayList<String> listeCle = new ArrayList<String>();
    
    /**
     * liste des demandes des utilisateur (log)
     */
    public static ArrayList<String> listeDemandeCle = new ArrayList<String>();


    
    /**
     * Table des cle et leurs nombre d'acces utilisateur
     */
    public static HashMap<String,Integer> tableCleNbDemander = new HashMap<String,Integer>();

    
    public void add(String key, Serializable obj){
        listeCle.add(key);
        mapObjetSeri.put(key, obj);
        
    }
    
    public Serializable get(String key){
        listeDemandeCle.add(key);
        
        if(tableCleNbDemander.containsKey(key)){
            tableCleNbDemander.put(key, tableCleNbDemander.get(key) + 1);
        }
        else{
            tableCleNbDemander.put(key, 1);
        }
        
        if(mapObjetSeri.containsKey(key)){
            return mapObjetSeri.get(key);
        }
        
        return null;
        
    }
    
    public List<String> derniereCleDemander(int x){
        List<String> liste = new ArrayList<>();
        
        int size = listeDemandeCle.size();
        
        for(int i = size - 1 ; i > size - x - 1; i--){
            if(i < 0) break;

            liste.add(listeDemandeCle.get(i));
        }
        
        return liste;
    }
    
    public List<String> derniereCleAjouter(int x){
        List<String> liste = new ArrayList<>();
        
        int size = listeCle.size();
        
        for(int i = size - 1 ; i > size - x - 1; i--){
            if(i < 0) break;

            liste.add(listeCle.get(i));
        }
        
        return liste;
    }
    


    public List<Serializable> dernierObjetAjouter(int x){
        List<Serializable> liste = new ArrayList<>();
        
        int size = listeCle.size();

        for(int i = size - 1 ; i > size - x - 1; i--){
            if(i < 0) break;
                       
            if(mapObjetSeri.containsKey(listeCle.get(i))){
                Serializable obj = mapObjetSeri.get(listeCle.get(i));
                liste.add(obj);
            }

        }
        
        return liste;
    }



    public ArrayList<String> getListeCleDemander() {
        return listeDemandeCle;
    }


    public HashMap<String, Integer> getMapCleFreqDemander(int x) {
        
        HashMap<String, Integer> map = new HashMap<>();
        
        Iterator<String> it = tableCleNbDemander.keySet().iterator();
        
        for(;it.hasNext();){
            String key = it.next();
            
            int value = tableCleNbDemander.get(key);
            
            if(value >= x){
                map.put(key, value);
            }
        }
        
        return map;
    }
    

    public String getClePlusDemander() {
        Iterator<String> it = tableCleNbDemander.keySet().iterator();
        String keyMax = null;
        int max = 0;
        
        for(;it.hasNext();){
            
            String keyComp = it.next();
            int value = tableCleNbDemander.get(keyComp);
            if (value > max) {
                max = value;
                keyMax = keyComp;
            }
        }
        return keyMax; 
    }

    
    
}
