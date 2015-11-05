package shareLimitedResource;

//:concurrency/SyncObject.java

class DualSynch{
	private Object syncObject = new Object();
	
	public synchronized void f(){
		for(int i = 0; i < 5; i++){
			System.out.println("f()");
			Thread.yield();
		}
	}
	
	public void g(){
		
		synchronized (syncObject) {
			for(int i = 0; i < 5; i++){
				System.out.println("g()");
				Thread.yield();
			}
		}
	}
}

public class SyncObject {
	public static void main(String[] args) {
		DualSynch dual = new DualSynch();
		new Thread(){
			public void run(){
				dual.g();
			}
		}.start();
		
		dual.f();
	}
}
