package concurrency.shareLimitedResource;

//: SynchronizedEvenGenerator.java

//使用同步锁机制来限制资源的访问，使一个固定的资源每次只能被一个任务访问


public class SynchronizedEvenGenerator extends IntGenerator {
	private int val = 0;

	@Override
	synchronized public int next() {
		val++;
		Thread.yield();
		val++;
		return val;
	}
	
	public static void main(String[] args) {
		EvenChecker.test(new SynchronizedEvenGenerator());
	}

}
