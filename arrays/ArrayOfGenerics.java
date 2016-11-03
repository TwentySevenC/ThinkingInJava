package arrays;

import java.util.ArrayList;
import java.util.List;

//It is possible to create arrays of generics

// 尽管不能创建实际的持有泛型的数组对象，但是可以创建非泛型的数组，然后将其转型

public class ArrayOfGenerics {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		List<String>[] ls;
		List[] la = new List[10];
		ls = (List<String>[])la;//unchecked warning
		ls[0] = new ArrayList<String>();
//		ls[1] = new ArrayList<Integer>(); // Compile error
		
		//The problem: List<String> is a subtype of Object
		Object[] objects = ls;
		objects[1] = new ArrayList<Integer>();
		
		//
		List<BerylliumSphere>[] spheres = (List<BerylliumSphere>[]) new List[10];
		for(int i = 0; i < spheres.length; i++) {
			spheres[i] = new ArrayList<BerylliumSphere>();
		}
	}
}
