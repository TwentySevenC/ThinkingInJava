package typeinfo;

import java.util.ArrayList;

public class Staff extends ArrayList<Position> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5580686591469408115L;

	public Staff(String ... titles) {
		add(titles);
	}
	
	public void add(String title, Person person) {
		add(new Position(title, person));
	}
	
	public void add(String ... titles) {
		for(String title : titles) {
			add(new Position(title));
		}
	}
	
	public boolean positionAvailable(String title) {
		for(Position p : this) {
			if(p.getTitle().equals(title) && p.getPerson() == Person.NULL)
				return true;
		}
		return false;
	}
	
	public void fillPosition(String title, Person person) {
		for(Position p : this) {
			if(p.getTitle().equals(title)) {
				p.setPerson(person);
				return ;
			}
		}
		throw new RuntimeException("Position " + title + " not available");
	}
	
	public static void main(String[] args) {
		Staff staff = new Staff("President", "CTO", "Marketing Manager", 
				"Product Manager", "Project Lead", "Software Engineer",
				"Test Engineer", "Software Engineer");
		
		staff.fillPosition("President", new Person("me", "Last", "The top, Look At."));
		staff.fillPosition("CTO", new Person("Bobo", "Allen", "New York, Three street."));
		if(staff.positionAvailable("Software Engineer")) {
			staff.fillPosition("Software Engineer", new Person("Dave", "Lee", "Yatenanda."));
		}
		
		System.out.println(staff);
	}

}
