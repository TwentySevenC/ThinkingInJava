package annotations;

import java.util.List;

public class PasswordUtils {
	
	@UseCase(id = 47, description = "Passwords must contain at least one numeric")
	public boolean validatePassword(String password) {
		return password.matches("\\w*\\d\\w*");
	}
	
	@UseCase(id = 48)
	public String encryptPassword(String password) {
		return new StringBuffer(password).reverse().toString();
	}
	
	@UseCase(id = 49, description = "New passwords can't equal previously used ones")
	public boolean checkForNewPassword(List<String> prePassword, String password) {
		return !prePassword.contains(password);
	}
}
