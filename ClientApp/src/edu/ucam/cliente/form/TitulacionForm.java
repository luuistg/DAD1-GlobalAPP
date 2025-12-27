package edu.ucam.cliente.form;

import java.util.Hashtable;
import java.util.Scanner;

import edu.ucam.domain.Titulacion;
import edu.ucam.domain.Asignatura;

public class TitulacionForm extends GenericForm{

	@Override
	public Titulacion addForm(Scanner sc){
		
		System.out.println("\n--- REGISTRO DE NUEVA TITULACIÓN ---");

        String id = pedirTextoNoVacio(sc, "Introduce el ID: ");

        String nombre = pedirTextoNoVacio(sc, "Introduce el Nombre: ");

        return new Titulacion(id, nombre, new Hashtable<String, Asignatura>());
	}

	@Override
	public String getForm(Scanner sc) {
		String id = pedirTextoNoVacio(sc, "Introduce el ID: ");
		return id;
	}

	@Override
	public Object updateForm(String id, Scanner sc) {
		String nombre = pedirTextoNoVacio(sc, "Introduce el Nombre: ");
		return new Titulacion(id, nombre, null);

	}
	

}
