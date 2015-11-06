package concurrency.stopTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//concurrency/CloseResource.java
//Interrupting a blocked task by close the underlying resources


public class CloseResource {
	public static void main(String[] args) throws IOException, InterruptedException {
		ExecutorService exe = Executors.newCachedThreadPool();
		ServerSocket serverSocket = new ServerSocket(8080);
		@SuppressWarnings("resource")
		InputStream socketInput = new Socket("localhost", 8080).getInputStream();
		
		exe.execute(new IOBlock(socketInput));
		exe.execute(new IOBlock(System.in));
		
		TimeUnit.SECONDS.sleep(1);
		
		System.out.println("Shutdown all thread.");
		exe.shutdownNow();
		
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Close serverSocket.");
		serverSocket.close();
		
		TimeUnit.SECONDS.sleep(1);
		System.out.println("close system in");
		System.in.close();
		
	}

}
