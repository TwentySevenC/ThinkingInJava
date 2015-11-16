package containers;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

import util.util.Generator;

//: container/QueueBehavior.java

public class QueueBehavior {
	private static int count = 10;
	
	static <T> void test(Queue<T> queue, Generator<T> gen){
		for(int i = 0; i < count; i++){
			queue.add(gen.next());
		}
		
		while(queue.peek() != null){
			System.out.print(queue.poll() + " ");
		}
		
		System.out.println("");
	}
	
	
	static class Gen implements Generator<String>{
		
		String[] chars = "one two three four five six seven eight nine ten".split(" ");
		private int i = 0;

		@Override
		public String next() {
			return chars[i++];
		}
		
	}
	
	public static void main(String[] args) {
		test(new LinkedList<>(), new Gen());
		test(new PriorityQueue<>(), new Gen());
		test(new ArrayBlockingQueue<>(count), new Gen());
		test(new LinkedBlockingQueue<>(), new Gen());
		test(new PriorityBlockingQueue<>(), new Gen());
		test(new ConcurrentLinkedQueue<>(), new Gen());
	}
}
