package generics;

import java.lang.reflect.Array;

public class GenericArrayWithTypeToken<T> {
	
	private T[] array;
	
	//类型标记Class<T>被传递到构造器中，以便从擦除中恢复，使得我们可以创建需要的实际类型的数组
	@SuppressWarnings("unchecked")
	public GenericArrayWithTypeToken(Class<T> type, int size) {
		array = (T[]) Array.newInstance(type, size);
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
	
	public static void main(String[] args) {
		GenericArrayWithTypeToken<Integer> gawtt = new GenericArrayWithTypeToken<>(Integer.class, 10);
		for(int i = 0; i < 10; i++) {
			gawtt.put(i, i);
		}
		
		Integer[] rep = gawtt.rep();
		for(int i : rep) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

}
