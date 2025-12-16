package edu.ucam.cliente.interfaces;

import java.util.Scanner;

import edu.ucam.domain.Titulacion;

public interface IForm {
	
	public Object addForm(Scanner sc);
	public String getForm(Scanner sc);
	public Object updateForm(String id, Scanner sc);

}
