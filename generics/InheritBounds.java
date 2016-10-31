package generics;

import java.awt.Color;

class HoldItem<T> {
	T item;
	public HoldItem(T item) {
		this.item = item;
	}
	T getItem() {return item;}
}

class Colored2<T extends HasColor> extends HoldItem<T> {

	public Colored2(T item) {
		super(item);
	}
	
	Color color() {
		return item.getColor();
	}
	
}

class ColoredDimension2<T extends Dimension & HasColor> extends Colored2<T> {

	public ColoredDimension2(T item) {
		super(item);
	}
	
	int getX() {return item.x;}
	
	int getY() {return item.y;}
	
	int getZ() {return item.z;}
}

class Sold2<T extends Dimension & HasColor & Weight> extends ColoredDimension2<T> {

	public Sold2(T item) {
		super(item);
	}
	
	int weight() {return item.weight();}
}

public class InheritBounds {
	public static void main(String[] args) {
		Sold2<Bounds> sold2 = new Sold2<>(new Bounds()); 
		sold2.weight();
		sold2.color();
		sold2.getY();
	}
}
