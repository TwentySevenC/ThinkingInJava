package containers;

import java.util.WeakHashMap;


// container/Element.java

class Element {
	private String ident;
	public Element(String id){
		ident = id;
	}
	
	public String toString(){
		return ident;
	}
	
	public int hashCode(){
		return ident.hashCode();
	}
	
	public boolean equals(Object o){
		return o instanceof Element && ident.equals(((Element)o).ident);
	}
	
	@Override
	protected void finalize() throws Throwable {
		System.out.println("Finalizing " + getClass().getSimpleName() + " " + ident);
	}
}

class Key extends Element{

	public Key(String id) {
		super(id);
	}
	
}

class Value extends Element{

	public Value(String id) {
		super(id);
	}
	
}

public class CanonicalMapping{
	public static void main(String[] args) {
		int size = 100;
		Key[] keys = new Key[size];
		WeakHashMap<Key, Value> whm = new WeakHashMap<>();
		for(int i = 0; i < size; i++){
			Key k = new Key(Integer.toString(i));
			Value v = new Value(Integer.toString(i));
			if(i % 3 == 0){
				keys[i] = k;
			}
			whm.put(k, v);
		}
		System.out.println("gc");
		System.gc();
		System.out.println(whm);
		System.gc();
	}
}
