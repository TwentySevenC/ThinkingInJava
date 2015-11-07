package concurrency.newComponents;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class GreenhouseScheduler {
	@SuppressWarnings("unused")
	private volatile boolean light = false;
	@SuppressWarnings("unused")
	private volatile boolean water = false;
	private String thermostat = "Day";
	
	public synchronized String getThermostat(){
		return thermostat;
	}
	
	public synchronized void setThermostat(String value){
		thermostat = value;
	}
	
	
	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);
	
	public void schedule(Runnable demmand, long delay){
		scheduler.schedule(demmand, delay, TimeUnit.MILLISECONDS);
	}
	
	public void scheduleAtFixedRate(Runnable demmand, long initialDelay, long period){
		scheduler.scheduleAtFixedRate(demmand, initialDelay, period, TimeUnit.MILLISECONDS);
	}
	
	
	class LightOn implements Runnable{

		@Override
		public void run() {
			System.out.println("Turn on light.");
			light = true;
		}
		
	}
	
	class LightOff implements Runnable{

		@Override
		public void run() {
			System.out.println("Turn off light.");
			light = false;
		}
		
	}
	
	class WaterOn implements Runnable{

		@Override
		public void run() {
			System.out.println("Water on");
			water = true;
		}
		
	}
	
	class WaterOff implements Runnable{

		@Override
		public void run() {
			System.out.println("Water off.");
			water = false;
		}
		
	}
	
	class ThermostatDay implements Runnable{

		@Override
		public void run() {
			System.out.println("Thermostat day.");
			thermostat = "Day";
		}
		
	}

	
	class ThermostatLight implements Runnable {

		@Override
		public void run() {
			System.out.println("Thermostat light.");
			thermostat = "Light";
		}
		
	}
	
	class Terminate implements Runnable{

		@Override
		public void run() {
			scheduler.shutdownNow();
			System.out.println("Terminate scheduler.");
			new Thread(){
				public void run(){
					for(DataPoint data : datas){
						System.out.println(data);
					}
				}
				
			}.start();
		}
		
	}
	
	
	static class DataPoint {
		private float temp;
		private float humidity;
		private Calendar time;
		
		public DataPoint(float temp, float humidity, Calendar time) {
			this.temp = temp;
			this.humidity = humidity;
			this.time = time;
		}
		
		public String toString(){
			return "[" + time.getTime() + String.format(" Temp: %2$.1f, Humidity: %2$.2f]", temp, humidity);
		}
		
	}
	
	
	private List<DataPoint> datas = Collections.synchronizedList(new ArrayList<>());
	private float lastTemp = 68.0f;
	private float lastHumidity = 0.23f;
	private int tempDerection = 1;
	private int humidityDerection = 1;
	private Calendar lastTime = Calendar.getInstance();
	{
		lastTime.set(Calendar.MINUTE, 30);
		lastTime.set(Calendar.SECOND, 00);
	}
	
	
	class SaveData implements Runnable{
		private Random random = new Random();

		@Override
		public void run() {
			synchronized (GreenhouseScheduler.this) {
				lastTime.set(Calendar.MINUTE, lastTime.get(Calendar.MILLISECOND) + 30);
				
				if(random.nextInt(4) == 3){
					tempDerection = -1 * tempDerection;
				}
				
				lastTemp = lastTemp +  tempDerection * (1.0f + random.nextFloat());
				
				if(random.nextInt(4) == 2){
					humidityDerection = -1 * tempDerection;
				}
				
				lastHumidity = lastHumidity + humidityDerection * random.nextFloat();
				datas.add(new DataPoint(lastTemp, lastHumidity, (Calendar)lastTime.clone()));
			}
			
		}
		
	}
	
	
	public static void main(String[] args) {
		GreenhouseScheduler gh = new GreenhouseScheduler();
		gh.schedule(gh.new Terminate(), 5000);
		gh.scheduleAtFixedRate(gh.new LightOff(), 200, 500);
		gh.scheduleAtFixedRate(gh.new WaterOn(), 300, 400);
		gh.scheduleAtFixedRate(gh.new ThermostatDay(), 100, 2000);
		gh.scheduleAtFixedRate(gh.new ThermostatLight(), 700, 2000);
		gh.scheduleAtFixedRate(gh.new LightOn(), 100, 500);
		gh.scheduleAtFixedRate(gh.new WaterOff(), 500, 400);
		gh.scheduleAtFixedRate(gh.new SaveData(), 100, 100);
	}
	
	
	
}
