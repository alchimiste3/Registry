package objetRMI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


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
    
    public List<String> dernierCleDemander(int x){
        List<String> liste = new ArrayList<>();
        
        int size = listeDemandeCle.size();
        
        for(int i = size - 1 ; i > size - x - 1; i--){
            if(i < 0) break;

            liste.add(listeDemandeCle.get(i));
        }
        
        return liste;
    }
    
    public List<String> dernierCleAjouter(int x){
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
        
        int size = listeDemandeCle.size();
        
        for(int i = size - 1 ; i > size - x - 1; i--){
            if(i < 0) break;
           
            liste.add(mapObjetSeri.get(listeDemandeCle.get(i)));
        }
        
        return liste;
    }


    public ArrayList<String> getListeCleDemander() {
        return listeDemandeCle;
    }

    /**
     * Permet de recupere toute les cle qui on ete demande au moins x fois
     * @param x
     * @return
     */
    public HashMap<String, Integer> getMapCleFreqDemand(int x) {
        
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
    
    /**
     * Permet de récupérer la clé la plus demandée
     * Renvoie null s'il n'y a pas de clé
     */
    public String getClePlusDemande() {
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
