package annotations.database;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TableCreator {
	public static void main(String[] args) throws ClassNotFoundException {
		if(args.length < 1) {
			System.out.println("arguments: annotated classes");
			System.exit(0);
		}
		
		for(String className : args) {
			Class<?> clazz = Class.forName(className);
			DBTable dbTable = clazz.getDeclaredAnnotation(DBTable.class);
			if(dbTable == null) {
				System.out.println("No DBTable annotations in class: " + className);
				continue;
			}
			
			String tableName = dbTable.name();
			//If the name is empty, use the class name.
			if(tableName.length() < 1) {
				tableName = className.toUpperCase();
			}
			
			List<String> columnDefs = new ArrayList<>();
			for(Field field : clazz.getDeclaredFields()) {
				String columnName = null;
				Annotation[] annos = field.getAnnotations();
				if(annos.length < 1) 
					continue;
				
				if(annos[0] instanceof SQLString) {
					SQLString sString = (SQLString) annos[0];
					columnName = sString.name();
					if(columnName.length() < 1)
						columnName = field.getName().toUpperCase();
				
					columnDefs.add(columnName + " VARCHAR(" + sString.value() + ")" +
							getConstraints(sString.constraints()));
				}
				
				if(annos[0] instanceof SQLInteger)  {
					SQLInteger sInteger = (SQLInteger) annos[0];
					columnName = sInteger.name();
					if(columnName.length() < 1)
						columnName = field.getName().toUpperCase();
					
					columnDefs.add(columnName + " INT" + getConstraints(sInteger.constraints()));
				}
			}
			
			StringBuilder createCommand = new StringBuilder();
			createCommand.append("CREATE TABLE " + tableName + "(");
			for(String columnCommand : columnDefs) {
				createCommand.append("\n    "  + columnCommand).append(",");
			}
			
			String tableCreated = createCommand.substring(0,  createCommand.length() - 1) + ");";
			
			System.out.println("Table creation sql for " + tableName + " is: \n" + 
					tableCreated);
		}
	}
	
	private static String getConstraints(Constraints cons) {
		String constraints = "";
		if(!cons.allowNull()) {
			constraints += " NOT NULL";
		}
		
		if(cons.primaryKey()) {
			constraints += " PRIMARY KEY";
		}
		
		if(cons.unique()) {
			constraints += " UNIQUE";
		}
		return constraints;
	}
}
