package newComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


//:concurrency/HorseRace.java
//Using CyclicBarrier

class Horse implements Runnable{
	private static int count = 0;
	private final int id = count++;
	private static Random random = new Random(47);
	private CyclicBarrier barrier;
	private int strides;
	public Horse(CyclicBarrier barrier){
		this.barrier = barrier;
	}
	
	public synchronized int getStrides() {
		return strides;
	}

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				synchronized (this) {
					strides += random.nextInt(3);
				}
				barrier.await();
			}
		}catch(InterruptedException e){
			System.out.println(e + " interrupted.");
		}catch (BrokenBarrierException e) {
			throw new RuntimeException();
		}
		
	}
	
	public String toString(){
		return "Horse " + id + " ";
	}
	
	public String track(){
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < getStrides(); i++){
			s.append("*");
		}
		s.append(id);
		return s.toString();
	}
	
}

public class HorseRace {
	static final int FINISH_LINE = 75;
	private List<Horse> horses = new ArrayList<>();
	
	private CyclicBarrier barrier;
	private ExecutorService exe = Executors.newCachedThreadPool();
	
	
	public HorseRace(int nHorse, int pause){
		barrier = new CyclicBarrier(nHorse, new Runnable() {
			
			@Override
			public void run() {
				StringBuilder s = new StringBuilder();
				for(int i = 0; i < FINISH_LINE; i++){
					s.append("=");
				}
				System.out.println(s.toString());
				
				for(Horse horse : horses){
					System.out.println(horse.track());
				}
				
				for(Horse horse : horses){
					if(horse.getStrides() >= FINISH_LINE){
						System.out.println(horse + " won!");
						exe.shutdownNow();
						return;
					}
					
				}
				
				try {
					TimeUnit.MILLISECONDS.sleep(pause);
				} catch (InterruptedException e) {
					System.out.println("barrier-action sleep interrupted.");
				}
				
			}
		});
		
		for(int i = 0; i < nHorse; i++){
			Horse horse = new Horse(barrier);
			horses.add(horse);
			exe.execute(horse);
		}
	}
	
	
	
	
	public static void main(String[] args) {
		int nHorse = 7;
		int pause = 100;
		new HorseRace(nHorse, pause);
	}
	
}
