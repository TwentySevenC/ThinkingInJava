package concurrency.newComponents;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import util.util.BasicGenerator;
import util.util.Generator;

//: concurrency/ExchangerDemo.java

class ExchangerProducer<T> implements Runnable{
	private Generator<T> generator;
	private Exchanger<List<T>> exchanger;
	private List<T> holder;
	
	public ExchangerProducer(Exchanger<List<T>> exchanger, Generator<T> generator, List<T> holder ) {
		this.exchanger = exchanger;
		this.generator = generator;
		this.holder = holder;
	}
	

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				for(int i = 0; i < ExchangerDemo.size; i++){
					holder.add(generator.next());
				}
				
				holder = exchanger.exchange(holder);
			}
		}catch(InterruptedException e){
			//
		}
		
	}
	
}



class ExchangerConsumer<T> implements Runnable{
	private Exchanger<List<T>> exchanger;
	private List<T> holder;
	private volatile T value;
	
	public ExchangerConsumer(Exchanger<List<T>> exchanger, List<T> holder) {
		this.exchanger = exchanger;
		this.holder = holder;
	}

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				holder = exchanger.exchange(holder);
				for(T t : holder){
					value = t;
					holder.remove(t);
				}
			}
		}catch(InterruptedException e){
			//
		}
		System.out.println("Final value: " + value);
		
	}
	
}

public class ExchangerDemo {
	static int size = 10;
	static int delay = 5;
	public static void main(String[] args) throws InterruptedException {
		ExecutorService exc = Executors.newCachedThreadPool();
		Exchanger<List<Fat>> xc = new Exchanger<>();
		List<Fat> 
			produceList = new CopyOnWriteArrayList<Fat>(),
			consumerList = new CopyOnWriteArrayList<Fat>();
		exc.execute(new ExchangerProducer<Fat>(xc, BasicGenerator.create(Fat.class), produceList));
		exc.execute(new ExchangerConsumer<Fat>(xc, consumerList));
		TimeUnit.SECONDS.sleep(delay);	
		exc.shutdownNow();
	}
}
