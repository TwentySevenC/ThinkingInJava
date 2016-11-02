package generics.latenttyping;

import java.lang.reflect.Method;

//using Reflection to produce latent typing


//Does not implement Performs
class Mime {
	public void walkAgainstTheWind() {}
	public void sit() {
		System.out.println("Pretending to sit");
	}
	public void pushInvisibleWalls() {}
	@Override
	public String toString() {
		return "Mime";
	}
}

class SmartDog {
	public void speak() {System.out.println("Woof!");}
	public void sit() {System.out.println("Sitting");}
	public void reproduce() {}
}

class CommunicateReflectively {
	public static void perform(Object speaker) {
		Class<?> spkl = speaker.getClass();
		
		try {
			try {
				Method speak = spkl.getMethod("speak");
				speak.invoke(speaker);
			} catch (NoSuchMethodException e) {
				System.out.println(speaker + " cannot speak");
			}
			
			try {
				Method sit = spkl.getMethod("sit");
				sit.invoke(speaker);
			} catch (NoSuchMethodException e) {
				System.out.println(speaker + " cannot sit");
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}

public class LatentReflection {
	public static void main(String[] args) {
		CommunicateReflectively.perform(new SmartDog());
		CommunicateReflectively.perform(new Robot());
		CommunicateReflectively.perform(new Mime());
	}
}
