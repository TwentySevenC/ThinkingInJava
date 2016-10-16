package innerclasses;

import static util.util.Print.*;

public class Parcel10 {
	
	public Destination destination(final String dest, final float price) {
		return new Destination() {
			private int cost;
			//Instance initialization for each object
			{
				cost = Math.round(price);
				if(cost > 100) {
					print("Over budget");;
				}
			}
			private String label = dest;//An anonymous inner class that performs initialization.
			
			@Override
			public String readLabel() {
				return label;
			}
		};
	}
	
	public static void main(String[] args) {
		Parcel10 p = new Parcel10();
		p.destination("Tina", 101.12f);
	}

}
