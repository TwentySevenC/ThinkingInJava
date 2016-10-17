package typeinfo.pets;

import static util.util.Print.print;
import static util.util.Print.printnb;

import util.util.TypeCounter;

public class PetCount4 {

	public static void main(String[] args) {
		TypeCounter counter = new TypeCounter(Pet.class);
		for(Pet pet : Pets.createArray(20)) {
			printnb(pet.getClass().getSimpleName() + " ");
			counter.count(pet);
		}
		
		print();
		print(counter);
	}

}
