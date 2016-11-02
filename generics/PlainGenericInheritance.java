package generics;

class GenericSetter<T> {
	void set(T arg) {
		System.out.println("GenericSetter.set(Base)");
	}
}

class DerivedGS extends GenericSetter<Base> {
	void set(DerivedBase derived) {
		System.out.println("DerivedGS.set(Derived)");
	}
}

public class PlainGenericInheritance {
	public static void main(String[] args) {
		Base base = new Base();
		DerivedBase derived = new DerivedBase();
		DerivedGS derivedGS = new DerivedGS();
		derivedGS.set(base);
		derivedGS.set(derived);//Compiles: overloaded, not overridden
	}
}
