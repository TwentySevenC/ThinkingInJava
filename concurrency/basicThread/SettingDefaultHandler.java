package basicThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//concurrency/SettingDefaultHandler.java

//将Thread 设置一个默认的UncaughtExceptionHandler

public class SettingDefaultHandler {
	public static void main(String[] args) {
		Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
		ExecutorService exe = Executors.newCachedThreadPool();
		exe.execute(new ExceptionThread());
	}
}
