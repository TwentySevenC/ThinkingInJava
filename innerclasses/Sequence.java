package innerclasses;

//迭代器 模式

interface Selector {
	boolean end();
	Object current();
	void next();
}

public class Sequence {
	private Object[] objects;
	private int item = 0;
	public Sequence(int size) {
		objects = new Object[size];
	}
	
	public void add(Object o) {
		if(item < objects.length) {
			objects[item] = o;
			item++;
		}
	}
	
	
	private class SequenceSelector implements Selector {
		private int index = 0;

		@Override
		public boolean end() {
			return index == objects.length;
		}

		@Override
		public Object current() {
			return objects[index];
		}

		@Override
		public void next() {
			index++;
		}
	}
	
	public Selector selector() {
		return new SequenceSelector();
	}
	
	public static void main(String[] args) {
		Sequence sequence = new Sequence(10);
		for(int i = 0; i < 10; i++) {
			sequence.add(Integer.toString(i));
		}
		
		Selector selector = sequence.selector();
		while(!selector.end()) {
			System.out.println(selector.current());
			selector.next();
		}
	}
}
