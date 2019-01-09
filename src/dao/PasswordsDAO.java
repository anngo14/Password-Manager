package dao;

import java.util.List;

import model.Password;
import model.User;

public interface PasswordsDAO {

	 List<Password> getAllPasswords(User user);
	void savePassword(Password passwordToSave, User user);
	
}
