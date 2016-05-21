package modules.struct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ThreeLayerHashMap<K1,K2,V> implements Cloneable, Serializable {
	
	private static final long serialVersionUID = 362498820763181266L;

//	HashMap
	private HashMap<K1,HashMap<K2,V>> layer3hm;
	
	public ThreeLayerHashMap(){
		layer3hm = new HashMap<K1,HashMap<K2,V>>();
	}

	public void put(K1 k1, K2 k2, V v){
		if(layer3hm.containsKey(k1)){
			HashMap<K2,V> hm = layer3hm.get(k1);
			hm.put(k2, v);
		}
		else{
			HashMap<K2,V> hm = new HashMap<K2,V>();
			hm.put(k2, v);
			layer3hm.put(k1, hm);
		}
	}
	
	public V get(K1 k1, K2 k2){
		return layer3hm.get(k1).get(k2);
	}
	
	public ArrayList<Pair> keys(){
		ArrayList<Pair> keys = new ArrayList<Pair>();
		Object l1_keys[] = layer3hm.keySet().toArray();
		for(int i=0;i<l1_keys.length;i++){
			Object l2_keys[] = layer3hm.get(l1_keys[i]).keySet().toArray();
			for(int j=0;j<l2_keys.length;j++){
				Pair<K1,K2> p = new Pair<K1,K2>((K1)l1_keys[i], (K2)l2_keys[j]);
				keys.add(p);
			}
		}
		return keys;
	}
	
	public boolean containKey(K1 k1, K2 k2){
		
		if(!layer3hm.containsKey(k1))
			return false;
		else{
			HashMap<K2,V> hm = layer3hm.get(k1);
			if(!hm.containsKey(k2))
				return false;
		}
		return true;
	}
	
	
	public static void main(String[] args) {
		
		ThreeLayerHashMap<Integer, Integer, String> tlhm = 
						new ThreeLayerHashMap<Integer, Integer, String>();
		
		tlhm.put(1, 2, "QQ");
		tlhm.put(1, 3, "QQ");
		tlhm.put(2, 7, "QQ");
	
		
		System.out.println(tlhm.get(1, 2));
		System.out.println(tlhm.keys());
		System.out.println(tlhm.containKey(1, 2));
	}
	
}
