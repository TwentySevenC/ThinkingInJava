package generics;

public class GenericsHolder<T> {
	private T obj;
	public void set(T t) {
		obj = t;
	}
	
	public T get() {
		return obj;
	}
	
	public static void main(String[] args) {
		GenericsHolder<String> holder = new GenericsHolder<>();
		holder.set("Hello!");
		@SuppressWarnings("unused")
		String s = holder.get();
	}
}
