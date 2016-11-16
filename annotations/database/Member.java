package annotations.database;

@DBTable(name = "MEMBER")
public class Member {
	@SQLString(30) 
	String fistName;
	@SQLString(50) 
	String lastName;
	@SQLInteger 
	Integer age;
	@SQLString(value = 30, constraints = @Constraints(primaryKey = true))
	String handle;
	static int memberCount;
	public String getFistName() {
		return fistName;
	}
	public String getLastName() {
		return lastName;
	}
	public Integer getAge() {
		return age;
	}
	public String getHandle() {
		return handle;
	}
	
}
