package basicThread;

//: concurrency/Ex1.jav
/**
 * 由于run() 内部每个输出语句后都调用Thread.yield() 方法，该方法的作用是通知其他线程，
 * “我得任务已经执行完了，你可抢占cpu资源了”，在这样的情况下只有在最开始和最后才会出现一个线程
 * 里面同时输出两个语句。
 * 如果去掉这些Thread.yield() 方法，输出结果中间就会出现一个线程同时输出三条语句的情况，这
 * 体现了Thread.yield() 方法的作用：平衡了每个线程占用cpu的时间。
 * @author liujian
 *
 */

public class Ex1 implements Runnable {
	private static int tastCount = 0;
	private final int id = tastCount ++ ;
	
	public Ex1() {
		// TODO Auto-generated constructor stub
		System.out.println("Start Ex1 class" + "#" + id);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("#" + id + "In run() One.");
		Thread.yield();
		System.out.println("#" + id + "In run() Two.");
		Thread.yield();
		System.out.println("#" + id + "In run() Three.");
		Thread.yield();
		return;
	}
	
	
	public static void main(String[] args) {
		for(int i = 0; i < 10; i++){
			new Thread(new Ex1()).start();
		}
		
		System.out.println("After the Threads.");
	}

}
