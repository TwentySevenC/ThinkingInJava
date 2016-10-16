package typeinfo.pets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class PetCreator {
	private Random random = new Random(47);
	
	public abstract List<Class<? extends Pet>> types();
	
	public Pet randomPet() {
		int n = random.nextInt(types().size());
		try {
			return types().get(n).newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException();
		} catch (IllegalAccessException e) {
			throw new RuntimeException();
		}
	}
	
	public Pet[] createArray(int size) {
		Pet[] pets = new Pet[size];
		for(int i = 0; i < size; i++) {
			pets[i] = randomPet();
		}
		return pets;
	}
	
	public List<Pet> arrayList(int size) {
		List<Pet> pets = new ArrayList<>(size);
		Collections.addAll(pets, createArray(size));
		return pets;
	}
}
