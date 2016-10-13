package reusing;

import static util.util.Print.*;

class Art {
	public Art() {
		print("Art constructor");
	}
}

class Drawing extends Art {
	public Drawing() {
		print("Drawing constructor");
	}
}

public class Cartoon extends Drawing {
	public Cartoon() {
		print("Carton constructor");
	}
	
	public static void main(String[] args) {
		new Cartoon();
	}
}
