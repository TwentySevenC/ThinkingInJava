package typeinfo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

import static util.util.Print.*;

/**
 * Using reflection to show all the methods of a class even if the methods are defined in the base class
 * @author liujian
 *
 */

public class ShowMethods {
	
	private static final String usage = "usage:\n" + 
			"ShowMethods qualified.class.name\n" + 
			"To show all methods in class or: \n" + 
			"ShowMethods qualified.class.name word\n" + 
			"To search for methods involving 'word'";
	
	private static Pattern p = Pattern.compile("\\w+\\.");
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		if(args.length < 1) {
			print(usage);
			System.exit(1);
		}
		try {
			Class<?> c = Class.forName(args[0]);
			Method[] methods = c.getMethods();
			Constructor[] ctors = c.getConstructors();
			if(args.length == 1) {
				for(Method method : methods) {
					print(p.matcher(method.toString()).replaceAll(""));
				}
				
				for(Constructor cons : ctors) {
					print(p.matcher(cons.toString()).replaceAll(""));
				}
			} else {
				for(Method method : methods) {
					if(method.toString().indexOf(args[1]) != -1) {
						print(p.matcher(method.toString()).replaceAll(""));
					}
				}
				
				for(Constructor ctor : ctors) {
					if(ctor.toString().indexOf(args[1]) != -1) {
						print(p.matcher(ctor.toString()).replaceAll(""));
					}
				}
			}
		} catch (ClassNotFoundException e) {
			print("No such class: " + e);
		}
	}

}
