package containers;

import java.util.LinkedHashMap;

import util.util.CountingMapData;

//: container/LinkedHashMapDemo.java

public class LinkedHashMapDemo {
	public static void main(String[] args) {
		LinkedHashMap<Integer, String> map = new LinkedHashMap<>(new CountingMapData(10));
		System.out.println(map);
		
		map = new LinkedHashMap<Integer, String>(16, 0.75f, true);//LRU 算法
		map.putAll(new CountingMapData(9));
		
		System.out.println(map);
		
		for(int i = 0; i < 5; i++){
			map.get(i);
		}
		
		System.out.println(map);
		System.out.println(map.get(3));
		System.out.println(map);
	}
}
