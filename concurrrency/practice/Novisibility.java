package concurrrency.practice;

//java concurrency in practice/Novisibility.java 

// 可见性问题： 在没有同步的情况下，编译器，处理器，运行时等都可能对操作的执行顺序进行一些意想不到的调整。

public class Novisibility {
	private static boolean ready = false;
	private static int number = 0;
	
	private static class ReadThread extends Thread{
		@Override
		public void run(){
			while(!ready)
				yield();
			System.out.println(number);
		}
	}
	
	public static void main(String[] args) {
		new ReadThread().start();
		number = 43;
		ready = true;
	}
}
