package generics;

class Fruit {}
class Apple extends Fruit {}
class Jonathan extends Apple {}
class Orange extends Fruit {}

public class CovariantArrays {
	public static void main(String[] args) {
		Fruit[] fruits = new Apple[10];//fruits中必须放置Apple及Apple的子类
		fruits[0] = new Apple();
		fruits[1] = new Jonathan();
		try {
			fruits[0] = new Fruit();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			fruits[0] = new Orange();
		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
