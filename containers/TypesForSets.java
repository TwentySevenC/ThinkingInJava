package containers;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;


//: container/TypesForSets.java

//Methods necessary to put your own type in a Set

class SetType{
	protected int i;
	
	public SetType(int i){
		this.i = i;
	}
	
	public boolean equals(Object o){
		return o instanceof SetType && (i == ((SetType)o).i);
	}
	
	public String toString(){
		return Integer.toString(i);
	}
}

class HashType extends SetType{

	public HashType(int i) {
		super(i);
	}
	
	public int hashCode(){
		return i;
	}
	
}


class TreeType extends SetType implements Comparable<SetType>{

	public TreeType(int i) {
		super(i);
	}

	@Override
	public int compareTo(SetType o) {
		return o.i < i ? -1 : (o.i == i ? 0 : 1);
	}

	
}

public class TypesForSets {
	static <T> Set<T> fill(Set<T> set, Class<T> type){
		
		for(int i = 0; i < 10; i++){
			try {
				set.add(type.getConstructor(int.class).newInstance(i));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		return set;
	}
	
	static <T> void test(Set<T> set, Class<T> type){
		fill(set, type);
		fill(set, type);
		fill(set, type);
		System.out.println(set);
	}
	
	public static void main(String[] args) {
		test(new HashSet<>(), HashType.class);
		test(new TreeSet<>(), TreeType.class);
		test(new LinkedHashSet<>(), HashType.class);
		
		
		//doesn's work
		test(new HashSet<>(), SetType.class);
		test(new LinkedHashSet<>(), SetType.class);
		
		test(new TreeSet<SetType>(), SetType.class);
		
	}
	
	
}
