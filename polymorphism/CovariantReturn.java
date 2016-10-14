package polymorphism;

import static util.util.Print.*;

/**
 * Java SE5 中添加了协变返回类型
 * @author liujian
 *
 */

class Grain {
	@Override
	public String toString() {
		return "Grain";
	}
}

class Wheat extends Grain {
	@Override
	public String toString() {
		return "Wheat";
	}
}

class Mill {
	Grain process() {return new Grain();}
}

class WheatMill extends Mill {
	@Override
	Wheat process() {
		return new Wheat();
	}
}

public class CovariantReturn {
	public static void main(String[] args) {
		Mill mill = new Mill();
		Grain g = mill.process();
		print(g);
		g = new WheatMill().process();
		print(g);
	}
}
