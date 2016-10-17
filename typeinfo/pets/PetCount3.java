package typeinfo.pets;

import java.util.LinkedHashMap;
import java.util.Map;

import util.util.MapData;

import static util.util.Print.*;

// Using isInstance()

public class PetCount3 {
	
	static class PetCounter extends LinkedHashMap<Class<? extends Pet>, Integer> {
		/**
		 * 
		 */
		private static final long serialVersionUID = -6421948431524372541L;
		
		public PetCounter() {
			super(MapData.map(LiteralPetCreator.ALL_TYPES, 0));
		}
		
		public void count(Pet pet) {
			for(Map.Entry<Class<? extends Pet>, Integer> pair : PetCounter.this.entrySet()) {
				if(pair.getKey().isInstance(pet)) {
					put(pair.getKey(), pair.getValue() + 1);
				}
			}
		}
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("{");
			for(Map.Entry<Class<? extends Pet>, Integer> pair : PetCounter.this.entrySet()) {
				sb.append(pair.getKey().getSimpleName());
				sb.append("=");
				sb.append(pair.getValue());
				sb.append(", ");
			}
			sb.delete(sb.length() - 2, sb.length());
			sb.append("}");
			return sb.toString();
		}
	}
	
	public static void countPets(PetCreator creator) {
		PetCounter counter = new PetCounter();
		for(Pet pet : creator.createArray(20)) {
			printnb(pet.getClass().getSimpleName() + " ");
			counter.count(pet);
		}
		
		print();
		print(counter);
	}
	
	public static void main(String[] args) {
		countPets(Pets.CREATOR);
	}
}
