package util.util;

import java.util.LinkedHashMap;


//A Map filled with data using a generator object

public class MapData<K, V> extends LinkedHashMap<K, V>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//A single Pair Generator
	public MapData(Generator<Pair<K,V>> gen, int quantity){
		for(int i = 0; i < quantity; i++){
			Pair<K, V> p = gen.next();
			put(p.key, p.value);
	 	}
	}
	
	// Two separate Generator
	public MapData(Generator<K> genK, Generator<V> genV, int quantity){
		for(int i = 0; i < quantity; i++){
			put(genK.next(), genV.next());
		}
	}
	
	// A key Generator and a single value
	public MapData(Generator<K> genK, V value, int quantity){
		for(int i = 0; i < quantity; i++){
			put(genK.next(), value);
		}
	}
	
	// An Iterable and a value Generator
	public MapData(Iterable<K> genK, Generator<V> genV){
		for(K key : genK)
			put(key, genV.next());
	}
	
	// An Iterable and a single value
	public MapData(Iterable<K> genK, V value){
		for(K key : genK)
			put(key, value);
	}
	
	public static <K,V> MapData<K, V> map(Generator<Pair<K, V>> gen, int quantity){
		return new MapData<K,V>(gen, quantity);
	}
	
	public static <K,V> MapData<K, V> map(Generator<K> genK, V value, int quantity){
		return new MapData<>(genK, value, quantity);
	}
	
	public static <K,V> MapData<K, V> map(Generator<K> genK, Generator<V> genV, int quantity){
		return new MapData<>(genK, genV, quantity);
	}

}