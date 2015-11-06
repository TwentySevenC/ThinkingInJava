package concurrency.basicThread;


public class SelfManaged implements Runnable {
	private int countDown = 5;
	private static int countThread = 0;
	private final int id = countThread ++ ;
	
	private Thread thread = new Thread(this);
	
	public SelfManaged() {
		// TODO Auto-generated constructor stub
		thread.start();
	}
	
	public String toString(){
		return "Thread-" + id + "(" + countDown + ")";
	}
	
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			System.out.println(this);
			
			if(--countDown == 0) return ;
		}
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 5; i++){
			new SelfManaged();
		}
	}

}
