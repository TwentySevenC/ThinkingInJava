package util.atunit;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import util.util.BinaryFile;
import util.util.Directory;
import util.util.Print;

/**
 * Java 
 * 
 * u1-one byte	u2-two bytes	u4-four bytes	u8-eight bytes
 * 
 * ClassFile {
 * 		u4				magic
 * 		u2				minor_version
 * 		u2				major_version
 * 		u2				constant_pool_count
 * 		cp_info			constant_pool[constant_pool_count-1]
 * 		u2				access_flags
 * 		u2				this_class
 * 		u2				super_class
 * 		u2				interfaces_count
 * 		u2				interfaces[interfaces_count]
 * 		u2				fields_count
 * 		field_info		fields[fields_count]
 * 		u2				methods_count
 * 		method_info     methods[methods_count]
 * 		u2				attributes_count
 * 		attribute_info  attributes[attributes_count]
 * }
 * 
 * field_info {
 * 		u2				access_flags
 * 		u2				name_index
 * 		u2				descriptor_index
 * 		u2				attributes_count
 * 		attribute_info  attributes[attributes_count]
 * }
 * 
 * method_info {
 * 		u2				access_flags
 * 		u2				name_index
 * 		u2				descriptor_index
 * 		u2				attributes_count
 * 		attribute_info  attributes[attributes_count]
 * }
 *
 */
public class ClassNameFinder {
	@SuppressWarnings("unused")
	public static String thisClass(byte[] classBytes) {
		Map<Integer, Integer> offsetTable = new HashMap<>(); //CONSTANT_Class_info map
		Map<Integer, String> classNameTable = new HashMap<>(); //CONSTANT_UTF_info map
		try {
			
			DataInputStream data = new DataInputStream(new ByteArrayInputStream(classBytes));
			int magic = data.readInt(); //0xcafebabe
			int minorVersion = data.readShort();
			int majorVersion = data.readShort();
			int constant_pool_count = data.readShort();
			int[] constant_pool = new int[constant_pool_count];
			for(int i = 1; i < constant_pool_count; i++) {
				int tag = data.read();
				int tableSize ;
				switch (tag) {
				case 1://UTF
					int length = data.readShort();
					char[] bytes = new char[length];
					for(int k = 0; k < bytes.length; k++) {
						bytes[k] = (char) data.read();
					}
					String className = new String(bytes);
					classNameTable.put(i, className);
					break;
				case 5: //LONG
					/**
					 * Constant pool:
   						#1 = Methodref          #12.#30        // java/lang/Object."<init>":()V
   						#2 = Long               1000l
   						#4 = Fieldref           #11.#31        // com/vogella/junit/first/MyClass.id:J
   						#5 = Double             10.5d
   						#7 = Fieldref           #11.#32        // com/vogella/junit/first/MyClass.d:D
   						#8 = Class              #33            // java/lang/IllegalArgumentException
					 */
				case 6: //DOUBLE
					data.readLong();//discard 8 bytes
					i++; //special skip necessary
					break;
				case 7: //CLASS
					int offset = data.readShort();
					offsetTable.put(i, offset);
					break;
				case 8: //STRING
					data.readShort(); //discard 2 bytes
					break;
				case 3: //INTEGER
				case 4: //FLOAT
				case 9: //FIELD_REF
				case 10: //METHOD_REF
				case 11: //INTERFACE_METHOD_REF
				case 12: //NAME_AND_TYPE
					data.readInt(); //discard 4 bytes
					break;
					
				default:
					throw new RuntimeException("Bad tag " + tag);
				}
			}
			short access_flags = data.readShort();
			int this_class = data.readShort();
			int super_class = data.readShort();
			return classNameTable.get(offsetTable.get(this_class)).replace("/", ".");
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	public static void main(String[] args) throws Exception {
		if(args.length > 0) {
			for(String arg : args) {
				Print.print(thisClass(BinaryFile.read(new File(arg))));
			}
		} else {
			//walk the entire tree
			for(File file : Directory.walk(".", ".*\\.class"))
				Print.print(thisClass(BinaryFile.read(file)));
		}
	}
}
