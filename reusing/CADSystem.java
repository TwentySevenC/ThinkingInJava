package reusing;

import static util.util.Print.*;

/**
 * Ensure proper cleanup
 * 
 * 在清理方法中（dispose）中，必须注意对基类清理方法和成员对象清理方法的调用顺序，以防某个子对象依赖于另一个子对象情形的发生。
 * 执行类的所有特定的清理动作，其顺序同生成顺序相反。
 * 如果需要进行清理，最好编写自己的清理方法，但不要使用finalize().
 * @author liujian
 *
 */

class Shape {
	Shape(int i) {
		print("Shape constructor");
	}
	
	void dispose() {
		print("Shape dispose");
	}
}

class Circle extends Shape {
	Circle(int i) {
		super(i);
		print("Drawing Circle");
	}
	
	@Override
	void dispose() {
		print("Erasing Circle");
		super.dispose();
	}
}

class Triangle extends Shape {
	Triangle(int i) {
		super(i);
		print("Drawing Triangle");
	}
	
	@Override
	void dispose() {
		print("Erasing Triangle");
		super.dispose();
	}
}


class Line extends Shape {
	private int start, end;
	public Line(int start, int end) {
		super(start);
		this.start = start;
		this.end = end;
		print("Drawing line: " + start + "->" + end);
	}
	
	@Override
	void dispose() {
		print("Erasing line: " + start + "->" + end);
		super.dispose();
	}
}

public class CADSystem extends Shape {
	private Circle circle ;
	private Triangle triangle;
	private Line[] lines = new Line[3];
	public CADSystem(int i) {
		super(i + 1);
		for(int j = 0; j < lines.length; j++) {
			lines[j] = new Line(j, j * j);
		}
		triangle = new Triangle(i);
		circle = new Circle(i);
		print("Combined constructor");
	}
	
	@Override
	void dispose() {
		print("CADSystem.dispose");
		circle.dispose();
		triangle.dispose();
		for(int i = lines.length - 1; i >= 0; i--) {
			lines[i].dispose();
		}
		super.dispose();
	}
	
	public static void main(String[] args) {
		CADSystem system = new CADSystem(1);
		try {
			//do something
		} finally {
			system.dispose();
		}
	}
}
