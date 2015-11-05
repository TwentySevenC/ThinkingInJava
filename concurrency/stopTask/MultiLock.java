package stopTask;


//: concurrency/MultiLock.java

//One thread can get the same lock

public class MultiLock {
	
	public static synchronized void f1(int count){
		if(count -- > 0){
			System.out.println("f1() calling f2(), count: " + count);
			f2(count);
		}
	}
	
	public static synchronized void f2(int count){
		if(count-- > 0){
			System.out.println("f2() calling f1(), count: " + count);
			f1(count);
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		new Thread(){
			public void run(){
				f1(10);
			}
		}.start();
		
//		TimeUnit.SECONDS.sleep(1);
	}

}
