package enumerated.menu;

import java.util.Random;

//: enumerated/Enums.java

public class Enums {
	private static Random rand = new Random(47);
	public static <T extends Enum<T>> T Random(Class<T> ec){
		return random(ec.getEnumConstants());
	}
	
	public static <T> T random(T[] values){
		return values[rand.nextInt(values.length)];
	}
}
