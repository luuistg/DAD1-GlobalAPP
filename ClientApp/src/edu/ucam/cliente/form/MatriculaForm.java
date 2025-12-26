package edu.ucam.cliente.form;

import java.util.Hashtable;
import java.util.Scanner;

import edu.ucam.cliente.factory.ServiceFactory;
import edu.ucam.cliente.service.AsignaturaService;
import edu.ucam.domain.Alumno;
import edu.ucam.domain.Matricula;
import edu.ucam.domain.Asignatura;

public class MatriculaForm extends GenericForm{

	@Override
	public Matricula addForm(Scanner sc){
		
		System.out.println("\n--- REGISTRO DE NUEVA MATRICULA ---");

        String id = pedirTextoNoVacio(sc, "Introduce el ID: ");

        String nombre = pedirTextoNoVacio(sc, "Introduce el Nombre del alumno: ");
        String apellidos = pedirTextoNoVacio(sc, "Introduce los apellidos del alumno: ");
        String dni = pedirTextoNoVacio(sc, "Introduce el dni del alumno: ");
        
        Alumno a = new Alumno(apellidos, dni, nombre);
        
        String idAsig = "";
    	Hashtable<String, Asignatura> asignaturas = new Hashtable<String, Asignatura>();
    	AsignaturaService service = (AsignaturaService) ServiceFactory.getInstance().getService(3);
        
    	while (true) {
            System.out.print("ID Asignatura > ");
            idAsig = sc.nextLine().trim();

            if ("FIN".equalsIgnoreCase(idAsig) || "SALIR".equalsIgnoreCase(idAsig)) {
                break;
            }
            
            if (idAsig.isEmpty()) continue;

            //Validamos que no esté ya añadida
            if (asignaturas.containsKey(idAsig)) {
                System.out.println("Esa asignatura ya está en la lista.");
                continue;
            }

            try {
                Asignatura asigEncontrada = (Asignatura) service.get(idAsig);

                if (asigEncontrada != null) {
                    asignaturas.put(idAsig, asigEncontrada);
                    System.out.println("Asignatura '" + asigEncontrada.getNombre() + "' añadida.");
                } else {
                    System.out.println("No existe asignatura con ese ID.");
                }
            } catch (Exception e) {
                System.out.println("Error de comunicación al buscar asignatura.");
            }
        }

        return new Matricula(id, a, asignaturas);
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
