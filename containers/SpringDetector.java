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
		
		Groundhog hog = new Groundhog(3);
		System.out.println("Looking up the prediction: " + hog);
		System.out.println(map.containsKey(hog));
	}
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		SpringDetector.detectSpring(Groundhog.class);;
	}
}
