package concurrency.basicThread;

//:concurrency/SimpleThread.java
//Inheriting directly from a Thread class
//直接重写了Thread的run（）方法，在Thread内部，run() 方法默认调用thread的Runnable变量的run（）方法。

public class SimpleThread extends Thread {

	private static int countThread = 0;
	private  int countDown = 5;
	
	public SimpleThread() {
		// TODO Auto-generated constructor stub
		super(Integer.toString(++countThread));
		start();
	}
	
	public String toString(){
		return "#" + getName() + " , id: " + countDown ;
	}
	
	public void run(){
		while(true){
			System.out.println(this);
			if(--countDown == 0){
				return ;
			}
		}
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 5; i++){
			new SimpleThread();
		}
	}

}
