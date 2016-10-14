package polymorphism;

import static util.util.Print.*;

/**
 * State pattern
 * 
 * 用继承表达行为间的差异，并用字段表达状态上的变化。
 * @author liujian
 *
 */

class Actor {
	public void act() {
		
	}
}


class HappyActor extends Actor {
	@Override
	public void act() {
		print("Happy Actor");
	}
}

class SadActor extends Actor {
	@Override
	public void act() {
		print("Sad Actor");
	}
}

class Stage {
	private Actor actor  = new HappyActor();
	
	public void change() {
		actor = new SadActor();
	}
	
	public void performPlay() {
		actor.act();
	}
}

public class Transmogrify {
	public static void main(String[] args) {
		Stage stage = new Stage();
		stage.performPlay();
		stage.change();
		stage.performPlay();
	}

}
