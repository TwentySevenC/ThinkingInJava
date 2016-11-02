package generics;

//捕获转换

public class CaptureConversion {
	static <T> void f1(Holder<T> holder) {
		T t = holder.get();
		System.out.println(t.getClass().getSimpleName());
	}
	
	static void f2(Holder<?> holder) {
		f1(holder);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		Holder raw = new Holder<Integer>(1);
//		f1(raw);//warning
		f2(raw);
		Holder rawBasic = new Holder();
		rawBasic.set(new Object());//warning
		f2(rawBasic);
		Holder<?> wildHolder = new Holder<Double>(1.0);
		f2(wildHolder);
	}
}
