package generics;

public class GenericArray<T> {
	private T[] array;
	@SuppressWarnings("unchecked")
	public GenericArray(int size) {
		array = (T[]) new Object[size];
	}
	
	public void put(int index, T item) {
		array[index] = item;
	}
	
	public T get(int index) {
		return array[index];
	}
	
	public T[] rep() {
		return array;
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		GenericArray<Integer> gia = new GenericArray<>(10);
//		Integer[] rep = gia.rep(); //ClassCastException
		//This is OK!
		Object[] rep = gia.rep();
	}
}
