package generics;

public class NotSelfBound<T> {
	private T element;
	public NotSelfBound<T> set(T arg) {
		element = arg;
		return this;
	}
	
	public T get() {
		return element;
	}
}

class A2 extends NotSelfBound<A2> {}
class B2 extends NotSelfBound<A2> {}

class C2 extends NotSelfBound<C2> {
	C2 setAndGet(C2 arg) {
		set(arg);
		return get();
	}
}

class D2 {}

class E2 extends NotSelfBound<D2> {}
