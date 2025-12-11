package edu.ucam.dao.impl;

import edu.ucam.data.UniversityRepository;
import edu.ucam.interfaces.UserDAO;

public class UserDAOImplementation implements UserDAO{
	
	private UniversityRepository db = UniversityRepository.getInstance();

	@Override
	public boolean getUser(String user) {
		if (db.usuarios.containsKey(user)) {
            return true;
        }
        return false;
	}

	@Override
	public boolean checkPass(String user, String pass) {
		if (db.usuarios.containsKey(user)) {
            return db.usuarios.get(user).equals(pass);
        }
        return false;
	}

}
