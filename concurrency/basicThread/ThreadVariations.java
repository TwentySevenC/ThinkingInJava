package basicThread;


//:concurrency/ThreadVariations.java

//Creating thread with inner class

//Using a named inner class

class InnerThread1{
	private int countDown = 5;
	@SuppressWarnings("unused")
	private Inner inner;
	class Inner extends Thread{
		public Inner(String name){
			super(name);
			start();
		}
		
		public void run(){
			while(true){
				System.out.println(this);
				if(--countDown == 0) return ;
			}
		}
		
		public String toString(){
			return getName() + ":" + countDown;
		}
	}
	
	public InnerThread1(String name){
		inner = new Inner(name);
	}
}


//Using an anonymous inner class

class InnerThread2{
	private int countDown = 5;
	Thread inner;

	/***
	 * 下面这种方式会抛出name是null的异常，初始化一个类时，先会给成员变量赋初始值，然后才会接受构造函数赋予的值，
	 * 而name的默认会赋值为null，
	 * 
	String name;
	Thread inner = new new Thread(name){
		
		
		public void run(){
			while(true){
				System.out.println(this);
				if(--countDown == 0) return;
			}
		}
		
		public String toString(){
			return getName() + ":" + countDown;
		}
		
	}; */
	
	public InnerThread2(String name){
		inner = new Thread(name){
			
			
			public void run(){
				while(true){
					System.out.println(this);
					if(--countDown == 0) return;
				}
			}
			
			public String toString(){
				return getName() + ":" + countDown;
			}
			
		};
		
		inner.start();
	}
	
}


//Using named inner runnable class

class InnerRunnable1 {
	private int countDown = 5;
	private Thread thread;
	private String name;
	
	private class InnerRunnale implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(true){
				System.out.println(this);
				if(--countDown == 0 ) return ;
			}
		}
		
		public String toString(){
			return name + ":" + countDown;
		}
		
	}
	
	public InnerRunnable1(String name){
		this.name = name;
		thread = new Thread(new InnerRunnale());
		thread.start();
	}
	
}


//Using an anonymous inner runnable
class InnerRunnable2{
	private int countDown = 5;
	private Thread thread;
	public InnerRunnable2(String name){
		
		thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					System.out.println(this);
					if(--countDown == 0) return ;
				}
				
			}
			
			public String toString(){
				return name + ":" + countDown;
			}
		});
		thread.start();
	}
}


//

class ThreadMethod{
	private int countDown = 5;
	private Thread t;
	private String name;
	public void runTask(){
		if(t == null){
			t = new Thread(name){
				
				public void run(){
					while(true){
						System.out.println(this);
						if(--countDown == 0) return;
					}
				}
				
				public String toString(){
					return getName() + ":" + countDown;
				}
			};
		}
		
		t.start();
	}
	
	public ThreadMethod(String name){
		this.name = name;
	}
}


public class ThreadVariations {
	public static void main(String[] args) {
		new InnerThread1("InnerThread1");
		
		new InnerRunnable1("InnerRunnable1");
		new InnerRunnable2("InnerRunnable2");
		new ThreadMethod("ThreadMethod").runTask();
		
		new InnerThread2("InnerThread2");
	}
	

}
