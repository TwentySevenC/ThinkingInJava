package generics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import typeinfo.pets.*;

//动态类型安全

public class CheckList {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static void oldStyleMethod(List probablyDogs) {
		probablyDogs.add(new Cat());
	}
	
	public static void main(String[] args) {
		List<Dog> dogs1 = new ArrayList<>();
		oldStyleMethod(dogs1);//Quietly accept a cat
		List<Dog> dogs2 = Collections.checkedList(new ArrayList<>(), Dog.class);
		
		try {
			oldStyleMethod(dogs2);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		//Derived pets work fine
		List<Pet> pets = Collections.checkedList(new ArrayList<>(), Pet.class);
		pets.add(new Dog());
		pets.add(new Cat());
	}
}
