package dao;

import java.util.ArrayList;
import java.util.List;

import model.Password;
import model.User;

public interface PasswordsDAO {

	 ArrayList<Password> getAllPasswords(User user);
	void savePassword(Password passwordToSave, User user);
	void updatePassword(Password passwordToSave);
	void deletePassword(Password passwordToDelete);
	
}
