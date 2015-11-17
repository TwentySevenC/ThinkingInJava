package containers;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import util.util.Countries;


// container/SimpleHashMap.java

public class SimpleHashMap<K, V> extends AbstractMap<K, V> {
	
	private static final int SIZE = 997;
	
	@SuppressWarnings("unchecked")
	LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[SIZE];
	
	@Override
	public V put(K key, V value){
		V oldValue = null;
		int index = Math.abs(key.hashCode()) % SIZE;
		if(buckets[index] == null)
			buckets[index] = new LinkedList<>();
		
		LinkedList<MapEntry<K, V>> bucket = buckets[index];
		boolean found = false;
		
/*		for(MapEntry<K, V> entry : bucket){
			if(entry.getKey().equals(key)){
				oldValue = entry.getValue();
				entry.setValue(value);             //这里是不能设置数据的，改变不了原来的map,for-each 结构遍历适用的是Iterator 不具备set的功能
				break;                             //ListIterator 具有set的功能。
			}
		}*/
		
		ListIterator<MapEntry<K, V>> lt = bucket.listIterator();
		while(lt.hasNext()){
			MapEntry<K, V> iPair = lt.next();
			if(iPair.getKey().equals(key)){
				oldValue = iPair.getValue();
				lt.set(new MapEntry<K, V>(key, value));
				found = true;
				break;
			}
		}
		
		
		if(!found)
			bucket.add(new MapEntry<K, V>(key, value));
		
		return oldValue;
	}
	
	@Override
	public V get(Object o){
		@SuppressWarnings("unchecked")
		K key = (K)o;
		int index = Math.abs(key.hashCode()) % SIZE;
		
		if(buckets[index] == null) return null;
		
		for(MapEntry<K, V> entry : buckets[index]){
			if(entry.getKey().equals(key)){
				return entry.getValue();
			}
		}
		
		return null;
	}
	

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		HashSet<Map.Entry<K, V>> set = new HashSet<>();
		for(int i = 0; i < SIZE; i++){
			if(buckets[i] == null) 
				continue;
			for(MapEntry<K, V> entry : buckets[i])
				set.add(entry);
		}
		return set;
	}
	
	public static void main(String[] args) {
		SimpleHashMap<String, String> map = new SimpleHashMap<>();
		map.putAll(Countries.capitals(10));
		
		System.out.println(map);
		System.out.println(map.get("ANGOLA"));
		System.out.println(map.entrySet());
	}
	
}
