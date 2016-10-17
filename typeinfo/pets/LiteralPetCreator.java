package typeinfo.pets;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LiteralPetCreator extends PetCreator {
	
	public static final List<Class<? extends Pet>> ALL_TYPES = 
			Collections.unmodifiableList(Arrays.asList(Cat.class, Cymric.class, 
					Dog.class, EgyptianMau.class, Hamster.class, Manx.class, Mouse.class,
					Mutt.class, Pug.class, Rat.class, Rodent.class));
	
	public static final List<Class<? extends Pet>> TYPES = 
			ALL_TYPES.subList(ALL_TYPES.indexOf(Hamster.class), ALL_TYPES.size());

	@Override
	public List<Class<? extends Pet>> types() {
		return TYPES;
	}

	public static void main(String[] args) {
		System.out.println(TYPES);
	}

}
