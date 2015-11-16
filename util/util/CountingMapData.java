package util.util;

import java.util.AbstractMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;



//Unlimited-length map containing sample data

public class CountingMapData extends AbstractMap<Integer, String>{
	private static String[] chars = ("A B C D E F G H I J K L M N O "
			+ "P Q R S T U V W X Y Z").split(" ");
	
	private int size;
	public CountingMapData(int size){
		if(size < 0) 
			size = 0;
		else
			this.size = size;
	}
	
	private static class Entry implements Map.Entry<Integer, String>{
		private int index;
		public Entry(int index){
			this.index = index;
		}
		
		@Override
		public Integer getKey() {
			return index;
		}

		@Override
		public String getValue() {
			return chars[getKey() % chars.length] + Integer.toString(index / chars.length);
		}

		@Override
		public String setValue(String value) {
			throw new UnsupportedOperationException();
		}
		
		public boolean equals(Object o){
			return Integer.valueOf(index).equals(o);
		}
		
		public int hashCode(){
			return Integer.valueOf(index).hashCode();
		}
		
	}
	
	
	
	@Override
	public Set<java.util.Map.Entry<Integer, String>> entrySet() {
		
		Set<java.util.Map.Entry<Integer, String>> entries = new LinkedHashSet<>();
		
		for(int i = 0; i < size; i++){
			entries.add(new Entry(i));
		}
		
		return entries;
	}
	
	
	public static void main(String[] args) {
		System.out.println(new CountingMapData(40));
	}
	
}
