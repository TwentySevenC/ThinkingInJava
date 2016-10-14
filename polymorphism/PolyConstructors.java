package polymorphism;

import static util.util.Print.*;

/**
 * 构造器方法内部的多态方法的行为
 * @author liujian
 *
 */

class Glyph {
	void draw() {
		print("Glyph.draw()");
	}
	
	public Glyph() {
		print("Glyph() before draw()");
		draw();// 不要在构造函数中区调用子类的方法
		print("Glyph() after draw()");
	}
}

class RoundGlyph extends Glyph {
	private int radius = 1;
	void draw() {
		print("RoundGlyph.draw() radius = " + radius);
	}
	
	public RoundGlyph(int radius) {
		this.radius = radius;
		print("RoundGlyph() radius = " + radius);
	}
}

public class PolyConstructors {
	public static void main(String[] args) {
		new RoundGlyph(5);
	}
}
/**
 * output:
 * Glyph() before draw()
 * RoundGlyph.draw() radius = 0   子类还没有开始构造，radius 没有赋值，只是在分配存储空间时自动初始化为0
 * Glyph() after draw()
 * RoundGlyph() radius = 5
 */


