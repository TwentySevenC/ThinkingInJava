package generics.latenttyping;

import java.util.List;

import generics.SimpleQueue;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Apply {
	public static <T, S extends Iterable<T>> void apply(S seq, Method method, Object... args) {
		try {
			for(T t : seq) 
				method.invoke(t, args);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	public static void main(String[] args) throws Exception {
		List<Shape> shapes = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			shapes.add(new Shape());
		}
		
		Apply.apply(shapes, Shape.class.getMethod("rotate"));
		Apply.apply(shapes, Shape.class.getMethod("resize", int.class), 5);
		
		List<Square> squares = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			squares.add(new Square());
		}
		Apply.apply(squares, Square.class.getMethod("rotate"));
		Apply.apply(squares, Square.class.getMethod("resize", int.class), 5);
		
		Apply.apply(new FilledList<Shape>(Shape.class, 10), Shape.class.getMethod("rotate"));
		Apply.apply(new FilledList<Square>(Square.class, 10), Square.class.getMethod("rotate"));
		
		SimpleQueue<Shape> shapeQ = new SimpleQueue<>();
		for(int i = 0; i < 5; i++) {
			shapeQ.add(new Shape());
			shapeQ.add(new Square());
		}
		Apply.apply(shapeQ, Shape.class.getMethod("rotate"));
	}
}

class Shape {
	public void rotate() {
		System.out.println(this + "rotate");
	}
	public void resize(int size) {
		System.out.println(this + " resize " + size);
	}
}

class Square extends Shape {}

class FilledList<T> extends ArrayList<T> {

	private static final long serialVersionUID = 1L;
	public FilledList(Class<? extends T> type, int size) {
		try {
			for(int i = 0; i < size(); i++) {
				add(type.newInstance());
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
}
