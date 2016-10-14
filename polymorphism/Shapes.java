package polymorphism;

import polymorphism.shape.RandomShapeGenerator;
import polymorphism.shape.Shape;

/**
 * 动态绑定 - 多态
 * @author liujian
 *
 */

public class Shapes {
	private static RandomShapeGenerator generator = new RandomShapeGenerator();
	public static void main(String[] args) {
		Shape[] shapes = new Shape[9];
		for(int i = 0; i < shapes.length; i++) {
			shapes[i] = generator.next();
		}
		
		for(Shape shape : shapes) {
			shape.draw();
		}
	}
}
