package typeinfo.pets;

import java.util.List;

/**
 * Facade to produce a default PetCreator
 * @author liujian
 *
 */

public class Pets {
	public static final PetCreator CREATOR = new LiteralPetCreator();
	
	public static Pet randomPet() {
		return CREATOR.randomPet();
	}
	
	public static Pet[] createArray(int size) {
		return CREATOR.createArray(size);
	}
	
	public static List<Pet> arrayList(int size) {
		return CREATOR.arrayList(size);
	}

}
