package generics;

//模板方法设计模式

abstract class GenericWithCreator<T> {
	final T element;
	public GenericWithCreator() {
		element = create();
	}
	abstract T create();
	
}

class X {}

class Creator extends GenericWithCreator<X> {

	@Override
	X create() {
		return new X();
	}
	
	void f() {
		System.out.println(element.getClass().getSimpleName());
	}
}

public class CreatorGeneric {
	public static void main(String[] args) {
		Creator c = new Creator();
		c.f();
	}
}
