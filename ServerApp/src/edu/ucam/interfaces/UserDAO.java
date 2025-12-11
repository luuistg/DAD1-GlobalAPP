package edu.ucam.interfaces;

public interface UserDAO {
	
	public boolean getUser(String user);
	public boolean checkPass(String user, String pass);

}
