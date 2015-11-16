package containers;

//: container/Groundhog2.java

public class Groundhog2 extends Groundhog{

	public Groundhog2(int num) {
		super(num);
	}
	
	public int hashCode(){
		return number;
	}
	
	public boolean equals(Object o){
		return o instanceof Groundhog2 && (((Groundhog2)o).number == number);
	}

}
