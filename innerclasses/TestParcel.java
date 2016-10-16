package innerclasses;

//内部类与向上转型

class Parcel4 {
	private class PContents implements Contents {
		private int i = 11;
		@Override
		public int value() {
			return i;
		}
	}
	
	protected class PDestination implements Destination {
		private String label;
		public PDestination(String label) {
			this.label = label;
		}

		@Override
		public String readLabel() {
			return label;
		}
		
	}
	
	Contents contents() {
		return new PContents();
	}
	
	PDestination destination(String s) {
		return new PDestination(s);
	}
}

public class TestParcel {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Parcel4 p = new Parcel4();
		Contents c = p.contents();
		Destination d = p.destination("SZ");
		//Parcel4.PContents pc = p.new PContents(); Illegal can't access private class
		Parcel4.PDestination pd = p.new PDestination("");
	}
}
