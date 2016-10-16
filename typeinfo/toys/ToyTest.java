package typeinfo.toys;

import static util.util.Print.*;

//Testing class Class

interface HasBatteries{}
interface Waterproof{}
interface Shoots{}

class Toy {
	Toy() {}
	Toy(int i){}
}

class FancyToy extends Toy implements HasBatteries, Waterproof, Shoots {
	public FancyToy() {
		super(1);
	}
}

public class ToyTest {
	static void printInfo(Class<?> clazz) {
		print("Class name: " + clazz.getName() + " is interface? [" + clazz.isInterface() + "]");
		print("Simple name: " + clazz.getSimpleName());
		print("Canonical name: " + clazz.getCanonicalName());
	}
	
	public static void main(String[] args) {
		Class<?> c = null;
		try {
			c = Class.forName("typeinfo.toys.FancyToy");
		} catch (ClassNotFoundException e) {
			print("Can't find class");
			System.exit(1);
		}
		
		printInfo(c);
		for(Class<?> cc : c.getInterfaces()) {
			printInfo(cc);
		}
		
		Class<?> up = c.getSuperclass();
		Object obj = null;
		try {
			obj = up.newInstance();
		} catch (InstantiationException e) {
			print("Cann't instantiation");
			System.exit(1);
		} catch (IllegalAccessException e) {
			print("Illegal access.");
			System.exit(1);
		}
		
		printInfo(obj.getClass());
	}

}
