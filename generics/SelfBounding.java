package generics;

// 如果使用自限定，就应该了解这个类所使用的类型参数将与使用这个参数的类具有相同的基类型
// B 与 A 都有相同的基类型SelfBounded

class SelfBounded<T extends SelfBounded<T>> {
	T element;
	SelfBounded<T> set(T arg) {
		element = arg;
		return this;
	}
	
	T get() {
		return element;
	}
}

class A extends SelfBounded<A> {}

class B extends SelfBounded<A> {}

class C extends SelfBounded<C> {
	C setAndGet(C arg) {
		set(arg);
		return get();
	}
}

class D {}

//class E extends SelfBounded<D> {}

///Alas, you can do this, so you can't force the idiom
@SuppressWarnings("rawtypes")
class F extends SelfBounded {}

public class SelfBounding {
	public static void main(String[] args) {
		A a = new A();
		a.set(new A());
		a = a.set(new A()).get();
		a = a.get();
		C c = new C();
		c = c.setAndGet(new C());
	}
}
