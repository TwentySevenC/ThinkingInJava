package generics;

//古怪的循环泛型

class Subtype extends BasicHolder<Subtype> {}

public class CRGWithBasicHolder {
	public static void main(String[] args) {
		Subtype st1 = new Subtype(), st2 = new Subtype();
		st1.set(st2);
		@SuppressWarnings("unused")
		Subtype st3 = st1.get();
		st1.f();
	}
}
