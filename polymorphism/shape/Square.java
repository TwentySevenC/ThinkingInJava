package polymorphism.shape;

import static util.util.Print.*;

public class Square extends Shape {
	@Override
	public void draw() {
		print("Square.draw()");
	}
	
	@Override
	public void erase() {
		print("Square.erase()");
	}
}
