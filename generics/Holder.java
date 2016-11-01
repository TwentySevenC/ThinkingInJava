package generics;

public class Holder<T> {
	private T value;
	public Holder() {}
	
	public Holder(T value) {
		this.value = value;
	}
	
	public void set(T value) {
		this.value = value;
	}
	
	public T get() {
		return value;
	}
	
	public boolean equals(Object o) {
		return value.equals(o);
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Holder<Apple> apple = new Holder<Apple>(new Apple());
		Apple a = apple.get();
		apple.set(a);
//		Holder<Fruit> fHolder = apple; can't upcast
		Holder<? extends Fruit> fHolder = apple;
		Fruit f = fHolder.get();
		a = (Apple)fHolder.get();
		
		try {
			Orange o = (Orange) fHolder.get();
		} catch(Exception e) {
			System.out.println(e);
		}
//		fHolder.set(new Apple());
		System.out.println(fHolder.equals(new Apple()));
	}

}
