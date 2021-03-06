package util.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CountingMapDataPlus extends AbstractMap<Integer, String>{
	private static String[] chars = ("A B C D E F G H I J K L M N O "
			+ "P Q R S T U V W X Y Z").split(" ");
	
	private int size;
	public CountingMapDataPlus(int size){
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
			return chars[index % chars.length] + Integer.toString(index / chars.length);
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
	
	
	private class EntrySet extends AbstractSet<Map.Entry<Integer, String>>{
		private int size;
		public EntrySet(int size){
			if(size < 0)
				size = 0;
			else
				this.size = size;
		}

		@Override
		public Iterator<java.util.Map.Entry<Integer, String>> iterator() {
			return new Iter();
		}

		@Override
		public int size() {
			return size;
		}
		
		private class Iter implements Iterator<Map.Entry<Integer, String>>{
			private Entry entry = new Entry(-1);
			@Override
			public boolean hasNext() {
				return entry.index < size;
			}

			@Override
			public java.util.Map.Entry<Integer, String> next() {
				entry.index ++ ;
				return entry;
			}
			
		}
		
	}

	@Override
	public Set<java.util.Map.Entry<Integer, String>> entrySet() {
		return new EntrySet(size);
	}
	
	
	public static void main(String[] args) {
		System.out.println(new CountingMapData(40));
	}
}
