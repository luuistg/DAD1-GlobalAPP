package edu.ucam.cliente.form;

import java.util.Scanner;

import edu.ucam.domain.Titulacion;

public class TitulacionForm extends GenericForm{

	@Override
	public Titulacion addForm(Scanner sc) {
		
		System.out.println("\n--- REGISTRO DE NUEVA TITULACIÓN ---");

        String id = pedirTextoNoVacio(sc, "Introduce el ID: ");

        String nombre = pedirTextoNoVacio(sc, "Introduce el Nombre: ");

        return new Titulacion(id, nombre, null);
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
