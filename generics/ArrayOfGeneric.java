package generics;

//成功创建泛型数组的唯一方式就是创建一个被擦除类型的新数组

class Generic<T> {}

public class ArrayOfGeneric {
	static final int SIZE = 100;
	static Generic<Integer>[] gia;
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		//Class cast exception
//		gia = (Generic<Integer>[])new Object[SIZE];
		
		gia = (Generic<Integer>[])new Generic[SIZE];
		System.out.println(gia.getClass().getSimpleName());
		
		
		gia[0] = new Generic<Integer>();
		
//		gia[1] = new Object();
		
//		gia[2] = new Generic<Double>();
	}

}
