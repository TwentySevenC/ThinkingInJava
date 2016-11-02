package generics.latenttyping;

class CommunicateSimply {
	public static void perform(Performs performer) {
		performer.speak();
		performer.sit();
	}
}

public class SimpleDogsAndRobots {
	public static void main(String[] args) {
		CommunicateSimply.perform(new PerformingDog());
		CommunicateSimply.perform(new Robot());
	}
}
