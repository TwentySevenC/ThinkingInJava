package concurrency.tasksCooperation;


import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//: concurrency/PipedIO.java


class Sender implements Runnable{
	private Random random = new Random();
	private PipedWriter writer = new PipedWriter();
	public PipedWriter getWriter(){
		return writer;
	}
	
	
	@Override
	public void run() {
		try{
			while(true){
				for(char c = 'a'; c < 'z'; c++){
					writer.write(c);
					TimeUnit.MILLISECONDS.sleep(random.nextInt(100));
				}
			}
		}catch(IOException e){
			System.out.println(e + " sender writer exception.");
		}catch(InterruptedException e){
			System.out.println(e + " sender writer exception.");
		}
	}
	
}


class Receiver implements Runnable{
	private PipedReader reader;
	
	public Receiver(Sender sender) throws IOException {
		reader = new PipedReader(sender.getWriter());
	}
	
	@Override
	public void run() {
		try{
			while(true){
				System.out.println("Reader "+ (char)reader.read());
			}
			
		}catch(IOException e){
			System.out.println(e + " receiver exception..");
		}
	}
	
}


public class PipedIO {
	public static void main(String[] args) throws IOException, InterruptedException {
		Sender sender = new Sender();
		Receiver reader = new Receiver(sender);
		
		ExecutorService exe = Executors.newCachedThreadPool();
		exe.execute(sender);
		exe.execute(reader);
		
		TimeUnit.SECONDS.sleep(4);
		exe.shutdownNow();
		
	}
}
