package polymorphism.shape;

import static util.util.Print.*;

public class Triangle extends Shape {
	@Override
	public void draw() {
		print("Triangle.draw()");
	}
	
	@Override
	public void erase() {
		print("Triangle.erase()");
	}
}
