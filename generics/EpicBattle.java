package generics;

interface SuperPower {}

interface XRayVision extends SuperPower {
	void seeThroughWalls();
}

interface SuperHearing extends SuperPower {
	void hearSubtleNoise();
}

interface SuperSmell extends SuperPower {
	void trackBySmell();
}

class SuperHero<POWER extends SuperPower> {
	POWER power ;
	public SuperHero(POWER power) {
		this.power = power;
	}
	POWER getPower() {
		return power;
	}
}

class SuperSleuth<POWER extends XRayVision> extends SuperHero<POWER> {

	public SuperSleuth(POWER power) {
		super(power);
	}
	void see() {
		power.seeThroughWalls();
	}
}

class CanineHero<POWER extends SuperHearing & SuperSmell> extends SuperHero<POWER> {

	public CanineHero(POWER power) {
		super(power);
	}
	
	void hear() {
		power.hearSubtleNoise();
	}
	
	void smell() {
		power.trackBySmell();
	}
	
}

class SuperHearSmell implements SuperHearing, SuperSmell {

	@Override
	public void trackBySmell() {
		System.out.println("Track by smell..");
	}

	@Override
	public void hearSubtleNoise() {
		System.out.println("Hear subtle noise..");
	}
	
}

class DogBoy extends CanineHero<SuperHearSmell> {

	public DogBoy() {
		super(new SuperHearSmell());
	}
	
}

public class EpicBattle {
	static <POWER extends SuperHearing> void useSuperHearing(SuperHero<POWER> hero) {
		hero.power.hearSubtleNoise();
	}
	
	static <POWER extends SuperHearing & SuperSmell> void superFind(SuperHero<POWER> hero) {
		hero.power.hearSubtleNoise();
		hero.power.trackBySmell();
	}
	
	public static void main(String[] args) {
		DogBoy db = new DogBoy();
		useSuperHearing(db);
		superFind(db);
	}
}