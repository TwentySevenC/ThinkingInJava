package generics;

public class SimpleHolder {
	private Object obj;
	public void set(Object obj) {
		this.obj = obj;
	}
	
	public Object get() {
		return obj;
	}
	
	public static void main(String[] args) {
		SimpleHolder sh = new SimpleHolder();
		sh.set("Item");
		@SuppressWarnings("unused")
		String s = (String) sh.get();
	}

}
