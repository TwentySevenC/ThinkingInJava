package generics;

interface GenericGetter<T extends GenericGetter<T>> {
	T get();
}

interface Getter extends GenericGetter<Getter> {
	
}

public class GenericsAndReturnTypes {
	@SuppressWarnings({ "unused", "rawtypes" })
	void test(Getter getter) {
		GenericGetter re = getter.get();
		Getter g = getter.get();
	}
}
