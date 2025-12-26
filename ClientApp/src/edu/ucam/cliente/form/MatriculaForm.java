package edu.ucam.cliente.form;

import java.util.Scanner;

import edu.ucam.domain.Alumno;
import edu.ucam.domain.Matricula;
import edu.ucam.domain.Titulacion;

public class MatriculaForm extends GenericForm{

	@Override
	public Matricula addForm(Scanner sc) {
		
		System.out.println("\n--- REGISTRO DE NUEVA MATRICULA ---");

        String id = pedirTextoNoVacio(sc, "Introduce el ID: ");

        String nombre = pedirTextoNoVacio(sc, "Introduce el Nombre del alumno: ");
        String apellidos = pedirTextoNoVacio(sc, "Introduce los apellidos del alumno: ");
        String dni = pedirTextoNoVacio(sc, "Introduce el dni del alumno: ");
        
        Alumno a = new Alumno(apellidos, dni, nombre);

        return new Matricula(id, a, null);
	}

	@Override
	public String getForm(Scanner sc) {
		String id = pedirTextoNoVacio(sc, "Introduce el ID: ");
		return id;
	}

	@Override
	public Object updateForm(String id, Scanner sc) {
		
        String nombre = pedirTextoNoVacio(sc, "Introduce el Nombre del alumno: ");
        String apellidos = pedirTextoNoVacio(sc, "Introduce los apellidos del alumno: ");
        String dni = pedirTextoNoVacio(sc, "Introduce el dni del alumno: ");
        
        Alumno a = new Alumno(apellidos, dni, nombre);
        
		return new Matricula(id, a, null);

	}

}
