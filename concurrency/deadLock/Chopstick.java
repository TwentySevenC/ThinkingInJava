package concurrency.deadLock;

//: concurrency/Chopstick.java

public class Chopstick {
	private boolean taken = false;

	synchronized void take() throws InterruptedException{
		while(taken == true)
			wait();
		
		taken = true;
	}
	
	synchronized void drop(){
		taken = false;
		notifyAll();
	}
	
}
