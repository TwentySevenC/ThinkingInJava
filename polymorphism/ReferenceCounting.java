package polymorphism;

import static util.util.Print.*;

/**
 * Cleaning up shared member objects
 * @author liujian
 *
 */

class Shared {
	private int refCount = 0;
	private static int count = 0;
	private final int id = count++;
	
	public Shared() {
		count++;
		print("Creating " + this);
	}
	
	public void addRef() {
		refCount++;
	}
	
	void dispose() {
		if(--refCount == 0) {
			print("disposing " + this);
		}
	}
	
	@Override
	public String toString() {
		return "Shared " + id;
	}
}


class Composing {
	private static int count = 0;
	private final int id = count++;
	private Shared shared;
	public Composing(Shared shared) {
		this.shared = shared;
		shared.addRef();
		print("Creating " + this);
	}
	
	void dispose() {
		print("disposing " + this);
		shared.dispose();
	}
	
	@Override
	public String toString() {
		return "Composing " + id;
	}
}

public class ReferenceCounting {
	public static void main(String[] args) {
		Shared shared = new Shared();
		
		Composing[] composings = new Composing[] {
				new Composing(shared),
				new Composing(shared),
				new Composing(shared),
				new Composing(shared),
		};
		
		for(Composing composing : composings) {
			composing.dispose();
		}
	}
}
