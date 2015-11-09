package concurrency.newComponents;

//: concurrency/Fat.java
// Objects that are expensive to create

public class Fat {
	private static int count = 0;
	private final int id = count ++;
	@SuppressWarnings("unused")
	private volatile double d ;
	
	public Fat(){
		for(int i = 0; i < 1000; i++){
			d += (Math.PI + Math.E)/(double)i;
		}
	}
	
	public void operation(){
		System.out.println(this);
	}
	
	public String toString(){
		return "Fat " + id;
	}
}
