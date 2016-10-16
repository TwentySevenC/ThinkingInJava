package typeinfo.pets;

import java.util.ArrayList;
import java.util.List;

public class ForNameCreator extends PetCreator {
	
	private static List<Class<? extends Pet>> types = 
			new ArrayList<>();
	
	private static String[] typeNames = new String[] {
			"typeinfo.pets.Cat",
			"typeinfo.pets.Dog",
			"typeinfo.pets.Cymric",
			"typeinfo.pets.EgyptianMau",
			"typeinfo.pets.Hamster",
			"typeinfo.pets.Manx",
			"typeinfo.pets.Mouse",
			"typeinfo.pets.Mutt",
			"typeinfo.pets.Pug",
			"typeinfo.pets.Rat",
			"typeinfo.pets.Rodent",
	};
	
	@SuppressWarnings("unchecked")
	private static void loader() {
		try {
			for(String name : typeNames) {
				types.add((Class<? extends Pet>)Class.forName(name));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException();
		}
	}
	
	static {loader();}

	@Override
	public List<Class<? extends Pet>> types() {
		return types;
	}
}
