package edu.ucam.cliente.form;

import java.util.Scanner;

import edu.ucam.domain.Asignatura;

public class AsigForm extends GenericForm {

	@Override
	public Object addForm(Scanner sc){
		
		System.out.println("\n--- REGISTRO DE NUEVA ASIGNATURA ---");

        String id = pedirTextoNoVacio(sc, "Introduce el ID: ");

        String nombre = pedirTextoNoVacio(sc, "Introduce el Nombre: ");
        
        int creditos = pedirEntero(sc, "Introduce el número de Creditos: ");

        return new Asignatura(id, nombre, creditos);
	}

	@Override
	public String getForm(Scanner sc) {
		String id = pedirTextoNoVacio(sc, "Introduce el ID de la asignatura: ");
		return id;
	}

	@Override
	public Object updateForm(String id, Scanner sc) {
		
		String nombre = pedirTextoNoVacio(sc, "Introduce el Nombre: ");
        
        int creditos = pedirEntero(sc, "Introduce el número de Creditos: ");

        return new Asignatura(id, nombre, creditos);
	}
	
	public String getTit(Scanner sc){
		
		String id = pedirTextoNoVacio(sc, "Introduce el ID de la Titulacion: ");
		return id;
		
	}

	

}
