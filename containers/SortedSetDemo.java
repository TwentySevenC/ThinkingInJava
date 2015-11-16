package containers;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

//: container/SortedSetDemo.java

public class SortedSetDemo {
	
	public static void main(String[] args) {
		SortedSet<String> sortedSet = new TreeSet<>();
		Collections.addAll(sortedSet, "one two three four five six seven eight".split(" "));
		
		System.out.println(sortedSet);
		
		System.out.println(sortedSet.first());
		System.out.println(sortedSet.last());
		
		String high = null, low = null;
		
		Iterator<String> iter = sortedSet.iterator();
		for(int i = 0; i < 7; i++){
			if(i == 3) high = iter.next();
			if(i == 6) low = iter.next();
			else {
				iter.next();
			}
		}
		
		System.out.println("low: " + low + " , hight: " + high);
		
		System.out.println(sortedSet.tailSet(high));
		System.out.println(sortedSet.subSet(high, low));
		System.out.println(sortedSet.headSet(low));
	}
}
