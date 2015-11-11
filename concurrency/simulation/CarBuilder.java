package concurrency.simulation;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;



//: concurrency/CarBuilder.java

class Car {
	private final int id;
	private boolean engine = false, driveTrain = false, wheels = false;
	public Car(int id){
		this.id = id;
	}
	
	public Car(){
		id = -1;
	}
	
	synchronized int getId(){
		return id;
	}
	
	synchronized void addEngine(){
		engine = true;
	}
	
	synchronized void addDriveTrain(){
		driveTrain = true;
	}
	
	synchronized void addWheels(){
		wheels = true;
	}
	
	public synchronized String toString(){
		return "Car: " + id + " engine: " + engine + " driveTrain: " + driveTrain + " wheels: " + wheels;
	}
}


class CarQueue extends LinkedBlockingQueue<Car>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;}

class ChassisBuilder implements Runnable{
	private CarQueue chassisQueue ;
	private int count = 0;
	
	public ChassisBuilder(CarQueue queue){
		chassisQueue = queue;
	}
	
	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				TimeUnit.MILLISECONDS.sleep(100);
				Car car = new Car(count++);
				chassisQueue.add(car);
			}
		}catch(InterruptedException e){
			System.out.println("Chassis builder interrupted.");
		}
		System.out.println("Chassis builder complete.");
	}
	
}


class Assembler implements Runnable{
	private CarQueue chassisQueue, finishingQueue;
	private Car car;
	CyclicBarrier barrier = new CyclicBarrier(4);
	private RobotPool pool;
	
	public Assembler(CarQueue chassisQueue, CarQueue finishingQueue, RobotPool pool) {
		this.chassisQueue = chassisQueue;
		this.finishingQueue = finishingQueue;
		this.pool = pool;
	}
	
	
	public Car car(){
		return car;
	}

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				car = chassisQueue.take();
				pool.hire(EngineRobot.class, this);
				pool.hire(DriveTrainRobot.class, this);
				pool.hire(WheelsRobot.class, this);
				barrier.await();
				
				finishingQueue.add(car);
			}
		}catch(InterruptedException e){
			System.out.println("Assembler exit via interrupt.");
		} catch (BrokenBarrierException e) {
			throw new RuntimeException();
		}
		System.out.println("Assembler complete.");
	}
	
}

class Reporter implements Runnable{
	private CarQueue finishingQueue;
	
	public Reporter(CarQueue queue) {
		finishingQueue = queue;
	}

	@Override
	public void run() {
		try{
			while(!Thread.interrupted()){
				System.out.println(finishingQueue.take());
			}
		}catch(InterruptedException e){
			System.out.println("Reporter interrupted.");
		}
		System.out.println("Reporter complete.");
	}
	
}


abstract class Robot implements Runnable{
	private RobotPool pool;
	protected Assembler assembler;
	private boolean engage = false;
	
	public Robot(RobotPool pool){
		this.pool = pool;
	}
	
	public Robot assignAssembler(Assembler assembler){
		this.assembler = assembler;
		return this;
	}
	
	public synchronized void engage(){
		engage = true;
		notify();
	}
	
	abstract protected void performService();
	
	@Override
	public void run(){
		try{
			powerDown();
			while(!Thread.interrupted()){
				performService();
				assembler.barrier.await();
				//done with that job
				powerDown();
			}
			
		}catch(InterruptedException e){
			System.out.println(this + " exit via interrupt.");
		} catch (BrokenBarrierException e) {
			throw new RuntimeException();
		}
		
		System.out.println(this + " off.");
	}
	
	
	private synchronized void powerDown() throws InterruptedException{
		engage = false;
		assembler = null;
		pool.release(this);
		while(engage == false)
			wait();
	}
	
	public String toString(){
		return getClass().getName();
	}
	
}


class EngineRobot extends Robot{

	public EngineRobot(RobotPool pool) {
		super(pool);
		
	}

	@Override
	protected void performService() {
		System.out.println(this + " installing engine.");
		assembler.car().addEngine();
	}
	
}

class DriveTrainRobot extends Robot{

	public DriveTrainRobot(RobotPool pool) {
		super(pool);
	}

	@Override
	protected void performService() {
		System.out.println(this + " installing drivetrain.");
		assembler.car().addDriveTrain();
	}
	
}

class WheelsRobot extends Robot{

	public WheelsRobot(RobotPool pool) {
		super(pool);
	}

	@Override
	protected void performService() {
		System.out.println(this + " installing wheels.");
		assembler.car().addWheels();
	}
	
}


class RobotPool {
	
	private Set<Robot> pool = new HashSet<Robot>();
	
	public synchronized void add(Robot r){
		pool.add(r);
		notifyAll();
	}
	
	public void hire(Class<? extends Robot> robotType, Assembler assembler) throws InterruptedException{
		for(Robot robot : pool){
			if(robot.getClass().equals(robotType)){
				pool.remove(robot);
				robot.assignAssembler(assembler);
				robot.engage();
				return ;
			}
		}	
		wait();
		hire(robotType, assembler);   //try again
	}
	
	public synchronized void release(Robot r){
		add(r);
	}
	
}

public class CarBuilder {
	public static void main(String[] args) throws InterruptedException {
		CarQueue chassisQueue = new CarQueue(),
				finishingQueue = new CarQueue();
		ExecutorService exec = Executors.newCachedThreadPool();
		RobotPool pool = new RobotPool();
		exec.execute(new EngineRobot(pool));
		exec.execute(new DriveTrainRobot(pool));
		exec.execute(new WheelsRobot(pool));
		exec.execute(new Reporter(finishingQueue));
		exec.execute(new Assembler(chassisQueue, finishingQueue, pool));
		exec.execute(new ChassisBuilder(chassisQueue));
		
		TimeUnit.SECONDS.sleep(4);
		
		exec.shutdownNow();
			
	}
}
