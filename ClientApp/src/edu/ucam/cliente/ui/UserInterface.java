package edu.ucam.cliente.ui;

import java.util.Scanner;

import edu.ucam.cliente.interfaces.IUserInterface;

public class UserInterface implements IUserInterface{
	
	
	
	public String showMainMenu(Scanner sc) {
		
		System.out.println("\n--- GESTIÓN UNIVERSIDAD ---");
        System.out.println("1. TITULACIONES");
        System.out.println("2. MATRÍCULAS");
        System.out.println("3. ASIGNATURAS");
        System.out.println("0. SAlIR");
        System.out.print("> ");
        
        return sc.nextLine();
        
        
	}
	
	public void showOptMenu() {
		
		System.out.println("\n--- QUE DESEA HACER  ---");
        System.out.println("1. AÑADIR");
        System.out.println("2. ELIMINAR");
        System.out.println("3. MODIFICAR");
        System.out.println("4. RECUPERAR POR ID"); 
	}
	
	public void showTitOption() {
        System.out.println("5. LISTAR");
		System.out.println("6. Numero de Titulaciones");
	}
	
	public void showAsigOptions() {
        System.out.println("5. LISTAR");
		System.out.println("6. AÑADIR ASIGNATURA A TITULACION");
        System.out.println("7. ELIMINAR ASIGNATURA DE TITULACION");
        System.out.println("8. LISTAR ASIGNATURAS DE TITULACION");
	}
	
	public String showchooseMenu(Scanner sc) {
		
		System.out.println("0. SAlIR");
        System.out.print("> ");
		
		return sc.nextLine();
	}
	
	

	@Override
	public String askUser(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("Escriba su usario: ");
		return sc.nextLine();
		
	}

	@Override
	public String askPass(Scanner sc) {
		// TODO Auto-generated method stub
		System.out.println("Escriba su Contraseña: ");
		return sc.nextLine();
	}

}
