package containers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


//: container/CountedString.java

//Create a good hashCode

public class CountedString {
	private static List<String> list = new ArrayList<>();
	private int id = 0;
	private String str;
	public CountedString(String s){
		str = s;
		list.add(str);
		for(String str : list){
			if(str.equals(s))
				id++;
		}
	}
	
	@Override
	public int hashCode(){
		int result = 17;
		result = result * 37 + str.hashCode();
		result = result * 37 + id;
		return result;
	}
	
	@Override
	public boolean equals(Object o){
		return o instanceof CountedString && ((CountedString)o).str.equals(str) && ((CountedString)o).id == id;
	}
	
	@Override
	public String toString(){
		return "String: " + str + " id: " + id + " hashCode: " + hashCode();
	}
	
	public static void main(String[] args) {
		HashMap<CountedString, Integer> map = new HashMap<>();
		CountedString [] cs = new CountedString[5];
		for(int i = 0; i < 5; i++){
			cs[i] = new CountedString("hi");
			map.put(cs[i], i);
		}
		
		System.out.println(map);
		for(CountedString s : cs){
			System.out.println(s);
			System.out.println(map.get(s));
		}
	}

}
