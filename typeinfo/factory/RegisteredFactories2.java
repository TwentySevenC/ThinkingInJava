package typeinfo.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Part2 {
	@Override
	public String toString() {
		return getClass().getSimpleName();
	}
	
	static List<Class<? extends Part2>> partClasses = new ArrayList<>();
	
	static {
		partClasses.add(FuelFilter2.class);
		partClasses.add(AirFilter2.class);
		partClasses.add(CabinAirFilter2.class);
		partClasses.add(OilFilter2.class);
		partClasses.add(FanBelt2.class);
		partClasses.add(PowerSteeringBelt2.class);
		partClasses.add(GeneratorBelt2.class);
	}
	
	private static Random rand = new Random(47);
	public static Part2 createRandom() {
		int n = rand.nextInt(partClasses.size());
		try {
			return partClasses.get(n).newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException();
		} catch (IllegalAccessException e) {
			throw new RuntimeException();
		}
	}
}

class Filter2 extends Part2 {}

class FuelFilter2 extends Filter2 {

}

class AirFilter2 extends Filter2 {

}

class CabinAirFilter2 extends Filter2 {

}

class OilFilter2 extends Filter2 {
}

class Belt2 extends Part2 {}

class FanBelt2 extends Belt2 {
}

class GeneratorBelt2 extends Belt2 {
}

class PowerSteeringBelt2 extends Belt2 {
}

public class RegisteredFactories2 {
	public static void main(String[] args) {
		for(int i = 0; i < 10; i++) {
			System.out.println(Part2.createRandom());
		}
	}
}
