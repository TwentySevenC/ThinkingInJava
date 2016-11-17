package annotations;

import util.atunit.Test;
import util.util.OSExecute;

public class AtUnitExample1 {
	public String methodOne() {
		return "This is methodOne";
	}
	
	public int methodTwo() {
		System.out.println("This is methodTwo");
		return 2;
	}
	
	@Test
	boolean methodOneTest()  {
		return methodOne().equals("This is methodTwo");
	}
	
	@Test boolean m2() {
		return methodTwo() == 2;
	}
	
	@Test private boolean m3() {
		return true;
	}
	
	@Test boolean failureTest() {
		return false;
	}
	
	@Test boolean anotherDisappointment() {
		return false;
	}
	
	public static void main(String[] args) {
		OSExecute.command("java util.atunit.AtUnit AtUnitExample1");
	}
}
