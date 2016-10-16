package typeinfo;

import static util.util.Print.*;

class Candy {
	static {print("Loading Candy");}
}

class Gum {
	static {print("Loading Gum");}
}

class Cookie {
	static {print("Loading Cookie");}
}

public class SweetShop {
	public static void main(String[] args) {
		print("Inside main");
		new Candy();
		try {
			Class.forName("typeinfo.Gum");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Cookie();
	}
}
