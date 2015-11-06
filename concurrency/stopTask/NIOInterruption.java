package concurrency.stopTask;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

//: concurrency/NIOInterruption.java

class NIOBlocked implements Runnable{
	
	private final SocketChannel sc;
	public NIOBlocked(SocketChannel sc) {
		this.sc = sc;
	}

	@Override
	public void run() {
		System.out.println("Waiting for read in " + this + sc.toString());
		try {
			sc.read(ByteBuffer.allocate(1));
		}catch(ClosedByInterruptException e){
			System.out.println("Closed by interrupt exception..");
		}catch (AsynchronousCloseException e) {
			System.out.println("AsynchronousCloseException");
		}catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
		
		System.out.println("Exit IOBlock run " + this);
	}
	
}

public class NIOInterruption {
	public static void main(String[] args) throws IOException, InterruptedException {
		ExecutorService exe = Executors.newCachedThreadPool();
		ServerSocket serverSocket = new ServerSocket(8080);
		InetSocketAddress ad = new InetSocketAddress("localhost", 8080);
		SocketChannel sc1 = SocketChannel.open(ad);
		SocketChannel sc2 = SocketChannel.open(ad);
		
		Future<?> f = exe.submit(new NIOBlocked(sc1));
		exe.execute(new NIOBlocked(sc2));
		exe.shutdown();
		
		TimeUnit.MILLISECONDS.sleep(1000);
		System.out.println("Produce an interrupt via cancel. " + sc1);
		f.cancel(true);
		
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Produce an interrupt via channel.. " + sc2);
		sc2.close();
		serverSocket.close();
	}
}
