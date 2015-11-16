package containers;

import java.util.List;
import java.util.Map;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import util.util.Countries;

//: container/SlowMap.java 

// A map implements with ArrayLists.

public class SlowMap<K, V> extends AbstractMap<K, V>{
	private List<K> keys = new ArrayList<>();
	private List<V> values = new ArrayList<>();
	
	public V put(K key, V value){
		V oldValue = get(key);
		
		if(!keys.contains(key)){
			keys.add(key);
			values.add(value);
		}else{
			values.set(keys.indexOf(key), value);
		}
		
		return oldValue;
	}
	
	public V get(Object o){
		if(!keys.contains(o))
			return null;
		
		return values.get(keys.indexOf(o));
	}
	

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		Set<Map.Entry<K, V>> set = new HashSet<>();
		Iterator<K> ki = keys.iterator();
		Iterator<V> vi = values.iterator();
		while(ki.hasNext())
			set.add(new MapEntry<K, V>(ki.next(), vi.next()));
		return set;
	}
	
	public static void main(String[] args) {
		SlowMap<String, String> map = new SlowMap<>();
		
		map.putAll(Countries.capitals(15));
		
		System.out.println(map);
		System.out.println(map.entrySet());
	}

}
