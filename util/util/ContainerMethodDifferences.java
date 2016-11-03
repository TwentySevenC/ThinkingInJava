package util.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

public class ContainerMethodDifferences {
	static Set<String> methodSet(Class<?> type) {
		Set<String> result = new TreeSet<>();
		for(Method m : type.getMethods()) {
			result.add(m.getName());
		}
		return result;
	}
	
	static void interfaces(Class<?> type) {
		System.out.println("Interfaces in " + type.getSimpleName() + ": ");
		List<String> interfaces = new ArrayList<>();
		for(Class<?> c : type.getInterfaces()) {
			interfaces.add(c.getSimpleName());
		}
		System.out.println(interfaces);
	}
	
	static Set<String> object = methodSet(Object.class);
	static {
		object.add("clone");
	}
	
	static void differences(Class<?> superSet, Class<?> subSet) {
		System.out.println(superSet.getSimpleName() + " extends " + subSet.getSimpleName() + ", add: ");
		Set<String> com = Sets.difference(methodSet(superSet), methodSet(subSet));
		com.removeAll(object);
		System.out.println(com);
		interfaces(superSet);
	}
	
	public static void main(String[] args) {
		System.out.println("Collection: " + methodSet(Collection.class));
		interfaces(Collection.class);
		differences(Set.class, Collection.class);
		differences(HashSet.class, Collection.class);
		differences(LinkedHashSet.class, Collection.class);
		differences(TreeSet.class, Collection.class);
		differences(List.class, Collection.class);
		differences(ArrayList.class, Collection.class);
		differences(LinkedList.class, Collection.class);
		differences(Queue.class, Collection.class);
		differences(PriorityQueue.class, Collection.class);
		System.out.println("Map: " + methodSet(Map.class));
		differences(HashMap.class, Map.class);
		differences(LinkedHashMap.class, Map.class);
		differences(SortedMap.class, Map.class);
		differences(TreeMap.class, Map.class);
	}
}
