package typeinfo;

import util.util.Null;


//A class with a Null Object

public class Person {
	
	private final String first;
	private final String last;
	private final String address;

	public Person(String first, String last, String address) {
		this.first = first;
		this.last = last;
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Person: " + first + " " +  last + " " + address;
	}
	
	public static class NullPerson extends Person implements Null {

		public NullPerson() {
			super("None", "None", "None");
		}
		
		@Override
		public String toString() {
			return "NullPerson";
		}
	}
	
	public static final Person NULL = new NullPerson();

}
