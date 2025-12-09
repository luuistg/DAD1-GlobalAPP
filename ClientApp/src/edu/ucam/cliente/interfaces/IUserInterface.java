package edu.ucam.cliente.interfaces;

import java.util.Scanner;

public interface IUserInterface {
	
	public String showMainMenu(Scanner sc);
	public void showOptMenu();
	public String askUser(Scanner sc);
	public String askPass(Scanner sc);
	public void showTitOption();
	public void showAsigOptions();
	public String showchooseMenu(Scanner sc);
	

}
