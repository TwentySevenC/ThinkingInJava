package generics;

class Building {}

class Hourse extends Building {}

public class ClassTypeCapture<T> {
	Class<T> kind;
	public ClassTypeCapture(Class<T> clazz) {
		kind = clazz;
	}
	
	public boolean f(Object org) {
		return kind.isInstance(org);
	}
	
	public static void main(String[] args) {
		ClassTypeCapture<Building> ctc1 = new ClassTypeCapture<>(Building.class);
		System.out.println(ctc1.f(new Building()));
		System.out.println(ctc1.f(new Hourse()));
		
		ClassTypeCapture<Hourse> ctc2 = new ClassTypeCapture<>(Hourse.class);
		System.out.println(ctc2.f(new Building()));
		System.out.println(ctc2.f(new Hourse()));
	}
}
