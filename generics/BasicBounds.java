package generics;

import java.awt.Color;

interface HasColor {
	Color getColor();
}

class Colored<T extends HasColor> {
	T item;
	public Colored(T item) {
		this.item = item;
	}
	
	T getItem() {
		return item;
	}
	
	Color color() {
		return item.getColor();
	}
}

class Dimension {
	public int x, y, z;
}


//This won't work -- class must be first, then interface
//class ColoredDimension<T extends HasColor & Dimension> {}

//Multiple bounds
class ColoredDimension<T extends Dimension & HasColor> {
	T item;
	public ColoredDimension(T item) {
		this.item = item;
	}
	
	T getItem() {
		return item;
	}
	
	Color color() {
		return item.getColor();
	}
	
	int getX() {
		return item.x;
	}
	
	int getY() {
		return item.y;
	}
	
	int getZ() {
		return item.z;
	}
}

interface Weight {
	int weight();
}

//As with inheritance, you can have only one concrete class but multiple interfaces
//
class Solid<T extends Dimension & HasColor & Weight> {
	T item;
	public Solid(T item) {
		this.item = item;
	}
	
	T getItem() {
		return item;
	}
	
	Color color() {
		return item.getColor();
	}
	
	int getX() {
		return item.x;
	}
	
	int getY() {
		return item.y;
	}
	
	int getZ() {
		return item.z;
	}
	
	int weight() {
		return item.weight();
	}
}

class Bounds extends Dimension implements HasColor, Weight {

	@Override
	public int weight() {
		return 0;
	}

	@Override
	public Color getColor() {
		return null;
	}
	
}

public class BasicBounds {
	public static void main(String[] args) {
		Solid<Bounds> solid = new Solid<>(new Bounds());
		solid.color();
		solid.weight();
		solid.getY();
	}

}
