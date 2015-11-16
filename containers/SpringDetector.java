package containers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

//container/SpringDetector.java

public class SpringDetector {
	public static <T extends Groundhog> void detectSpring(Class<T> type) 
			throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Constructor<T> ghog = type.getConstructor(int.class);
		Map<Groundhog, Prediction> map = new HashMap<>();
		for(int i = 0; i < 5; i++)
			map.put(ghog.newInstance(i), new Prediction());
		
		System.out.println(map);
		
		Groundhog hog = ghog.newInstance(3);  //
		
		System.out.println("Looking up the prediction: " + hog);
		if(map.containsKey(hog))
			System.out.println(map.get(hog));
		else
			System.out.println("Key not found: " + hog);
	}
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		SpringDetector.detectSpring(Groundhog.class);;
	}
}
