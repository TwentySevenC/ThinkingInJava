package concurrency.basicThread;

//:concurrency/LiftOff.java
//Demonstration of the Runnable interface

public class LiftOff implements Runnable{
	protected int countDown = 10;
	private static int taskCount = 0;
	private final int id = taskCount ++ ;
	
	public LiftOff() {
		// TODO Auto-generated constructor stub
	}

	
	public LiftOff(int count){
		countDown = count;
	}
	
	
	public String status(){
		String num = countDown > 0 ? countDown + "" : "LiftOff";
		return "#" + id + "(" + num + ")";
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(countDown-- > 0){
			System.out.println(status());
			Thread.yield();
		}
	}
	
}
