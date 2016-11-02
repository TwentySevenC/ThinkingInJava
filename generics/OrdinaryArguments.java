package generics;

class OrdinarySetter {
	void set(Base base) {
		System.out.println("OrdinarySetter.set(Base)");
	}
}

class DerivedSetter extends OrdinarySetter {
	void set(DerivedBase derived) {//方法重载
		System.out.println("DerivedSetter.set(DerivedBase)");
	}
}

public class OrdinaryArguments {
	public static void main(String[] args) {
		Base base = new Base();
		DerivedBase derived = new DerivedBase();
		DerivedSetter setter = new DerivedSetter();
		setter.set(base);
		setter.set(derived);
	}
}
