package generics;

import java.util.EnumSet;
import java.util.Set;
import static util.util.Print.*;

import generics.watercolors.WaterColors;
import util.util.Sets;

public class WaterColorSets {
	public static void main(String[] args) {
		Set<WaterColors> set1 = EnumSet.range(WaterColors.BRILLIANT_RED, WaterColors.VIRIDIAN_HUE);
		Set<WaterColors> set2 = EnumSet.range(WaterColors.CERULEAN_BLUE_HUE, WaterColors.BURANT_UMBER);
		print("set1" + set1);
		print("set2" + set2);
		
		print("set1 union set2: " + Sets.union(set1, set2));
		print("set1 intersection set2: " + Sets.intersection(set1, set2));
		print("set1 difference set2: " + Sets.difference(set1, set2));
		print("set1 complement set2: " + Sets.complement(set1, set2));
	}
}
